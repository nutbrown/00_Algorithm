import java.util.Scanner;

public class Main {
	static int[][] green;
	static int[][] blue;
	static int sum;
	
	// 문제가 좀 헷갈리게 나오는데
	// 1. 블록을 싹 내려준다
	// 2. 점수가 난 줄을 한꺼번에 점수내고 삭제한다
	// 3. 다시 블록을 싹 내려준다(이때 연한칸도 내려준다)
	// 4. 점수가 날 때까지 반복한다
	// 5. 연한칸에 블록이 남아있으면 그 줄수만큼 내려준다
	// 	  (이때 어짜피 빈틈없이 내려져있어서 다시 내릴 필요 없다) 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 열은 0~3
		// 행은 0~1 연한칸, 2~5 초록파랑
		green = new int[7][4];
		blue = new int[7][4];
		
		// 바닥을 1로 채워준다
		for(int i = 0; i < 4; i++) {
			green[6][i] = 1;
			blue[6][i] = 1;
		}
		
		// 점수
		sum = 0;
		
		// 블록을 놓은 횟수 N
		int N = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			// 블록 놓은 정보 t, r, c
			// 1 : (r, c)
			// 2 : (r, c) (r, c + 1)
			// 3 : (r, c) (r + 1, c)
			int t = sc.nextInt();
			int r = sc.nextInt();
			int c = sc.nextInt();

			// 함수의 재활용을 위해서 연한칸에 놓는다
			// 행이 0~1이면 행+=4, 행이 2~3이면 행+=2 -> 죽어라
			
			// ******* 와 이걸 틀렸네 왜 그랬니 *******
			// ******* 기존 로직이랑 동일한데 이것만 틀렸네 아오 *******
			// 행이 0이면 +4, 1이면 +3, 2면 +2, 3이면 +1
			// (위에 빨간색 없애고) 그냥 상관없이 무조건 0행으로 넣는다

			// green은 행과열 그대로 입력
			// 블록이 2면 왼쪽 블록에 귀속되어 있다
			green[0][c] = 1;
			if(t == 2) green[0][c + 1] = 2;
			else if(t == 3) green[0 + 1][c] = 1;
			
			// blue는 행과열이 바뀐다
			// 블록이 2면 왼쪽 블록에 귀속되어 있다
			blue[0][r] = 1;
			if(t == 2) blue[0 + 1][r] = 1;
			else if(t == 3) blue[0][r + 1] = 2;
			
			// 첫 입력 블럭을 다른 블럭이나 보드의 경계를 만날 때까지 내려간다
			goDown(green);
			goDown(blue);
			
			// 점수 났는지 확인
			checkScore(green);
			checkScore(blue);
			
			// 연한 칸에서 행 밀어내기 (+ 그리고 점수 확인)
			checkLight(green);
			checkLight(blue);

		}
		
		// 얻은 점수 출력
		System.out.println(sum);
		
		// 파란색 초록색 보드에 타일 들어있는 칸의 개수
		int cnt = 0;
		for(int i = 2; i <= 5; i++) {
			for(int j = 0; j < 4; j++) {
				if(green[i][j] != 0) cnt++;
				if(blue[i][j] != 0) cnt++;
			}
		}
		System.out.println(cnt);
		
	}

	
	// 연한칸(4행)부터 행 바닥(9행)으로 내리는 함수
	// 연한칸 제외(6행)부터 행 바닥(9행)으로 내리는 함수
	static void goDown(int[][] map) {
		
		// 블록을 만나면 바닥에 닿을 때까지 내린다
		// 가로여서 블록이 2면 같이 와야하니까, 뒤에서부터 확인
		for(int i = 4; i >= 0; i--) {
			for(int j = 3; j >= 0; j--) {
				
				// 뒤에 2가 없는 블록 1이라면
				if(map[i][j] == 1 && (j == 3 || map[i][j + 1] != 2)) {
					// 다음행부터 시작해서, 그 행이 막혀있으면 직전으로 내려준다
					for(int r = i + 1; r <= 6; r++) {
						if(map[r][j] != 0) {
							// 1줄도 못 내리는 경우를 생각해서 0이 먼저 와야한다 
							map[i][j] = 0;
							map[r - 1][j] = 1;
							break;
						}
					}
				}
				
				// 블록이 2라면 앞에 블록1이랑 같이 내려준다
				if(map[i][j] == 2) {
					// 다음행부터 시작해서, 그 행이 막혀있으면 직전으로 내려준다
					for(int r = i + 1; r <= 6; r++) {
						// 해당칸이 0이 아니거나, 앞칸이 0이 아니면
						if(map[r][j] != 0 || map[r][j - 1] != 0) {
							map[i][j] = 0;
							map[i][j - 1] = 0;
							map[r - 1][j] = 2;
							map[r - 1][j - 1] = 1;
							break;
						}
					}
				}
				
			}
		}
	}
	
	
	// 한 줄이 완성되었으면 점수 얻고 한 줄 삭제
	static void checkScore(int[][] map) {
		int score = 0;
		
		for(int i = 2; i <= 5; i++) {
			boolean isFull = true;
			for(int j = 0; j < 4; j++) {
				if(map[i][j] == 0) {
					isFull = false;
					break;
				}
			}
			
			// 한 줄이 꽉 차있으면 점수 증가하고 줄 비우기
			if(isFull) {
				score++;
				for(int j = 0; j < 4; j++) {
					map[i][j] = 0;
				}
			}
		}
		
		// 점수가 났으면 내리고 다시 점수 확인
		if(score > 0) {
			sum += score;
			
			// ****아니 어이가 없어서 점수 나면 연한칸도 아래로 내려준다
			goDown(map);
			checkScore(map);
		}
	}
	
	
	// 연한 칸에 블록 있으면 내리기
	static void checkLight(int[][] map) {
		
		// 연한 칸 1줄을 내릴지, 2줄을 내릴지
		int cnt = 0;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				if(map[i][j] != 0) {
					cnt++;
					break;
				}
			}
		}
		
		// cnt줄만큼 내려준다
		for(int i = 5; i >= 2; i--) {
			for(int j = 0; j < 4; j++) {
				map[i][j] = map[i - cnt][j];
			}
		}
		
		// 연한칸을 없애준다
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				if(map[i][j] != 0) map[i][j] = 0;
			}
		}
		
		// 이미 다 내려져있는데 이거 왜 내려
		//goDown(map);
		
		// 어짜피 점수 안 나는데 이거 확인 왜해
		//checkScore(map);
	}
	
	
	// 확인용 출력
	static void print() {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(green[i][j] + " ");
			}
			System.out.print("     ");
			for(int j = 0; j < 4; j++) {
				System.out.print(blue[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
}

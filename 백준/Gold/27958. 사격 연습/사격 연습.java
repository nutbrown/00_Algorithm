import java.util.Scanner;

public class Main {
	static int N;
	static int K;
	static int max;
	static int[][] map;
	static int[] bullets;
	static int[] chosen;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// NxN 크기의 보드판과 1번부터 K번까지 K개의 총알
		// 사격할 때 1행부터 N행까지 중에 하나의 행을 선택해서 사격을 진행
		// 총알은 1열에서 시작해 오른쪽으로 수평으로 한 칸씩 이동하며 날라간다
		// 표적이 있으면 초기 체력 숫자, 표적이 없으면 0으로 표시
		
		// 총알이 표적에 닿으면 현재 체력에서 총알의 공격력만큼 감소
		// 총알이 표적에 닿으면 총알은 사라지고,
		// 표적은 체력이 0이하가 되면 사라진다
		// 이때 초기체력만큼 점수를 얻고,
		// 상하좌우 중 빈칸에 초기 체력의 1/4를 초기 체력으로 가지는 새로운 표적이 생성된다
		// 소수점 아래는 버리고 0이면 생성되지 않는다
		
		// 값이 10 이상인 표적은 보너스 표적,
		// 맞히는 순간 사라지며 보너스 표적만큼 점수를 더한다
		// 보너스 표적은 새로운 표적을 생성하지 않는다
		
		// 사격으로 얻을 수 있는 점수의 최댓값 구하기
		N = sc.nextInt();
		K = sc.nextInt();
		max = 0;
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		bullets = new int[K];
		for(int i = 0; i < K; i++) {
			bullets[i] = sc.nextInt();
		}
		
		// 총알 K개를 0부터 N-1까지 중 하나에 쏜다
		// 총알이 공격력이 다르니까 조합이 아니라 순열이다
		// 같은 곳을 여러번 쏠 수 있어서 중복 순열이다
		chosen = new int[K];
		permutation(0);
		

		System.out.println(max);
	}
	
	
	// 중복순열 !!!!
	static void permutation(int depth) {
	
		// 총알을 쏠 N개를 다 골랐다면 쏜다
		if(depth == K) {
			shoot();
			return; //**자꾸 리턴을 안하네 여기서
		}
		
		for(int i = 0; i < N; i++) {
			
			// 하나 고르고 다음 꺼
			chosen[depth] = i;
			permutation(depth + 1);
		}
		
	}
	
	
	static void shoot() {
		
		// 점수 기록 
		int score = 0;
		
		// 타겟의 초기체력 저장용 지도 복사
		int[][] map2 = new int[N][N];
		for(int i = 0; i < N; i++) {
			map2[i] = map[i].clone();
		}

		// 사용할 현황판 지도 복사
		int[][] copy = new int[N][N];
		for(int i = 0; i < N; i++) {
			copy[i] = map[i].clone();
		}
		
		// 총알 하나씩 쏜다
		for(int i = 0; i < K; i++) {
			
			// 총알을 쏘는 위치
			int row = chosen[i];
			
			// 열을 왼쪽부터 탐색
			for(int j = 0; j < N; j++) {
				if(copy[row][j] != 0) {
					
					// 이 표적이 보너스라면, 점수를 더하고 사라진다
					if(copy[row][j] >= 10) {
						score += map2[row][j];
						copy[row][j] = 0;
					}
					
					// 보너스가 아니라면 우선 점수를 깎는다
					else {
						copy[row][j] -= bullets[i];
						
						// 만약 타겟이 0이하가 되었다면
						if(copy[row][j] <= 0) {
							// 0보다 작아도 0으로 바꿔주고
							copy[row][j] = 0;
							
							// 점수는 초기체력에서 얻어준다
							score += map2[row][j];
							
							// 주변 빈칸에 1/4씩 나눠준다
							for(int d = 0; d < 4; d++) {
								int nr = row + dr[d];
								int nc = j + dc[d];
								if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
								
								// 이때 새로 타겟이 생성된다
								if(copy[nr][nc] == 0) {
									// 새로 생기는 타겟의 초기체력을 저장해야 한다
									map2[nr][nc] = map[row][j] / 4; 
									copy[nr][nc] = map[row][j] / 4; 
								}
							}
						}
					}
					// 한 번 맞추면 총알은 사라진다
					break;
				}
			}
		}
		
		// 점수 최댓값인지 갱신
		max = Math.max(max, score);
		
	}
	
	
}
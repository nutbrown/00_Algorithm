import java.util.Scanner;

public class Main {
	static int R;
	static int C;
	static int M;
	static int[][] map;
	static int[][] srks;
	static int sum;
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		
		// 상어 확인 지도
		map = new int[R + 1][C + 1];
		
		// 행, 열, 속력, 이동방향, 크기
		// 인덱스는 문제에 맞춰서 제로인덱스 아니다
		srks = new int[M + 1][5];
		for(int i = 1; i <= M; i++) {
			for(int j = 0; j < 5; j++) {
				srks[i][j] = sc.nextInt();
			}

			// 1번부터 M번까지 상어 지도에 입력
			map[srks[i][0]][srks[i][1]] = i;
		}
		
		
		// 잡은 상어 크기의 합
		sum = 0;

		// 낚시왕이 1열로 먼저 이동해서 상어 잡는다
		for(int i = 1; i <= R; i++) {
			if(map[i][1] != 0) {
				sum += srks[map[i][1]][4];
				srks[map[i][1]][4] = -1;
				
				// 한 마리만 잡는다
				break;
			}
		}

		// 1초 움직이고 2초 잡고.... C-1초 움직이고 C초 잡고
		for(int t = 2; t <= C; t++) {
			
			// 모든 상어 이동시키고
			// 2부터 C칸 이동시킬 때까지 이동시킨 지도에서 바로 상어 잡기
			moveSrks(t);
		}
		
		
		System.out.println(sum);
	}

	
	static void moveSrks(int t) {
		// 상어 겹치는지 확인 맵
		int[][] copy = new int[R + 1][C + 1];
		
		for(int i = 1; i <= M; i++) {
			// 상어가 없어서 크기가 -1이면
			if(srks[i][4] == -1) continue;
			
			// 행 렬 속력 방향
			int cr = srks[i][0];
			int cc = srks[i][1];
			int s = srks[i][2];
			int d = srks[i][3];
			
			// d방향으로 s만큼 움직이기
			int nr = cr + dr[d] * s;
			int nc = cc + dc[d] * s;

			// 수식 규칙으로 하고 싶지만 우선 그냥
			while(nr <= 0 || nr > R) {

				// 위쪽에서 아래쪽으로
				if(nr <= 0) {
					nr = (nr - 2) * -1;
					d = 2;
				}
				
				// 아래쪽에서 위쪽으로
				else if(nr > R) {
					//nr = R - (nr - R);
					nr = 2 * R - nr;
					d = 1;
				}
			}

			while(nc <= 0 || nc > C) {
				
				// 왼쪽에서 오른쪽으로
				if(nc <= 0) {
					nc = (nc - 2) * -1;
					d = 3;
				}

				// 오른쪽에서 왼쪽으로
				else if(nc > C) {
					nc = 2 * C - nc;
					d = 4;
				}
			}
			
			// 바뀐 상어 정보 저장
			srks[i][0] = nr;
			srks[i][1] = nc;
			srks[i][3] = d;
			
			// 지도에 입력하는데 상어가 있다면
			// 크기가 작은 상어가 잡아먹힌다
			if(copy[nr][nc] != 0) {
				int temp = copy[nr][nc];
				
				// 지금 상어가 더 크면 잡아먹고, 아니면 잡아먹힌다
				if(srks[i][4] > srks[temp][4]) {
					srks[temp][4] = -1;
					copy[nr][nc] = i;
				} else {
					srks[i][4] = -1;
				}
			}
			
			// 아니면 그냥 지도에 입력
			else {
				copy[nr][nc] = i;
			}
		}
		

		// 확인용 출력
//		for(int i = 1; i <= R; i++) {
//			for(int j = 1; j <= C; j++) {
//				System.out.print(copy[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		// t열에 있는 상어는 낚시왕이 잡는다
		for(int i = 1; i <= R; i++) {
			if(copy[i][t] != 0) {
				// 낚시왕이 잡아가고
				sum += srks[copy[i][t]][4];

				// 상어는 없어진다
				srks[copy[i][t]][4] = -1;
				
				// 한 마리만 잡는다
				break;
			}
		}
		
	}
	
	
}
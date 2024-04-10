import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[][] cctv;
	static int cctvN;
	static int min;
	static int[][] copy;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		// CCTV는 5가지 종류가 있다
		// ->  <-->	ㄴ모양	3방향		4방향
		//			 ->		<-->	<-->
		// 1234, 12, 1234, 1234,	1
		
		
		// 방향에 있는 칸 전체를 감시할 수 있고
		// 벽을 통과할 수 없고, CCTV는 통과할 수 있다
		
		// NxM 크기의 직사각형에 K개의 CCTV
		// 0은 빈칸, 6은 벽, 1~5는 CCTV 번호
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		
		// 합성어가 한자어인 경우에는 사이시옷을 받치지 않아서 -> 개수
		// cctv 종류랑 총 개수 저장
		// 행, 렬, cctv종류, cctv방향
		cctv = new int[8][4];
		cctvN = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				
				if(map[i][j] >= 1 && map[i][j] <= 5) {
					cctv[cctvN][0] = i;
					cctv[cctvN][1] = j;
					cctv[cctvN++][2] = map[i][j];
				}
			}
		}
	
		// 사각 지대의 최소 크기
		min = Integer.MAX_VALUE;
		
		// cctv 1번부터 cctvN개까지 경우의수 해보기
		combination(0);
		
		System.out.println(min);
		
	}
	
	
	// 사각지대는 얼마든지 줄어들 수 있어서 다 해봐야한다
	static void combination(int idx) {
//		for(int i = 0; i < 8; i++) {
//			for(int j = 0; j < 4; j++) {
//				System.out.print(cctv[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		// cctv 개수에 도달하면 다 해본 것
		if(idx == cctvN) {
			// 지도 복사
			copy = new int[N][M];
			for(int i = 0; i < N; i++) {
				copy[i] = map[i].clone();
			}
			
			
			// cctv가 닿는 영역은 7로 지우기
			// 1234, 12, 1234, 1234,	1
			for(int i = 0; i < cctvN; i++) {
				int r = cctv[i][0];
				int c = cctv[i][1];
				int n = cctv[i][2];
				int d = cctv[i][3];

				// r, c부터 d방향으로 체크하기
				if(n == 1) {
					checkCCTV(r, c, d);
					
				} else if(n == 2) {
					// 0이면 02, 1이면 13
					checkCCTV(r, c, d);
					checkCCTV(r, c, d + 2);
					
				} else if(n == 3) {
					// 0이면 01, 1이면 12, 2이면 23, 3이면 30
					checkCCTV(r, c, d);
					checkCCTV(r, c, d != 3 ? d + 1 : 0);
				
				} else if(n == 4) {
					// 0이면 012, 1이면 123, 2이면 230, 3이면 301
					checkCCTV(r, c, d);
					checkCCTV(r, c, d + 1 < 4 ? d + 1 : d + 1 - 4);
					checkCCTV(r, c, d + 2 < 4 ? d + 2 : d + 2 - 4);
					
				} else if(n == 5) {
					for(int j = 0; j < 4; j++) {
						checkCCTV(r, c, j);
					}
				}
			}
			
			// 남은 사각지대 0 구하기
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(copy[i][j] == 0) cnt++;
				}
			}
			
			min = Math.min(cnt, min);
			return;
		}
		
		
		// cctv 경우의 수 해보기
		// 134번은 4가지, 2번은 2가지, 5번은 1가지
		int num = cctv[idx][2];
		if(num == 1 || num == 3 || num == 4) {
			
			for(int d = 0; d < 4; d++) {
				cctv[idx][3] = d;
				combination(idx + 1);
				// 덮어씌울 거여서 원상복귀 할 필요 없다
			}
			
		} else if(num == 2) {
			for(int d = 0; d < 2; d++) {
				cctv[idx][3] = d;
				combination(idx + 1);
			}
			
		} else if(num == 5) {
			cctv[idx][3] = 0;
			combination(idx + 1);
		}
	}

	
	// 앞으로 가면서 cctv 영역 표시하기
	static void checkCCTV(int r, int c, int d) {
		
		// r, c부터 d방향으로 체크하기
		// 벽이나 경계에 부딪힐 때까지
		while(isRange(r + dr[d], c + dc[d])) {
			r += dr[d];
			c += dc[d];
			// cctv도 그냥 덮어도 된다. 어짜피 저장되어 있다.
			copy[r][c] = 7;
		}
	}
	
	
	// 영역 안에 위치하는지 **50분정도
	static boolean isRange(int r, int c) {
		if(r >= 0 && r < N && c >= 0 && c < M && map[r][c] != 6) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
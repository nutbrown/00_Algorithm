import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int dir;
	static int cnt;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// ======== 입 력 ========
		// 방의 크기 N과 M
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		
		// 처음 로봇청소기 좌표 r, c
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		// 바라보는 방향 북0 동1 남2 서3
		// (0, 0) 부터 오른쪽 아래로 (N - 1, M - 1)
		dir = sc.nextInt();
		
		// 각 장소 상태
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		
		// ======== 구 현 ========
		// 작동을 멈출 때까지 청소하는 칸의 개수
		cnt = 0;
		
		while(true) {
//			System.out.println(r + " " + c + " / " + dir + " / " + cnt);
			
			// 1. 현재 칸이 청소되지 않은 경우 청소
			if(map[r][c] == 0) {
				map[r][c] = 2;
				cnt++;
//				System.out.println("1번");

			}
			
			// 현재 칸이 청소되었는데
			else {
				// 주변 4칸이 다 청소되었으면 true
				boolean isCleaned = true;
				for(int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					// 벽이 아니고 청소되어 있지 않다면
					// isCleaned가 false다
					if(!isWall(nr, nc) && map[nr][nc] == 0) {
						isCleaned = false;
						break;
					}
				}
//				System.out.println("isCleaned " + isCleaned);

				
				// 2. 주변 4칸 중에서 청소되지 않은 빈 칸이 없는 경우
				if(isCleaned) {
//					System.out.println(Arrays.toString(moveBackward(r, c)));
					int nr = moveBackward(r, c)[0];
					int nc = moveBackward(r, c)[1];
//					System.out.println(nr + " " + nc + " ");

					// 후진할 수 있으면
					if(!isWall(nr, nc)){
						r = nr;
						c = nc;
					
					// 벽이라서 후진할 수 없으면
					} else {
//						System.out.println("isWall ");
						break;
					}
//					System.out.println("2번");
				}
				
				// 3. 현재 칸 주변 칸 중 청소되지 않은 빈 칸이 있는 경우
				else {
					// 반시계 방향 회전하고
					rotate();
//					System.out.println("dir : " + dir);

					// 전진했을 때 앞 칸
					int nr = moveForward(r, c)[0];
					int nc = moveForward(r, c)[1];
					
					// 앞에 있는게 벽이 아니고, 청소 안 된 거 찾을 때까지 회전
//					System.out.println(isWall(nr, nc) + " " + map[nr][nc]);

					if(!isWall(nr, nc) && map[nr][nc] == 0) {
						r = nr;
						c = nc;
//						System.out.println("n : " + r + " " + c + " ");
					} 
//					System.out.println("3번");
				}

				
			}
			
//			for(int i = 0; i < N; i++) {
//				for(int j = 0; j < M; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();

		}
		
		System.out.println(cnt);
	}
	
	
	
	// 해당칸이 범위 벽이 아닌지
	private static boolean isWall(int r, int c) {
		if(map[r][c] != 1) return false;
		else return true;
	}
	
	
	// 해당 방향으로 한 칸 후진한 칸 반환 (북0 동1 남2 서3)
	private static int[] moveBackward(int r, int c) {
		if(dir == 0) return new int[] {r + 1, c};
		else if(dir == 1) return new int[] {r, c - 1};
		else if(dir == 2) return new int[] {r - 1, c};
		else return new int[] {r, c + 1};
	}
	
	// 반시계 방향으로 90도 회전
	private static void rotate() {
		dir--;
		if(dir < 0) dir = 3;
	}
	
	// 해당 방향으로 한 칸 후진한 칸 반환 (북0 동1 남2 서3)
	private static int[] moveForward(int r, int c) {
		if(dir == 0) return new int[] {r - 1, c};
		else if(dir == 1) return new int[] {r, c + 1};
		else if(dir == 2) return new int[] {r + 1, c};
		else return new int[] {r, c - 1};
	}
	
	
}
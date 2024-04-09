import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int total;
	static int min;
	static int[][] visited;
	static int totalcheck;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		// 입력이 없을 때까지
		int testcase = 0;
		while(true) {
			
			// 입력이 없으면 끝나도록
			try {
				testcase++;
				StringTokenizer st = new StringTokenizer(br.readLine());
				N = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
			
			// IOException이 아니라 모든 예외를 받을 수 있도록 해야한다
			} catch (Exception e) {
				return;
			}

			
			map = new int[N][M];
			total = 0;
			
			for(int i = 0; i < N; i++) {
				String in = br.readLine();
				for(int j = 0; j < M; j++) {
					char ch = in.charAt(j);
					
					if(ch == '*') {
						map[i][j] = 1;
					} else {
						map[i][j] = 0;
						total++;
					}
				}
			}
			
			min = Integer.MAX_VALUE;
			getMin();
			
			if(min == Integer.MAX_VALUE) min = -1;
			System.out.println("Case " + testcase + ": " + min);
			
		}
	}
	

	
	// 시작점에 따른 이동횟수의 최솟값을 구하기
	static void getMin() {
		
		// 시작점 정해서 출발하기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				// 장애물이면 안한다 
				if(map[i][j] == 1) continue;
				
				// 이 시작점에서 모든 빈칸을 방문하는 이동횟수
				visited = new int[N][M];
				totalcheck = total;

				// 시작점에서 이동하기
				visited[i][j] = 1;
				totalcheck--;
				move(i, j, 0);
				
			}
		}
	}
	

	// 이동하기
	static void move(int r, int c, int cnt) {
//		System.out.println(r + " " + c + " " + totalcheck + " " + cnt );
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				System.out.print(visited[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		
		// 현재 진행중 cnt가 min보다 커졌으면 중단
		if(cnt > min) return;
		
		// 모든 칸을 방문했으면 돌아가기
		if(totalcheck == 0) {
			// Math.min 할 필요 없다
			min = cnt;
			return;
		}
		
		int cr = r;
		int cc = c;
		
		// 모든 점을 방문할 때까지 4방향 중 하나로 가기 반복
		for(int d = 0; d < 4; d++) {
			boolean move = false;

			// 다음점으로 이동해도 괜찮으면
			while(isRange(cr + dr[d], cc + dc[d])) {
				
				// 이미 방문한 거면 아웃
				if(visited[cr + dr[d]][cc + dc[d]] == 1) break;
				
				// isRange니까 이동시킨다
				cr += dr[d];
				cc += dc[d];

				move = true;
				visited[cr][cc] = 1;
				totalcheck--;
			}
			// (cr, cc)까지 이동한 것
			
			// 해당 방향으로 이동할 수 쭉 있어서 갔으면 카운트 
			if(move) {
				// 같은 방향으로 쭉 이동했으면 다시 4방향 중 하나 골라서 이동
				move(cr, cc, cnt + 1);

				// 돌아왔으면 시작점에 도착할 때까지 원상복귀
				// ***와 여기 조건문 andor 잘못해서 원상복귀가 안 되고 있었다
				// ***내 3일....... 이거먼저 찍어볼걸
				while(cr != r || cc != c) {
					visited[cr][cc] = 0;
					totalcheck++;
					
					cr -= dr[d];
					cc -= dc[d];
				}
			}

			// 해당 방향으로 이동할 수 없으면 다음 방향 (이 코드가 없어도)
			else continue;
		}
		// 모든 방향으로 못 가면 돌아가기 (이 코드가 없어도)
		return;
	}

	
	// 벽이나 장애물에 부딪혔는지 확인
	static boolean isRange(int r, int c) {
		if(r < 0 || r >= N || c < 0 || c >= M || map[r][c] == 1) return false;
		else return true;
		
	}
	
}
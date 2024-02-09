import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] map;
	static int min;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 지도 크기 N
		N = sc.nextInt();

		// 지도 입력
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				map[i][j] = sc.nextInt();
			}
		}
		

		// 2번 부터 시작해서 각자 다른 섬이라고 표시
		int idx = 2;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				// 아직 영역표시를 안해준 육지 섬 1이라면
				if(map[i][j] == 1) {

					Queue<int[]> q = new ArrayDeque<>();
                    map[i][j] = idx;
					q.add(new int[] {i, j});
					
					while(!q.isEmpty()) {
						int cr = q.peek()[0];
						int cc = q.poll()[1];
						
						for(int d = 0; d < 4; d++) {
							int nr = cr + dr[d];
							int nc = cc + dc[d];
							if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

							if(map[nr][nc] == 1) {
								map[nr][nc] = idx;
								q.add(new int[] {nr, nc});
							}
						}
					}
					
					// 다 끝났으면 인덱스 증가
					idx++;
				}
			}
		}

		
		// 가장 짧은 다리의 길이 찾기
		min = Integer.MAX_VALUE;
		
		// 육지여서 0이 아닌데, 바다에 붙어있으면 다리 만들러 가보기 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				if(map[i][j] == 0) continue;

				for(int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					// 바다로 붙어있으면 그 방향으로 나가보기
					if(map[nr][nc] == 0) {
						bfs(nr, nc, map[i][j]);
					}
					
				}
			}
		}
		
		System.out.println(min);
	}
	
	
	static void bfs(int cr, int cc, int idx) {
		
		// 그 위치까지의 거리보다 크면 안하는 방향으로 하고 싶은데
		// A -> B 방식이 아니라 섬이 여러개여서 번잡한 걸
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		q.add(new int[] {cr, cc, 1});
		visited[cr][cc] = true;
		
		while(!q.isEmpty()) {
			cr = q.peek()[0];
			cc = q.peek()[1];
			int cl = q.poll()[2];
			
			for(int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if(visited[nr][nc]) continue;
				
				// 인접한 곳이 바다면 다리를 놓는다
				if(map[nr][nc] == 0) {
					q.add(new int[] {nr, nc, cl + 1});
					visited[nr][nc] = true;
				}
				
				// 인접한 곳이 다른 섬이면 최소 길이 갱신
				else if(map[nr][nc] != idx) {
					min = Math.min(min, cl);
					break;
				}
			}
			
		}
		
	}
}

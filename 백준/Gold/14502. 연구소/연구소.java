import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static ArrayList<int[]> list;
	static int[][] map;
	static boolean[][] visited;
	static int max;
	static int[][] copy;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 지도크기 세로 N 가로 M (3이상 8이하)
		N = sc.nextInt();
		M = sc.nextInt();

		// 빈공간 저장 리스트
		list = new ArrayList<>();
		
		// 빈칸 0, 벽 1, 바이러스 2
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 0) list.add(new int[] {i, j});
			}
		}
		
		// 벽을 3개 세워야한다 -> 모든 경우의 수
		visited = new boolean[N][M];
		
		// 이때 안전 영역의 최댓값
		max = Integer.MIN_VALUE;
		
		dfs(0);
		System.out.println(max);
		
	}
	
	// 벽 3개 세우는 모든 경우의 수 함수
	static void dfs(int depth) {
		// 벽을 3개 세웠으면
		if(depth == 3) {
			// 바이러스를 퍼뜨리고
			copy = new int[N][M];
			for(int i = 0; i < N; i++) {
				copy[i] = map[i].clone();
			}
			
			virus();
			
			// 안전영역을 구한다
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(copy[i][j] == 0) cnt++;
				}
			}
			max = Math.max(max, cnt);
			return;
			
		}
		
		
		// 빈공간에 벽 세우기 백트래킹
		for(int[] wall : list) {
			int r = wall[0];
			int c = wall[1];
			if(!visited[r][c]) {
				// 벽 세우기
				visited[r][c] = true;
				map[r][c] = 1;
				dfs(depth + 1);
				
				// 원상복귀
				map[r][c] = 0;
				visited[r][c] = false;
				
			}

		}
	}
	
	// 바이러스 퍼뜨리는 함수
	static void virus() {
		// 바이러스 방문 배열
		boolean[][] visited2 = new boolean[N][M];
		// 4방면
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		// 바이러스 각각에서 bfs
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				// bfs 사용 큐
				Queue<int[]> q = new LinkedList<>();
				
				// 방문 안 했는데 && 바이러스면
				if(!visited2[i][j] && map[i][j] == 2) {
					q.add(new int[] {i, j});
					visited2[i][j] = true;
					
					while(!q.isEmpty()) {
						int cr = q.peek()[0];
						int cc = q.poll()[1];
						
						for(int d = 0; d < 4; d++) {
							int nr = cr + dr[d];
							int nc = cc + dc[d];
							
							if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
							if(copy[nr][nc] == 0) {
								q.add(new int[] {nr, nc});
								// 아 오타
								visited2[nr][nc] = true;
								// 바이러스로 바꿔준다
								copy[nr][nc] = 3;
							}
						}
					}
				}
			}
		}
	}
	
	
}

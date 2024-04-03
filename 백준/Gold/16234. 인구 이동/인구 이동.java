import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int N;
	static int L;
	static int R;
	static int[][] map;
	static int days;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N x N 크기의 땅
		// 인구 차이가 L명 이상, R명 이하면 국경선 열어서 이동
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 인구 이동이 며칠 동안 발생하는지
		days = 0;
		while(check()) {
			bfs();
		}
		
		System.out.println(days);
		
	}
	
	
	static boolean check() {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				for(int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					// 비교해서 인구이동이 일어나면 true 반환
					int diff = Math.abs(map[i][j] - map[nr][nc]);
					if(diff >= L && diff <= R) return true;
				}
			}
		}
		
		// 다 했는데도 인구 이동이 없으면 false 반환
		return false;
		
	}
	
	
	static void bfs() {
		boolean[][] visited = new boolean[N][N];
		boolean flag = false;
		
		// 한 점을 기점으로, 국경선이 열리면 더하면서 퍼진다
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				// 이미 방문한 건 하지 않는다
				if(visited[i][j]) continue;
				
				
				// 퍼지는 큐
				Queue<int[]> q = new LinkedList<>();
				
				// 시작점에서 시작
				q.add(new int[] {i, j});
				visited[i][j] = true;

				// 연합 인구수, 연합 칸의 개수
				int sum = map[i][j];
				int cnt = 1;
				
				// 연합하는 칸 저장
				Stack<int[]> st = new Stack<>();
				st.add(new int[] {i, j});
				
				while(!q.isEmpty()) {
					int r = q.peek()[0];
					int c = q.poll()[1];
					
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
						
						// 비교해서 인구이동이 일어나면 저장하고 퍼지기
						int diff = Math.abs(map[r][c] - map[nr][nc]);
						if(diff >= L && diff <= R) {
							q.add(new int[] {nr, nc});
							visited[nr][nc] = true;
							
							// 연합 계산용 저장
							st.add(new int[] {nr, nc});
							sum += map[nr][nc];
							cnt++;

							// 인구 이동이 일어났다
							if(!flag) flag = true;
						}
					}
				}
				
				// 각 칸의 인구수 바꿔주기
				while(!st.isEmpty()) {
					int r = st.peek()[0];
					int c = st.pop()[1];
					
					map[r][c] = sum / cnt;
				}
				
				
			}
		}
		
		// 인구 이동 날 더하기 **여기서
		if(flag) days++;
		
		
	}
}
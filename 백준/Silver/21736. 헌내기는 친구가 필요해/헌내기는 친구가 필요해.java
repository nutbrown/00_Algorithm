import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int K;
	static int[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.next());
		int M = Integer.parseInt(sc.next());
		
		// 도연이 위치 
		int r = 0;
		int c = 0;
		
		// 지도 입력
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String in = sc.next();
			for(int j = 0; j < M; j++) {
				map[i][j] = in.charAt(j);
				
				if(map[i][j] == 'I') {
					r = i;
					c = j;
				}
			}
		}
		
		// 도연이가 만날 수 있는 사람 수
		int cnt = 0;
		
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		
		visited[r][c] = true;
		q.add(new int[] {r, c});
		while(!q.isEmpty()) {
			
			int cr = q.peek()[0];
			int cc = q.poll()[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(visited[nr][nc]) continue;
				
				// 방문하는데 사람이면 카운트 증가
				if(map[nr][nc] == 'O' || map[nr][nc] == 'P') {
					visited[nr][nc] = true;
					q.add(new int[] {nr, nc});
					
					if(map[nr][nc] == 'P') cnt++;
				}
			}
		}
		
		
		if(cnt == 0) System.out.println("TT");
		else System.out.println(cnt);
	}
}
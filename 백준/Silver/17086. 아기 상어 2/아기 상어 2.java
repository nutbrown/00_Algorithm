import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 실버는 30분 안에 정확하게 빨리 풀기 연습 6:53~7:04
		// 마음이 급해서 문제도 잘못 읽고 실수 많이해서 실패
		
		// 공간의 크기 N, M
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 안전거리는 **상어가 없는 어떤 칸에서
		// 가장 거리가 가까운 아기상어와의 거리
		// 한 칸에서 다른 칸으로 가기 위해서 지나가야 하는 칸의 수
		int max = 0;;
		
		// 대각선 포함
		int[] dr = {0, 1, 0, -1, 1, 1, -1, -1};
		int[] dc = {1, 0, -1, 0, 1, -1, -1, 1};
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				
				// 상어면 안 된다
				if(map[i][j] == 1) continue;
				
				// 제일 빨리 다른 상어 만날 때까지
				boolean[][] visited = new boolean[N][M];
				Queue<int[]> q = new LinkedList<>();
	
				visited[i][j] = true;
				q.add(new int[] {i, j, 0});
				
				loop:
				while(!q.isEmpty()) {
					int cr = q.peek()[0];
					int cc = q.peek()[1];
					int ct = q.poll()[2];
					
					for(int d = 0; d < 8; d++) {
						int nr = cr + dr[d];
						int nc = cc + dc[d];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
						if(visited[nr][nc]) continue;
						
						// 다른 상어 만나면 끝
						if(map[nr][nc] == 1) {
							max = Math.max(max, ct + 1);
							break loop;
						}
						
						else {
							visited[nr][nc] = true;
							q.add(new int[] {nr, nc, ct + 1});
						}
					}
				}
			}
		}
		
		
		System.out.println(max);
	}
}

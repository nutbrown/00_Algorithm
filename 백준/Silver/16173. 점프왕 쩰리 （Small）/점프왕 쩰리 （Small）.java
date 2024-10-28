import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		// N*N 지도에서 이동할 수 있는 칸 입력
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		
		int[] dr = {0, 1};
		int[] dc = {1, 0};
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];

		// 왼쪽 위에서 시작
		int cr = 0;
		int cc = 0;
		
		visited[cr][cc] = true;
		q.add(new int[] {cr, cc});
		
		while(!q.isEmpty()) {
			cr = q.peek()[0];
			cc = q.poll()[1];
			
			// 나왔더니 끝이면 가능! 하고 그만
			if(cr == N - 1 && cc == N - 1) {
				System.out.println("HaruHaru");
				return;
			}
			
			// 여기서 아래에 적힌 숫자만큼
			// 오른쪽이랑 아래로 이동할 수 있다
			for(int d = 0; d < 2; d++) {
				int nr = cr + dr[d] * map[cr][cc];
				int nc = cc + dc[d] * map[cr][cc];
				
				// 범위 밖이면 안 된다, 방문한 곳이면
				if(nr >= N || nc >= N || visited[nr][nc]) continue;
				
				// 그게 아니면 이동하기
				visited[nr][nc] = true;
				q.add(new int[] {nr, nc});
				
			}
		}
		
		// 끝까지 했는데 도착 못했으면 불가능 출력
		System.out.println("Hing");
		
		
	}
}

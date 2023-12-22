import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[][] visited;
	static int[] dr;
	static int[] dc;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 세로 크기 N, 가로 크게 M
		N = sc.nextInt();
		M = sc.nextInt();
	
		// 지도 입력
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 최댓값
		max = 0;
				
		// 첫째칸부터 시작해서 4개 합을 찾는데
		// 최대한 겹치지 않기 위해서 왼쪽으로 가는 거 제외
		dr = new int[] {0, -1, 1};
		dc = new int[] {1, 0, 0};
		
		// 왔다갔다 방지
		visited = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = 1;
				move(i, j, 1, map[i][j]);
				visited[i][j] = 0;
			}
		}
		
		System.out.println(max);
	}
	
	static void move(int cr, int cc, int cnt, int sum) {
		//System.out.println(cr + " " + cc + " " + cnt + " " + sum);
		// 최댓값 갱신
		if(cnt == 4) max = Math.max(max, sum);
		
		// 3방향으로 이동
		else if(cnt < 4) {
			// 3방향으로 이동한 거 넣어줌
			for(int k = 0; k < 3; k++) {
				int nr = cr + dr[k];
				int nc = cc + dc[k];
				
				// 범위 밖이면, 이미 방문했으면 안 함
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] == 1) continue;
				
				// (ㅗ 모양 고려) 2번 간 상황에서 두 군데로 진출해야 함
				// cnt가 1, 3이 아니라 2일 때 두 갈래로 나가야 함
				if(cnt == 2) {
					visited[nr][nc] = 1;
					// nr, nc를 방문했지만
					// 여전히 같은 자리인 cr, cc에서 다른 갈래로 나갈 것임
					move(cr, cc, cnt + 1, sum + map[nr][nc]);
					visited[nr][nc] = 0;
				}

				// 방문체크, 이동, 방문체크 해제
				visited[nr][nc] = 1;
				move(nr, nc, cnt + 1, sum + map[nr][nc]);
				visited[nr][nc] = 0;
			}
		}
		
	}
}

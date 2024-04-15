import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	// 벽 부수고 이동하기1 코드 기반
    	
    	// N x M 지도
    	// 0은 이동할 수 있는 곳, 1은 벽이 있는 곳
    	// 벽을 K개까지 부수고 이동할 수 있다
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    	int K = sc.nextInt();
    	
    	// 지도 입력
    	int[][] map = new int[N][M];
    	for(int i = 0; i < N; i++) {
    		String in = sc.next();
    		for(int j = 0; j < M; j++) {
    			map[i][j] = Character.getNumericValue(in.charAt(j));
    		}
    	}
    	
    	
    	// 최단거리
    	int min = Integer.MAX_VALUE;
    		
		// (1, 1)부터 (N, M)까지 이동
		// 시작하는 칸이랑 끝나는 칸을 포함해서 센다
		Queue<int[]> q = new LinkedList<>();
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};

		// 벽을 부순 횟수별로 -> visited를 따로 만들어야 하는 이유
		// 0 0 0 0 가다가 마지막에 벽 부수고 통과해야하는데
		// 중간에 벽 부수고 지나간 애가 visited를 해놓아서
		// 이동하지 못하는 경우 발생
		// 정작 얘는 마지막에 있는 벽을 못 부숴서 통과 못한다
		
		// 벽을 1번 부쉈으면 -> 이제 계속 0으로만 가야한다
		// 				 -> 얘네들끼리 visited 만들어준다
		// 벽을 안 부순 애들은 마지막 기회를 남겨두고 잘 따라갈 수 있게
		// ++새로운 visited 맵에 들어가면 뒤로 돌아갈까봐 걱정 -> 어짜피 큐로 최단거리로 도착
		
		boolean[][][] visited = new boolean[N][M][K + 1];
		
		// 경로 1개당 벽을 K개 부숴볼 수 있다
		// bfs 시작 (r좌표, c좌표, 지나온거리, 벽을 몇 개 부쉈는지)
		
		// 같은 칸에 머물러 있어도 지나온거리 증가
		// 시간이 홀수면 낮, 짝수면 밤, 벽은 낮에만 부술 수 있다
		q.add(new int[] {0, 0, 1, 0});
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int cr = q.peek()[0];
			int cc = q.peek()[1];
			int cnt = q.peek()[2];
			int broken = q.poll()[3];
			
			// 도착하면 최단 경로 확인
			if(cr == N - 1 && cc == M - 1) {
				min = Math.min(min, cnt);
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				// 범위 밖이면 못 간다
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				// 그 맵에서 이미 방문했으면 안 간다
				if(visited[nr][nc][broken]) continue;
				
				// 벽을 K번보다 적게 부쉈다면
				if(broken < K) {
					
					// 벽이 아니면 안 부수고 간다
					if(map[nr][nc] == 0) {
						q.add(new int[] {nr, nc, cnt + 1, broken});
						visited[nr][nc][broken] = true;
						
					// 벽이면 부수고 넘어가도 된다
					} else {
						// 시간이 밤이어서 못 부수면 기다려보자
						if(cnt % 2 == 0) {
							q.add(new int[] {cr, cc, cnt + 1, broken});
						}
						
						// 시간이 홀수여서 낮일 때만 부술 수 있다
						else {
							// 다른 방식으로 벽을 부수고 지나갔다면 안 간다고?
							// 거기까지 가는데 이미 최단경로로 갔는데, 또 할 필요 없어서?
							if(visited[nr][nc][broken + 1]) continue;
							
							q.add(new int[] {nr, nc, cnt + 1, broken + 1});
							visited[nr][nc][broken + 1] = true;
						}
					}
				}
				
				// 벽을 다 부순 애라면
				else {
					// 벽이 아닐 때만 간다
					if(map[nr][nc] == 0) {
						q.add(new int[] {nr, nc, cnt + 1, broken});
						visited[nr][nc][broken] = true;
					}
				}
			}
		}
    		
    	
    	// 불가능하면 -1 출력
    	if(min == Integer.MAX_VALUE) min = -1;
    	System.out.println(min);
    	
    }
}
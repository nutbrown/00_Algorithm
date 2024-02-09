import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static Queue<int[]> ice;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 빙산이 분리되는 최초의 시간 (다 녹을 떄까지 분리 안 되면 0)
		// 빙산은 인접하는 0의 개수만큼 녹는다
		// 어느 빙산도 녹지 않았을 때 : 인접한 0의 개수를 샌다
		// 빙산의 개수만큼 돌면서 : 동시에 녹인다
		// 다 녹인 다음에 : 아예 녹은 건 아닌지, 연결되어 있는지 확인

		
		// 행 N개, 열 M개
		N = sc.nextInt();
		M = sc.nextInt();

		// 지도 입력
		map = new int[N][M];
		
		// 빙산 위치 저장
		ice = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				// i행 j열 1초
				if(map[i][j] != 0) ice.add(new int[] {i, j, 0});
			}
		}

		// 빙산 전체가 녹을 때까지
		while(ice.size() != 0) {
			
			// 빙산에 인접한 0의 개수 세기
			int size = ice.size();
			for(int i = 0; i < size; i++) {
				int cr = ice.peek()[0];
				int cc = ice.peek()[1];
				int ct = ice.poll()[2];
				int zero = 0;
				
				for(int d = 0; d < 4; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					
					if(map[nr][nc] == 0) zero++;
				}
				
				// 0의 개수랑 같이 저장
				ice.add(new int[] {cr, cc, ct, zero});
			}
			
			// 0의 개수만큼 빙산 녹이기
			for(int i = 0; i < size; i++) {
				int cr = ice.peek()[0];
				int cc = ice.peek()[1];
				int ct = ice.peek()[2] + 1;
				int zero = ice.poll()[3];
				
				// 0보다 더 녹을 수 없다
				map[cr][cc] = Math.max(map[cr][cc] - zero, 0);
				
				// 녹았더니 0이 아니라면 다시 빙산 추가
				if(map[cr][cc] != 0) ice.add(new int[] {cr, cc, ct});

			}
			
			// 분리되기 전에 빙산이 다 녹았으면 0 출력
			if(ice.size() == 0) {
				System.out.println(0);
				break;
			}
			
			// 빙산이 다 녹은 게 아니면 끊겼다면 시간 출력
			if(!isConnected()) {
				System.out.println(ice.peek()[2]);
				break;
			}
			
			// 끊긴 게 아니면 계속 이어서 하면 된다
			
		}
	}

	
	static boolean isConnected() {
		// 얼음들이 다 이어져 있는지 확인
		// 1개로 들어가서 전체 얼음이 다 이어져있음을 확인해야 한다
		int cr = ice.peek()[0];
		int cc = ice.peek()[1];
		
		// 현재 얼음 개수 (전체 얼음 개수는 ice.size())
		int cnt = 1;
		
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		visited[cr][cc] = true;
		q.add(new int[] {cr, cc});
		
		while(!q.isEmpty()) {
			cr = q.peek()[0];
			cc = q.poll()[1];
			
			
			// 4개 인접한 곳으로 이동
			for(int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				// 범위를 벗어나면 안 된다
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				// 얼음끼리 이어져있는지 보는거니까 0이면 안 된다
				if(map[nr][nc] == 0) continue;
				
				// 이미 방문한 얼음이면 안 된다
				if(visited[nr][nc]) continue;
				
				// 인접한 곳으로 이동하면 카운트
				visited[nr][nc] = true;
				q.add(new int[] {nr, nc});
				cnt++;
			}
		}
		
		// 1개로 시작해서 인접한 걸 다 셌더니 전체가 되었다면 true 반환
		if(cnt == ice.size()) return true;
		else return false;
		
	}
}

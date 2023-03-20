import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 지도 입력
		int N = Integer.parseInt(sc.nextLine());
		char[][] map = new char[N][N];
		for(int i = 0; i < N; i++) {
			String str = sc.nextLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		// 사방면
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		// 확인했는지 배열
		char[][] visited = new char[N][N];
		int total = N * N;

		// 큐에 초기 좌표  (0,0)
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0});
		visited[0][0] = map[0][0];
		total--;
		
		// 적록색약 아닌사람, 적록색약 사람 개수 변수
		int cnt = 1;
		int cntRG = 1;
		
		// 적록색약 아닌 사람
		// 전체 25개가 바닥날 때까지 해야한다
		while(total > 0) {
			// 큐가 빌 때까지 하고
			while(!queue.isEmpty()) {
				// 현재 좌표
				int r = queue.peek()[0];
				int c = queue.peek()[1];
				char color = map[r][c];
				queue.poll();
				
				// 사방 확인
				for(int k = 0; k < 4; k++) {
					// 다음 좌표
					int nr = r + dr[k];
					int nc = c + dc[k];
					
					// 범위 안에 있다면
					// 같은색이 있는지 확인
					if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
						// 같은 색이라면 큐에 넣고 잇는다
						if(color == map[nr][nc] && visited[nr][nc] == '\u0000') {
							queue.offer(new int[] {nr, nc});
							visited[nr][nc] = map[nr][nc];
							total--;
						}
					}
				}
			}
			
			// 다 확인했으면 break하고
			// 아니라면 다음 빈 거 추가
			queue = new LinkedList<>();
			if(total == 0) break;
			loop: for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(visited[i][j] == '\u0000') {
						//System.out.println("i : " + i + " / j : " + j);
						queue.offer(new int[] {i, j});
						total--;
						visited[i][j] = map[i][j];
						// 새로 시작할 때 구역 개수 증가
						cnt++;
						//System.out.println("cnt" + cnt);
						break loop;
					}
				}
			}
		}
		
		
		// 적록색약인 사람 : 초기화
		visited = new char[N][N];
		total = N * N;

		// 큐에 초기 좌표  (0,0)
		queue = new LinkedList<>();
		queue.offer(new int[] {0, 0});
		visited[0][0] = map[0][0];
		total--;
		
		while(total > 0) {
			// 적록색약 아닌 사람 - 큐가 빌 때까지 하고
			while(!queue.isEmpty()) {
				// 현재 좌표
				int r = queue.peek()[0];
				int c = queue.peek()[1];
				char color = map[r][c];
				queue.poll();
				
				// 사방 확인
				for(int k = 0; k < 4; k++) {
					// 다음 좌표
					int nr = r + dr[k];
					int nc = c + dc[k];
					
					// 범위 안에 있다면
					// 같은색이 있는지 확인
					if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
						// 같은 색이라면 큐에 넣고 잇는다
						if(color == map[nr][nc] && visited[nr][nc] == '\u0000') {
							queue.offer(new int[] {nr, nc});
							visited[nr][nc] = color;
							total--;
							
						// 같은 색이 아니라도 적록색약 처리
						} else if((color == 'R' && map[nr][nc] == 'G' || color == 'G' && map[nr][nc] == 'R')
								&& visited[nr][nc] == '\u0000') {
							queue.offer(new int[] {nr, nc});
							visited[nr][nc] = color;
							total--;
						}
					}
				}
			}
			
			// 다 확인했으면 break하고
			// 아니라면 다음 빈 거 추가
			if(total == 0) break;
			loop : for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(visited[i][j] == '\u0000') {
						queue = new LinkedList<>();
						queue.offer(new int[] {i, j});
						visited[i][j] = map[i][j];
						total--;
						// 새로 시작할 때 구역 개수 증가
						cntRG++;
						break loop;
					}
				}
			}
		}
		
		System.out.println(cnt + " " + cntRG);
		
		
	}
}

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int w;
	static int h;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// https://data-make.tistory.com/509 (깔끔)
		// 빈공간과 벽, 상근이는 출구를 향해 가고 있다
		// 매초 상근이와 불은 동서남북 방향 빈 공간으로 퍼지고 이동한다
		// 상근이는 이제 불이 붙으려는 칸으로 이동할 수 없다
		// 상근이는 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다
		
		// 테스트 케이스
		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			// 빌딩 지도의 너비 w, 높이 h
			w = sc.nextInt();
			h = sc.nextInt();
			
			// 상근이 이동 큐
			Queue<int[]> sg = new LinkedList<>();
			
			// 불 이동 큐 
			Queue<int[]> fire = new LinkedList<>();
			
			// 입력받으면서 상근이와 불 위치 큐에 삽입
			// 불이나 상근이가 t초에 (r, c)에 위치한다
			char[][] map = new char[h][w];
			for(int i = 0; i < h; i++) {
				String in = sc.next();
				for(int j = 0; j < w; j++) {
					char ch = in.charAt(j);
					map[i][j] = ch;

					// 상근이랑 뷸 큐에 넣음
					if(ch == '@') {
						sg.add(new int[] {0, i, j});
					} else if(ch == '*') {
						fire.add(new int[] {0, i, j});
					} 
				}
			}
			
			// 동서남북 이동
			int[] dr = {1, 0, -1, 0};
			int[] dc = {0, 1, 0, -1};
			
			// 탈출하는데 걸리는 시간
			int time = 0;
			
			// 상근이가 다 끝날 때까지
			loop:
			while(!sg.isEmpty()) {
				
				// 1. 불이 퍼지는 게 먼저
				// 같은 시간인 것만 한 번 돌리고 싶은데
				// 꺼내서 시간을 확인할 필요없이
				// 맨 처음 큐 사이즈만큼만 돌리면 된다
				int size = fire.size();
				for(int i = 0; i < size; i++) {
					int ct = fire.peek()[0];
					int cr = fire.peek()[1];
					int cc = fire.poll()[2];
					
					// 다음 이동
					for(int d = 0; d < 4; d++) {
						int nr = cr + dr[d];
						int nc = cc + dc[d];
						
						// 범위를 벗어나면 pass
						if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
						// 이미 벽이 있거나 불이 있으면 pass
						if(map[nr][nc] == '#' || map[nr][nc] == '*') continue;
						
						// 그게 아니면 우선 불 퍼짐
						map[nr][nc] = '*';
						fire.add(new int[] {ct + 1, nr, nc});
					}
				}
				
				// 2. 상근이가 불을 피해서 다님
				// 어짜피 뒤에서 불이 와서 덮어씌웠더라도 상근이는 움직인다
				// 만약 움직일 수 있는 곳이 없다면 못 움직이고
				// 상근 큐에 상근이는 못 들어갔을 것
				size = sg.size();
				for(int i = 0; i < size; i++) {
					int ct = sg.peek()[0];
					int cr = sg.peek()[1];
					int cc = sg.poll()[2];
					
					// 다음 이동
					for(int d = 0; d < 4; d++) {
						int nr = cr + dr[d];
						int nc = cc + dc[d];
						
						// 범위를 벗어나면 탈출
						if(nr < 0 || nr >= h || nc < 0 || nc >= w) {
							time = ct + 1;
							break loop;
						}
						// 이미 벽이 있거나 불이 있거나 방문한 곳이면 pass
						// 이미 방문했던 곳을 다시 가면 빙글빙글 돈다
						// 걔는 이미 다른 어딘가를 가서 잘 이동하거나 없어졌을 것
						if(map[nr][nc] == '#' || map[nr][nc] == '*' || map[nr][nc] == '@') continue;

						// 그게 아니면 이동
						map[nr][nc] = '@';
						sg.add(new int[] {ct + 1, nr, nc});
					}
				}
			}
			
			// 중간에 탈출하지 못하고 여기까지 오면
			if(time != 0) System.out.println(time);
			else System.out.println("IMPOSSIBLE");

		}
	}
}

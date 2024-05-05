import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int R;
	static int C;
	static int K;
	static int[][] map;
	static ArrayList<int[]> heater;
	static ArrayList<int[]> checkpoint;
	static int[][][] walls;
	static int choco;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// R×C인 격자판
		R = sc.nextInt();
		C = sc.nextInt();
		
		// 모든 칸의 온도가 K이상이면 테스트 중단
		K = sc.nextInt();
		
		// 방의 정보
		map = new int[R + 1][C + 1];
		
		// 온풍기 정보 저장
		// 온풍기 방향	1234가 오른쪽 왼쪽 위 아래
		// 방향 보정	0231가 오른쪽 왼쪽 위 아래
		heater = new ArrayList<>();
		
		// 온도를 조사해야하는 칸
		checkpoint = new ArrayList<>();
		
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				int in = sc.nextInt();
				
				// 온풍기 저장
				if(in >= 1 && in <= 4) {
					if(in == 1) in = 0;
					else if(in == 4) in = 1;
					heater.add(new int[] {i, j, in});
				}
				
				// 온도 조사 칸 저장
				else if(in == 5) checkpoint.add(new int[] {i, j});
			}
		}
		
		
		// 벽의 개수
		int W = sc.nextInt();
		
		// 3차원으로 오른쪽왼쪽위아래에 벽이 있는지
		walls = new int[R + 1][C + 1][4];
		
		// 벽이 오른쪽왼쪽위아래면 1248를 더해준다 -> 비트마스킹?
		for(int i = 0; i < W; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int t = sc.nextInt();
			
			// t가 0이면 위에칸이랑, 1이면 오른쪽칸이랑
			if(t == 0) {
				walls[x][y][3] = 1;
				walls[x - 1][y][1] = 1;
			} else if(t == 1) {
				walls[x][y][0] = 1;
				walls[x][y + 1][2] = 1;
			}
		}
		
		
		// 먹은 초콜릿 출력 
		choco = 0;
		heater();
		
		System.out.println(choco);
	}
	
	
	// 온풍기 한 사이클
	static void heater() {
		// 1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
		wind();
		
		// 2. 온도가 조절됨
		adjust();
		
		// 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
		edge();
		
		// 4. 초콜릿을 하나 먹는다.
		choco++;
		
		// 초콜릿 개수가 100을 넘어가면 101을 출력한다 ***이거 30분
		if(choco > 100) return;
		
		// 5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사 -> 반복
		for(int[] point : checkpoint) {
			int r = point[0];
			int c = point[1];
			
			if(map[r][c] < K) {
				// 온도가 K보다 작으면 다시 반복
				heater();
				
				// 돌아왔으면 더 검사하지 말고
				return;
			}
		}
		
		// 다 K이상이면 돌아가기
	}
	
	
	// 온풍기에서 바람 나오기
	static void wind() {
		
		for(int[] h : heater) {
			int r = h[0];
			int c = h[1];
			int d = h[2];
			
			// 온풍기 1개당 칸은 온도 상승 1번만
			boolean[][] visited = new boolean[R + 1][C + 1];
			
			// 온풍기 바람이 나오는 방향에 있는 칸은 항상 존재
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			Queue<int[]> q = new LinkedList<>();
			map[nr][nc] += 5;
			visited[nr][nc] = true;
			q.add(new int[] {nr, nc, 5});

			while(!q.isEmpty()) {
				int cr = q.peek()[0];
				int cc = q.peek()[1];
				int ch = q.poll()[2];
				
				// 바람이 1이면 중단
				if(ch == 1) continue;
				
				// 바람 방향에 벽이 없으면 이동
				if(walls[cr][cc][d] == 0) {
					nr = cr + dr[d];
					nc = cc + dc[d];
					
					// 범위 안이고 방문한 적 없다면
					if(inRange(nr, nc) && !visited[nr][nc]) {
						map[nr][nc] += ch - 1;
						visited[nr][nc] = true;
						q.add(new int[] {nr, nc, ch - 1});
					}
				}
				
				// 대각선 방향에 벽이 없으면 이동
				// 대각선으로 이동할 때 처음 방향
				int[] dd = {d - 1 >= 0 ? d - 1 : d - 1 + 4,
							d + 1 <= 3 ? d + 1 : d + 1 - 4};
				for(int i = 0; i < 2; i++) {
					
					// 1차 양옆 방향으로 벽이 없으면
					if(walls[cr][cc][dd[i]] == 0) {

						// 대각선 1차로 이동하는 칸
						nr = cr + dr[dd[i]];
						nc = cc + dc[dd[i]];
						if(!inRange(nr, nc)) continue;
						
						// 1차 이동 통과하면 2차 진짜 이동
						// 1차 이동칸에서 원래 방향으로 벽이 없으면 우선 가기
						if(walls[nr][nc][d] == 0) {
							
							// 갔는데 범위 이내면 진짜 이동
							nr += dr[d];
							nc += dc[d];
							
							if(inRange(nr, nc) && !visited[nr][nc]) {
								map[nr][nc] += ch - 1;
								visited[nr][nc] = true;
								q.add(new int[] {nr, nc, ch - 1});
							}
						}
					}
				}
			}
		}
	}
	
	
	// 인접한 칸 온도조절하기
	static void adjust() {
		
		// 증감온도 저장
		int[][] increase = new int[R + 1][C + 1];
		
		// 모든 칸 확인
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				
				// 중복 방지를 위해서 방향은 오른쪽, 아래만 확인
				for(int d = 0; d < 2; d++) {
					// 그 방향으로 벽이 없으면 온도조절
					if(walls[i][j][d] == 0) {
						
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(!inRange(nr, nc)) continue;
						
						int diff = Math.abs(map[i][j] - map[nr][nc]) / 4;
						
						if(map[i][j] > map[nr][nc]) {
							increase[i][j] -= diff;
							increase[nr][nc] += diff;
						} else {
							increase[i][j] += diff;
							increase[nr][nc] -= diff;
						}
					}
				}
			}
		}
		
		// 증감온도 반영
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				map[i][j] += increase[i][j];
			}
		}
		
	}
	
	
	// 바깥 온도 1감소
	static void edge() {
		
		// 1행 R행
		for(int j = 1; j <= C; j++) {
			if(map[1][j] != 0) map[1][j]--;
			if(map[R][j] != 0) map[R][j]--;
		}
		
		// 1열 C열
		for(int i = 2; i <= R - 1; i++) {
			if(map[i][1] != 0) map[i][1]--;
			if(map[i][C] != 0) map[i][C]--;
		}
		
	}
	
	
	// 범위 내에 있는지
	static boolean inRange(int r, int c) {
		if(r > 0 && r <= R && c > 0 && c <= C) return true;
		else return false;
	}

	
	// 확인용 출력  - 1시간반
	static void print(int[][] arr) {
		
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}

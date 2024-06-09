import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int R;
	static int C;
	static int[][] map;
	static int total;
	static int t;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 공기와 접촉한 칸은 1시간이 지나면 녹아 없어진다
		// 치즈가 모두 녹아 없어지는 시간
		// 모두 녹기 1시간 전 남아있는 치즈 칸 개수
		
		// 사각형 모양 판의 세로, 가로
		R = sc.nextInt();
		C = sc.nextInt();
		
		// 총 치즈 갯수
		total = 0;
		
		map = new int[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) total++;
			}
		}

		// 시간
		t = 0;
		
		// 1시간마다 녹일 거 확인
		checkOutside();
		
	}
	
	
	static void checkOutside() {
		
		// 1시간 지나기
		t++;
		
		// 바깥에서 BFS로 녹일 치즈 저장하기
		ArrayList<int[]> cheeze = new ArrayList<>();
		
		// BFS
		boolean[][] visited = new boolean[R][C];
		Queue<int[]> q = new LinkedList<>();
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		visited[0][0] = true;
		q.add(new int[] {0, 0});
		
		while(!q.isEmpty()) {
			int cr = q.peek()[0];
			int cc = q.poll()[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if(visited[nr][nc]) continue;
				
				// 0이나 치즈나 방문체크하기
				visited[nr][nc] = true;
				
				// 0이면 이동하고
				if(map[nr][nc] == 0) q.add(new int[] {nr, nc});
				
				// 치즈면 저장하기
				else if(map[nr][nc] == 1) cheeze.add(new int[] {nr, nc});
			}
		}
		
		
		// 녹일 치즈를 다 없앴을 때 남은 치즈가 0이면 출력하고 끝
		if(total - cheeze.size() == 0) {
			System.out.println(t);
			System.out.println(total);
			return;
		}
		
		// 그게 아니면 치즈 없애고 계속 진행
		else {
			total -= cheeze.size();
			for(int[] c : cheeze) {
				map[c[0]][c[1]] = 0;
			}
			
			checkOutside();
		}
		
	}
}
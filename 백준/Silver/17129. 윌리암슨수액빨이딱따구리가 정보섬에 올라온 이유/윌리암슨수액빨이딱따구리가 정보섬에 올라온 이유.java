import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 정보섬 크기 n행 m열
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		// 식구 위치
		int tr = 0;
		int tc = 0;
		
		// 지도 입력
		// 식구는 2, 청국장은 3, 스시는 4, 맥앤치즈는 5
		int[][] map = new int[n][m];
		for(int i = 0; i < n; i++) {
			// 숫자 끊어서 입력
			String in = sc.next();
			for(int j = 0; j < m; j++) {
				map[i][j] = Character.getNumericValue(in.charAt(j));
				// 식구 위치 저장
				if(map[i][j] == 2) {
					tr = i;
					tc = j;
				}
			}
		}

		// 방문 배열
		int[][] visited = new int[n][m];
//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j < m; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		// 식구 이동 시작
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {tr, tc, 0});
		
		int[] dr = {1, 0, -1, 0};
		int[] dc = {0, 1, 0, -1};
		
		// 식구 하나가 왔던 길을 다시 안 간다고 생각하면
		// 방문배열 하나로 체크해도 괜찮다
		visited[tr][tc] = 1;
		
		while(!q.isEmpty()) {
			int cr = q.peek()[0];
			int cc = q.peek()[1];
			int cl = q.poll()[2];

			// 큐에 넣어서 하니까 먼저 나온 게 제일 먼저 도착
			if(map[cr][cc] == 3 || map[cr][cc] == 4 || map[cr][cc] == 5) {
				System.out.println("TAK");
				System.out.println(cl);
				return;
			}
			
			// 아니라면 4방면으로 이동
			for(int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				// 범위를 벗어나지 않고, 벽이 아니고, 방문 안 했다면
				if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				if(map[nr][nc] != 1 && visited[nr][nc] == 0) {
					// 다음 이동
					visited[nr][nc] = 1;
					q.add(new int[] {nr, nc, cl + 1});
				}
			}
				
		}
		
		// 여기까지 왔다면 아무 음식 못 먹은 것
		System.out.println("NIE");
		
	}
}
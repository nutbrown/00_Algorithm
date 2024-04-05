import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static ArrayList<int[]> cheese;
	static int t;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		// 모눈종이의 크기 N, M
		N = sc.nextInt();
		M = sc.nextInt();
		
		// 치즈는 1, 없으면 0
		map = new int[N][M];
		
		// 녹일 치즈 저장
		cheese = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) cheese.add(new int[] {i, j});
			}
		}
		
		// 초기 외부공간 2로 체크
		checkOutside();
		
		// 치즈가 녹아 없어지는데 걸리는 정확한 시간
		t = 0;
		
		while(!cheese.isEmpty()) {
			// 외부공간 체크하고, 치즈 녹이고, 시간 증가시키고
			checkOutside();
			melt();
			t++;
		}
		
		System.out.println(t);
	}
	
	
	// 한 번 녹인 다음에, 외부 공기를 한 번에 체크하는 게 중복이 안 된다
	// 한 점마다 외부를 확인하면 **메모리초과
	static void checkOutside() {
		// 초기 외부공간 2로 체크
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(new int[] {0, 0});
		visited[0][0] = true;
		map[0][0] = 2;
				
		while(!q.isEmpty()) {
			int cr = q.peek()[0];
			int cc = q.poll()[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
				
				if(map[nr][nc] != 1) {
					q.add(new int[] {nr, nc});
					visited[nr][nc] = true;
					map[nr][nc] = 2;
				}
			}
		}	
	}
	
	
	// 녹일 치즈 찾고 녹이는 함수 (이번에는 함수를 쪼개보자)
	static void melt() {
		// 녹일 치즈 저장
		Stack<Integer> st = new Stack<>();
		
		// 전체탐색해서 치즈고, 두 변이 바깥으로 나있으면, 녹인다
		for(int c = 0; c < cheese.size(); c++) {
			int i = cheese.get(c)[0];
			int j = cheese.get(c)[1];
			
			// 두 변이 안 치즈와 맞닿아 있다면
			int meetOut = 0;
			for(int d = 0; d < 4; d++) {
				int nr = i + dr[d];
				int nc = j + dc[d];
				
				// 맨 가장자리에는 치즈가 놓이지 않는다
				// 치즈가 아니라 공기에 닿아있는지
				if(map[nr][nc] == 2) meetOut++;
			}

			// 두 변 이상이 외부 공기에 닿아있으면, 치즈를 녹인다
			if(meetOut >= 2) st.add(c);

		}
		
		
		// 녹일 치즈가 있으면 true 반환
		if(!st.isEmpty()) {

			// 치즈 전체 녹이기
			while(!st.isEmpty()) {
				int idx = st.pop();

				// 외부 공기랑 닿아있어서 녹였으니까 2로 바꾼다
				map[cheese.get(idx)[0]][cheese.get(idx)[1]] = 2;
				
				// 치즈 없애고(스택으로 넣어서 뺀 인덱스 순서대로 지워도 된다)
				cheese.remove(idx);
				
			}
		}
		
		// 확인용 출력
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
	
	
	// 한 번 녹일 때마다 외부공기 체크해서, 필요없는 함수
	static boolean meetOut(int r, int c) {

		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 외부 공기로 이어지는지 확인
			if(map[nr][nc] == 0) {
				
				Queue<int[]> q = new LinkedList<>();
				boolean[][] visited = new boolean[N][M];
				
				// nr, nc에서 시작
				q.add(new int[] {nr, nc});
				visited[nr][nc] = true;
				
				while(!q.isEmpty()) {
					int cr = q.peek()[0];
					int cc = q.poll()[1];
					
					// 외부 공기인 게 확인되면 바꾸기
					if(cr == 0 || cr == N - 1 || cc == 0 || cc == M - 1 || map[cr][cc] == 2) {
						map[nr][nc] = 2;
						break;
					}
					
					// 4방면으로 나아가기
					for(int d2 = 0; d2 < 4; d2++) {
						int nr2 = cr + dr[d2];
						int nc2 = cc + dc[d2];
						
						if(map[nr2][nc2] != 1 && !visited[nr2][nc2]) {
							q.add(new int[] {nr2, nc2});
							visited[nr2][nc2] = true;
						}
					}
				}
			}
			
			
			// 하나 바꿀 때마다 외부공기 2개 닿아있는지 확인
			int out = 0;
			for(int d2 = 0; d2 < 4; d2++) {
				int nr2 = r + dr[d2];
				int nc2 = c + dc[d2];
				
				if(map[nr2][nc2] == 2) out++;
			}
			
			if(out >= 2) return true;
		}
		
		// 외부 공기로 이어지는 게 없으면 false 반환
		return false;
	}
	
	
	
	
}
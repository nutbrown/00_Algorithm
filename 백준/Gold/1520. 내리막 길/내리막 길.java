import java.util.Scanner;

public class Main {
	static int R;
	static int C;
	static int[][] map;
	static int[][] dp;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 어제보다 오늘이 잘 읽히네 7:23~7:40
		
		// 세로 크기 M, 가로 크기 N
		R = sc.nextInt();
		C = sc.nextInt();
		
		map = new int[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 해당 지점까지 오는 경우의 수가 몇 개인지
		dp = new int[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				dp[i][j] = -1;
			}
		}
		
		// 시작점에는 경우의 수 1을 넣어준다
		dp[0][0] = 1;
		
		// 오른쪽 아래에서 시작해서 어느 점에서 올 수 있는지
		System.out.println(recur(R - 1, C - 1));
		
//		for(int i = 0; i < R; i++) {
//			for(int j = 0; j < C; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
	
	
	// 내리막길로만 이동했을 때 (r, c)에 오는 경우의 수
	static int recur(int r, int c) {
		
		// 만약 그 점까지의 경우의 수를 이미 구했다면, 반환
		// 여기를 0으로 하면 경우의 수가 0인점을 계속 다시 구한다
		if(dp[r][c] != -1) return dp[r][c];
		
		// 어느곳에서도 올 수 없어도 초기화해서 0입력
		dp[r][c] = 0;
		
		// 사방면에서 올 수 있다
		// 어짜피 내리막길로만 이동해서 왔다갔다 할 수 없다
		for(int d = 0; d < 4; d++) {
			int nr = r - dr[d];
			int nc = c - dc[d];
			if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			
			// 직전 점이 이 점 높이보다 크면 거기서 올 수 있다
			if(map[nr][nc] > map[r][c]) {
				dp[r][c] += recur(nr, nc);
			}
		}
		
		// 그 점까지의 경우의 수를 반환한다
		return dp[r][c];
	}
}

import java.util.Scanner;

public class Main {
	static int N;
	static int[][] map;
	static int[][] dp;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 백트래킹으로 디피 문제 7:44~7:56
		
		// 대나무숲 크기 N
		N = sc.nextInt();
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 그 점에 풀어두면 얼마나 멀리 갈 수 있는지
		dp = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				dp[i][j] = -1;
			}
		}
		
		// 모든 점을 구한다음에 최댓값을 찾는다
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				dp[i][j] = recur(i, j);
				
				max = Math.max(max, dp[i][j]);
			}
		}
		
		System.out.println(max);
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
	

	// (r, c)에서 얼마나 멀리 이동할 수 있는지
	static int recur(int r, int c) {
		
		// 만약 그 점까지의 최대 거리를 이미 구했다면, 반환
		// 여기를 0으로 하면 최대거리가 0인점을 계속 다시 구한다
		if(dp[r][c] != -1) return dp[r][c];

		// 하나도 못 이동하더라도 초기화해서
		// 본인칸 1개 방문할 수 있으니까 1
		dp[r][c] = 1;
		
		// 해당 지역보다 대나무가 더 많은 지역으로 옮길 수 있다
		// 이동할 수 있는 칸중에서 제일 멀리가는 max + 1로 값을 반환한다
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			
			// 대나무가 더 많으면 이동 가능
			if(map[nr][nc] > map[r][c]) {
				dp[r][c] = Math.max(dp[r][c], recur(nr, nc) + 1);
			}
		}
		
		// 그 점까지의 경우의 수를 반환한다
		return dp[r][c];
	}
}

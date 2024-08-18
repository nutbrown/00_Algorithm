import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 버스는 3행 N열
		// N은 200,000이하 전체칸 600,000이하
		int N = sc.nextInt();
		
		// 운영진 M명 입력
		int M = sc.nextInt();
		int[][] ac = new int[4][N + 1];
		for(int i = 0; i < M; i++) {
			
			// 1 또는 3 x행, y열
			// 에 1이면 에어컨 가동 0이면 안 가동
			int x = sc.nextInt();
			int y = sc.nextInt();
			int t = sc.nextInt();
			
			// 에어컨 틀었으면 1, 껐으면 -1
			if(t == 1) ac[x][y] = 1;
			else ac[x][y] = -1;
		}
		
//		for(int i = 1; i <= 3; i++) {
//			for(int j = 1; j <= N; j++) {
//				System.out.print(ac[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		
		// 버스의 모든칸이 시원해야하는 최소 에어컨 수
		// 2행을 시원하게 만드려면, 1행 3행 중 하나는 틀어야한다
		// 에어컨이 꺼져있으면, 양옆 중 하나는 틀어져있어야 한다
		
		// r번이
		// 1행 틀고 3행 시원할 때,
		// 1행 틀고 3행 안 시원할 때,
		// 3행 틀고 1행 시원할 때,
		// 3행 틀고 1행 안 시원할 때,
		// 1,3행 틀었을 때의 - 최솟값을 기록한다
		int[][] dp = new int[N + 1][6];
		
		// Integer.MAX_VALUE를 넣으면 1을 더했을 때 -2147483648가 된다
		// 에어컨 대수 최댓값 400,000에 1을 더해서 넣기
		for(int i = 0; i <= N; i++) {
			for(int j = 0; j < 6; j++) {
				dp[i][j] = 1_000_000;
			}
		}

		// 1번 1행만 틀었을 때 입력
		if(ac[1][1] != -1 && ac[3][1] != 1) {
			// 1행은 틀고 3행은 안 시원하다
			dp[1][2] = 1;
		}
		// 1번 3행만 틀었을 때 입력
		if(ac[3][1] != -1 && ac[1][1] != 1) {
			// 3행은 틀고 1행은 안 시원하다
			dp[1][4] = 1;
		}
		// 1번 1,3행 틀었을 때 입력
		if(ac[1][1] != -1 && ac[3][1] != -1) {
			dp[1][5] = 2;
		}
		
		
		for(int c = 2; c <= N; c++) {
			
			// 1행만 틀기
			if(ac[1][c] != -1 && ac[3][c] != 1) {
				
				// c-1번에서 3행을 안 틀었는데 시원하다면
				// c번은 1행은 틀고 3행은 안시원하다
				dp[c][2] = Math.min(dp[c][2], dp[c - 1][1] + 1);
				
				// c-1번에서 3행에 에어컨을 틀었다면
				// c번은 1행은 틀고 3행은 시원하다
				dp[c][1] = Math.min(dp[c][1], dp[c - 1][3] + 1);
				dp[c][1] = Math.min(dp[c][1], dp[c - 1][4] + 1);
				dp[c][1] = Math.min(dp[c][1], dp[c - 1][5] + 1);
			}
			
			// 3행 틀기
			if(ac[3][c] != -1 && ac[1][c] != 1) {
				
				// c-1번에서 1행을 안 틀었는데 시원하다면
				// c번은 3행은 틀고 1행은 안 시원하다
				dp[c][4] = Math.min(dp[c][4], dp[c - 1][3] + 1);
				
				// c-1번에서 1행에 에어컨을 틀었다면
				// c번은 3행에 틀고 1행은 시원하다
				dp[c][3] = Math.min(dp[c][3], dp[c - 1][1] + 1);
				dp[c][3] = Math.min(dp[c][3], dp[c - 1][2] + 1);
				dp[c][3] = Math.min(dp[c][3], dp[c - 1][5] + 1);
			}
			
			// 1,3행 틀기
			if(ac[1][c] != -1 && ac[3][c] != -1) {

				// c-1번의 모든 경우의 수가 아니라*** 맞아***
				for(int x = 1; x <= 5; x++) {
					dp[c][5] = Math.min(dp[c][5], dp[c - 1][x] + 2);
				}
			}
		}
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= 5; j++) {
//				if(dp[i][j] == 1_000_000) System.out.print(0 + " ");
//				else System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		// 마지막 N열이 다 시원한 경우 중 가장 작은 걸 찾는다
		int ans = 1_000_000;
		ans = Math.min(ans, dp[N][1]);
		ans = Math.min(ans, dp[N][3]);
		ans = Math.min(ans, dp[N][5]);
		
		// 답이 없으면 -1 출력
		if(ans >= 1_000_000) System.out.println(-1);
		else System.out.println(ans);
	
	}
}
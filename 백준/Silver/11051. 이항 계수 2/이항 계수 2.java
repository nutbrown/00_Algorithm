import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 이항계수 (N K)를 구하는 프로그램
		// nCk = n-1Ck + n-1Ck-1
		
		// N은 1이상, K는 0이상
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		// dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
		int[][] dp = new int[N + 1][K + 1];

		
		// 0C0 = 1 입력
		dp[0][0] = 1;
		
		for(int i = 1; i <= N; i++) {
			
			// iC0 = 1 입력
			dp[i][0] = 1;
			
			for(int j = 1; j <= K; j++) {
				// 왼쪽 윗줄 + 그냥 윗줄
				dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
			}
		}
		
		System.out.println(dp[N][K]);
	}
}

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i = 0; i < T; i++) {
			int N = sc.nextInt();
			
			if(N < 6) {
				if(N == 1) System.out.println(1);
				if(N == 2) System.out.println(1);
				if(N == 3) System.out.println(1);
				if(N == 4) System.out.println(2);
				if(N == 5) System.out.println(2);
				
			} else {
				long[] dp = new long[N + 1];
				dp[1] = 1;
				dp[2] = 1;
				dp[3] = 1;
				dp[4] = 2;
				dp[5] = 2;
				
				for(int j = 6; j <= N; j++) {
					dp[j] = dp[j - 1] + dp[j - 5];
				}
				
				System.out.println(dp[N]);
			}
			
		}
	}
}

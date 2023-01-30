package allProblems;

import java.util.Scanner;

public class BJ_11726 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if(n == 1) { System.out.print(1); }
		if(n == 2) { System.out.print(2); }
		
		if(n > 2) {
			int[] dp = new int[n + 1];
			dp[1] = 1;
			dp[2] = 2;
			
			for(int i = 3; i <= n; i++) {
				dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
			}
			System.out.print(dp[n]);
		}
	}
}

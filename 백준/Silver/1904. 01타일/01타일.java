import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 1타일과 00타일만 있을 때 만들 수 있는
		// 크기가 N인 모든 2진 수열의 개수
		int N = sc.nextInt();

		// dp
		int[] dp = new int[N + 1];
		
		// 길이가 1일 때는 1만 가능
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i = 2; i <= N; i++) {
			// -2한 거에서 +00한 거랑
			// -1한 거에서 +1한 거
			dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
		}
		
		System.out.println(dp[N]);
	}
}
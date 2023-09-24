import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		// 1 4 9 16 25
		
		// -1제곱에서 1을 더하거나
		// -2제곱에서 1을 더하거나
		// -3제곱에서 1을 더하거나
		// -4제곱에서 1을 더하거나 하다가
		// 뺐을 때 0보다 작으면 멈춰야 한다
		
		int[] dp = new int[N + 1];
		
		// 초기값 1에 1을 넣음
		dp[1] = 1;
		
		for(int i = 2; i <= N; i++) {
			// 우선 1을 뺀 거에서 1을 더할 수 있음
			dp[i] = dp[i - 1] + 1;
			
			// 그다음에 2제곱부터 빼본다
			int j = 2;
			// 뺐을 때 0보다 작으면 안 됨
			// 제곱수면 dp[0] + 1 이런식으로 1이 됨
			while(i - Math.pow(j, 2) >= 0) {
				dp[i] = Math.min(dp[i], dp[(int) (i - Math.pow(j, 2))] + 1);
				j++;
			}
		}
		
		System.out.println(dp[N]);
	}
}
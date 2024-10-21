import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N x M 배열 100을 안 넘는다
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] map = new int[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		
		// dp
		int[][] dp = new int[N + 1][M + 1];
		
		// 다시 위로 못 올라가니까
		// 위에서 어떻게 오면 이 점으로 올 수 있을지 본다
		// 위에서 어느 점으로 내려와서, 현재 점까지 오른쪽 또는 왼쪽으로 쭉 왔는지
		
		// 왼쪽에서 온 거 오른쪽에서 온 거 구간합을 구하기 위해서 누적합
		int[][] prefixSum = new int[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				prefixSum[i][j] = prefixSum[i][j - 1] + map[i][j];
			}
		}


		// 그 전에 첫 줄은 해당 점까지 가려면
		// 무조건 왼쪽에서 쭉 가는 경우만 있다
		for(int j = 1; j <= M; j++) {
			dp[1][j] = prefixSum[1][j];
		}
		
		// 3가지를 비교해서 하기(왼쪽에서 온 거, 바로 내려온 거, 오른쪽에서 온 거) 
		for(int i = 2; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				
				// 음수일 때를 생각 안해서, 다 음수일 때 0이 더 크니까 무시했네
				dp[i][j] = Integer.MIN_VALUE;
				
				// 왼쪽 위쪽 오른쪽에서 온 거 더하기
				// 왼쪽에서 온 거 + 위에서 온 거
				for(int left = 1; left <= j; left++) {
					int sum = dp[i - 1][left] 
							+ (prefixSum[i][j] - prefixSum[i][left - 1]);
					
					// 계속 더 큰 거 넣기
					dp[i][j] = Math.max(dp[i][j], sum);
				}
				
				// 오른쪽에서 온 거
				for(int right = M; right > j; right--) {
					int sum = dp[i - 1][right]
							 + (prefixSum[i][right] - prefixSum[i][j - 1]);
					
					// 더 큰 거 넣기
					dp[i][j] = Math.max(dp[i][j], sum);
				}
				
				
			}
		}
		
		// (N, M)에 도착했을 때 최댓값
		System.out.println(dp[N][M]);
		
		
	}	
}
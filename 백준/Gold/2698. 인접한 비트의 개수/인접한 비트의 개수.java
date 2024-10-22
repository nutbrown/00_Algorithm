import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 테스트 케이스 T개
		int T = sc.nextInt();
		
		// 크기가 N인 수열 중에서, 인접한 비트의 개수가 k인 개수
		int[][] arr = new int[T][2];

		// 제일 긴 길이까지 구할 거다 
		int maxLen = 0;
		
		for(int t = 0; t < T; t++) {
			arr[t][0] = sc.nextInt();
			arr[t][1] = sc.nextInt();
			
			maxLen = Math.max(maxLen, arr[t][0]);
		}
		
		
		// n과 k는 100 미만 자연수
		// 길이 1부터 maxLen까지 인접한 비트 개수를 저장한다
		// 인접한 비트의 개수는 1부터 maxLen-1 까지다
		// 끝이 0으로 끝나는 거, 1로 끝나는 거
		int[][][] dp = new int[maxLen + 1][maxLen + 1][2];
		
		// 길이가 1인 0,1 초기값 넣기
		dp[1][0][0] = 1;
		dp[1][0][1] = 1;
		

		// i를 구하려면 [i][j] 기준으로 하는 게 아니라
		// 길이가 i-1인 것중에서 0으로 끝나는 거 1로 끝나는 거에서 올라와야한다
		// 아래에서부터 올라가야하는데*** 반대로 했다
		for(int i = 1; i < maxLen; i++) {
			for(int j = 0; j <= i; j++) {

				// 인접 j개 0으로 끝나는 거에 +0 - 인접 j개 0 끝
				// 인접 j개 0으로 끝나는 거에 +1 - 인접 j개 1 끝
				// 인접 j개 1로 끝나는 거에 +0 - 인접 j개 0 끝
				// 인접 j개 1로 끝나는 거에 +1 - 인접 j+1개 1 끝

				dp[i + 1][j][0] += dp[i][j][0];
				dp[i + 1][j][1] += dp[i][j][0];
				dp[i + 1][j][0] += dp[i][j][1];
				dp[i + 1][j + 1][1] += dp[i][j][1];
			}
		}
		

		StringBuilder sb = new StringBuilder();
		for(int t = 0; t < T; t++) {
			sb.append(dp[arr[t][0]][arr[t][1]][0]
							+ dp[arr[t][0]][arr[t][1]][1])
				.append("\n");
		}
		
		System.out.println(sb);
		
	}	
}
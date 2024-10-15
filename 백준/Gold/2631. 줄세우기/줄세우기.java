import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 답을 찾아봤다
		// LIS 최장증가부분수열을 찾고 나머지를 옮긴다
		// 전에 틀렸던거니까 우선 풀고 -> 바텀업으로 변경
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 각 숫자까지의 증가하는최장길이 저장
		int[] dp = new int[N];
		
		// 처음에 길이 1을 저장한다
		dp[0] = 1;
		
		// 그 다음부터는 앞에 최장길이 + 에서 1이 더해질수 있는지 확인
		for(int i = 1; i < N; i++) {
			
			// 앞에서부터 이 숫자까지의 최대가 뭔지 찾기
			// 혼자 있을 때 길이가 1이어야지, 그것보다 큰 거의 길이가 2가 된다
			int max = 1;
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) {
					max = Math.max(max, dp[j] + 1);
				}
			}
			
			// 최댓값을 dp에 저장
			dp[i] = max;
		}
		
		// 지금까지 나온 최장길이 중에서 제일 긴 거,
		// 를 제외하고 나머지 옮기기
		int max = 0;
		for(int i = 0; i < N; i++) {
			if(dp[i] > max) max = dp[i];
		}
		
		System.out.println(N - max);
		
		
	}
}
import java.util.Scanner;

public class Main {
	static int N;
	static int[] arr;
	static long[] dp;
	
	// dp를 백트래킹으로 푼 거 처음이야!!!!!!!
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		arr = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 백트래킹을 dp로 바꾸는 메모이제이션
		dp = new long[N + 1];

		// N이 2이하일 때 처리
		if(N <= 2) {
			if(N == 1) System.out.println(0);
			else System.out.println(Math.min(arr[1], arr[2]));
			return;
		}
		
		
		// 초기값 설정 (N이 1이상이지만 커플이어서 2이상이다)
		dp[2] = arr[2];
		dp[1] = arr[1];
		
		
		// 결과는 N-1을 선택한 거랑, N-2랑 N을 선택한 거의 최솟값
		System.out.println(Math.min(solve(N - 1), solve(N - 2) + arr[N]));
	}

	
	// idx를 선택했을 때의 최솟값
	static long solve(int idx) {
		
		// solve(idx)의 값이 이미 dp에 메모이제이션 되어있으면 바로 반환
		if(dp[idx] != 0) return dp[idx];
		
		// 이전에 구한 값이 아니라면 이번에 구한다
		// N을 선택했을 때의 최솟값은
		// N-1을 선택했을 때랑 N-2를 선택했을 때랑 비교
		return dp[idx] = Math.min(solve(idx - 1), solve(idx - 2)) + arr[idx];
		
	}
}
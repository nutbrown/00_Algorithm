import java.util.Scanner;

public class Main {
	static int N;
	static int K;
	static int[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		// K개를 골라서 합이 N인 경우의 수
		// K-1개를 골라서 합이 N인 경우의 수 + 뒤에 0을 고르기
		// K-1개를 골라서 합이 N-1인 경우의 수 + 뒤에 1을 고르기
		// ...
		// K-1개를 골라서 합이 0인 경우의 수 + 뒤에 N을 고르기
		
		// 10에서 -2 -3 해서 5로 온 거랑
		// 10에서 -0 -5 해서 5로 온 거랑 반환하는 게 같다
		// 메모이제이션
		dp = new int[K + 1][N + 1];
		
		// ****틀리지 않기**** 끝범위인 (200, 200)은 넣어봐야지...
		System.out.println(recur(K, N));
	}
	
	static int recur(int K, int X) {
		
		// 0인 경우는 없어서 초기화되면 무조건 0이다
		if(dp[K][X] != 0) return dp[K][X];
		
		
		// K개를 골라서 0이 되어야 한다면 - 1가지 경우
		// 1개를 골라서 합이 X가 되려면 - 1가지 경우
		if(K == 1 || X == 0) return dp[K][X] = 1;
		
		// K개를 골라서 합이 X가 되는 경우는
		// K-1개의 합이 X부터 0이 되는 경우의 합이다
		int sum = 0;
		for(int i = 0; i <= X; i++) {
			// int범위가 2,147,483,647이니까
			// 1번은 더할 수 있다. 더하고 모듈러
			sum = (sum + recur(K - 1, X - i)) % 1_000_000_000;
		}
		
		return dp[K][X] = sum;
	}
}
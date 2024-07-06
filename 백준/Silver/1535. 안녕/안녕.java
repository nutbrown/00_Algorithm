import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 세준이를 생각해준 사람 N명
		int N = sc.nextInt();
		
		// i번 사람에게 인사를 하면
		// L만큼의 체력을 잃고, J만큼의 기쁨을 얻는다
		// 체력은 100, 기쁨은 0
		int[][] arr = new int[N + 1][2];
		for(int j = 0; j < 2; j++) {
			for(int i = 1; i <= N; i++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// 배낭문제 디피
		// i번~N번 사람까지 확인했을 때, 체력이 100~1까지 얻는 최대 기쁨
		int[][] dp = new int[N + 1][101];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 100; j > 0; j--) {
				
				// 이 사람한테 인사를 안 한다면
				// 이전 물건 확인했을 때 기쁨 그대로
				int sum = dp[i - 1][j];
				
				// 체력을 써서 j가 될 수 있다면, 인사했을 경우 확인
				if(j + arr[i][0] <= 100) {
					sum = Math.max(sum, dp[i - 1][j + arr[i][0]] + arr[i][1]);
				}
				
				// 갱신
				dp[i][j] = sum;
			}
		}
		
		System.out.println(dp[N][1]);
	}
}
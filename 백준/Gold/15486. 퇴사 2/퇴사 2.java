import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] arr =new int[n+2][2];
		int[] dp = new int[n+2];
		for(int i=1; i<n+1; i++) {
			
			int t = sc.nextInt();
			int p = sc.nextInt();
			arr[i][0] = t; // 기간
			arr[i][1] = p; // 금액
		}
		
		int max = -1;
		for(int i=1; i<=n+1; i++) {
			if(max < dp[i]) {
				max = dp[i];
			}
			
			int nxt = i +arr[i][0];
			if(nxt < n+2) {
				dp[nxt] = Math.max(dp[nxt], max+arr[i][1]);
			}
		}
		System.out.println(dp[n+1]);

    }
}

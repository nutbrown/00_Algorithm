import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[][] arr = new int[N + 2][2];
        for(int i = 1; i <= N; i++) {
            // 기간과 금액
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        int[] dp = new int[N + 2];
        for(int i = 1; i <= N; i++) {
            // (해당일 + 기간) 날짜에 돈을 받는다
            int nextDay = i + arr[i][0];

            // 나중에 돈 받는 날을 기준으로 생각한다
            // dp는 돈 받는 날을 기준으로 최댓값으로 갱신되어서
            // 그 전에 다른 날짜 일이 더 최댓값이라면 그 값이 들어간다
            if(nextDay <= N + 1) {
                // dp[i]는 일이 끝난 날에 돈을 받은 상태니까
                // i날부터 nextDay까지 내내 일을 하다가 받은 돈을 기준으로
                // dp[nextDay]가 갱신된다
                dp[nextDay] = Math.max(dp[nextDay], dp[i] + arr[i][1]);
            }

            // 다음날 dp 값을 미리 넣어줌(마지막을 생각해서)
            // 그동안 갱신해온 금액 dp[i + 1]랑
            // 일 안하고 전날에서 이어온 금액 dp[i] 중에 최댓값
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        System.out.println(dp[N + 1]);
    }
}

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int t = 0; t < T; t++) {
            // n자리수
            int n = sc.nextInt();

            // 2자리는 00 01 02 부터 99까지 10 + 9 + ...1 -> 55가 나옴
            // 000 001 011 처럼 숫자가 줄어들지만 않으면 됨

            // 3자리 수 0시작 -> 1, 2, 3... 시작 2자리 수를 붙임
            // 3자리 수 1시작 -> 2, 3, 4... 시작 2자리 수를 붙임

            // r자리수 숫자 c로 시작하는 숫자 개수
            long[][] dp = new long[n + 1][10];
            for(int i = 0; i <= 9; i++) {
                dp[1][i] = 1;
            }

            // 한자리수인 경우 처리
            if(n == 1) {
                System.out.println(10);
                continue;
            }
            // i자리수를 구한다면 i-1자리수에서 더한다
            for(int i = 2; i <= n; i++) {
                // 맨 앞 시작 숫자가 0부터 9
                for(int j = 0; j <= 9; j++) {
                    // 해당 숫자 이상인 것만 모으자
                    for(int k = j; k <= 9; k++) {
                        dp[i][j] += dp[i - 1][k];
                    }
                }
            }

            // 마지막줄을 다 더하면 됨
            long cnt = 0;
            for(int i = 0; i <= 9; i++) {
                cnt += dp[n][i];
            }
            System.out.println(cnt);


        }
    }
}

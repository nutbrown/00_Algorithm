import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 테스트케이스 T
    	int T = sc.nextInt();
    	for(int t = 0; t < T; t++) {
    		
    		// 동전의 가지수 N (1 <= N <= 20)
    		int N = sc.nextInt();
    		
    		// 동전의 금액 입력
    		int[] coins = new int[N];
    		for(int i = 0; i < N; i++) {
    			coins[i] = sc.nextInt();
    		}
    		
    		// 동전으로 만들어야 할 금액 M (1 <= M <= 10000) 
    		int M = sc.nextInt();
    		
    		int cnt = 0;
    		
    		// i원을 만드는 방법의 수
    		int[] dp = new int[M + 1];
    		
    		// dp[0]에 1을 넣어둬서
    		// 0 + coin = coin일 때 방법 1가지로 시작할 수 있게
    		// 이렇게 하면 동전이 2, 4, 8 있을 때
    		// 동전 8을 시작할 때 (기존 2, 4로 만들어진 수) + (0에서 8로 올라오는 1)을 할 수 있다  
    		dp[0] = 1;
    		
    		for(int i = 0; i < N; i++) {
    			// 동전이 5원 7원이 있을 때 22원을 만드는 방법의 수
    			// 5원 - ... 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1 0 0
    			// 7원 - ... 0 1 0 0 1 0 0 0 1 1 0 0 0 0 0 0 1
    			
    			// 동전이 2원 5원이 있을 때 얼마 만드는 방법의 수
    			// 2원 - 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
    			// 5원 - 0 1 0 1 1 1 0 1 0 2 1 1 1 1 2
    			// 이렇게 쌓아보면 10000 * 20 연산횟수
    			
    			// 동전 가지수마다 기록한다
    			int coin = coins[i];
    			
    			for(int j = 0; j + coin <= M; j++) {
    				// 현재 금액 + 동전 금액 = 다음 금액
    				// 다음 금액 가지수 = 기존 다음 금액 가지수 + (-coin)금액 가지수
    				dp[j + coin] += dp[j]; 
    			}
    			
    		}
    		System.out.println(dp[M]);
    		
    	}
    	
    }
}

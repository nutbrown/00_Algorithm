import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 남은 N일동안 최대한 많은 상담
    	int N = sc.nextInt();
    	
    	// T기간 상담을 하면 P금액을 받을 수 있다
    	int[][] arr = new int[N + 1][2];
    	for(int i = 1; i <= N; i++) {
    		arr[i][0] = sc.nextInt();
    		arr[i][1] = sc.nextInt();
    	}
    	
    	// i일에 벌 수 있는 돈을 저장한다
    	int[] dp = new int[N + 1];
    	for(int i = 1; i <= N; i++) {
    		// 1일에 3일 걸리고 10을 받는 일을 한다면 -> 0 0 10
    		// 2일에 5일 걸리고 20을 받는 일을 한다면 -> 0 0 10 0 0 20
    		// 3일에 1일 걸리고 10을 받는 일을 한다면 -> 0 0 10 0 0 20
    		//	전날인 2일에서 +10이 된다. 전날 2일은 0이니까 0 + 10이어서 10이다
    		// 4일에 1일 걸리고 10을 받는 일을 한다면 -> 0 0 10 30 0 20
    		// 5일에 2일 걸리고 15를 받는 일을 한다면 -> 0 0 10 30 30 [20/45]
    		// 	전날에 30을 번 걸 그대로 갖고 있는 게 5일 안에서는 최대치다
    		//	6일 기준으로는 2일부터 5일동안 일해서 20을 버는 거랑
    		// 	4일까지 30을 벌고 5일부터 2일동안 15를 일해서 45를 버는 거랑 비교한다
    		
    		// 오늘 일을 한다면 t일 뒤에 총 버는 돈
    		int p1 = dp[i - 1] + arr[i][1];

    		// t일 뒤에 일을 할 수 있다면
    		if(i + arr[i][0] - 1 <= N) {
    			// 오늘 일 들어오기 전에 t일 뒤에 총 버는 돈
    			int p2 = dp[i + arr[i][0] - 1];

    			// 이 둘 중에서 최댓값으로 갱신
    			dp[i + arr[i][0] - 1] = Math.max(p1, p2);
    		}
    		
    		
    		// 현재 오늘 dp에는 과거에 일해서 버는 돈이 들어있다 (0일 수도)
    		// 그냥 오늘 일 안하고 어제일 이어받는 거랑 비교해서 최댓값 
    		dp[i] = Math.max(dp[i - 1], dp[i]);
    		
    	}
    	
    	System.out.println(dp[N]);
    }
}

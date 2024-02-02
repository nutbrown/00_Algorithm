import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// A B C D E .... Z
    	// 1 2 3 4 5 .... 26
    	String str = sc.next();
    	char[] nums = str.toCharArray();
    	
    	// 똑같은 방식을 큐(메모리초과)에서 dp로 바꿔보자
    	// idx에 도달했을 때 경우의 수를 저장한다
    	int[] dp = new int[nums.length + 1];
    	
    	// 첫 자리 수 입력
        // 인덱스가 조금 헷갈리는데 나중에 보자
    	if(nums[0] != '0') dp[0] = 1;
    	
    	// 입력된 숫자에서 어느 인덱스인지
    	for(int i = 0; i < nums.length; i++) {
    		// i인덱스인 경우
    		
    		// 뒤에 두 자리가 남았으면
    		if(nums.length - i >= 2) {
    			// 1로 시작하는 두 자리를 빼거나
    			if(nums[i] == '1') {
    				// dp[i + 2]의 경우의수가 있을텐데
    				// dp[i]에서 2자리를 없애서 가는 것도 추가해준다
    				dp[i + 2] += dp[i] % 1000000;

    				// 2로 시작하는 두 자리를 빼거나
    			} else if(nums[i] == '2' && nums[i + 1] <= '6') {
    				dp[i + 2] += dp[i] % 1000000;
    			}
    		}
    		
    		// 시작이 0이 아니면 한 자리를 빼거나
    		if(nums[i] != '0') {
    			// dp[i + 1] 경우의수에 dp[i]에서 1자리를 없애서 가는 것도 추가해준다
    			dp[i + 1] += dp[i] % 1000000;
    		}
    	}
    	
    	System.out.println(dp[nums.length] % 1000000);
    }
}

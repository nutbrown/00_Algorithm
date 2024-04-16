import java.util.Arrays;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// M이상 N이하의 소수를 모두 출력
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		// 소수인지 아닌지
		boolean[] isprime = new boolean[N + 1];

		// 다 소수라고 두고, 소수가 아닌 걸 거른다 -> 에라토스테네스의 체
		Arrays.fill(isprime, true);
		
		// 1은 소수가 아니다
		isprime[1] = false;
		
		// 2부터 루트 N 이하로 나눠지지 않으면 소수다 
		for(int i = 2; i <= Math.sqrt(N); i++) {
			
			// 이미 소수가 아닌 건 배수를 볼 필요 없다
			if(!isprime[i]) continue;
			
			// i의 배수들 i*j를 소수에서 없앤다
			// 이때 i보다 작은 j들은 이전에 처리가 되니까 할 필요 없다
			for(int j = i; i * j <= N; j++) {
				
				// 배수들을 소수 아님 처리한다
				isprime[i * j] = false;
			}
		}
		
		// 소수 출력
		for(int i = M; i <= N; i++) {
			if(isprime[i]) System.out.println(i);
		}
		
	}
}

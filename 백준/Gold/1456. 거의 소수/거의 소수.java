import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 거의 소수 : 어떤 수가 소수의 N제곱이면
		// A 이상이고 B 이하인 거의 소수 개수 출력
		long A = sc.nextLong();
		long B = sc.nextLong();
		
		// 소수 구하기
		// 1. 모든 수가 소수다
		// 2. 1은 소수가 아니다
		int limit = (int)Math.sqrt(B) + 1;
		boolean[] prime = new boolean[limit + 1];
		Arrays.fill(prime, true);
		for(int i = 2; i <= (int)Math.sqrt(limit + 1); i++) {
			if(prime[i]) {
				for(int j = i; j * i <= limit; j++) {
					prime[i * j] = false;
				}
			}
		}
		
		
		// 거의 소수 구하기
		int cnt = 0;
		for(int i = 2; i <= limit; i++) {
			if(prime[i]) {
				// 거듭제곱했을 때 B 이하인 것까지만
				for(int j = 2; Math.pow(i, j) <= B; j++) {
					if(Math.pow(i, j) >= A && Math.pow(i, j) <= B) cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}
}

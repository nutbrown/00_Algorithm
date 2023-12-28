import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 매우 큰 소수의 곱으로 만들어진 수가 암호 키
		// 1,000,000=10^6보다 큰 소수이면 매우 큰 소수
		// 숫자 S의 모든 소인수가 10^6보다 크다면 적절한 암호 키 
		
		// 수의 개수 N
		int N = sc.nextInt();
		for(int t = 0; t < N; t++) {
			// 주어진 수 S : 범위 10^12 이상 10^18 이하
			long S = sc.nextLong();
			
			// 결과
			String ans = "YES";

			// 2부터 소인수분해를 해보는데
			// 소인수가 10^6보다 작으면 NO
			for(int i = 2; i <= 1000000; i++) {
				if(S % i == 0) {
					ans = "NO";
					break;
				}
			}
			
			// 10^6보다 작은 수로 나눠지지 않으면 YES
			System.out.println(ans);
		}
	}
}
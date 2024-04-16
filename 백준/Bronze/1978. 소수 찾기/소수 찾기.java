import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 1000 이하의 자연수 N개
		int N = sc.nextInt();
		
		// 중에서 소수의 개수
		int cnt = 0;
		
		for(int n = 0; n < N; n++) {
			int num = sc.nextInt();
			
			// 1은 소수가 아니다
			if(num == 1) continue;
			
			// 소수인지 아닌지
			boolean isPrime = true;
			
			for(int i = 2; i <= Math.sqrt(num); i++) {

				// 나눠진다면 소수가 아니다
				if(num % i == 0) {
					isPrime = false;
					break;
				}
			}
			
			if(isPrime) cnt++;
		}
		
		System.out.println(cnt);
		
	}	
}
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long a = sc.nextLong();
		long b = sc.nextLong();
		
		// a에서 b까지의 합
		System.out.println(func(b) - func(a - 1));
	}
	
	static long func(long n) {

		// 1 2 3 4 5 6 7 8 9	- 맨 끝 숫자가 홀수면 나누기2 + 1 만큼 2^0을 더함
		//   1   2   3   4		- 맨 끝 숫자가 짝수면 나누기2 만큼 2^1을 더함
		//       1       2		- 맨 끝 숫자가 짝수면 나누기2 만큼 2^2를 더함
		//               1		- 1이 남을 때까지 더해줌
		
		// 합을 구함
		long sum = 0;
		
		// 곱해지는 수 (Math.pow 안 쓰고, 처음에는 2^0으로 시작)
		long math = 1;

		// 맨 끝 숫자가 1일 때까지 반복
		while(n > 0) {
			
			// 맨 끝 숫자가 짝수라면
			if(n % 2 == 0) {
				sum += n / 2 * math;
			}
			// 맨 끝 숫자가 홀수라면
			else {
				sum += (n / 2 + 1) * math;
			}
			
			// 곱하는 2^0 지수를 1 증가시킴
			math *= 2;
			// 맨 끝 숫자는 /2
			n /= 2;
			
		}
		
		return sum;
	}
}

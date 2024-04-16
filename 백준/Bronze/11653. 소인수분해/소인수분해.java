import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 숫자 N을 소인수분해(소수들의 곱)
		int N = sc.nextInt();

		// 2부터 루트 N까지 나눈다
		// 작은 수부터 나누니까 소수로만 나눌 수 있다
		for(int i = 2; i <= Math.sqrt(N); i++) {
			
			// 나눠질 때까지 나눈다
			while(N % i == 0) {
				N /= i;
				System.out.println(i);
			}
		}
		
		// 소인수분해여서 소수로만 나눈다
		// 소수로 다 나눠지면 1이 된다
		// 그런데 이미 N이 소수면 다른 소수로 나눠지지 않으면, 그대로 출력한다
		// 처음부터 N이 1인 경우 아무것도 출력하지 않는
		if(N != 1) {
			System.out.println(N);
		}
		
	}
}

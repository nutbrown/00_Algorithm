import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long N = sc.nextInt();
		int A = sc.nextInt();
		
		// N * N-1 * N-2 * ... 1 안에
		// 소수인 A가 몇 개 들어있는지 구하기
		// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20이라고 하면
		//   2   2   2   2    2     2     2     2     2     2
		//       2       2          2           2           2
		//               2                      2
		//                                      2
		// 이런 방식으로 소수가 들어있다
		// 20을 2로 나눴을 때 몇 개 +
		// 20을 4로 나눴을 때 몇 개 +
		// 20을 8로 나눴을 때 몇 개 + 
		// 20을 16로 나눴을 때 몇 개 = 를 더하면 된다

		if (N == 0 || N == 1) {
            System.out.println(0);
            return;
		}
		
		int cnt = 0;
		int i = 1;
		while(Math.pow(A, i) <= N) {
			cnt += N / (long)Math.pow(A, i);
			i++;
		}

		// 이건 다른 방식
		// 20보다 작은 2의 배수 10개
		// 다시 10개를 2로 나누면 5개
		// 다시 5개를 2로 나누면 2개
		// 다시 2개를 2로 나누면 1개
//		int cnt = 0;
//		int x = N;
//		while(x > 0) {
//			x /= A;
//			cnt += x;
//		}
		
		System.out.println(cnt);
		
	}
}

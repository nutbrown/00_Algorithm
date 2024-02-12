import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			
			// 수의 개수 N
			int N = sc.nextInt();
			int[] arr = new int[N];
			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			// 가능한 모든 쌍의 GCD의 합
			long sum = 0;
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {
					
					int a = Math.max(arr[i], arr[j]);
					int b = Math.min(arr[i], arr[j]);
					
					// 유클리드 호제법 : 두 수의 최대공약수는 두 수의 차의 최대공약수와 같다
					while(a % b != 0) {
						int temp = b;
						b = a % b;
						a = temp;
					}

					sum += b;
				}
			}
			
			System.out.println(sum);
		}
	}
}
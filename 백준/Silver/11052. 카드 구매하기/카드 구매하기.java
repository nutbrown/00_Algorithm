import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 민규가 구매하려고 하는 카드 개수 N
		int N = sc.nextInt();
		
		// 1~N개 구매 가격
		int[] arr = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// i개를 구매했을 때 최대 가격
		int[] dp = new int[N + 1];
		
		// 0개 구매할 대 비용은 0
		dp[0] = 0;
		
		// 시간복잡도 N^2인데 N범위 1,000
		for(int i = 1; i <= N; i++) {

			// 최대 가격
			int max = 0;
			
			// -1에서 1개 더하기
			// -i개에서 i개 더하기
			for(int j = 1; j <= i; j++) {
				max = Math.max(max, dp[i - j] + arr[j]);
			}
		
			// 최대 가격 입력
			dp[i] = max;
		}
		
		System.out.println(dp[N]);

		
		
		// 가성비가 안 좋은 순서대로 - 그리디로 풀면
		// 5 : 1 9 18 25 1
		// 답이 9 + 18인데 25를 먼저 해버려서 25 + 1이 나온다
		
//		// [1~N 개를 구매했을 때][가격][1개당 얼마]
//		double[][] arr = new double[N + 1][3];
//		for(int i = 0; i < N; i++) {
//			arr[i][0] = (double)(i + 1);
//			arr[i][1] = sc.nextDouble();
//			arr[i][2] = arr[i][1] / arr[i][0];
//		}
//		
//		// 1개당 가격이 제일 비싼 순으로 정렬
//		Arrays.sort(arr, new Comparator<>() {
//
//			@Override
//			public int compare(double[] o1, double[] o2) {
//				// https://help.acmicpc.net/judge/rte/IllegalArgumentException
//				// https://www.acmicpc.net/board/view/77398
//				// Comparator에 대해 모든 x, y가 sgn(compare(x, y)) == -sgn(compare(y, x))를
//				// 성립하지 않으면 IllegalArgument Exception 이 발생한다고 하네요.
//				// compareTo 메서드에서 동일한 값을 비교할 경우 0을 반환하도록 수정하면 통과됩니다.
//
//				if(o2[2] > o1[2]) return 1;
//				else if(o2[2] < o1[2]) return -1;
//				else return 0;
//			}
//		});
		
		
	}
}
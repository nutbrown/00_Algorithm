import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// A의 부 배열의 합에 B의 부 배열의 합을 더해서
		// T가 되는 모든 부배열의 쌍의 개수 구하기
		int T = sc.nextInt();
		
		// 배열 A와 누적합
		int n = sc.nextInt();
		int[] A = new int[n + 1];
		int[] prefixA = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			A[i] = sc.nextInt();
			prefixA[i] = prefixA[i - 1] + A[i];
		}

		// 배열 B와 누적합
		int m = sc.nextInt();
		int[] B = new int[m + 1];
		int[] prefixB = new int[m + 1];
		for(int i = 1; i <= m; i++) {
			B[i] = sc.nextInt();
			prefixB[i] = prefixB[i - 1] + B[i];
		}
		
		// A가 	(1, 1) ~ (1, n)까지
		// 		(2, 2) ~ (2, n)까지
		// 		...		 (n, n)까지
		// 대충 1부터 n까지 2개를 고르는 경우의 수
		// n이 1000이라면 1000*999/2 = 499500개
		
		// 배열 원소는 1,000,000 보다 작고
		// 배열 개수는 1,000 이하면
		// 다 더했을 때의 최댓값은 1,000,000,000 -> int
		
		// 배열 A의 부배열의 합 저장
		ArrayList<Integer> subsumA = new ArrayList<>();
		for(int i = 1; i <= n; i++) {
			for(int j = i; j <= n; j++) {
				// A[i]부터 A[j]까지 부배열의 합
				int subsum = prefixA[j] - prefixA[i - 1];
				// 중복되더라도 다른 부배열이니까 넣어준다
				subsumA.add(subsum);
			}
		}
		Collections.sort(subsumA);
		
		// 배열 B의 부배열의 합 저장
		ArrayList<Integer> subsumB = new ArrayList<>();
		for(int i = 1; i <= m; i++) {
			for(int j = i; j <= m; j++) {
				// B[i]부터 B[j]까지 부배열의 합
				int subsum = prefixB[j] - prefixB[i - 1];
				// 중복되더라도 다른 부배열이니까 넣어준다
				subsumB.add(subsum);
			}
		}
		Collections.sort(subsumB);
		
		// 배열 A의 부분합을 기준으로
		// 더해서 T가 되는 배열 B의 부분합이 있는지 이분탐색
		// 500,000 * 500,000 = 250,000,000,000 -> long
		long cnt = 0;
		for(int a : subsumA) {
			int target = T - a;
			
			int left = 0;
			int right = subsumB.size(); // -1로 하면 1개인 경우 안 된다
			
			// target이 여러 개일 경우 target의 왼쪽, 오른쪽을
			// lowerbound upperbound로 찾는다 (정리 필요)
			while(left < right) {
				
				int mid = (left + right) / 2;
				
				if(subsumB.get(mid) < target) {
					left = mid + 1;
				} else {
					right = mid;
				}
			}
			int lowerbound = right;

			left = 0;
			right = subsumB.size();
			while(left < right) {
				
				int mid = (left + right) / 2;
				
				if(subsumB.get(mid) <= target) {
					left = mid + 1;
				} else {
					right = mid;
				}
			}
			int upperbound = right;
			
			cnt += upperbound - lowerbound;
		}
		
		
		System.out.println(cnt);
	}
}

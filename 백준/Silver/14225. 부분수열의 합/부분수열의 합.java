import java.util.Scanner;

public class Main {
	static int N;
	static int[] arr;
	static boolean[] arrSum;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 수열 크기 N
		N = sc.nextInt();
		
		// 수열 S
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 수열 S의 부분 수열의 합으로 나올 수 없는 가장 작은 자연수
		// S는 100,000 이하의 수, N은 20 이하 -> 최대 2,000,000
		// 모든 조합을 골라서 수를 없앤다음에 제일 작은 거 찾기
		
		arrSum = new boolean[2000001];

		// 1개부터 N개까지 고를 수 있다
		for(int i = 1; i <= N; i++) {
			combination(0, 0, 0, i);
		}
		
		// 제일 작은 자연수 찾기
		int num = 1;
		while(true) {
			if(!arrSum[num]) break;
			num++;
		}
		
		System.out.println(num);
	}
	
	
	static void combination(int depth, int idx, int sum, int total) {
		
		if(depth == total) {
			
			// 부분수열의 합이 나왔다고 표시
			arrSum[sum] = true;
			return;
		}
		
		
		// idx가 N을 넘으면 안 된다
		if(idx >= N) return;
		
		// idx를 고르고
		combination(depth + 1, idx + 1, sum + arr[idx], total);
		
		// idx를 안 고르고
		combination(depth, idx + 1, sum, total);
		
	}
}

import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static int minDiff;
	static int[] chosen;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 재료 N개
		N = sc.nextInt();
		
		arr = new int[N][2];
		for(int i = 0; i < N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		
		// 신맛과 쓴맛의 차이가 적게 고르기
		minDiff = Integer.MAX_VALUE;
		chosen = new int[N];
		
		// 재료는 1개부터 N개까지 사용할 수 있다
		for(int i = 1; i <= N; i++) {
			combination(0, 0, i);
		}
		
		System.out.println(minDiff);
	}
	
	static void combination(int depth, int idx, int total) {
		
		if(depth == total) {
			// 신맛은 곱으로, 쓴맛은 합으로 합친다
			int sour = 1;
			int bitter = 0;
			for(int i = 0; i < total; i++) {
				sour *= arr[chosen[i]][0];
				bitter += arr[chosen[i]][1];
			}
			
			minDiff = Math.min(minDiff, Math.abs(sour - bitter));
			return;
		}
		
		// idx가 N을 넘으면 안 된다
		if(idx >= N) return;
		
		// idx를 고른 거 하나
		chosen[depth] = idx;
		combination(depth + 1, idx + 1, total);
		
		// idx를 안 고른 거 하나
		combination(depth, idx + 1, total);
	}
}

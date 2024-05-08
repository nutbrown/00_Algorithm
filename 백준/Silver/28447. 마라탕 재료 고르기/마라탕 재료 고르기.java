import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static int K;
	static int[][] arr;
	static int max;
	static ArrayList<Integer> chosen;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 마라탕 재료의 수 N
		N = sc.nextInt();
		
		// 고를 재료의 수 K
		K = sc.nextInt();
		
		// 재료들의 궁합 수열
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// 마라탕 맛의 최댓값 (0보다 작을 수도 있다)
		max = Integer.MIN_VALUE;

		// 재료를 뭘 골랐는지
		chosen = new ArrayList<>();
		
		// N개 중에서 K개 고르기
		combination(0, 0, 0);
		
		System.out.println(max);
 	}

	static void combination(int depth, int cnt, int sum) {
		
		// cnt가 K여서 다 골랐으면
		if(cnt == K) {
			// 최댓값 갱신
			max = Math.max(max, sum);
			return;
		}
		
		// depth가 N이여서 끝까지 갔으면 돌아가기
		if(depth == N) return;
		
		// depth의 재료를 넣기
		int add = 0;
		for(int n : chosen) {
			add += arr[depth][n];
		}
		chosen.add(depth);
		combination(depth + 1, cnt + 1, sum + add);
		
		// 원상복귀
		chosen.remove(chosen.size() - 1);
		
		// depth의 재료 안 넣기
		combination(depth + 1, cnt, sum);
		
	}
}

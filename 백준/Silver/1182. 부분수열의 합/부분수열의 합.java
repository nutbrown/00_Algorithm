import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int S;
	static int[] arr;
	static int cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 정수의 개수 N
		N = sc.nextInt();
		
		// 수열의 원소를 다 더한 값이 정수 S
		S = sc.nextInt();
		
		// 수열 입력
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 길이가 1인 조합부터, 길이가 N인 조합까지
		// 경우의 수 개수
		cnt = 0;
		
		for(int i = 1; i <= N; i++) {
			combination(i, 0, 0, 0);
		}
		
		System.out.println(cnt);
	}
	
	static void combination(int total, int depth, int idx, int sum) {
		
		// 개수가 다 채워졌다면
		if(depth == total) {
			if(sum == S) cnt++;
			return;
		}
		
		// idx가 N을 넘어갔으면 안 된다
		if(idx >= N) return;
		
		// 조합으로 (왜 자꾸 순열이랑 조합을 반대로 하지)
		// 해당 인덱스를 방문하는 거 하나
		combination(total, depth + 1, idx + 1, sum + arr[idx]);
				
		// 방문 안 하는 거 하나
		combination(total, depth, idx + 1, sum);
	}
}

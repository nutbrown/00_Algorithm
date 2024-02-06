import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[] arr;
	static int[] chosen;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 1부터 N까지 자연수 중에서 M개를 고른 수열
		// 사전 순으로 증가하는 순서대로 출력
		// ++ 중복가능 점점 커지는 비내림차순
		N = sc.nextInt();
		M = sc.nextInt();
		
		// 숫자 입력
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
				
		// 사전순이니까 정렬
		Arrays.sort(arr);
				
		// 고르는 숫자
		chosen = new int[M];
		sb = new StringBuilder();
		permutation(0, 0);

		System.out.println(sb);
		
	}
	
	// 중복 순열
	static void permutation(int depth, int idx) {
		
		// M개가 되면 출력
		if(depth == M) {
			for(int i = 0; i < M; i++) {
				sb.append(chosen[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = idx; i < N; i++) {
			
			// i번째 숫자를 고르고
			chosen[depth] = arr[i];
			
			// 다음 depth숫자 고르러 간다
			permutation(depth + 1, i);
		}
		
	}
}

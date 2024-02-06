import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[] chosen;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 주석은 이전 풀이가 나을듯하다
		// 1부터 N까지 자연수 중에서 중복없이 M개를 고른 수열
		// 사전 순으로 증가하는 순서대로 출력
		N = sc.nextInt();
		M = sc.nextInt();
		
		// 고르는 숫자
		chosen = new int[M];
		sb = new StringBuilder();
		combination(0, 1);

		System.out.println(sb);
		
	}
	
	static void combination(int depth, int idx) {
		
		// M개가 되면 출력
		if(depth == M) {
			for(int i = 0; i < M; i++) {
				sb.append(chosen[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// idx가 N을 넘으면 안 된다
		if(idx > N) return;
		
		// depth번째에 idx 숫자 고르기
		chosen[depth] = idx;
		combination(depth + 1, idx + 1);
		
		// depth에 idx숫자 고르는 거 다 하고 돌아왔으면
		// 생략해도 되지만 원상복귀
		chosen[depth] = 0;
		
		// depth번째에 idx 숫자 안 고르기
		combination(depth, idx + 1);
		
	}
}

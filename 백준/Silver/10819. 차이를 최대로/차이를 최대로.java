import java.util.Scanner;

public class Main {
	static int N;
	static int[] arr;
	static boolean[] visited;
	static int[] chosen;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		
		// 정수는 -100에서 100
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		// 완전탐색
		visited = new boolean[N];
		chosen = new int[N];
		max = 0;
		permutation(0, 0);
		
		System.out.println(max);
	}
	
	
	static void permutation(int depth, int sum) {
		
		if(depth == N) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			chosen[depth] = arr[i];
			if(depth != 0) permutation(depth + 1, sum + Math.abs(chosen[depth - 1] - chosen[depth]));
			else permutation(depth + 1, sum);
			visited[i] = false;
		}
	}
}
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static boolean[] visited;
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
		visited = new boolean[N + 1];
		chosen = new int[M];
		sb = new StringBuilder();
		permutation(0);

		System.out.println(sb);
		
	}
	
	static void permutation(int depth) {
		
		// M개가 되면 출력
		if(depth == M) {
			for(int i = 0; i < M; i++) {
				sb.append(chosen[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			
			// 방문하지 않았다면
			if(visited[i]) continue;
			
			// 고르고
			chosen[depth] = i;
			visited[i] = true;
			
			// 다음거 고르고
			permutation(depth + 1);
			
			// 돌아오면 원상복귀하고
			chosen[depth] = 0;
			visited[i] = false;
			
			
			// 구구절절 예시 (4 2) :
			// 1을 고르고 다시 perm들어가면 1은 visited니까 
			// for문에서 다음 숫자 2를 고른다
			// 2개가 골라지면 return해서 함수를 나오는데
			// 2 고른 걸 visited까지 취소하고 다음 for문을 돈다
			// 3을 고르지 않았으니까 3을 고르고 visited하는 방식 반복
			// 1로 시작하는 2개를 (1 4)까지 다 고르고 나와서 원상복귀를 하면
			// for문이 끝나서 함수 끝에 도달한다
			// return 되어서 처음 1을 골랐던 perm 함수를 return한다
			// 그러면 처음에 첫번째 숫자로 1 고른 걸 다시 원상복귀하고
			// for문에 다음 첫번째 숫자로 2를 고르고 들어간다
			// 1 고른 거 visited를 원상복귀 했으니까
			// 첫 번째 숫자로 2를 고르고 perm 함수를 들어가서 두 번째 숫자를 고를 때
			// visited가 아닌 1부터 다시 차례대로 고른다 - 반복
		}
		
	}
}

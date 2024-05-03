import java.util.Scanner;

public class Main {
	static int N;
	static int K;
	static int[] A;
	static int[][] R;
	static int[][] M;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		// ***변수 헷갈려 다음에 이러면 변수명 바꾸자
		// 캔의 종류 N가지
		N = sc.nextInt();
		
		// K일동안
		K = sc.nextInt();
		
		// 집사가 가진 캔의 수
		A = new int[N];
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		
		// i날 j번째 캔을 먹었을 때 랑이의 만족도
		R = new int[K][N];
		for(int i = 0; i < K; i++) {
			for(int j = 0; j < N; j++) {
				R[i][j] = sc.nextInt();
			}
		}
		
		// i날 j번째 캔을 먹었을 때 메리의 만족도
		M = new int[K][N];
		for(int i = 0; i < K; i++) {
			for(int j = 0; j < N; j++) {
				M[i][j] = sc.nextInt();
			}
		}
		
		// 랑이와 메리의 만족도의 합의 최댓값
		max = 0;
		
		// K일동안 랑이메리가 N개의 캔을 중복해서 먹어도 되니까
		// visited는 할 필요가 없다
		dfs(0, 0);
		
		System.out.println(max);
		
	}
	
	
	// 랑이 먼저 정하고, 메리를 정한다
	static void dfs(int depth, int sum) {
		
		// 랑이랑 메리 다 정해서 2K가 되었다면
		if(depth == 2 * K) {
			max = Math.max(max, sum);
			return;
		}
		
		
		// depth가 K보다 작으면 랑이 캔 정하기
		if(depth < K) {
			for(int i = 0; i < N; i++) {
				
				// 캔이 남아있으면 그 캔을 먹자
				if(A[i] == 0) continue;
					
				// 캔 개수 감소
				A[i]--;
					
				// 랑이 만족도 더하기
				dfs(depth + 1, sum + R[depth][i]);
					
				// 랑이가 depth날에 N캔 먹은 거 하고 왔으면 원상복귀
				A[i]++;
			}
		}
		
		// depth가 K이상이면 메리 캔 정하기
		else {
			for(int i = 0; i < N; i++) {
				if(A[i] == 0) continue;
				
				A[i]--;
				dfs(depth + 1, sum + M[depth - K][i]);
				A[i]++;
			}
		}
		
	}
}

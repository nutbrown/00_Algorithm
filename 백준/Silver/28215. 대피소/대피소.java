import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int K;
	static int[][] arr;
	static int[][] dist;
	static int[] chosen;
	static int min;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 2차원 평면에 N개의 집, K개의 집에 대피소를 설치한다
		N = sc.nextInt();
		K = sc.nextInt();
		
		// 각 집 까지의 거리를 저장하고
		// 50개 중에서 3개를 골랐을 때, 거리의 최댓값을 쭉 찾으면서 - 최솟값 찾기
		arr = new int[N][2];
		dist = new int[N][N];
		
		// N개의 집을 받으면서 거리 갱신
		for(int i = 0; i < N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
			
			// 앞에서부터 i-1까지의 거리 찾고 저장
			for(int j = 0; j < i; j++) {
				int d = Math.abs(arr[i][0] - arr[j][0]) + Math.abs(arr[i][1] - arr[j][1]);
				dist[i][j] = d;
				dist[j][i] = d;
			}
		}
		
		// 가장 긴 거리가 최소로 되도록, 그 긴 거리 구하기
		// N개 중 K개(최대 3) 조합으로 고르고 로직 수행
		chosen = new int[K];
		min = Integer.MAX_VALUE;
		
		combination(0, 0);
		System.out.println(min);
		
		
	}
	
	
	// 몇 개 골랐고, 어디까지 봤는지
	static void combination(int depth, int idx) {
		
		// K개를 다 골랐으면
		if(depth == K) {
			// 고른 걸 바탕으로, 대피소-집 거리 중 제일 큰 거 찾기
			int maxdist = 0;
			
			// 중복을 피하려고 0부터 자기직전까지 본다
			// 가 아니라, 고른 대피소와의 차이를 봐야지
			for(int i = 0; i < N; i++) {

				// 여기서는 i집과 j대피소와의 최솟값을 구해야한다
				// 제일 가까운 대피소를 찾아야하니까
				int mindist = Integer.MAX_VALUE;
				for(int j = 0; j < K; j++) {
					
					mindist = Math.min(mindist, dist[i][chosen[j]]);
				}
				
				// 이렇게 제일 가까운 대피소와의 거리의 최댓값을 구한다
				maxdist = Math.max(maxdist, mindist);
			}
			
			// 이때 최댓값이 작도록 해야한다, 갱신
			min = Math.min(min, maxdist);

			return;
		}
		
		// idx를 끝까지 봤으면 돌아가기
		if(idx == N) return;
		
		// depth에서 고르고 넘어가기
		chosen[depth] = idx;
		combination(depth + 1, idx + 1);
	
		// depth에서 안 고르고 넘어가기
		combination(depth, idx + 1);
		
	}
	
	
}
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// NxN 행렬 1부터 10까지의 수
		int N = sc.nextInt();
		
		// 1부터 10까지 수의 개수 누적합
		// 4bite 300*300*10 = 1,200,000바이트 = 1,200KB = 1MB
		int[][][] prefixSum = new int[N + 1][N + 1][11];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				
				// 해당 숫자 입력
				int num = sc.nextInt();
				prefixSum[i][j][num] = 1;
				
				// 숫자 1부터 10까지 누적합
				for(int k = 1; k <= 10; k++) {
					prefixSum[i][j][k] += prefixSum[i - 1][j][k]
								+ (prefixSum[i][j - 1][k] - prefixSum[i - 1][j - 1][k]);
				}
			}
		}
		

		// 쿼리 Q개
		int Q = sc.nextInt();
		
		// (x1, y1)부터 (x2, y2)까지 서로 다른 수의 개수
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Q; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			int cnt = 0;
			for(int j = 1; j <= 10; j++) {
				
				// 1부터 10까지 숫자개수가 1이상이면 cnt증가
				// 예시를 보니 x가 행, y가 열
				if(prefixSum[x2][y2][j] - prefixSum[x1 - 1][y2][j]
						- prefixSum[x2][y1 - 1][j] + prefixSum[x1 - 1][y1 - 1][j] > 0) cnt++;
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
		
	}
}
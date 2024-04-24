import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 별똥별이 떨어지는 구역의 가로 N, 세로 M
		int N = sc.nextInt();
		int M = sc.nextInt();

		// 트램펄린의 한 변의 길이 L, 별똥별의 수 K
		int L = sc.nextInt();
		int K = sc.nextInt();
		
		
		// 구역 가로세로가 500,000로 매우 큰 반면
		// 별똥별의 개수가 100개 이하니까 별똥별 위치를 기준으로 완전탐색
		ArrayList<int[]> stars = new ArrayList<>();

		// 지도에서 보는 게 아니라 100개 별을 기준으로 탐색한다 
		//int[][] map = new int[M + 1][N + 1];
		
		// 먼저 별똥멸 입력받고
		for(int i = 0; i < K; i++) {

			// 좌표 범위가 0~N, 0~M
			// 아 가로세로여서 열이 먼저 주어진다
			int c = sc.nextInt();
			int r = sc.nextInt();
			
			// 별똥별 저장
			stars.add(new int[] {r, c});
		}
		
//		for(int i = 0; i <= M; i++) {
//			for(int j = 0; j <= N; j++) {
//				System.out.print(map[i][j] + " ");
//			}System.out.println();
//		}System.out.println();
		
		
 		// 지구에 부딪히는 별똥별의 개수 최소
		// (r, c)를 기준으로 트램펄린이 받을 수 있는 별똥별의 개수가 최대여야한다
		int max = 0;

		
		// 별똥별 있는 곳마다 트램펄린을 놓아본다
		// 별이 좌상단, 우상단, 우하단, 좌하단일 때 -> 이렇게하면 다이아몬드로 분포되어있는 걸 못 잡는다
		for(int[] s1 : stars) {
			for(int[] s2 : stars) {
				
				// 별 두 개가 모서리에 걸치는 왼쪽 상단 좌표
				int r = Math.min(s1[0], s2[0]);
				int c = Math.min(s1[1], s2[1]);
				
				// 짤려도 되니까 범위지정을 할 필요가 없나
				// if(r + L > M || c + L > N) continue;
				
				// 트램펄린 너비도 최대 100,000니까 다 탐색하면 안 되지***
				// 지도에서 보는 게 아니라 100개 별을 기준으로 탐색한다
				int cnt = 0;
				for(int[] star : stars) {
					if(star[0] >= r && star[0] <= r + L && star[1] >= c && star[1] <= c + L)
						cnt++;
				}
				
				max = Math.max(max, cnt);
			}
		}
		
		System.out.println(stars.size() - max);

		
		
//		for(int i = 1; i <= M + 1; i++) {
//			for(int j = 1; j <= N + 1; j++) {
//
//				// 누적합 구하기 : (1, 1) 부터 (r, c)까지의 합을 구한다
//				// 나중에 누적합을 구하는거랑 반대로 생각하면 된다
//				prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1]
//								- prefix[i - 1][j - 1]
//								+ prefix[i][j];
//				
//				
//				// 가로세로 L인 트래펄린이 갖는 별똥별 개수 구하기
//				if(i - L >= 1 && j - L >= 1) {
//					// i-L행부터 i행까지, j-L열부터 j열까지의 합
//					// (i, j) 누적합에서
//					// (i-L-1, j) 누적합을 빼고
//					// (i, j-L-1) 누적합을 빼고
//					// (i-L-1, j-L-1) 누적합을 더해준다
//					
//					int cnt = prefix[i][j]
//								- prefix[i - L - 1][j] - prefix[i][j - L - 1]
//								+ prefix[i - L - 1][j - L - 1];	
//					
//					// 최댓값 갱신
//					max = Math.max(max, cnt);
//				}
//			}
//		}
		
	}
}

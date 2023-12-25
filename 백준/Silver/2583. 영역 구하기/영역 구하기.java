import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// M행 N열 지도, K개 직사각형
		int M = sc.nextInt();
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		// 지도 색칠
		int[][] map = new int[M][N];
		for(int i = 0; i < K; i++) {
			int c1 = sc.nextInt();
			int r1 = sc.nextInt();
			int c2 = sc.nextInt();
			int r2 = sc.nextInt();
			
			for(int j = r1; j < r2; j++) {
				for(int k = c1; k < c2; k++) {
					map[j][k] = 1;
				}
			}
		}
		
//		for(int i = 0; i < M; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		// 완전탐색으로 구역 찾기
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		// 구역 넓이 저장
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				// 아직 체크하지 않았고 빈칸이라면
				if(map[i][j] == 0) {
					Queue<int[]> q = new LinkedList<>();
					int cnt = 1;
					map[i][j] = 2;
					q.add(new int[] {i, j});
					
					while(!q.isEmpty()) {
						int cr = q.peek()[0];
						int cc = q.poll()[1];
						
						for(int k = 0; k < 4; k++) {
							int nr = cr + dr[k];
							int nc = cc + dc[k];
							
							if(nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
							if(map[nr][nc] == 0) {
								cnt++;
								map[nr][nc] = 2;
								q.add(new int[] {nr, nc});
							}
						}
					}
					list.add(cnt);
				}
			}
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		for(int num : list) {
			System.out.print(num + " ");
		}
	}
}
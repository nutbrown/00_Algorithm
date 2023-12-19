import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 지도 크기 N
		int N = Integer.parseInt(sc.next());
		
		// 총 집의 수
		int total = 0;
		
		// 지도 입력
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			// 25자리니까 long으로도 안 됨
			String str = sc.next();
			for(int j = 0; j < N; j++) {
				map[i][j] = Character.getNumericValue(str.charAt(j));
				if(map[i][j] == 1) total++;
			}
		}
		
		// 단지수 넣는 리스트
		ArrayList<Integer> list = new ArrayList<>();
		
		// 좌우상하 확인
		int[] dr = {0, 0, -1, 1};
		int[] dc = {-1, 1, 0, 0};
		
		// 전체 탐색
		loop:
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 해당 좌표가 아직 확인하지 않은 집이 있어서 "1"이라면
				if(map[i][j] == 1) {
					
					// 방문처리를 하고, 총 집의 수 줄이고, BFS를 넣어서 단지를 찾아봄
					map[i][j] = 0;
					total--;
					Queue<int[]> q = new LinkedList<>();
					q.add(new int[] {i, j});
					
					// 단지내 집의 수
					int cnt = 1;
					
					while(!q.isEmpty()) {
						int currR = q.peek()[0];
						int currC = q.poll()[1];
						
						// 인접 좌우상화 확인
						for(int k = 0; k < 4; k++) {
							int nextR = currR + dr[k];
							int nextC = currC + dc[k];
							
							// 범위를 벗어났으면 안 됨 
							if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= N) continue;
							
							// 인접이 집이라면 
							if(map[nextR][nextC] == 1) {
								// 방문하고, 단지 수 올려주고
								map[nextR][nextC] = 0;
								cnt++;
								
								// 총 집의 수 줄여주는데, 다 찾았으면 아웃
								if(--total == 0) {
									list.add(cnt);
									break loop;
								}
								
								// 다음 인접 찾아봄
								q.add(new int[] {nextR, nextC});
							}
						}
					}
					
					// 단지내 집의 수 리스트에 추가
					list.add(cnt);
				}
			}
		}
		
		
		// 단지내 집의 수를 오름차순으로 정렬하고 출력
		Collections.sort(list);
		
		System.out.println(list.size());
		for(int cnt : list) {
			System.out.println(cnt);
		}
	}
}
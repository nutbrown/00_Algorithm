import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[][] domino;
	static int[][] map;
	static boolean[][] visited;
	static int cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 도미노 28가지
		// 0, 0~6
		// 1, 1~6
		// ... 6, 6
		domino = new int[7][7];
		
		// 격자의 상태
		map = new int[8][7];
		for(int i = 0; i < 8; i++) {
			String in = sc.next();
			for(int j = 0; j < 7; j++) {
				map[i][j] = Character.getNumericValue(in.charAt(j));
			}
		}
		
		// 격자 채웠는지
		visited = new boolean[8][7];
		
		// 경우의 수
		cnt = 0;
		
		// 그냥 전체 완전탐색하기
		dfs(0);
		
		System.out.println(cnt);
		
	}
	
	
	static void dfs(int total) {
		
		// 모든칸을 다 했으면
		if(total == 56) {
			cnt++;
			return;
		}
		
		// 왼쪽 위부터 한 칸씩 확인
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 7; j++) {
				if(visited[i][j]) continue;
				
				// 지금 확인하는 칸 오른쪽이랑 아래 확인
				int cur = map[i][j];
				
				// 오른쪽도 아직 방문 안 했으면
				if(j + 1 < 7 && !visited[i][j + 1]) {
					int right = map[i][j + 1];
					
					// 도미노가 있으면
					if(domino[cur][right] == 0) {
						// 사용하기
						domino[cur][right] = 1;
						domino[right][cur] = 1;
						
						visited[i][j] = true;
						visited[i][j + 1] = true;
						
						// 다음꺼 진행
						dfs(total + 2);

						// 원상복귀
						domino[cur][right] = 0;
						domino[right][cur] = 0;
						
						visited[i][j] = false;
						visited[i][j + 1] = false;
					}
				}
				
				if(i + 1 < 8) {
					int down = map[i + 1][j];
					
					// 도미노가 있으면
					if(domino[cur][down] == 0) {
						// 사용하기
						domino[cur][down] = 1;
						domino[down][cur] = 1;
						
						visited[i][j] = true;
						visited[i + 1][j] = true;
						
						// 다음꺼 진행
						dfs(total + 2);

						// 원상복귀
						domino[cur][down] = 0;
						domino[down][cur] = 0;
						
						visited[i][j] = false;
						visited[i + 1][j] = false;
					}
				}
				
				// 가로세로 둘다 놓을 곳이 없으면 중단해야한다
				return;
				
			}
		}
		
		
	}
}

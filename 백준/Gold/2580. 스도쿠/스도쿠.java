import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static boolean[][] row;
	static boolean[][] col;
	static boolean[][] zone;
	static ArrayList<int[]> list;
	static int[][] map;
	static boolean isFinish;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 가로 숫자 기록
		row = new boolean[10][10];
		
		// 세로 숫자 기록
		col = new boolean[10][10];
		
		// 구역 숫자 기록
		// 행 - 1을 3으로 나눈거 + 1이 1이면 첫째줄
		// 행 - 1을 3으로 나눈거 + 1이 2이면 둘째줄 ...열도 마찬가지
		// (1, 1) (1, 2) (1, 3) 3*(r-1)+c
		// (2, 1) (2, 2) (2, 3)
		// (3, 1) (3, 2) (3, 3)
		zone = new boolean[10][10];

		// 채워야 하는 빈칸 좌표
		list = new ArrayList<>();
		
		// 스도쿠
		map = new int[10][10];
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9; j++) {
				map[i][j] = sc.nextInt();
				
				// 빈칸 저장
				if(map[i][j] == 0) {
					list.add(new int[] {i, j});
				}

				// 0이 아니라면
				else {
					// 가로 숫자 기록
					row[i][map[i][j]] = true;
					// 세로 숫자 기록
					col[j][map[i][j]] = true;
					// 구역 숫자 기록
					// 간단하게 하면 (i - 1) / 3 * 3 + (j - 1) / 3 + 1
					// 나누기하면 버림을 하니까 /3*3을 합하면 안 된다
					zone[3 * ((i - 1) / 3 + 1 - 1) + (j - 1) / 3 + 1][map[i][j]] = true;
				}
			}
		}
		
		// 모든 조합을 하는 bfs말고 하나씩 내려가보는 dfs로 해보자
		isFinish = false;
		dfs(0);
		
		// 결과를 만들어서 왔으니까 출력
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	
	static void dfs(int idx) {
	
		// idx가 빈칸좌표 개수에 도달하면
		if(idx == list.size()) {
			isFinish = true;
			return;
		}
		
		// 현재 채우는 빈칸
		int r = list.get(idx)[0];
		int c = list.get(idx)[1];
		
		
		// 모든 숫자를 넣어본다
		for(int i = 1; i <= 9; i++) {
			
			// 3개 조건을 다 만족하면 넣어준다
			if(!row[r][i] && !col[c][i] && !zone[(r - 1) / 3 * 3 + (c - 1) / 3 + 1][i]) {
				
				// (r, c)에 i넣어준다
				map[r][c] = i;
				row[r][i] = true;
				col[c][i] = true;
				zone[(r - 1) / 3 * 3 + (c - 1) / 3 + 1][i] = true;
				
				// 다음 찾으러 가기
				dfs(idx + 1);
				
				// 만약에 성공했으면 돌아가
				if(isFinish) return;
				
				// 만약에 안 돼서 돌아왔으면 원상복귀
				map[r][c] = 0;
				row[r][i] = false;
				col[c][i] = false;
				zone[(r - 1) / 3 * 3 + (c - 1) / 3 + 1][i] = false;
			}
		}
	}
	
	
}

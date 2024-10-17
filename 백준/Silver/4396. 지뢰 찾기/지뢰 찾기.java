import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// nxn 격자 위에서 지뢰찾기
		int N = Integer.parseInt(sc.next());
		
		// 지뢰가 있는 칸 입력
		char[][] bomb = new char[N][N];
		for(int i = 0; i < N; i++) {
			String in = sc.next();
			for(int j = 0; j < N; j++) {
				bomb[i][j] = in.charAt(j);
			}
		}

		// 주변 숫자 입력한 값
		char[][] ans = new char[N][N];

		// x를 입력 받으면 주변 숫자 확인
		int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
		int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};
		
		// 지뢰를 밟았는지
		boolean isBomb = false;
		
		for(int i = 0; i < N; i++) {
			String in = sc.next();
			for(int j = 0; j < N; j++) {
				
				// x로 누른 곳이면 주변 8곳 확인
				if(in.charAt(j) == 'x') {

					// 확인한 곳이 지뢰면 출력하고 끝 
					if(bomb[i][j] == '*') isBomb = true;
					
					// 아니면 개수 세기
					int cnt = 0;
					for(int d = 0; d < 8; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						
						if(bomb[nr][nc] == '*') cnt++;
					}
					
					ans[i][j] = Character.forDigit(cnt, 10);
					
				}
				
				else {
					ans[i][j] = '.';
				}
			}
		}
		
		// 지뢰를 밟았으면 지뢰 있는 곳 *로 변경
		if(isBomb) {
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(bomb[r][c] == '*') ans[r][c] = '*';
				}
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				System.out.print(ans[r][c]);
			}
			System.out.println();
		}
		
		
	}
}
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 체스판 크기 N*M
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String in = sc.next();
			for(int j = 0; j < M; j++) {
				map[i][j] = in.charAt(j);
			}
		}
		
		// 칠해야하는 정사각형 최솟값
		int min = Integer.MAX_VALUE;
		
		// 완전탐색
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				
				// 8*8을 할 수 있는 범위인지
				if(i + 7 >= N || j + 7 >= M) continue;
				
				// W로 시작하는 거 하나
				int cnt = 0;
				boolean isWhite = true;
				for(int r = i; r < i + 8; r++) {
					for(int c = j; c < j + 8; c++) {
						// 하얀색이어야 한다면
						if(isWhite && map[r][c] == 'B') cnt++;
						// 검정색이어야 한다면
						if(!isWhite && map[r][c] == 'W') cnt++;
						
						// 하양검정 반대
						isWhite = !isWhite;
					}
					// 줄 바꿀 때 하양검정 반대
					isWhite = !isWhite;
				}
				min = Math.min(min, cnt);
				
				// B로 시작하는 거 하나
				cnt = 0;
				isWhite = false;
				for(int r = i; r < i + 8; r++) {
					for(int c = j; c < j + 8; c++) {
						// 하얀색이어야 한다면
						if(isWhite && map[r][c] == 'B') cnt++;
						// 검정색이어야 한다면
						if(!isWhite && map[r][c] == 'W') cnt++;
						
						// 하양검정 반대
						isWhite = !isWhite;
					}
					// 줄 바꿀 때 하양검정 반대
					isWhite = !isWhite;
				}
				min = Math.min(min, cnt);
			}
		}
		
		System.out.println(min);
		// 오 이제 이런 문제는 10분
	}
}
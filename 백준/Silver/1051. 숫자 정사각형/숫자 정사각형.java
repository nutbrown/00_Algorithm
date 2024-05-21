import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.next());
		int M = Integer.parseInt(sc.next());
		
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			String in = sc.next();
			for(int j = 0; j < M; j++) {
				map[i][j] = Character.getNumericValue(in.charAt(j));
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				
				for(int size = Math.min(N - i, M  - j); size > 0; size--) {
					
				}
			}
		}
		
		// 변의 길이를 줄여가면서
		for(int s = Math.min(N, M); s >= 1; s--) {
			for(int i = 0; i + s <= N; i++) {
				for(int j = 0; j + s <= M; j++) {
					
					// 네 꼭짓점의 숫자가 같으면 끝
					if(map[i][j] == map[i + s - 1][j]
							&& map[i][j] == map[i][j + s - 1]
							&& map[i][j] == map[i + s - 1][j + s - 1]) {
						System.out.println(s * s);
						return;
					}
				}
			}
		}
		
	}
}
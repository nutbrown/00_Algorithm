import java.util.Scanner;

public class Main {
	static int N;
	static int[][] map;
	static int sum;
	static int r;
	static int c;
	
	// 좌 하 우 상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		// 격자의 크기 N
		N = sc.nextInt();
		
		// 각 칸에 있는 모래
		map = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
	
		// 격자 밖으로 나간 모래의 양
		sum = 0;
		
		// 가운데 칸부터 토네이도의 이동 시작
		// N은 홀수니까, (N/2+1, N/2+1)부터 시작한다
		r = N / 2 + 1;
		c = N / 2 + 1;
		
		// 방향은 좌하 우상 좌하 우상 을 반복하고
		int dir = 0;
		
		// 1칸 1칸, 2칸 2칸 ... 5칸 5칸, 6칸 6칸 + 마지막 6(N - 1)칸을 간다
		for(int i = 1; i < N; i++) {
			
			// i칸 이동을 2번씩 한다
			for(int t = 0; t < 2; t++) {
	
				// i번만큼 모래 이동하기
				for(int j = 0; j < i; j++) {
					windSand(dir);
				}
				
				// 끝나면 방향 바꾸기
				dir = (dir != 3 ? dir + 1 : 0);
			}
		}
		
		// 마지막 한 번 더 가기
		for(int j = 0; j < N - 1; j++) {
			windSand(dir);
		}

		
		System.out.println(sum);
	}
	
	
	// 토네이도 이동시 모래 흩날리기
	static void windSand(int dir) {

		// 토네이도가 이동하는 y위치에서 모든 모래양이 퍼진다
		int sand = map[r + dr[dir]][c + dc[dir]];

		// 퍼지는 모래의 비율과 위치
		// 를 직접 입력하는 것보다 하나씩 조건으로
		// 1% - 0인 방향에 플마1, 이동하는 방향은 그대로
		// 2% - 0인 방향에 플마2, 이동하는 방향은 *1
		// 7% - 0인 방향에 플마1, 이동하는 방향은 *1
		// 10% - 0인 방향에 플마1, 이동하는 방향은 *2
		// 5% - 0인 방향은 그대로, 이동하는 방향은 *3
		
		// 좌우로 움직일 때
		if(dr[dir] == 0) {
			// 1% 이동
			moveSand(r + 1, c, sand * 0.01);
			moveSand(r - 1, c, sand * 0.01);
			
			// 7% 이동
			moveSand(r + 1, c + dc[dir], sand * 0.07);
			moveSand(r - 1, c + dc[dir], sand * 0.07);
			
			// 2% 이동
			moveSand(r + 2, c + dc[dir], sand * 0.02);
			moveSand(r - 2, c + dc[dir], sand * 0.02);
			
			// 10% 이동
			moveSand(r + 1, c + dc[dir] * 2, sand * 0.1);
			moveSand(r - 1, c + dc[dir] * 2, sand * 0.1);
		}
		
		// 상하로 움직일 때
		else {
			// 1% 이동
			moveSand(r, c + 1, sand * 0.01);
			moveSand(r, c - 1, sand * 0.01);
			
			// 7% 이동
			moveSand(r + dr[dir], c + 1, sand * 0.07);
			moveSand(r + dr[dir], c - 1, sand * 0.07);
			
			// 2% 이동
			moveSand(r + dr[dir], c + 2, sand * 0.02);
			moveSand(r + dr[dir], c - 2, sand * 0.02);

			// 10% 이동
			moveSand(r + dr[dir] * 2, c + 1, sand * 0.1);
			moveSand(r + dr[dir] * 2, c - 1, sand * 0.1);
		}
		
		// 5% 이동
		moveSand(r + dr[dir] * 3, c + dc[dir] * 3, sand * 0.05);

		// 위에서 날린 모래 빼주기
		sand -= (int)(sand * 0.01) * 2 + 
				(int)(sand * 0.07) * 2 +
				(int)(sand * 0.02) * 2 +
				(int)(sand * 0.1) * 2 + (int)(sand * 0.05);
		
		// a로 나머지 이동 (55%가 아니라)
		moveSand(r + dr[dir] * 2, c + dc[dir] * 2, sand);

		// y에 있는 모래는 0
		map[r + dr[dir]][c + dc[dir]] = 0;
		
		// 현재 위치를 x에서 y로 이동
		r += dr[dir];
		c += dc[dir];
		
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();

	}
	
	
	// (r, c)로 amount만큼의 모래 이동
	static void moveSand(int r, int c, double amount) {
		
		// 범위 밖이라면 따로 모은다
		if(r < 1 || r > N || c < 1 || c > N) {
			sum += (int)amount;
		}
		
		// 아니라면 모래 이동
		else {
			map[r][c] += (int)amount;
		}
	}
	
}

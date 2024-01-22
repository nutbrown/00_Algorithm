import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int x;
	static int y;
	static int[][] map;
	static int[] dice = {-1, 0, 0, 0, 0, 0, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		// N x M 지도, 오른쪽은 동쪽, 위쪽은 북쪽
		// (r, c) r은 북쪽으로부터 거리, c는 서쪽으로부터 거리
		N = sc.nextInt();
		M = sc.nextInt();
		
		// 주사위 놓은 곳 + 현재 위치 x, y
		// x가 세로 행, y가 가로 열 
		x = sc.nextInt();
		y = sc.nextInt();
		
		// 명령 개수 K
		int K = sc.nextInt();
		
		// 지도 입력
		// 북쪽부터 남쪽으로 : r이 0부터
		// 서쪽부터 동쪽으로 : c가 0부터
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 주사위 입력 (제로X)(위에서 했다)
		
		// 명령 입력
		// 동쪽 1, 서쪽 2, 북쪽 3, 남쪽 4 
		for(int i = 0; i < K; i++) {
			int cmd = sc.nextInt();
			
			if(cmd == 1) move_east();
			else if(cmd == 2) move_west();
			else if(cmd == 3) move_north();
			else if(cmd == 4) move_south();
		}
	}
	
	
	static void move_east() {
		// 범위 안에 없으면 안 함
		if(y + 1 >= M) return;
		
		// 지도 위 주사위 위치는 오른쪽으로 한 칸
		y++;
		// 지도 칸이 0이 맞다면, 주사위 숫자 -> 지도
		if(map[x][y] == 0) map[x][y] = dice[3];
		// 지도 칸이 0이 아니면, 지도 숫자 -> 주사위
		else {
			dice[3] = map[x][y];
			// 복사한 다음 칸에 쓰여있는 수는 0이 된다 !!!!!!!
			map[x][y] = 0;
		}
		
		// 주사위 오른쪽으로 굴리기
		int temp = dice[4];
		dice[4] = dice[6];
		dice[6] = dice[3];
		dice[3] = dice[1];
		dice[1] = temp;
		
		// 이동할 떄마다 주사위 윗 면 숫자 출력
		System.out.println(dice[1]);
	}
	
	static void move_west() {
		if(y - 1 < 0) return;
		
		y--;
		if(map[x][y] == 0) map[x][y] = dice[4];
		else {
			dice[4] = map[x][y];
			map[x][y] = 0;
		}

		int temp = dice[3];
		dice[3] = dice[6];
		dice[6] = dice[4];
		dice[4] = dice[1];
		dice[1] = temp;
		
		System.out.println(dice[1]);
	}
	
	static void move_south() {
		if(x + 1 >= N) return;
		
		x++;
		if(map[x][y] == 0) map[x][y] = dice[5];
		else {
			dice[5] = map[x][y];
			map[x][y] = 0;
		}
		
		int temp = dice[6];
		dice[6] = dice[5];
		dice[5] = dice[1];
		dice[1] = dice[2];
		dice[2] = temp;
		
		System.out.println(dice[1]);
	}
	
	static void move_north() {
		if(x - 1 < 0) return;
		
		x--;
		if(map[x][y] == 0) map[x][y] = dice[2];
		else {
			dice[2] = map[x][y];
			map[x][y] = 0;
		}
		
		int temp = dice[2];
		dice[2] = dice[1];
		dice[1] = dice[5];
		dice[5] = dice[6];
		dice[6] = temp;
		
		System.out.println(dice[1]);
	}
}

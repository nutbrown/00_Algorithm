import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 30분 안에 풀 수 있을 것만 같아 아니네 40분
		
		// 드래곤 커브를 미리 만들어둔다
		// 0세대는 1
		// 1세대는 1*2 = 2
		// 2세대는 2*2 = 4
		// 3세대는 4*2 = 8
		// ... 10세대는 2^10
		int[] gen = new int[(int) Math.pow(2, 10) + 1];
		
		// (중간부터) 동북서북
		// 시계방향 90도 돌려서 -> 남동북동
		// 순서를 바꿔서 -> 동북동남
		// 거꾸로 가는거니까 반대로 -> 서남서북
		// dp로 합치면 -> 동북서북 서남서북
		
		// 시계방향 90도 돌려서 반대로 하면
		// 동-북, 남-동, 서-남, 북-서
		// 반시계방향 90도 회전한 꼴이 된다
		
		// 동서남북을 0 1 2 3
		gen[1] = 0;
		for(int i = 1; i <= 10; i++) {
			// i세대는 1부터 2^(i-1)까지에 규칙을 적용시켜서
			// 2^(i-1) + 1부터 2^i까지 넣는다
			for(int j = 1; j <= Math.pow(2, i - 1); j++) {
				// 반시계방향 90도
				int nd = gen[j] != 0 ? gen[j] - 1 : 3;
				
				// 거꾸로 넣어준다
				gen[(int)Math.pow(2, i) - j + 1] = nd;
			}
		}
		

		// 드래곤 커브 저장 지도
		boolean[][] map = new boolean[101][101];
		
		// 드래콘 커브 개수
		int N = sc.nextInt();
		for(int n = 0; n < N; n++) {
			// 드래곤 커브 시작점
			// x가 col 열방향, y가 row 행방향
			int c = sc.nextInt();
			int r = sc.nextInt();
			
			// 시작 방향 (0 1 2 3이 동 북 서 남)
			int d = sc.nextInt();
			// 시작방향 보정
			if(d == 0) d = 0;
			else if(d == 3) d = 1;
			else if(d == 2) d = 2;
			else if(d == 1) d = 3;
			
			// 드래곤 커브 세대
			int g = sc.nextInt();
			
			// 방향 좌표
			int[] dr = {0, 1, 0, -1};
			int[] dc = {1, 0, -1, 0};
			
			// 드래곤 커브 기록
			// 동이면 그대로, 남이면 +1, 서면 +2, 북이면 +3
			int cr = r;
			int cc = c;
			map[cr][cc] = true;
			for(int i = 1; i <= Math.pow(2, g); i++) {
				
				// 방향 보정
				int dir = gen[i] + d;
				if(dir > 3) dir -= 4; 
				
				cr += dr[dir];
				cc += dc[dir];
				// 0세대를 생각하면 여기서 true를 찍어준다
				map[cr][cc] = true;
			}
		}

		
		// 0 <= x, y, <= 100 안에서 드래곤 커브의 일부인 것 구하기
		int cnt = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] && map[i][j + 1] && 
						map[i + 1][j] && map[i + 1][j + 1]) cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}
}
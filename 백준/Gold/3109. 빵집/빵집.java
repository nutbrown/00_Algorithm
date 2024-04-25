import java.util.Scanner;

public class Main {
	static int R;
	static int C;
	static int[][] map;
	static int sum;
	static boolean flag;
	static int[] dr = {-1, 0, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 행과 열
		R = Integer.parseInt(sc.next());
		C = Integer.parseInt(sc.next());

		// 빵집 지도
		map = new int[R][C];
		for(int i = 0; i < R; i++) {
			String in = sc.next();
			for(int j = 0; j < C; j++) {
				if(in.charAt(j) == '.') map[i][j] = 0;
				else if(in.charAt(j) == 'x') map[i][j] = 1;
			}
		}
		
		// 왼쪽 열은 옆집, 오른쪽 열은 원웅 빵집
		// 파이프는 오른쪽, 오른쪽위, 오른쪽아래로 연결 가능할 때
		// 최대한 많은 파이프 설치
		sum = 0;
		
		// 모든 행에서 최대한 위로 붙여서 가게 만들기
		for(int i = 0; i < R; i++) {
			
			// visited를 취소해야하는가....
			// 거기까지 갔는데, 도착을 못했어
			// 그러면 다음에 거기까지 간 애도 도착을 못하니까, 아니네
			
			// bfs가 아니라 dfs로 위로 붙여서 가면
			// 위로 못가는데 아래로 갔을 때 도착한다면 이런 생각 안 해도 된다
			flag = false;
			dfs(i, 0);
			if(flag) sum++;
			
		}
		
		System.out.println(sum);
	}
	
	
	static void dfs(int r, int c) {

		// 이미 오른쪽에 도착했으면 안하기
		if(flag) return;
		
		// 오른쪽 열에 도착했으면 성공
		if(c == C - 1) {
			flag = true;
			return;
		}
		
		// 방문체크를 여기서
		map[r][c] = 2;
		
		// 그게 아니면 최대한 위로 방문
		for(int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + 1;
			
			// 범위 확인
			if(nr < 0 || nr >= R) continue;
			
			// 방문할 수 있으면 가기
			if(map[nr][nc] == 0) {
				// 방문했으면 2
				//map[nr][nc] = 2;
				
				// 다음 방문
				dfs(nr, nc);
				
				// 성공했으면 여기서 다음을 안 해야한다
				//if(flag) return;
			}
		}
		
		// 3방향 다 갈 곳이 없으면 돌아가야지
		// 아 다음부터는 숫자리턴 안 해야지
	}
	
}

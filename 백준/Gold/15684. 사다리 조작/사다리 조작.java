import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int H;
	static int[][] map;
	static int min;
	static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 세로선 개수 N
		// 주어진 사다리 개수 M
		// 가로선 개수 H
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		
		// 사다리 지도
		map = new int[H + 1][N + 1];
		
		// 	 1 2 3 4 5
		// 1  1 
		// 2  1
		// (1, 1)과 (1, 2)가 연결되어 있으면
		// (1, 1)를 1로 표시한다
		for(int i = 0; i < M; i++) {
			// b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			map[a][b] = 1;
		}
		
		// 추가해야 하는 가로선 개수 최솟값
		min = Integer.MAX_VALUE;
		
		// 사다리 최대 3개 골라보기
		visited = new boolean[H + 1][N + 1];
		addladder(0, 1, 1);
		
		// 정답이 3보다 크면 -1, 불가능해도 -1
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
		
	}
	
	// 사다리 하나씩 추가해보기 (최대 3개)
	static void addladder(int cnt, int r, int c) {
		// 사다리가 3개 넘으면 안 된다
		if(cnt > 3) return;
		
		// 이미 나온 최솟값보다 크면 할 필요 없다
		if(cnt > min) return;
		
		// 결과가 나오는지 확인
		if(godown()) min = Math.min(min, cnt);

		// 다른 사다리 추가
        // 시간 초과 이유 : 순열로 했다. 조합으로 해야한다.
		// 나온 인덱스 (r, c)의 다음 인덱스만 넣어봐야 한다.
		for(int i = r; i <= H; i++) {
			for(int j = 1; j < N; j++) {
				// 사다리가 있거나 + 고른 적 있으면 안 된다
				if(map[i][j] == 1 || visited[i][j]) continue;
				
				// 사다리를 연달아서 놓을 수 없다
				if(map[i][j - 1] == 1 || map[i][j + 1] == 1) continue;
				
				// 사다리 놓고, 사다리 개수 증가
				map[i][j] = 1;
				visited[i][j] = true;
				addladder(cnt + 1, i, j);
				
				// 돌아왔으면 원상복귀
				map[i][j] = 0;
				visited[i][j] = false;
			}
		}
	}
	
	
	// 사다리 내려가보기
	static boolean godown() {
		// 세로선 1부터 N까지 해본다
		for(int i = 1; i <= N; i++) {
			// (i, i)에서 시작
			int col = i;
			
			// 가로선 위치가 1부터 마지막 M일 때까지 내려간다
			for(int j = 1; j <= H; j++) {
				// 오른쪽으로 갈 수 있다면, 세로선을 오른쪽으로 옮기고
				if(map[j][col] == 1) col++;

				// 왼쪽으로 갈 수 있다면, 세로선을 왼쪽으로 옮기고
				// 제로인덱스가 아니어서 범위 처리 할 필요 없다
				else if(map[j][col - 1] == 1) col--;
				
				// 한 칸 내려간다
			}
			
			// 가로선이 M이 됐으면 세로선이 i가 되어야 한다
			if(col != i) return false;
		}
		
		// 다 통과했으면 true
		return true;
	}
	
	
}
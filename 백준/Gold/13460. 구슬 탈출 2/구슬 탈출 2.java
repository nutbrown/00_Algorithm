import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static char[][] map;
	static int[] red;
	static int[] blue;
	static int min;
	static int[] order;
	static int cnt;
	static int[] cRed;
	static int[] cBlue;
	static int success;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 보드 세로 N 가로 M (3이상 10이하)
		N = sc.nextInt();
		M = sc.nextInt();
		
		// 빈칸 . 장애물 # 구멍 0 빨간구슬 R 파란구슬 B
		// 빨간구슬 파란구슬 구멍은 모두 1개씩
		map = new char[N][M];
		red = new int[2];
		blue = new int[2];
		
		for(int i = 0; i < N; i++) {
			String in = sc.next();
			for(int j = 0; j < M; j++) {
				map[i][j] = in.charAt(j);
				
				// 구슬 위치정보 저장
				if(map[i][j] == 'R') {
					red[0] = i;
					red[1] = j;
				} else if(map[i][j] == 'B') {
					blue[0] = i;
					blue[1] = j;
				}
			}
		}
		
		// 최소 몇 번만에 빨간 구슬을 빼낼 수 있는지
		// 10번 이하로 성공하지 못하면 -1 출력
		min = 11;
		
		// 왼쪽, 오른쪽, 위쪽, 아래쪽으로 기울이기
		// 구슬이 움직이지 않을 때까지 기울인다
		// 빨간 구슬이 빠지면 성공, 파란 구슬이 빠지면 실패
		// 모든 경우의 수
		order = new int[10];
		dfs(0);
		
		if(min == 11) System.out.println(-1);
		else System.out.println(min);
	}
	
	
	// 10번동안 모든 경우의 수(중복 순열)
	static void dfs(int depth) {
		// 10번의 경우의 수가 정해졌으면
		if(depth == 10) {
			// System.out.println(Arrays.toString(order)); // 중복순열인지 확인
			// 그 순서대로 이동시켜보기
			cRed = new int[2];
			cBlue = new int[2];
			cRed[0] = red[0];
			cRed[1] = red[1];
			cBlue[0] = blue[0];
			cBlue[1] = blue[1];
			
			// 성공여부 : 성공은 1, 실패는 2
			success = 0;
			
			for(int i = 0; i < 10; i++) {
				lean(order[i]);
				
				// 성공인지, 실패인지, 계속 하는 건지
				if(success == 1) {
					min = Math.min(min, i + 1);
					break;
				}else if(success == 2) {
					break;
				} else if(success == 0) {
					continue;
				}
			}
			
			return;
		}
		
		// 4가지의 방향 다 넣어보기
		for(int i = 0; i < 4; i++) {
			order[depth] = i;
			dfs(depth + 1);
		}
	}
	
	
	// 기울이기
	static void lean(int d) {
		// 0 1 2 3 순서대로 오른쪽 아래 왼쪽 위
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		// 구슬이 움직일 때까지 이동
		boolean flag = true;
		
		// 빨간 구슬이 있는지 **로직 개선
		boolean isRed = true;
		
		while(flag) {
			flag = false;
			// 각각 이동하는 위치
			int[] nRed = new int[2];
			if(isRed) {
				nRed[0] = cRed[0] + dr[d];
				nRed[1] = cRed[1] + dc[d];
			}
			int[] nBlue = {cBlue[0] + dr[d], cBlue[1] + dc[d]};
			
			// 빨강 파랑이 벽에 부딪혀서 못 움직이는지 **로직 개선
			boolean red = true;
			boolean blue = true;
			if(map[nRed[0]][nRed[1]] == '#') red = false;
			if(map[nBlue[0]][nBlue[1]] == '#') blue = false;
			
			// 빨간구슬이 구멍에 빠지면 성공 **오타 !!!!!! 0아니고 O
			if(map[nRed[0]][nRed[1]] == 'O') {
				success = 1;
				flag = true;
				// 빨간구슬 없어진다
				nRed[0] = 0;
				nRed[1] = 0;
				isRed = false;
			}
			
			// 파란구슬이 구멍에 빠지면 실패 + 중단
			if(map[nBlue[0]][nBlue[1]] == 'O') {
				success = 2;
				flag = true;
				break;
			}

			// 파란구슬이 움직인다면 nBlue랑 비교, 안 움직인다면 cBlue랑 비교 **로직 개선
			if(red && (
					(blue && !(nRed[0] == nBlue[0] && nRed[1] == nBlue[1]))
					|| (!blue && !(nRed[0] == cBlue[0] && nRed[1] == cBlue[1]))
					)) {
				cRed = nRed.clone();
				flag = true;
			}
			if(blue && (
					!isRed || 
					isRed && (
							(red && !(nBlue[0] == nRed[0] && nBlue[1] == nRed[1]))
							|| (!red && !(nBlue[0] == cRed[0] && nBlue[1] == cRed[1]))
							)
					)) {
				cBlue = nBlue.clone();
				flag = true;
			}
			
			
		}
		
		// 이동을 중단하면 성공여부가 판단난다
	}
	
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 보드 크기 N, 사과개수 K
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		// 맨 위 맨 좌측은 1행 1열
		int[][] map = new int[N + 1][N + 1];

		// 사과 위치 (행, 열)
		for(int i = 0; i < K; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			map[r][c] = 1;
		}
		
		// 뱀의 방향 변환 횟수 L
		int L = sc.nextInt();
		
		// 뱀의 방향 변환 <시간, 방향>으로 저장
		HashMap<Integer, Character> hm = new HashMap<>();
		for(int i = 0; i < L; i++) {
			// X초가 끝난 뒤에 왼쪽 L 또는 오른쪽 D로 방향 회전
			hm.put(sc.nextInt(), sc.next().charAt(0));
		}
		
		// 뱀 몸 길이 저장
		ArrayList<int[]> snake = new ArrayList<>();
		
		// 방향 동서남북
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		// 초기 시간, 위치, 방향
		int time = 0;
		int cr = 1;
		int cc = 1;
		int d = 0;
		snake.add(new int[] {0, 0});
		
		
		loop:
		while(true) {
			// 1. 시간 재기
			time++;
			
			// 2. 뱀 이동
			// 머리를 1칸 늘려서 다음칸에 위치
			int nr = cr + dr[d];
			int nc = cc + dc[d];
			
			// 3. 범위를 벗어나거나, 뱀 몸통 만나면 종료
			if(nr <= 0 || nr > N || nc <= 0 || nc > N) break;
			for(int[] onesnake : snake) {
				if(onesnake[0] == nr && onesnake[1] == nc) break loop;
			}
			
			// 4. 사과가 있을 때 없을 때
			// 사과가 있으면, 사과가 없어지고, 꼬리 그대로
			// 사과가 없으면, 꼬리가 1칸 없어진다
			if(map[nr][nc] == 1) {
				map[nr][nc] = 0;
				snake.add(new int[] {nr, nc});
			} else {
				// 새로 추가되는 머리가 뒤쪽 인덱스
				// 맨 처음에 들어간 꼬리가 인덱스0
				snake.add(new int[] {nr, nc});
				snake.remove(0);
				// 여기서 앞에 1칸 추가, 뒤에 1칸 추가되면서
				// 직관적으로 느껴지게 뱀이 앞으로 간다
				
				//	  	  s
				//		a s 이렇게 내려오다가 오른쪽으로 꺾은 상태에서 이동하면
				
				//	  	  s
				// 		s s 사과를 먹으면서 몸이 늘어난다
				
				//	  s	s s 그 방향으로 앞에를 추가하고, 뒤에를 제거하면
				//			자연스럽게 꺾어서 이동하는 방식이 된다
				
				// 뱀이 꺾어서 이동하는 게 잘 와닿지가 않았는데
				// 앞 머리는 그 방향으로 1칸 추가하고
				// 뒤 꼬리는 1칸을 제거하면
				// 꺾은 부분에서 꺾어서 이동하는 형태가 된다
			}
			
			
			// 5. 방향 바꿔주는 시간 만나면 방향 변경
			if(hm.containsKey(time)) {
				if(hm.get(time) == 'L') {
					// 왼쪽 회전이면 dir 감소
					d = d == 0 ? 3 : d - 1;
				} else if(hm.get(time) == 'D') {
					// 오른쪽 회전이면 dir 증가
					d = d == 3 ? 0 : d + 1;
				}
			}
			
			// 6. 현재값 업데이트
			cr = nr;
			cc = nc;
		}
	
		// 종료되면 시간 출력
		System.out.println(time);
	}
}

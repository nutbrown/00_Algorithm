import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static char[][] map;
	static boolean[][] crash_visited;
	static ArrayList<int[]> remove_list;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 뿌요뿌요 규칙
		// - 뿌요는 중력의 영향을 받아서 아래 바닥이나 다른 뿌요가 나올 때까지 떨어진다
		// - 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 한꺼번에 없어진다. 1연쇄
		// - 뿌요가 없어지고 다른 뿌요들이 있다면 중력의 영향을 받아서 떨어진다
		// - 떨어지고 다시 뿌요들이 4개 이상 모이면 한꺼번에 없어진다. 1연쇄 추가
		// - 터질 수 있는 뿌요가 여러 그룹 있다면 동시에 터진다. 여러 개 터져도 1연쇄 추가
		// 연쇄가 몇 번 연속 일어날지
		
		// 12개 줄, 각 줄에는 6개 문자
		// 0행이 맨 바닥, 11행이 맨 위
		// . 빈공간 R 빨강 G 초록 B 파랑 P 보라 Y 노랑
		map = new char[12][6];
		
		// 입력
		for(int i = 11; i >= 0; i--) {
			String in = sc.nextLine();
			for(int j = 0; j < 6; j++) {
				map[i][j] = in.charAt(j);
			}
		}
		
		// 연쇄 개수 세기
		int chain = 0;
		boolean flag = true;
		// 터뜨릴게 없어서 false면 중단
		while(flag) {
			// 전체 탐색해서 4개가 모여있는 게 있는지 표시
			flag = crash();

			// 터뜨릴게 있어서 true면
			// 뿌요 한꺼번에 없애고 뿌요 내려보내고 연쇄 증가
			if(flag) {
				remove();
				godown();
				chain++;
			}
			
			// 확인 출력
			for(int i = 11; i >= 0; i--) {
				for(int j = 0; j < 6; j++) {
					//System.out.print(map[i][j]);
				}
				//System.out.println();
			}
		}
		
		System.out.println(chain);
	}
	
	
	// 1번 연쇄반응 일으킬 뿌요 확인
	static boolean crash() {
		// 방문 배열
		crash_visited = new boolean[12][6];
		
		// 없애야할 뿌요 리스트
		remove_list = new ArrayList<>();

		// 전체 탐색
		for(int i = 11; i >= 0; i--) {
			for(int j = 0; j < 6; j++) {
				// 뿌요 다 탐색했으면 아웃 (나중에)
				// 빈칸이 아니라면 탐색
				if(map[i][j] != '.') check(i, j);
			}
		}
		
		// 터뜨릴게 없으면 false
		if(remove_list.size() != 0) return true;
		else return false;
	}
	
	
	// 탐색하는 (cr, cc) 기준으로
	// 주변에 4개 이상일 때 무슨 뿌요를 터뜨릴건지 bfs 확인
	static void check(int cr, int cc) {
		//System.out.println(cr + " " + cc + " " +  map[cr][cc]);
		Queue<int[]> q = new LinkedList<>();
		ArrayList<int[]> temp_list = new ArrayList<>();
		
		int[] dr = {1, 0, -1, 0};
		int[] dc = {0, 1, 0, -1};
		
		int cnt = 1;
		char puyo = map[cr][cc];
		q.add(new int[] {cr, cc});
		temp_list.add(new int[] {cr, cc});
		crash_visited[cr][cc] = true;
		
		// 주위 퍼져나가다가 4개 이상이 되는지
		while(!q.isEmpty()) {
			cr = q.peek()[0];
			cc = q.poll()[1];
			
			// 주변으로 퍼져 나가는데
			// 4개 이상이 되면 
			for(int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(nr < 0 || nr >= 12 || nc < 0 || nc >= 6) continue;
				if(crash_visited[nr][nc]) continue;
				
				if(map[nr][nc] == puyo) {
					//System.out.println(" " + nr + " " + nc + " " + map[nr][nc]);
					cnt++;
					q.add(new int[] {nr, nc});
					temp_list.add(new int[] {nr, nc});

					// 같은 뿌요일 때만 visited 체크
					crash_visited[nr][nc] = true;
				}
			}
		}
		
		// 다 했을 때 4개 이상이면
		// 다 없애야하는 뿌요로 추가
		if(cnt >= 4) {
			for(int[] array : temp_list) {
				remove_list.add(array);
			}
		}
	}
	
	
	// 1연쇄에 터뜨릴 뿌요 전부 제거
	static void remove() {
		for(int[] array : remove_list) {
			int r = array[0];
			int c = array[1];
			
			map[r][c] = '.';
		}
	}
	
	
	// 1연쇄 이후 뿌요 내리기
	static void godown() {
		// 열 1개를 골라서 - 0행부터 12행까지
		for(int j = 0; j < 6; j++) {
			// 세로로 있는 뿌요 개수 (제로인덱스)
			int bottom = -1;
			for(int i = 0; i < 12; i++) {
				if(map[i][j] != '.') {
					// 빈공간이 아니어서 뿌요 개수 증가 
					bottom++;
					// 그런데 떠있어서 인덱스가 다르면 갖고 내려옴
					if(bottom != i) {
						map[bottom][j] = map[i][j];
						map[i][j] = '.';
					}
				}
			}
		}
	}
	
	
	
}

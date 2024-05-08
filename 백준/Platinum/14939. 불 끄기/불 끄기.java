import java.util.Scanner;

public class Main {
	static boolean[][] map;
	static boolean[][] copy;
	static int min;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 전구 입력 (켜져있으면 1, 꺼져있으면 0)
		map = new boolean[10][10];
		for(int i = 0; i < 10; i++) {
			String in = sc.next();
			for(int j = 0; j < 10; j++) {
				char ch = in.charAt(j);
				
				if(ch == '#') map[i][j] = false;
				else if(ch == 'O') map[i][j] = true;
			}
		}
		
		// 모두 끄기 위해 최소한으로 눌러야하는 스위치의 개수
		min = Integer.MAX_VALUE;

		
		// 윗줄부터 쭉 내려온다. 스위치를 2번 누를 필요는 없다.
		// 윗줄이 아랫줄에 어떤 효과를 미칠지 모르니까
		// 첫째 줄은 스위치를 켜고 끄고 모든 경우의 수를 한다
		// 윗줄에 켜져있는 스위치를 아랫줄에서 꺼주는 방식으로 내려온다.
		
		// 1~2 줄만 복사해서 조합 시작
		copy = new boolean[10][10];
		copy[0] = map[0].clone();
		copy[1] = map[1].clone();
		combination(0, 0);
		
		System.out.println(min);
		
	}
	
	
	static void combination(int depth, int cnt) {
		
		// 첫째 줄 모든 조합을 다 했으면
		if(depth == 10) {
			
			// 새로 배열을 만들어서 복사해준다
			boolean[][] copy2 = new boolean[10][10];
			
			// 위에 두 줄은 조합해준 걸 복사
			for(int i = 0; i < 2; i++) {
				copy2[i] = copy[i].clone();
			}
			
			// 1~2줄 이후 나머지 줄 복사하고 시작
			for(int i = 2; i < 10; i++) {
				copy2[i] = map[i].clone();
			}
			
			// 두 번쩨 줄부터*** 불을 최대한 끄는 방향으로 하나씩
			for(int i = 1; i < 10; i++) {
				for(int j = 0; j < 10; j++) {
					
					// 바로 위에 불이 켜져있으면 얘가 꺼줘야 한다
					// 왼쪽에 불이 켜져있을 때도 누르기 안 누르기 해야하나, 그러면 최소가 아닌가,
					// 스위치를 누르면 양 옆이 바뀌어서 양 옆은 컨트롤 할 수가 없다,
					// 아 왼쪽을 끄려고 누르면 위에가 켜지니까 위에 불만 -> 신경써야한다
					if(copy2[i - 1][j]) {
						lightswitch(i, j, copy2);
						cnt++;
					}
				}
			}
			
			// 맨 마지막줄을 확인하는데, 켜진 게 있으면 안 된다
			for(int j = 0; j < 10; j++) {
				if(copy2[9][j]) return;
			}
			
			// 다 꺼져있으면 최솟값 갱신
			min = Math.min(min, cnt);
			return;
		}
		
		
		// depth번째 스위치 누르기 + 하고 와서 원상복구
		lightswitch(0, depth, copy);
		combination(depth + 1, cnt + 1);
		lightswitch(0, depth, copy);
		
		// depth번째 스위치 안 누르기
		combination(depth + 1, cnt);
		
	}
	
	
	// 스위치 위아래왼쪽오른쪽도 상태 바꾸기
	static void lightswitch(int r, int c, boolean[][] arr) {
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		
		// 주변말고 본인 상태도 바꾸기***
		arr[r][c] = !arr[r][c];

		// 그리고 주변 바꾸기
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= 10 || nc < 0 || nc >= 10) continue;
			
			arr[nr][nc] = !arr[nr][nc];
		}
		
	}
}

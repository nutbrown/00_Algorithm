import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int R;
	static int C;
	static char[][] map;
	static int max;
	static ArrayList<Character> list;
	static HashSet<Character> hs;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		// 세로 R칸, 가로 C칸
		R = Integer.parseInt(sc.next());
		C = Integer.parseInt(sc.next());
		
		map = new char[R][C];
		for(int i = 0; i < R; i++) {
			String in = sc.next();
			for(int j = 0; j < C; j++) {
				map[i][j] = in.charAt(j);
			}
		}
		
		// 과거와 다른 알파벳만 지날 수 있다
		// (1, 1)을 1칸으로 시작해서 최대한 몇 칸을 지날 수 있는지
		max = 1;
		
		// 갖고 있는 알파벳을 순서대로 리스트랑, 중복없이 셋으로 가지고 있는다
		list = new ArrayList<>();
		hs = new HashSet<>();

		// (1, 1)칸을 1개로 시작해서 dfs
		list.add(map[0][0]);
		hs.add(map[0][0]);
		dfs(0, 0, 1);
		
		Queue<int[]> q = new LinkedList<>();
		
		
		System.out.println(max);
	}
	
	
	static void dfs(int r, int c, int cnt) {
		
		// 이 점을 기준으로 4방향으로 이동한다
		// dfs로 돌아와도 그 다음만 가고, 이미 갔던 알파벳은 안 가니까 visited 필요 없다
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(inRange(nr, nc)) {
				if(!hs.contains(map[nr][nc])) {

					// 같은 알파벳이 아니라면 가자
					list.add(map[nr][nc]);
					hs.add(map[nr][nc]);
					dfs(nr, nc, cnt + 1);
					
					// 다하고 돌아왔으면 원상복귀
					hs.remove(list.get(list.size() - 1));
					list.remove(list.size() - 1);
				}
			}
		}
		
		// 어디에 있어도 상관없지만, 더 갈 데 없어서 돌아갈 떄마다 최댓값 갱신
		max = Math.max(max, cnt);
	}
	
	
	// 15분
	static boolean inRange(int r, int c) {
		if(r >= 0 && r < R && c >= 0 && c < C) return true;
		else return false;
	}
}

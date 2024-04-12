import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int[][] map;
	static ArrayList<int[]> one;
	static int min;
	static int oneN;
	static int paperN;
	static int[] paper = {0, 5, 5, 5, 5, 5};
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		map = new int[10][10];
		one = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) one.add(new int[] {i, j});
			}
		}
		
		// 모든 1을 덮는데 필요한 색종이의 최소 개수
		// 덮은 1의 개수
		// 붙인 색종이 개수
		min = Integer.MAX_VALUE;
		oneN = 0;
		paperN = 0;
		
		// 색종이는 5, 4, 3, 2, 1 가능
		dfs(0);
		
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}
	
	
	// 몇 번째 1을 확인하고 있는지, 현재 덮은 1의 개수, 현재 붙인 색종이 개수
	static void dfs(int idx) {
		
		// 스티커를 다 덮었으면 최소 개수 반환
		if(oneN == one.size()) {
			min = paperN;
			return;
		}
		
		// 스티커 끝까지 돌았으면 돌아가기
		if(idx == one.size()) return;
		
		// 현재 붙인 색종이 개수가 min보다 작지 않으면 돌아가기
		if(paperN >= min) return;
		
		
		int r = one.get(idx)[0];
		int c = one.get(idx)[1];
		
		// 이미 붙여서 안 된 거면 다음 꺼
		if(map[r][c] == 0) {
			dfs(idx + 1);
		}

		// 이미 붙여서 안 된 게 아니라면 안 됐을 때, 돌아가야 한다
		else {
			// 5부터 1까지 크기의 스티커를 붙일 수 있으면 붙인다
			for(int i = 5; i >= 1; i--) {
				// 종이 다 쓰면 안 된다
				if(paper[i] == 0) continue;
				
				// 스티커 붙이기
				if(canStick(r, c, i)) {
					stick(r, c, i, idx);
				}
			}
		}

	}
	
	
	// (r, c)에 크기 n인 스티커를 붙일 수 있는지
	static boolean canStick(int r, int c, int n) {
		
		for(int i = r; i < r + n; i++) {
			for(int j = c; j < c + n; j++) {
				if(i < 0 || i >= 10 || j < 0 || j >= 10) return false;
				if(map[i][j] == 0) return false;
			}
		}
		
		// 다 통과했으면 가능
		return true;
	}
	

	// 스티커 붙이기
	static void stick(int r, int c, int n, int idx) {
		
		// 스티커 붙여서 0으로 바꿔주기
		for(int i = r; i < r + n; i++) {
			for(int j = c; j < c + n; j++) {
				map[i][j] = 0;
			}
		}
		
		// 덮은 1의 개수 증가
		oneN += n * n;
		
		// 붙인 색종이 개수 증가
		paperN++;

		// 붙인 색종이 종류 감소
		paper[n]--;
		
		// 스티커 붙였으면 다음 스티커
		dfs(idx + 1);
		
		// 나왔으면 원상복귀 + 여기서 리턴하면 돌아가서 다른 스티커
		unstick(r, c, n);
		
	}

	
	// 스티커 제거
	static void unstick(int r, int c, int n) {
		
		for(int i = r; i < r + n; i++) {
			for(int j = c; j < c + n; j++) {
				map[i][j] = 1;
			}
		}
		
		oneN -= n * n;
		paperN--;
		paper[n]++;
	}
	
	
	
	
}
import java.util.Scanner;

public class Main {
	static int N;
	static int L;
	static int[][] map;
	static int cnt;
	static boolean[] ramp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 크기가 N*N, 경사로의 길이가 L
		N = sc.nextInt();
		L = sc.nextInt();
		
		// 지도 입력
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 지나갈 수 있는 길의 개수
		cnt = 0;

		// i행 경사로 확인
		for(int i = 0; i < N; i++) {
			if(check(i, true)) cnt++;
		}
		
		// j열 경사로 확인
		for(int j = 0; j < N; j++) {
			if(check(j, false)) cnt++;
		}
		
		System.out.println(cnt);
		
		
	}
	
	static boolean check(int x, boolean isRow) {
		// 경사로 있는지
		boolean[] ramp = new boolean[N];
		
		// x행을 한 칸씩 확인
		// x열을 한 칸씩 확인
		for(int j = 0; j < N - 1; j++) {
			
			// 현재높이 ch와 다음높이 nh
			// 행을 확인하는 경우
			int ch = map[x][j];
			int nh = map[x][j + 1];
			
			// 열을 확인하는 경우
			if(!isRow) {
				ch = map[j][x];
				nh = map[j + 1][x];
			}
			
			
			// 경사가 1보다 크면 안 된다
			if(Math.abs(nh - ch) > 1) return false;
			
			// 경사가 올라가면
			if(ch < nh) {
				// 지나온 길에 경사로 설치가 가능한지 확인
				// j열부터 (j - L + 1)열까지 설치
				for(int k = j; k >= j - L + 1; k--) {
					// 범위를 벗어나면 안 된다
					if(k < 0) return false;
					// 이미 경사로가 있으면 안 된다
					if(ramp[k]) return false;
					
					// j열과 같은 높이여야 한다
					if((isRow && map[x][j] != map[x][k])
							|| (!isRow && map[j][x] != map[k][x])) return false;
				}
				
				// 다 통과해서 설치 가능하면 설치
				for(int k = j; k >= j - L + 1; k--) {
					ramp[k] = true;
				}
			}

			// 경사가 내려가면
			if(ch > nh) {
				// 앞으로 가는 길에 경사로 설치가 가능한지 확인
				// (j + 1)열부터 (j + L)열 까지 설치
				for(int k = j + 1; k <= j + L; k++) {
					// 범위를 벗어나면 안 된다
					if(k >= N) return false;
					// 이미 경사로가 있으면 안 된다
					if(ramp[k]) return false;
					// j열과 같은 높이여야 한다
					if((isRow && map[x][j + 1] != map[x][k])
							|| (!isRow && map[j + 1][x] != map[k][x])) return false;
				}
				
				// 다 통과해서 설치가능하면 설치
				for(int k = j + 1; k <= j + L; k++) {
					ramp[k] = true;
				}
			}
		}
		
		// 다 통과했으면 지나갈 수 있는 길
		return true;
	}
}

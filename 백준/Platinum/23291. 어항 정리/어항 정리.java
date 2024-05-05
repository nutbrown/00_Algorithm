import java.util.Scanner;

public class Main {
	static int N;
	static int K;
	static int[][] arr;
	static int t;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 어행 N개
		N = sc.nextInt();
		
		// 가장 많은 물고기, 가장 적은 물고기 차이가 K이하
		K = sc.nextInt();
		
		// 어항에 들어있는 물고기
		arr = new int[1][N];
		for(int i = 0; i < N; i++) {
			arr[0][i] = sc.nextInt();
		}
		
		// 어행을 몇 번 정리했는지
		t = 0;
		
		// 어행 정리
		fishbowl();
		
		System.out.println(t);
		
	}
	
	
	// 어항 1번 정리하기
	static void fishbowl() {
		
		// 어항 정리하는 횟수 증가
		t++;
		
		// 1.
		// 물고기 최솟값 찾기
		int min = arr[0][0];
		for(int i = 0; i < N; i++) {
			min = Math.min(min, arr[0][i]);
		}
		
		// 최솟값 어항이 여러 개면 다 넣어주기
		for(int i = 0; i < N; i++) {
			if(arr[0][i] == min) arr[0][i]++;
		}
		
		
		// 2.
		// 어항 돌려주기
		// 처음 뭉텅이의 세로와 가로는 1이다
		rotate1(1, 1);

		
		// 3.
		// 물고기 수 차이 따라서 물고기 이동시키기
		moveFish();

		
		// 4.
		// 왼쪽 어항부터 일렬로 놓기
		intoLine();

		
		// 5.
		// 왼쪽 N/2개를 공중부양 시켜서 돌려주기 2번
		rotate2();
		rotate2();

		
		// 6.
		// 다시 물고기 조절 작업
		moveFish();

		
		// 6.
		// 다시 바닥에 일렬로 놓는 작업
		intoLine();

		
		// 가장 많은 물고기, 가장 적은 물고기 차이가 K이하가 되면 끝
		int max = 0;
		min = arr[0][0];
		for(int i = 0; i < N; i++) {
			max = Math.max(max, arr[0][i]);
			min = Math.min(min, arr[0][i]);
		}
		
		
		if(max - min <= K) return;
		else fishbowl();
		
	}
	
	
	// 어항 공중부양해서 돌리기
	static void rotate1(int h, int w) {
		
		// 새로운 세로는 (가로 + 1)이다
		// 새로운 가로는 (세로)다
		int nh = w + 1;
		int nw = h;
		
		// 그걸 담을 전체 배열의 행크기는 (세로)
		// 열크기는 (N - 세로*가로 + 가로)다
		int nr = nh;
		int nc = N - nh * nw + nw;
		int[][] next = new int[nr][nc];
		
		// 제일 첫줄은 왼쪽 뭉탱이 빼고 기존 오른쪽
		for(int j = 0; j < nc; j++) {
			next[0][j] = arr[0][j + w];
		}
		
		// 그 위에 왼쪽 뭉탱이 쌓기
		// 오른쪽으로 돌려서, 기존가로만큼 위로 가고 기존세로만큼 오른쪽으로 간다 
		for(int i = 1; i <= w; i++) {
			for(int j = 0; j < h; j++) {
				
				// 열이 0 1 2...h-1 인 건 -> 행이 0 1 2...h-1 였던 것
				// 행이 1 2 3...w인 건 -> 열이 w-1...2 1 0 였던 것
				// 행이 i 인 건 -> 열이 w-i 였던 것
				next[i][j] = arr[j][w - i];
			}
		}
		
		// 배열 바꿔치기
		arr = next;
		
		// 오른쪽에 남아있는 어항이 뭉텅이의 세로보다 작으면 못한다
		if((N - nh * nw) < nh) return;
		
		// 그게 아니면 한 번 더 돌리기
		else rotate1(nh, nw);

	}
	
	
	// 물고기 수 차이 따라서 물고기 이동시키기
	static void moveFish() {
		
		// 현재 배열의 행과열
		int R = arr.length;
		int C = arr[0].length;
		
		// 증감해야하는 물고기 수
		int[][] increase = new int[R][C];
		
		// 중복이 안 되게 오른쪽 아래로만 수행***
		int[] dr = {0, 1};
		int[] dc = {1, 0};
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				
				// 물고기가 있는 칸만
				if(arr[i][j] == 0) continue;
				
				// 주변 물고기랑 비교
				for(int d = 0; d < 2; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					// 범위를 벗어났거나 물고기 없으면 안 된다
					if(nr < 0 || nr >= R || nc < 0 || nc >= C 
							|| arr[nr][nc] == 0) continue;
					
					// 차이를 5로 나눈 몫이 0보다 크면
					// 물고기가 많은 곳에서 적은 곳으로 몫만큼 보낸다
					int diff = Math.abs(arr[i][j] - arr[nr][nc]) / 5;
					if(diff > 0) {
						if(arr[i][j] > arr[nr][nc]) {
							increase[i][j] -= diff;
							increase[nr][nc] += diff;
						} else {
							increase[nr][nc] -= diff;
							increase[i][j] += diff;
						}
					}
				}
				
			}
		}
		
		// 증감 적용시키기
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				arr[i][j] += increase[i][j];
			}
		}
		
	}
	
	
	// 일렬로 놓기
	static void intoLine() {
		
		// 새로 담을 배열
		int[][] next = new int[1][N];
		
		// 현재 배열의 행과열
		int R = arr.length;
		int C = arr[0].length;
		
		// 왼쪽부터 아래부터 순서대로 바닥에 둔다
		int idx = 0;
		
		// 열 1개씩을 기준으로 행 증가시키면서
		for(int j = 0; j < C; j++) {
			for(int i = 0; i < R; i++) {
				
				// 물고기가 있는 칸만
				if(arr[i][j] == 0) continue;

				// 자연스레 idx가 N이면 멈추겠지? 아니면 에러
				next[0][idx++] = arr[i][j];
			}
		}
		
		// 배열 바꿔치기
		arr = next;
		
	}
	
	
	// 어항 공중부양해서 돌리기 두 번째
	static void rotate2() {
		
		// 현재 배열의 행과열
		int R = arr.length;	
		int C = arr[0].length;
		
		// 다음 열 크기는 반토막
		// 다음 행 크기는 두배
		int[][] next = new int[R * 2][C / 2];
		
		// 오른쪽 반토막은 그대로
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C / 2; j++) {
				next[i][j] = arr[i][j + C / 2];
			}
		}
		
		// 왼쪽 반토막은 돌려서 올려준다
		for(int i = R; i < R * 2; i++) {
			for(int j = 0; j < C / 2; j++) {
				
				// 행이 증가하면서 R-1부터 0까지 거꾸로
				// 현재 행이 R에서 1 증가한거면, 기존 행은 R에서 1 감소한 거다
				// 열이 증가하면서 C/2-1부터 0까지 거꾸로
				// 현재 열이 0에서 1 증가한거면, 기존 열은 C/2에서 1 감소한 거다
				next[i][j] = arr[R - (i - (R - 1))][C / 2 - (j + 1)];
			}
		}
		
		// 배열 바꿔치기
		arr = next;
		
	}
	
	
	// 확인용 출력 - 1시간반
	static void print(int[][] arr) {
		
		int R = arr.length;
		int C = arr[0].length;
		
		for(int i = R - 1; i >= 0; i--) {
			for(int j = 0; j < C; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}

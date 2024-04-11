import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int K;
	static int[][] map;
	static int R;
	static int C;
	static ArrayList<int[]> sticker;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 노트북 세로 N 가로 M 스티커 개수 K
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		// 지도
		map = new int[N][M];
		
		for(int k = 0; k < K; k++) {
			
			// 스티커 모눈종이 행과 열
			R = sc.nextInt();
			C = sc.nextInt();
			
			// 스티커의 행렬을 추가
			sticker = new ArrayList<>();
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(sc.nextInt() == 1) sticker.add(new int[] {i, j});
				}
			}
			
			// 스티커 붙이기
			for(int i = 0; i < 4; i++) {
				// 4번 해보고 안 되면 다음 스티커
				boolean result = stick();
				
				// 붙이는게 가능해서 붙였으면 아웃
				if(result) break;
				else rotate();
				
//				for(int[] s : sticker) {
//					System.out.println(Arrays.toString(s));
//				}
			}
			
//			for(int ii = 0; ii < N; ii++) {
//				for(int jj = 0; jj < M; jj++) {
//					System.out.print(map[ii][jj] + " ");
//				}
//				System.out.println();
//			}
			
		}
		
		// 붙은 칸의 수
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1) cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}
	
	
	// 스티커를 붙일 수 있는지 확인
	static boolean stick() {
		
		// 위쪽부터, 왼쪽부터
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				boolean flag = true;
				
				// 스티커가 붙을 수 있는지
				for(int[] s : sticker) {
					if(i + s[0] < 0 || i + s[0] >= N || j + s[1] < 0 || j + s[1] >= M
							|| map[i + s[0]][j + s[1]] != 0) {
						flag = false;
						break;
					}
				}

				// 스티커가 붙을 수 있으면 붙이기
				if(flag) {
					for(int[] s : sticker) {
						map[i + s[0]][j + s[1]] = 1;
					}
					
					// 스티커 붙일 수 있고 붙였다고 반환
					return true;
				}
			}
		}
		
		// 다했는데도 안 되면 안된다고 반환
		return false;
	}

	
	// 스티커 시계 방향으로 90도 회전
	static void rotate() {
		
		for(int i = 0; i < sticker.size(); i++) {

			// 90도 회전이면
			// 열이 왼쪽에서 세 번째여서 -> 행이 위에서 세 번째
			// 행이 위에서 첫 번째여서 -> 열이 오른쪽에서 첫 번째
			// 제로인덱스랑 1인덱스랑 다른 거 같네... 나중에 
			// arr(i, j) == copy(j, R - 1 - i)
			
			//**앞에서부터 하나씩 빼고 넣으니까 인덱스 주의
			int r = sticker.get(0)[0];
			int c = sticker.get(0)[1];
			
			sticker.remove(0);
			sticker.add(new int[] {c, R - 1 - r});
			
		}
		
		// 회전시키면 행렬 바꿔주기
		int temp = R;
		R = C;
		C = temp;
	}
	
	
}
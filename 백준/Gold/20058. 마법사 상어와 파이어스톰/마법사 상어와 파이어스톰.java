import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] arr;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		// 가로세로 2^N 크기의 지도
		int N = sc.nextInt();
		int len = (int)Math.pow(2, N);
		
		// 마법사 상어가 시전한 단계 Q
		int Q = sc.nextInt();
		
		// 얼음 입력
		arr = new int[len + 1][len + 1];
		for(int i = 1; i <= len; i++) {
			for(int j = 1; j <= len; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		

		for(int q = 0; q < Q; q++) {
			
			// 가로세로 2^L 크기의 부분 격자로 나누고
			int L = sc.nextInt();
			int size = (int)Math.pow(2, L);
			
			// 모든 부분 격자를 시계 방향으로 90도 회전한다
			for(int i = 0; i < len / size; i++) {
				for(int j = 0; j < len / size; j++) {
			
					// 격자O - 0 0, 0 2, 1 1, 1 3, 2 0, 2 2, 3 1, 3 3
					// 격자X - 0 1, 0 3, 1 0, 1 2, 2 1, 2 3, 3 0, 3 2
					// 둘이 더했을 때 짝수일때만 회전한다 ***여기서 틀렸다
					// ***아니 모든 격자를 회전시킨대... 아오
					// if((i + j) % 2 != 0) continue;
					
					// 시작점
					int r = 1 + size * i;
					int c = 1 + size * j;
					
					// 회전시키기
					rotate(r, c, size);
				}
			}

			
			// 얼음 줄어드는 칸 저장
			boolean[][] reduce = new boolean[len + 1][len + 1];
			
			// 주변에 얼음이 3~4개가 아니면 얼음 1 줄어든다
			for(int r = 1; r <= len; r++) {
				for(int c = 1; c <= len; c++) {
					
					// 이미 얼음이면 하지 않는다
					if(arr[r][c] == 0) continue;

					// 주변 얼음 개수
					int cnt = 0;
					
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if(nr < 1 || nr > len || nc < 1 || nc > len) continue;
						
						// 주변이 얼음이면 증가시키기
						if(arr[nr][nc] > 0) cnt++;
					}

					// 인접 얼음이 3개보다 작으면 얼음 감소
					if(cnt < 3) {
						reduce[r][c] = true;
					}
					
				}
			}

			// 줄어드는 얼음 반영
			for(int r = 1; r <= len; r++) {
				for(int c = 1; c <= len; c++) {
					if(reduce[r][c]) arr[r][c]--;
				}
			}
			
			
			// 확인용 출력
//			for(int r = 1; r <= len; r++) {
//				for(int c = 1; c <= len; c++) {
//					System.out.print(arr[r][c] + " ");
//				}
//				System.out.println();
//			}
			
		}
		
		
		// 정답 찾기
		// 남아있는 얼음 A[r][c]
		int sum = 0;
		
		// 가장 큰 덩어리가 차지하는 칸의 개수
		int max = 0;
		
		// 덩어리 확인 visited
		boolean[][] visited = new boolean[len + 1][len + 1];
		
		for(int r = 1; r <= len; r++) {
			for(int c = 1; c <= len; c++) {
				if(visited[r][c]) continue;

				// 얼음 수 증가 + 방문
				sum += arr[r][c];
				visited[r][c] = true;
				
				// 이거 얼음이 없어서 0이면 다음꺼
				if(arr[r][c] == 0) continue;
				
				// 이 얼음을 기준으로 덩어리 갯수 세기
				Queue<int[]> q = new LinkedList<>();
				q.add(new int[] {r, c});
				int cnt = 1;
				
				while(!q.isEmpty()) {
					int cr = q.peek()[0];
					int cc = q.poll()[1];
					
					for(int d = 0; d < 4; d++) {
						int nr = cr + dr[d];
						int nc = cc + dc[d];
						if(nr < 1 || nr > len || nc < 1 || nc > len) continue;
						
						// 이미 방문했으면 패스(아마 이미 얼음 아닌 거)
						if(visited[nr][nc]) continue;
						
						// 방문하고
						visited[nr][nc] = true;
						
						// 그런데 얼음이면 카운트 증가하고 연결
						if(arr[nr][nc] > 0) {
							cnt++;
							sum += arr[nr][nc];
							q.add(new int[] {nr, nc});
						}
					}
				}
				
				// 덩어리 최댓값 갱신
				max = Math.max(max, cnt);
				
			}
		}
		
		
		System.out.println(sum);
		System.out.println(max);
		
	}

	
	// o(1) additional memory
	// 메모리제한으로 2차원 배열 90도 돌리기
	public static void rotate(int r, int c, int N) {
		
		// NxN 배열 돌리기 코드 테스트
		// 겉 한바퀴부터 안쪽으로 들어간다
		for(int i = 1; i <= N / 2; i++) {
			
			// 가로변이
			// (i, i)에서 시작해서 (i, N - (i-1))까지니까
			// 가로길이가 4면 3개까지만 돌려주기
			for(int j = i; j < N - (i - 1); j++) {
				
				// (r, c)부터 시작하게 좌표 보정
				// i + r - 1
				// j + c - 1
				// 값을 구할 때만 보정하기
				
				// 메모리제한 O(1) 이니까
				// (i, j)에서 시작해서 하나씩 돌리기
				int temp = arr[r + i - 1][c + j - 1];
				
				// 4번 돌리면서 변하는 행과 열 저장
				int cr = i;
				int cc = j;
				
				for(int k = 0; k < 4; k++) {
					
					// (r, c)에서 90도 돌려서 덮는 위치
					// 원래 자리의 열이 -> 행으로 간 자리를 덮는다
					// 원래 자리의 위에서 r번째 행이 -> 오른쪽에서 r번째 열으로 간 자리를 덮는다 
					int nr = cc;
					int nc = N - cr + 1;
					
					// 행과 열 갱신
					cr = nr;
					cc = nc;
					
					// 지금 덮으려는 숫자 임시 저장
					int temp1 = arr[r + cr - 1][c + cc - 1];
					
					// 왼쪽이 이동한 자리고 = 오른쪽이 원래 자리다
					// arr[j][N - i + 1] = arr[i][j];
					// 이전에 저장해둔 값 넣기
					arr[r + cr - 1][c + cc - 1] = temp;
					
					// 덮어씌워진 값을 다음에 쓰기 위해서 전달
					temp = temp1;
				}
			}
		}
	}
	
	
	
	// 배열 회전 로직 확인용 문제
	public static void rotate() {
		Scanner sc = new Scanner(System.in);

		// NxN 배열 입력 
		int N = sc.nextInt();
		
		int[][] arr = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// 겉 한바퀴부터 안쪽으로 들어간다
		for(int i = 1; i <= N / 2; i++) {
			
			// 가로변이 
			// (i, i)에서 시작해서 (i, N - (i-1))까지니까
			// 가로길이가 4면 3개까지만 돌려주기
			for(int j = i; j < N - (i - 1); j++) {
				
				// 메모리제한 O(1) 이니까
				// (i, j)에서 시작해서 하나씩 돌리기
				int temp = arr[i][j];
				
				// 4번 돌리면서 변하는 행과 열 저장
				int r = i;
				int c = j;
				
				for(int k = 0; k < 4; k++) {
					
					// (r, c)에서 90도 돌려서 덮는 위치
					// 원래 자리의 열이 -> 행으로 간 자리를 덮는다
					// 원래 자리의 위에서 r번째 행이 -> 오른쪽에서 r번째 열으로 간 자리를 덮는다 
					int nr = c;
					int nc = N - r + 1;
					
					// 행과 열 갱신
					r = nr;
					c = nc;
					
					// 지금 덮으려는 숫자 임시 저장
					int temp1 = arr[r][c];
					
					// 왼쪽이 이동한 자리고 = 오른쪽이 원래 자리다
					// arr[j][N - i + 1] = arr[i][j];
					// 이전에 저장해둔 값 넣기
					arr[r][c] = temp;
					
					// 덮어씌워진 값을 다음에 쓰기 위해서 전달
					temp = temp1;
				}
			}
		}
		
		// 확인용 출력
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
	}
	
}
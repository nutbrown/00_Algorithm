import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int K;
	static int[][][] map;
	static int[][] srks;
	static int srksN;
	static int[][][] dir;
	static int t;
	
	// 1, 2, 3, 4는 각각 위, 아래, 왼쪽, 오른쪽
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);

    	// N x N 크기의 격자
    	N = sc.nextInt();
    	
    	// 상어 M 마리
    	M = sc.nextInt();
    	
    	// 냄새는 k초 뒤에 사라진다
    	K = sc.nextInt();
    	
    	
    	// 상어가 있으면 -> [i][j][0]에 상어 번호
    	// 빈칸이면 -> [i][j][0]이 0인데 [i][j][1]에 냄새정보 없는 경우
    	// 냄새가 있으면 -> [i][j][0]이 어쨌든 [i][j][1]에 무슨 상어인지, [i][j][2]에 몇 초 남았는지
    	map = new int[N][N][3];
    	
    	// 상어 위치 행, 열, 방향
    	// 상어가 죽으면 방향을 -1로 표시한다
    	srks = new int[M + 1][3];
    	
    	// 상어 총 마릿수
    	srksN = M;
    	
    	// 지도 입력
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			map[i][j][0] = sc.nextInt();
    			
    			if(map[i][j][0] > 0) {
    				srks[map[i][j][0]][0] = i;
    				srks[map[i][j][0]][1] = j;
    			}
    		}
    	}
    	
    	// 상어 초기 방향 입력
    	for(int i = 1; i <= M; i++) {
    		srks[i][2] = sc.nextInt();
    	}
    	
    	
    	// 상어의 방향 우선순위
    	dir = new int[M + 1][5][4];
    	for(int i = 1; i <= M; i++) {
    		// 위, 아래, 왼쪽, 오른쪽을 향할 때 우선순위
    		for(int j = 1; j <= 4; j++) {
    			for(int k = 0; k < 4; k++) {
    				dir[i][j][k] = sc.nextInt();
    			}
    		}
    	}
    	
    	
    	// 1번 상어만 격자에 남게 되기까지 걸리는 시간
    	t = 0;

    	shark();	
    	
    	// 1000초가 넘어도 다른 상어가 격자에 남아있으면 -1
    	System.out.println(t > 1000 ? -1 : t);
    }
    
    
    
    // 1초가 지날 때 상황 변화
    // 그냥 반복문으로 해도 될 거 같으니까, 이왕 뺀 거 함수를 많이 만들자, 반복되는 걸 함수로 빼야한다
    static void shark() {
    	
    	// 시간이 1000초가 넘었으면 돌아가기. while로 할 걸
    	if(t > 1000) return;
    	
    	// 상어 마릿수가 1마리면 돌아가기
    	if(srksN == 1) return;
    	
    	// 1. 모든 상어들 현재 위치에 냄새를 뿌리기
    	for(int i = 1; i <= M; i++) {
    		// 상어 죽었으면 패스
    		if(srks[i][2] == -1) continue;
    		
    		int r = srks[i][0];
    		int c = srks[i][1];
    		
    		// 냄새를 k로 저장
    		map[r][c][1] = i;
    		map[r][c][2] = K;
    	}
    	
    	
    	// 2. 모든 상어들 우선순위에 맞게 이동 + 겹치면 상어 없애기
    	for(int i = 1; i <= M; i++) {
    		// 상어 죽었으면 패스
    		if(srks[i][2] == -1) continue;
    		
    		int cr = srks[i][0];
    		int cc = srks[i][1];
    		int cd = srks[i][2];
    		
    		// 빈칸으로 움직였는지
    		boolean flag = false;
    		
    		// 빈칸이 있으면 이동하는데,
    		// 작은 상어부터 이동하니까,
    		// 그 빈칸에 작은 상어가 먼저 와있었다면,
    		// 나도 빈칸이었던 곳에 들어가서 잡아먹혀야한다
    		
    		// 방향의 우선순위대로 확인 srks[i][cd][0~3]
    		for(int d = 0; d < 4; d++) {
    			int nd = dir[i][cd][d];
    			int nr = cr + dr[nd];
    			int nc = cc + dc[nd];
    			
    			// 빈칸이란 건 냄새가 없다는 것.
    			if(inRange(nr, nc) && map[nr][nc][1] == 0) {
    				// i번 상어를 (cr, cc)에서 (nr, nc, nd)로 이동
    				moveShark(i, cr, cc, nr, nc, nd);
    				flag = true;
    				break;
    			}
    		}
    		
    		// 빈칸으로 움직였으면 다음 상어
    		if(flag) continue;

    		// 그게 아니면 본인 냄새가 있는 곳으로 이동
    		for(int d = 0; d < 4; d++) {
    			int nd = dir[i][cd][d];
    			int nr = cr + dr[nd];
    			int nc = cc + dc[nd];
    			
    			// 냄새가 있는데 본인 냄새인지
    			if(inRange(nr, nc) && map[nr][nc][1] == i) {
    				// i번 상어를 (cr, cc)에서 (nr, nc, nd)로 이동
    				moveShark(i, cr, cc, nr, nc, nd);
    				break;
    			}
    		}
    		
    		// 왔던 곳으로 되돌아가는 거여서 무조건 이동한다
    	}
    	
    	
    	// 3. 냄새 유효기간 1씩 줄이고 0되면 없애주기
    	// 상어가 k번 이동하면 냄새가 사라지니까 순서가 여기
    	// 모든 칸을 확인하면 20*20*1000 = 400,000
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			
    			// 냄새가 없으면 패스
    			if(map[i][j][1] == 0) continue;
    			
    			// 유효기간 줄여주고
    			map[i][j][2]--;
    			
    			// 0이되면 상어 없애기
    			if(map[i][j][2] == 0) {
    				map[i][j][1] = 0;
    			}
    		}
    	}
    	
    	
    	// 확인용 출력
//    	System.out.println();
//    	System.out.println("상어 지도  |  냄새 지도(상어번호 몇초)");
//    	for(int i = 0; i < N; i++) {
//    		for(int j = 0; j < N; j++) {
//    			System.out.print(map[i][j][0] + " ");
//    		}
//    		System.out.print("     ");
//    		for(int j = 0; j < N; j++) {
//    			System.out.print(map[i][j][1] +""+ map[i][j][2] + " ");
//    		}
//    		System.out.println();
//    	}
//    	for(int i = 1; i <= M; i++) {
//    		System.out.print(srks[i][2] + " ");
//    	}
//    	System.out.println();
    	
    	
    	// 시간 증가하고 반복
    	t++;
    	shark();
    	
    }

    
    
    // s번 상어를 (cr, cc)에서 (nr, nc, nd)로 이동
    static void moveShark(int s, int cr, int cc, int nr, int nc, int nd) {
    	
    	// 지도에서 현재 위치 지우기
    	map[cr][cc][0] = 0;
    	
    	// 이동할 곳이 상어 있으면 겨루기
    	// 아직 모든 상어들이 이동한 게 아니니까, 이동했는데, 상어 번호가 나보다 작다면, 겨루기
    	if(map[nr][nc][0] != 0 && map[nr][nc][0] < s) {
    		
    		// 상어 번호가 나보다 작아서 어짜피 잡아먹힌다
    		// 지도에 위치 입력 못하고 그냥 죽기
    		srks[s][2] = -1;
    		
    		// 상어 마릿수 감소
    		srksN--;
    	}
    	
    	// 빈칸이면 그냥 이동하기
    	// 번호 큰 상어가 있어도, 어짜피 다른데로 갈거니까 덮어씌우기
    	// 상어 위치 정보는 상어배열에 있는 걸 쓴다 
    	else {
    		srks[s][0] = nr;
    		srks[s][1] = nc;
    		srks[s][2] = nd;
    		
    		// 상어가 들어가고
    		map[nr][nc][0] = s;
    		
    		// 냄새를 여기서 없애면 다음 상어가 들어온다
    		// 냄새는 상어가 여기를 뜨면서 덮는다
    		//map[nr][nc][1] = 0;
    		//map[nr][nc][2] = 0;
    	}
    }
    
    
    // 범위 안에 있는지 확인
    static boolean inRange(int r, int c) {
    	if(r >= 0 && r < N && c >= 0 && c < N) return true;
    	else return false;
    }
    
}
// 1H 10M

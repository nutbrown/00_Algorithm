import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] map;
	static ArrayList<int[]> bishop1;
	static ArrayList<int[]> bishop2;
	static int max1;
	static int max2;

	// 대각선 네 방향말고 위쪽 두 방향만
	static int[] dr = {-1, -1};
	static int[] dc = {1, -1};
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);

    	// 체스판 크기 (10 이하)
    	N = sc.nextInt();
    	
    	// 비숍 놓을 수 있는 곳 저장
    	bishop1 = new ArrayList<>();
    	bishop2 = new ArrayList<>();
    	
    	// 비숍 놓을 수 있는 곳 1, 놓을 수 없는 곳 0
    	map = new int[N][N];
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			map[i][j] = sc.nextInt();
    			
    			// (0, 0) 부터 시작하는 게 비숍1
    			// 00 02 04 11 13 22	-> 행 열 더하면 짝수 
    			// (0, 1) 부터 시작하는 게 비숍2
    			// 01 03 10 12 30 14	-> 행 열 더하면 홀수
    			
    			if(map[i][j] == 1) {
    				if((i + j) % 2 == 0) bishop1.add(new int[] {i, j});
    				else bishop2.add(new int[] {i, j});
    			}
    		}
    	}
    	
    	
    	// 서로가 서로를 잡을 수 없는 위치에 놓을 수 있는 비숍 최대 개수
    	max1 = 0;
    	max2 = 0;
    	
    	// 비숍 몇 번째 자리를 하고 있는지, 비숍 몇 개 놓았는지, 비숍 타입
    	dfs(0, 0, 1);
    	dfs(0, 0, 2);
    	
    	System.out.println(max1 + max2);
    	
    }
    
    
    // 비숍 1개 놓을 때마다 안 되는 자리를 계속 탐색하면 시간 오래 걸린다
    // 비숍 1개 놓을 때 -> 탐색해서 비숍 만나면 돌아오는 걸로 하기
    // 대각선이 2개 영역으로 나뉘어서 서로 안 겹친다
    // 조합하는 칸 수가 반으로 줄어들어서 더 빠른가보다
    
    // 비숍 놓을 수 있는 자리에 비숍을 놓느냐 안 놓느냐 
    static void dfs(int depth, int cnt, int type) {

    	// 타입별로 같은 방식으로 걸러내기
    	if(type == 1) {
    		// 남은 자리 다 비숍 놓아도 최댓값보다 작으면 중단 ->
    		// 총 bishop1개 중에서 depth개를 확인했는데 cnt만큼 놓았다
    		// 아직 확인하지 않은 bishop1-depth개에 비숍을 다 놓을 수 있어도
    		// 거기에 cnt를 더했을 때 max보다 작거나 같으면 중지
    		if(bishop1.size() - depth + cnt < max1) return;
    		
    		// 비숍 놓을 수 있는 거 끝까지 다 확인했으면 최댓값 갱신하고 돌아가기
    		if(depth == bishop1.size()) {
    			max1 = Math.max(max1, cnt);
    			return;
    		}
    	}
    	else {
    		if(bishop2.size()  - depth + cnt < max2) return;
    		if(depth == bishop2.size()) {
    			max2 = Math.max(max2, cnt);
    			return;
    		}
    	}

    	
    	// 지금 놓을 수 있는 자리 행,열
    	int cr = 0;
    	int cc = 0;
    	if(type == 1) {
    		cr = bishop1.get(depth)[0];
    		cc = bishop1.get(depth)[1];
    	} else {
    		cr = bishop2.get(depth)[0];
    		cc = bishop2.get(depth)[1];
    	}
    	
    	
    	// 대각선으로 내려가서 비숍이 있는지 확인
    	// 사전순 조합으로, 위에서부터 놓으니까 아래는 확인할 필요 없다
    	boolean flag = true;
    	
    	loop:
    	for(int d = 0; d < 2; d++) {
    		// 다음 좌표
    		int nr = cr + dr[d];
    		int nc = cc + dc[d];
    		
    		// 범위 안에 있다면 놓을 수 없다고 표시하기
    		while(inRange(nr, nc)) {
    			
    			// 비숍이 있으면 안 된다
    			if(map[nr][nc] == 2) {
    				flag = false;
    				break loop;
    			}
    			
    			// 다음 좌표
    			nr += dr[d];
    			nc += dc[d];
    		}
    	}
    	
    	// 4방향 다 봤는데 비숍이 없으면 비숍 놓기
    	if(flag) {
    		// 비숍 놓고 넘어가기
    		map[cr][cc] = 2;
    		
    		// 다음 단계 진행
    		dfs(depth + 1, cnt + 1, type);
    		
    		// 돌아오면 원상복귀
    		map[cr][cc] = 1;
    	}
    	
    	// 이러나저러나 비숍 안 놓고 넘어가기
    	dfs(depth + 1, cnt, type);

    }


    
    // 범위 밖을 벗어나는지
    static boolean inRange(int r, int c) {
    	if(r < 0 || r >= N || c < 0 || c >= N) {
    		return false;
    	} else {
    		return true;
    	}
    }
}

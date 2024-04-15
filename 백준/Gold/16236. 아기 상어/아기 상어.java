import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int r;
	static int c;
	static int size;
	static int cnt;
	static int[][] map;
	static int t;
	static ArrayList<int[]> fishes;
	
	// 가까운 물고기를 찾을 때, 위에 왼쪽에 있는 것부터 찾기 **안 된다
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 공간 크기 N
    	N = sc.nextInt();
    	
    	// 상어 정보
    	r = 0;
    	c = 0;
    	size = 2;
    	
    	// 물고기는 자기 크기수만큼 물고기를 먹어야지
    	// 크기가 1 증가한다
    	cnt = 0;
    	
    	// 빈칸0, 물고기 1~6, 아기상어9
    	map = new int[N][N];
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			map[i][j] = sc.nextInt();
    			
    			// 상어 위치 입력
    			if(map[i][j] == 9) {
    				r = i;
    				c = j;
    			}
    		}
    	}
    	
    	// 먹을 수 있는 물고기가 있을 때까지
    	t = 0;
    	
    	// 물고기 먹을 수 있으면, 먹고 이동하고 true
    	// 물고기 먹을 수 없으면, false
    	while(getFish()) {
    		// 시간 증가시키려고 했는데...
    	}
    	
    	System.out.println(t);
    }
    
    
    static boolean getFish() {
    	
    	// 1마리가 아니라 여러 마리면, 가장 가까운 물고기
    	// 거리가 가까운 물고기가 많다면, 가장 위에 왼쪽에 있는 물고기
    	fishes = new ArrayList<>();

    	// 상어 위치에서 가까운 물고기 찾기
    	Queue<int[]> q = new LinkedList<>();
    	boolean[][] visited = new boolean[N][N];
    	
    	q.add(new int[] {r, c, t});
    	visited[r][c] = true;
    	
    	
    	while(!q.isEmpty()) {
    		int cr = q.peek()[0];
    		int cc = q.peek()[1];
    		int ct = q.poll()[2];
    		
    		// 잡을 수 있는 물고기 나왔는데, 더 먼 물고기 나오면
    		// 물고기 찾기 중단하고 물고기 먹기
    		if(fishes.size() > 0 && fishes.get(0)[2] < ct) {
    			break;
    		}
    		
    		// 아기상어가 먹을 수 있는 물고기면 저장
    		if(map[cr][cc] > 0 && map[cr][cc] < size) {
    			fishes.add(new int[] {cr, cc, ct});
    		}
    		
    		for(int d = 0; d < 4; d++) {
    			int nr = cr + dr[d];
    			int nc = cc + dc[d];
    			
    			// 범위 이내인지, 이미 방문했는지
    			if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
    			
    			// 아기상어 크기보다 크면 못 지나간다
    			if(map[nr][nc] > size) continue;
    			
    			// 이동하기
    			q.add(new int[] {nr, nc, ct + 1});
    			visited[nr][nc] = true;
    			
    		}
    	}
    	
    	
    	// 잡을 수 있는 물고기가 있으면 true
    	if(fishes.size() > 0) {
    		eatFish();
    		return true;
    	}
    	// 물고기 없으면 false 반환
    	else {
    		return false;
    	}
    }
    
    
    static void eatFish() {
    	// 위쪽부터 왼쪽부터 물고기 정렬 **오 메소드 제대로 적었다
    	Collections.sort(fishes, new Comparator<>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// o1-o2 처럼 오름차순으로 쓰면 오름차순이다
				// 앞에서 뒤에꺼를 뺐더니 마이너스면 안 바꾼다.
				// 앞에서 뒤에꺼를 뺐더니 플러스면 바꾼다.
				
				// 가장 위에 있는
				if(o1[0] != o2[0]) return o1[0] - o2[0];
				
				// 같다면 가장 왼쪽에 있는
				else return o1[1] - o2[1];
			}
    		
    	});
    	
    	// 물고기 정하기
    	int cr = fishes.get(0)[0];
    	int cc = fishes.get(0)[1];
    	int ct = fishes.get(0)[2];
    	
    	// 상어자리 없애기
		map[r][c] = 0;

		// 물고기 위치로 이동
		r = cr;
		c = cc;
		
		// 잡은 물고기수랑 시간 증가
		cnt++;
		t = ct;
	
		// 본인 크기만큼 먹었으면 크기 증가
		if(cnt == size) {
			cnt = 0;
			size++;
		}
		
		// 물고기 없애고 상어이동
		map[cr][cc] = size;
		
//		System.out.println(cr + " " + cc + " / " + size + " " + cnt +  " " + ct);
//		for(int i = 0; i < N; i++) {
//    		for(int j = 0; j < N; j++) {
//    			System.out.print(map[i][j] + " ");
//    		}
//    		System.out.println();
//		}
//		System.out.println();
    	
    }
}
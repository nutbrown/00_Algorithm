import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int W;
	static int H;
	static boolean[][] map;
	// 최소한의 값으로 이동
	static int min = -1;
	
	// 말처럼 이동하는 것
	static int[] hr = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] hc = {2, 1, -1, -2, -2, -1, 1, 2};
	
	// 인접하게 이동하는 것
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static Queue<Node> q;
	static boolean[][][] visited;
	
	// 배열이 아니라 객체 선언으로 메모리초과 해결
	static class Node {
		int r;
		int c;
		int horse;
		int cnt;
		
		Node(int r, int c, int horse, int cnt) {
			this.r = r;
			this.c = c;
			this.horse = horse;
			this.cnt = cnt;   
		}
	}
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 원숭이는 K번만 말처럼 움직일 수 있다
    	K = Integer.parseInt(br.readLine());
    	
    	// 가로길이 열 W, 세로길이 행 H
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	W = Integer.parseInt(st.nextToken());
    	H = Integer.parseInt(st.nextToken());
    	
    	// 0은 평지, 1은 장애물
    	map = new boolean[H][W];
    	for(int i = 0; i < H; i++) {
        	st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < W; j++) {
    			int in = Integer.parseInt(st.nextToken());
    			if(in == 1) map[i][j] = true;
    			else map[i][j] = false;
    		}
    	}
    	
    	
    	// (0, 0)에서 (W-1, H-1)까지 가야한다
    	q = new ArrayDeque<>();
    	
    	// 벽 부수고 이동이랑 비슷 : 0번부터 K번 사용한 visited
    	visited = new boolean[H][W][K + 1];
    	
    	// 0, 0에서 시작해서 말처럼 0번 이동했고, 동작수는 0번
    	q.add(new Node(0, 0, 0, 0));
    	visited[0][0][0] = true;
    	
    	
    	while(!q.isEmpty()) {
    		Node node = q.poll();
    		int cr = node.r;
    		int cc = node.c;
    		int horse = node.horse;
    		int cnt = node.cnt;
    		
    		// 도착점에 도착했다면 -> 이게 최소니까 끝
    		// 굳이 Math.min 할 필요 없다
    		if(cr == H - 1 && cc == W - 1) {
    			min = cnt;
    			break;
    		}
    		
    		// 말처럼 갈 수 있는 횟수가 남았다면
    		if(horse < K) {
    			// 8가지 방법을 다 가본다
    			for(int d = 0; d < 8; d++) {
    				int nr = cr + hr[d];
    				int nc = cc + hc[d];
    				
    				if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
    				if(visited[nr][nc][horse + 1]) continue;
    				// 방해물이 있는 곳에는 갈 수 없다
    				if(map[nr][nc]) continue;
    				
    				q.add(new Node(nr, nc, horse + 1, cnt + 1));
    				visited[nr][nc][horse + 1] = true;
    			}
    		}
    		
    		// 그거랑 상관없이 인접한 곳으로 간다
    		for(int d = 0; d < 4; d++) {
    			int nr = cr + dr[d];
    			int nc = cc + dc[d];
    			
    			if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
    			if(visited[nr][nc][horse]) continue;
				if(map[nr][nc]) continue;
    			
				q.add(new Node(nr, nc, horse, cnt + 1));
    			visited[nr][nc][horse] = true;
    		}
    	}
    	
    	
    	// 갈 수 없으면 -1 출력
    	System.out.println(min);
    }
}

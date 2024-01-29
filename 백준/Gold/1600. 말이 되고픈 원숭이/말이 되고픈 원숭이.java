import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 메모리 133584KB, 시간 1136 ms
	public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 원숭이는 K번만 말처럼 움직일 수 있다
    	int K = sc.nextInt();
    	
    	// 가로길이 열 W, 세로길이 행 H
    	int W = sc.nextInt();
    	int H = sc.nextInt();
    	
    	// 0은 평지, 1은 장애물
    	boolean[][] map = new boolean[H][W];
    	for(int i = 0; i < H; i++) {
    		for(int j = 0; j < W; j++) {
    			int in = sc.nextInt();
    			if(in == 1) map[i][j] = true;
    			else map[i][j] = false;
    		}
    	}
    	
    	// 최소한의 값으로 이동
    	int min = -1;
    	
    	// 말처럼 이동하는 것
    	int[] hr = {1, 2, 2, 1, -1, -2, -2, -1};
    	int[] hc = {2, 1, -1, -2, -2, -1, 1, 2};
    	
    	// 인접하게 이동하는 것
    	int[] dr = {0, 1, 0, -1};
    	int[] dc = {1, 0, -1, 0};
    	
    	// (0, 0)에서 (W-1, H-1)까지 가야한다
    	Queue<int[]> q = new LinkedList<>();
    	
    	// 벽 부수고 이동이랑 비슷 : 0번부터 K번 사용한 visited
    	boolean[][][] visited = new boolean[H][W][K + 1];
    	
    	// 0, 0에서 시작해서 말처럼 0번 이동했고, 동작수는 0번
    	q.add(new int[] {0, 0, 0, 0});
    	visited[0][0][0] = true;
    	
    	
    	while(!q.isEmpty()) {
    		int cr = q.peek()[0];
    		int cc = q.peek()[1];
    		int horse = q.peek()[2];
    		int cnt = q.poll()[3];
    		
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
    				// **메모리초과이유
    				// horse + 1에서 visited를 찾아줘야 하는데
    				// horse에서 visited를 찾아주고 있었다
    				if(visited[nr][nc][horse + 1]) continue;
    				// 방해물이 있는 곳에는 갈 수 없다
    				if(map[nr][nc]) continue;
    				
    				q.add(new int[] {nr, nc, horse + 1, cnt + 1});
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
    			
    			q.add(new int[] {nr, nc, horse, cnt + 1});
    			visited[nr][nc][horse] = true;
    		}
    	}
    	
    	
    	// 갈 수 없으면 -1 출력
    	System.out.println(min);
    }
}

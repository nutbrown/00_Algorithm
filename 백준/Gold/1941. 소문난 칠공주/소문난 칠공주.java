import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static char[][] map;
	static int cnt;
	static int[] combi;
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// '이다솜파'가 소문난칠공주를 결성하는 경우의 수
    	// 서로 인접해있는 7명
    	// 7명 중에서 4명 이상이 '이다솜파'여야 한다
    	// 7명 중에서 '임도연파'는 최대 3명이다
    	
    	map = new char[5][5];
    	for(int i = 0; i < 5; i++) {
    		String in = sc.next();
    		for(int j = 0; j < 5; j++) {
    			map[i][j] = in.charAt(j);
    		}
    	}
    	
    	// 겹치지 않게 하는 게 문제
    	// 25칸 중에서 7칸 경우의 수를 조합으로 구한다
    	
    	// 0 1 2 3 4
    	// 5 6 7 8 9
    	// 10 11 12 13 14
    	// 15 16 17 18 19
    	// 20 21 22 23 24

    	// 칸 번호를 5로 나눈 몫이 -> 행
    	// 칸 번호를 5로 나눈 나머지가 -> 열
    	
    	cnt = 0;
    	combi = new int[7];
    	combination(0, 0);
    	System.out.println(cnt);
    }
    
    
    static void combination(int depth, int idx) {
    	
    	// idx가 24일 때를 체크하고 오면 idx가 25다.
    	// 다 고른 경우를 체크하되, 새로 내려가지 않는다
    	// 7개를 다 골랐으면
    	if(depth == 7) {
    		
    		// 고른 좌표 지도에 입력
    		char[][] copy = new char[5][5];
    		
    		// 임도연파가 3개보다 크면 아웃
    		int cntY = 0;
    		
    		for(int i = 0; i < 7; i++) {
    			int r = combi[i] / 5; 
    			int c = combi[i] % 5;
    			copy[r][c] = map[r][c];
    			
    			if(copy[r][c] == 'Y') cntY++;
    			if(cntY > 3) return;
    		}
    		
    		// 서로 이어져 있는지 확인
    		int[] dr = {0, 1, 0, -1};
    		int[] dc = {1, 0, -1, 0};
    		boolean[][] visited = new boolean[5][5];
    		
    		// 하나로 시작해서 다 돌 수 있는지
    		Queue<int[]> q = new LinkedList<>();
    		q.add(new int[] {combi[0] / 5, combi[0] % 5});
    		visited[combi[0] / 5][combi[0] % 5] = true;
    		int isSeven = 1;
    		
    		while(!q.isEmpty()) {
    			int cr = q.peek()[0];
    			int cc = q.poll()[1];
    			
    			for(int j = 0; j < 4; j++) {
    				int nr = cr + dr[j];
    				int nc = cc + dc[j];
    				
    				// 범위 안에 있는지
    				if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
    				// 방문한 게 아닌지
    				if(visited[nr][nc]) continue;
    				
    				// 인접한 게 있으면 true
    				if(copy[nr][nc] == 'Y' || copy[nr][nc] == 'S') {
    					q.add(new int[] {nr, nc});
    					visited[nr][nc] = true;
    					isSeven++;
    				}
    			}
    		}
    		
    		// 7개가 다 이어져 있으면, 경우의 수 증가
    		if(isSeven == 7) cnt++;
    		return;
    	}
    	
    	
    	// idx가 25면 새로 내려가지 않는다
    	// 이걸 위에 둬서 idx 24가 안 들어갔다
    	if(idx == 25) return;
    	
    	// idx를 고르고 내려가는 거 하나
    	combi[depth] = idx;
    	combination(depth + 1, idx + 1);
    	
    	// idx를 안 고르고 내려가는 거 하나
    	combination(depth, idx + 1);
    }
}

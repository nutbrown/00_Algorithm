import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// N x M 지도
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    	
    	// 지도 입력
    	int[][] map = new int[N][M];
    	for(int i = 0; i < N; i++) {
    		String in = sc.next();
    		for(int j = 0; j < M; j++) {
    			map[i][j] = Character.getNumericValue(in.charAt(j));
    		}
    	}
    	
    	// 0이 모여있는 구간 저장하기
    	// 0은 빈칸, 1은 벽이니까, 2부터
    	HashMap<Integer, Integer> hm = new HashMap<>();
    	int key = 2;
    	
    	// bfs 준비
    	Queue<int[]> q = new LinkedList<>();
    	boolean visited[][] = new boolean[N][M];
    	int[] dr = {0, 1, 0, -1};
    	int[] dc = {1, 0, -1, 0};
    	
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			if(!visited[i][j] && map[i][j] == 0) {
    				
    				// 넓이 저장
    				int area = 1;
    				
    				// 큐 시작
    				q.clear();
    				q.add(new int[] {i, j});
    				visited[i][j] = true;
    				map[i][j] = key;
    				
    				while(!q.isEmpty()) {
    					int cr = q.peek()[0];
    					int cc = q.poll()[1];
    					
    					for(int d = 0; d < 4; d++) {
    						int nr = cr + dr[d];
    						int nc = cc + dc[d];
    						
    						if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
    						if(visited[nr][nc]) continue;
    						
    						// 0이라면 영역 증가
    						if(map[nr][nc] == 0) {
    							// 키 값으로 바꿔주고
    							map[nr][nc] = key;
    							// 넓이 증가
    							area++;
    							// 큐 삽입
    							q.add(new int[] {nr, nc});
    							visited[nr][nc] = true;
    						}
    						
    					}
    				}
    				
    				
    				// 영역 한 끝내면
    				// 넓이 저장 + 키 값 증가
    				hm.put(key, area);
    				key++;
    			}
    		}
    	}

    	// 상하좌우 인접칸 세어보기 (10으로 나눈)
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			// 벽이 아니라면 0 출력
    			if(map[i][j] != 1) sb.append("0");
    			
    			// 벽이라면 인접 탐색
    			else {
    				// 주변 빈공간 키 값 저장
    				ArrayList<Integer> list = new ArrayList<>();
    				for(int d = 0; d < 4; d++) {
    					int nr = i + dr[d];
    					int nc = j + dc[d];
						if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
    					
    					// 인접한 칸이 1이 아니라 빈공간 키 값이라면
    					// 키 값 저장
    					if(map[nr][nc] != 1) {
    						if(!list.contains(map[nr][nc])) list.add(map[nr][nc]);
    					}
    				}
    				
    				// 키값 바탕으로 빈공간 개수 더하기 
    				int cnt = 1;
    				for(int n : list) {
    					cnt += hm.get(n);
    				}
    				
    				// 이동할 수 있는 칸의 개수 출력
    				sb.append(cnt % 10);
    			}
    		}
    		sb.append("\n");
    	}
    	
    	System.out.println(sb);
    }
}

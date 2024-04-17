import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int G;
	static int R;
	static int[][] map;
	static ArrayList<int[]> lands;
	static ArrayList<int[]> greens;
	static ArrayList<int[]> reds;
	static int max;

	static int[][][] copy;
	static Queue<int[]> gq;
	static Queue<int[]> rq;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);

    	// 정원 너비 N x M
    	N = sc.nextInt();
    	M = sc.nextInt();
    	
    	// 초록색 배양액 G개, 빨간색 배양액 R개
    	G = sc.nextInt();
    	R = sc.nextInt();
    	
    	// 호수0, 배양액불가능1, 배양액가능2
    	map = new int[N][M];
    	
    	// 배양액 가능 땅, 초록색, 빨간색 저장
    	lands = new ArrayList<>();
    	greens = new ArrayList<>();
    	reds = new ArrayList<>();
    	
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			map[i][j] = sc.nextInt();
    			
    			if(map[i][j] == 2) lands.add(new int[] {i, j});
    		}
    	}
    	

    	// 피울 수 있는 꽃 최대 개수
    	max = 0;
    	
    	// 배양액 뿌릴 수 있는 땅의 수는 10개 이하
    	// 배양액 중에서 G개 고르고, 나머지에서 R개 고르기 조합
    	combination(0, 0, 0);
    	
    	System.out.println(max);
    	
    }
    
    
    // 초록색 빨간색 조합
    static void combination(int depth, int green, int red) {
    	
    	// 먼저 정답을 골라내고 -> 범위 안 맞는 거를 쳐내야한다 
    	// 초록색 빨강색을 맞게 골랐으면 꽃 피워보기
    	if(green == G && red == R ) {
    		// 배양액 입력
    		input();

    		// 꽃 피우기
    		bfs();
    		return;
    	}
    	
    	
    	// ***순서를 위에 두고 정답인데도 범위로 걸려서 버리고 있었다
    	// depth가 땅 개수 넘었으면 아웃
    	if(depth >= lands.size()) return;

    	// 초록색 빨강색 개수 넘었으면 아웃
    	if(green > G || red > R) return;
    	
    	// 초록색 빨강색 개수를 넘었으면 돌아가야한다
    	// 지금까지 고른 초록색 + 빨간색 총개수가 배양액땅을 넘기면 안 된다
    	if(green + R > lands.size()) return;
    	if(red + G > lands.size()) return;
    	

    	// 분양나온 땅이 무엇인가
    	int r = lands.get(depth)[0];
    	int c = lands.get(depth)[1];
    	
    	// 초록색 선택하고 내려가기 + 원상복구
    	greens.add(new int[] {r, c});
    	combination(depth + 1, green + 1, red);
    	greens.remove(greens.size() - 1);

    	// 빨강색 선택하고 내려가기 + 원상복구
    	reds.add(new int[] {r, c});
    	combination(depth + 1, green, red + 1);
    	reds.remove(reds.size() - 1);
    	
    	// 안 선택하고 내려가기
    	combination(depth + 1, green, red);
    	
    }
    
    
    // 지도에 배양액 입력
    static void input() {
    	// 먼저 사용할 지도 복사
    	// 호수0, 배양액불가능1, 배양액가능2
    	// 초록색3, 빨간색4, 꽃5 -> 이 언제 도달했는지 시간
    	copy = new int[N][M][2];
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < M; j++) {
    			copy[i][j][0] = map[i][j];
    		}
    	}
    	
    	// 초록색 배양액 큐에 넣고 지도에 입력
    	gq = new LinkedList<>();
    	for(int[] g : greens) {
    		copy[g[0]][g[1]][0] = 3;
    		gq.add(new int[] {g[0], g[1]});
    	}
    	
    	// 빨간색 배양액 큐에 넣고 지도에 입력
    	rq = new LinkedList<>();
    	for(int[] r : reds) {
    		copy[r[0]][r[1]][0] = 4;
    		rq.add(new int[] {r[0], r[1]});
    	}
    }

    
    // 배양액 퍼지면서 꽃 피우기
    static void bfs() {
    	
    	// 배양액 뿌리는 시간
    	int t = 0;
    	
    	// 피운 꽃 개수
    	int flower = 0;
    	

    	// 초록색이나 빨간색이 더이상 움직일 수 없으면 멈춘다
		// 초록색도 안 비고 빨간색도 안 빌 때만 한다
		while(!gq.isEmpty() && !rq.isEmpty()) {

			// 시간 증가
			t++;
			
			// 초록색 배양액 뿌리기 **그래도 이 로직을 기억한 게 마음에 든다
			int gsize = gq.size();
			for(int i = 0; i < gsize; i++) {
				// 행 열 색깔
				int cr = gq.peek()[0];
				int cc = gq.poll()[1];
				
				// 꺼냈는데 빨간색이랑 만나서 꽃이 된 초록색이면 넘어가기
				// *****처음부터 계속 틀린 이유. 여기 copy로 안하고 map으로 해서... 아오
				// *****오타를 정말정말 주의하자 정말 꼼꼼하게
				if(copy[cr][cc][0] == 5) continue;
				
				for(int d = 0; d < 4; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					
					// 범위 밖이거나 호수면 안 된다
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || copy[nr][nc][0] == 0) continue;
					
					// 배양액이 퍼질 수 있는 빈자리면 퍼지기
					if(copy[nr][nc][0] == 1 || copy[nr][nc][0] == 2) {
						gq.add(new int[] {nr, nc});
						
						// 초록색이랑 퍼진 시간 넣기
						copy[nr][nc][0] = 3;
						copy[nr][nc][1] = t;
					}
					
					// 여기서 만난 빨간색은 지난 시간이어서 만나서 꽃 피울 수 없다
				}
			}
			
			
			// 빨간색 배양액 뿌리기
			int rsize = rq.size();
			for(int i = 0; i < rsize; i++) {
				// 행 열 색깔
				int cr = rq.peek()[0];
				int cc = rq.poll()[1];
				
				// 빨간색이 나중에 만나서 꽃을 피우니까
				// 꺼냈는데 꽃일 경우는 없다
				
				for(int d = 0; d < 4; d++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];
					// 범위 밖이거나 호수면 안 된다
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || copy[nr][nc][0] == 0) continue;
					
					// 배양액이 퍼질 수 있는 빈자리면 퍼지기
					if(copy[nr][nc][0] == 1 || copy[nr][nc][0] == 2) {
						rq.add(new int[] {nr, nc});
						
						// 빨간색이랑 퍼진 시간 넣기
						copy[nr][nc][0] = 4;
						copy[nr][nc][1] = t;
					}
					
					// 초록색을 만났는데 같은 시간에 뿌려진 초록색이라면
					else if(copy[nr][nc][0] == 3 && copy[nr][nc][1] == t) {
						// 꽃이 되었으니까 빨간색 큐에 넣지 않고
						
						// 꽃 피우고 입력한다
						copy[nr][nc][0] = 5;
						flower++;
					}
				}
			}
		}
		
		
    	// 땅 다 뿌렸으면 꽃 최댓값 갱신
		max = Math.max(max, flower);
    	
    }
    
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int W;
	static int H;
	static char[][] map;
	static ArrayList<int[]> dirty;
	static int D;
	static int[][] dist;
	static int min;
	static boolean[] visited;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	// dc = {1, 0, -1, -1} 이라고 했네....
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		loop:
		while(true) {
			// 방 가로W 세로H
			W = Integer.parseInt(sc.next());
			H = Integer.parseInt(sc.next());

			// 0 0이면 입력 종료
			if(W == 0 && H == 0) break;
			
			// 지도 입력, 시작위치 입력, 더러운 칸 입력
			map = new char[H][W];
			dirty = new ArrayList<>();
			
			// dirty의 0번째 칸은 시작위치로 둔다
			dirty.add(null);
			
			for(int i = 0; i < H; i++) {
				
				String in = sc.next();
				for(int j = 0; j < W; j++) {
					
					map[i][j] = in.charAt(j);
					if(map[i][j] == '*') dirty.add(new int[] {i, j});
					else if(map[i][j] == 'o') dirty.set(0, new int[] {i, j});
				}
			}
			
			
			// 더러운 칸 사이 거리 구하기
			D = dirty.size();
			dist = new int[D][D];
			
			for(int i = 0; i < D; i++) {
				for(int j = i + 1; j < D; j++) {
					int dirtyDist = bfs(dirty.get(i)[0], dirty.get(i)[1], dirty.get(j)[0], dirty.get(j)[1]);
					
					// 먼지를 도달하지 못하면 -1 출력
					if(dirtyDist == -1) {
						System.out.println(-1);
						continue loop;
					}

					dist[i][j] = dirtyDist;
					dist[j][i] = dirtyDist;
				}
			}

//			System.out.println();
//			// 확인용 출력
//			for(int i = 0; i < D; i++) {
//				for(int j = 0; j < D; j++) {
//					System.out.print(dist[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			
			// 더러운 칸을 어떤 순서(순열)로 방문하면 이동 횟수가 최소일까
			min = Integer.MAX_VALUE;
			visited = new boolean[D];
			permutation(0, 0, -1);
			
			System.out.println(min);
		}
	}
	
	
	// (r1, c1)에서 (r2, c2)로 가는 거리
	static int bfs(int r1, int c1, int r2, int c2) {
		
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		
		q.add(new int[] {r1, c1, 0});
		visited[r1][c1] = true;
		
		while(!q.isEmpty()) {
			int cr = q.peek()[0];
			int cc = q.peek()[1];
			int ct = q.poll()[2];
			
			// 도착하면 거리 반환
			if(cr == r2 && cc == c2) return ct;
			
			for(int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
				if(visited[nr][nc] || map[nr][nc] == 'x') continue;
				
				q.add(new int[] {nr, nc, ct + 1});
				visited[nr][nc] = true;
			}
		}
		
		// 도착못하면 -1 반환
		return -1;
	}
	
	
	// 순열의 깊이, 지금까지 이동거리, 어느 점에서 왔는지
	static void permutation(int depth, int d, int idx) {

		// 현재 이동거리가 min보다 크거나 같으면 멈춤
		if(d >= min) return;
		
		// depth가 D가 되면 최솟값 입력
		if(depth == D) {
			min = d;
			return;
		}
		
		// 첫번째 순서는 무조건 시작점인 0번이다
		if(depth == 0) {
			visited[0] = true;
			permutation(depth + 1, 0, 0);
			
			// 시작점으로 돌아왔으면 끝
			return;
		}

		// 0번부터 D번까지 골라서 넣기
		for(int i = 0; i < D; i++) {
			if(visited[i]) continue;
			
			// 안 골랐으면, 고르고 다음꺼 진행
			visited[i] = true;
			permutation(depth + 1, d + dist[idx][i], i);
			
			// 이 depth에 이 idx를 고른 걸 다 했으면,
			// for문을 돌아서 다음 idx를 고른 걸 한다
			// 원상복귀 하지 말고 들고다녀야지 **visited는 원상복귀해야지 아래서 쓰지
			visited[i] = false;
		}
		
		
	}
	
	
	
}
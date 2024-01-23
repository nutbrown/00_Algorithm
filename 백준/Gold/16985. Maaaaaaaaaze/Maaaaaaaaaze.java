import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][][] input;
	static int[][][] copy;
	static int[] order;
	static int[] visited;
	static int min;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 판 5개를 쌓아서 미로 탈출
		// 판을 시계방향, 반시계방향으로 회전할 수 있다
		// 판 5개 쌓는 순서는 자유롭게
		// 입구를 정하면 반대편이 출구. 입구는 자유롭게
		// 가장 적은 이동 횟수로 탈출하는 게 몇 번 이동인가
		
		// 0은 참가자가 들어갈 수 없는 칸
		// 1은 참가자가 들어갈 수 있는 칸
		
		// 전체 판 입력 (제로X)
		input = new int[5][5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 5; k++) {
					input[i][j][k] = sc.nextInt();
				}
			}
		}
		
		// 들어가는 입구를 고정하면
		// 쌓는 순서는 : 5 * 4 * 3 * 2 * 1
		// 5개의 판을 돌릴 때 : 4 * 4 * 4 * 4 * 4

		// 가장 적은 이동 횟수
		min = Integer.MAX_VALUE;
		
		// 1. 쌓는 순서별로 경우의 수
		order = new int[5];
		visited = new int[5];
		perm(0);
		
		
		// 탈출 못하면 -1 출력
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
		
	}
	
	
	// 판 쌓는 순서 : 순열
	static void perm(int depth) {
		if(depth == 5) {
			// 2. 판 순서대로 다 돌려보기
			// 순서대로 copy에 쌓음
			copy = new int[5][5][5];
			for(int i = 0; i < 5; i++) {
				// clone()으로 깊은 복사
				copy[i] = input[order[i]].clone();
			}
			
			rotate();
			return;
		}
		
		for(int i = 0; i < 5; i++) {
			if(visited[i] == 0) {
				// depth번째 판을 i로 골랐다
				order[depth] = i;
				visited[i] = 1;
				perm(depth + 1);
				
				// depth번째 판 i 해제
				order[depth] = 0;
				visited[i] = 0;
			}
		}
	}
	
	
	// 판 5개 돌려보기 : 중복순열
	static void rotate() {
		// 01234에 해당하는 판이 정해져있다
		// 5중 포문으로 (0~4, 0~4, 0~4, 0~4, 0~4) 돌리는 횟수 모든 경우의 수
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				for(int k = 0; k < 4; k++) {
					for(int l = 0; l < 4; l++) {
						for(int m = 0; m < 4; m++) {

							// 확인용 출력 -> 판 배치는 문제 없다
							
							// 이게 완성된 판 -> bfs로 탈출 가능 여부 찾기
							// 입구가 1이어서 들어갈 수 있을 때만!!!! bfs 탐색
							if(copy[0][0][0] == 1) bfs();
							
							copy[4] = clockwise(copy[4]);
						}
						copy[3] = clockwise(copy[3]);
					}
					copy[2] = clockwise(copy[2]);
				}
				copy[1] = clockwise(copy[1]);
			}
			
			// 경우의 수 1번 돌면 1번 회전시킨다
			copy[0] = clockwise(copy[0]);
		}
		
	}
	
	
	// 판 시계방향 회전
	static int[][] clockwise(int[][] map1) {
		
		int[][] map2 = new int[5][5];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				// (행, 열) -> (열, 행대칭(4 - idx))
				// map1[i][j]의 값을 map2[j][4 - i]로 옮긴다
				map2[j][4 - i] = map1[i][j];
			}
		}
		return map2;
	}
	
	
	// 탈출 가능한지 확인
	static void bfs() {
		// (0, 0, 0)에서 (4, 4, 4)로 갈 수 있는지 
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visited = new boolean[5][5][5];
		int[] dr = {0, 1, 0, -1, 0, 0};
		int[] dc = {1, 0, -1, 0, 0, 0};
		int[] dz = {0, 0, 0, 0, 1, -1};
		
		q.add(new int[] {0, 0, 0, 0});
		visited[0][0][0] = true;
		while(!q.isEmpty()) {
			int cr = q.peek()[0];
			int cc = q.peek()[1];
			int cz = q.peek()[2];
			int t = q.poll()[3];
			
			// 나왔더니 (4, 4, 4)라면 이동횟수는 t
			// 꼭짓점 자체가 입구여서 시간 0이고
			// 다른 꼭짓점 자체가 출구여서 밖으로 나가지 않아도 꼭짓점에 도달하면 탈출 
			if(cr == 4 && cc == 4 && cz == 4) {
				min = Math.min(min, t);
				break;
			}
			
			for(int i = 0; i < 6; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				int nz = cz + dz[i];
				
				// 범위를 벗어나면 안 된다
				if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || nz < 0 || nz >= 5) continue;
				// 0이어서 못 들어가거나 || 이미 방문했으면 안 된다
				if(copy[nr][nc][nz] == 0 || visited[nr][nc][nz]) continue;
				
				// 다 통과했으면 큐에 삽입
				q.add(new int[] {nr, nc, nz, t + 1});
				visited[nr][nc][nz] = true;
			}
		}
	}
	
	
	// 확인용 출력 -> 판 배치는 문제 없다
//	for(int a = 0; a < 5; a++) {
//		for(int b = 0; b < 5; b++) {
//			for(int c = 0; c < 5; c++) {
//				System.out.print(copy[a][b][c] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}						
//	System.out.println("==============");
	
}

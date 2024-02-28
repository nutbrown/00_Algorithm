import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int[][] map;
	static int[][] copy;
	static int cnt;
	static ArrayList<int[]> removeList;
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 7x7 격자에서 진행하는 게임
		// 1이상 7이하의 정수 하나가 적힌 공을 받아서 7개의 열 중에 한 곳에 떨어뜨린다
		// 이미 존재하는 공 바로 위나, 바닥 바로 위에 떨어진다
		// 가로 또는 세로 방향으로 연속해서 놓여 있는 공들
		// 크기가 x인 그룹에 x가 적힌 공이 있다면 그 공은 동시에.모두. 없어진다
		// 공이 없어지면 위에 있던 공들이 아래로 내려가고,
		// 공이 없어지는 이벤트는 연쇄적으로 발생한다


		// 격자에 있는 공의 개수
		int total = 0;

		// 현재 격자 상태
		map = new int[7][7];
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] != 0) total++;
			}
		}
		
		// 떨어뜨려야 하는 공의 번호
		int ball = sc.nextInt();
		
		// 없앨 수 있는 공의 최댓값
		int max = 0;
		
		// 공을 7개 줄에 다 떨어뜨리는 경우
		for(int i = 0; i < 7; i++) {
			
			// 이때의 지도
			copy = new int[7][7];
			for(int j = 0; j < 7; j++) {
				copy[j] = map[j].clone();
			}

			// 이때 떨어뜨리는 공의 개수
			cnt = 0;
			
			// i줄에 ball을 떨어뜨린다
			for(int r = 0; r < 7; r++) {
				// 다른 공을 만나면 그 위에 공을 쌓고
				if(copy[r][i] != 0) {
					copy[r - 1][i] = ball;
					break;
				}
				
				// 맨 바닥까지 간다면 바닥에 놓는다
				if(r == 6) copy[6][i] = ball;
			}
			
			
			// 가로세로 그룹 확인
			check();
			
			// 떨어뜨리는 공의 개수 최댓값 갱신
			max = Math.max(max, cnt);
		}
		
		System.out.println(total + 1 - max);
		
	}
	
	
	static void check() {
		
		// 가로 세로 그룹을 확인해서 없어질 공이 있는지
		removeList = new ArrayList<>();
		
		// 0행부터 6행까지 확인
		for(int i = 0; i < 7; i++) {
			
			// 그룹의 길이
			int st = 0;
			int ed = 0;
			int length = 0;
			
			for(int j = 0; j < 7; j++) {
				
				// 공이 있으면 길이를 늘려준다
				if(copy[i][j] != 0) {
					length++;
					
					// 그런데 길이가 1이면 내가 시작점이다
					if(length == 1) st = j;
				}
				
				// 공이 없는데
				// 길이가 0이 아니면 직전이 끝점이다
				else if(length != 0) {
					ed = j - 1;
					
					// 그 그룹 크기에 맞는 공이 있는지
					for(int k = st; k <= ed; k++) {
						if(copy[i][k] == length) {
							removeList.add(new int[] {i, k});
						}
					}
					
					// 공 확인하면 끝점 초기화
					length = 0;
				}
				
				// 공이 있어서 length가 이어졌는데 경계에 도달했다면
				if(j == 6 && length > 0) {
					ed = 6;
					
					// 그룹에 맞는 공이 있는지 확인
					for(int k = st; k <= ed; k++) {
						if(copy[i][k] == length) {
							removeList.add(new int[] {i, k});
						}
					}
				}
			}
		}

		// 0열부터 6열까지 확인
		for(int j = 0; j < 7; j++) {
			
			int st = 0;
			int ed = 0;
			int length = 0;
			
			for(int i = 0; i < 7; i++) {
				
				if(copy[i][j] != 0) {
					length++;
					if(length == 1) st = i;
				}
				
				else if(length != 0) {
					ed = i - 1;
					for(int k = st; k <= ed; k++) {
						if(copy[k][j] == length) {
							removeList.add(new int[] {k, j});
						}
					}
					
					length = 0;
				}
				
				if(i == 6 && length > 0) {
					ed = 6;
					
					for(int k = st; k <= ed; k++) {
						if(copy[k][j] == length) {
							removeList.add(new int[] {k, j});
						}
					}
				}
			}
		}
		
		
		// 만약 없앨 공이 더이상 없다면 리턴
		if(removeList.size() == 0) return;
		
		// 공을 한꺼번에 없애고 && 내린다
		removeBalls();
		
		// 가로 세로 그룹을 확인해서 없앨 공이 있는지 확인한다
		check();
	}
	
	
	static void removeBalls() {

		// 공을 한꺼번에 없앤다
		for(int i = 0; i < removeList.size(); i++) {
			int r = removeList.get(i)[0];
			int c = removeList.get(i)[1];
			
			if(copy[r][c] != 0) {
				copy[r][c] = 0;
				cnt++;
			}
		}
		
		
		// 공을 없앤 다음에 공을 내린다
		for(int j = 0; j < 7; j++) {
			for(int i = 6; i >= 0; i--) {

				// 각 열의 바닥부터 올라와서, 공이 있다면
				if(copy[i][j] != 0) {
					// 그 아래 공이 있으면 안 내린다
					if(i == 6 || copy[i + 1][j] != 0) continue;
					
					// 공을 내린다면 다음꺼부터 확인
					for(int r = i + 1; r < 7; r++) {
						// 다른 공을 만나면 그 위에 공을 쌓고
						if(copy[r][j] != 0) {
							copy[r - 1][j] = copy[i][j];
							copy[i][j] = 0;
							break;
						}
						
						// 맨 바닥까지 간다면 바닥에 놓는다
						if(r == 6) {
							copy[6][j] = copy[i][j];
							copy[i][j] = 0;
						}
					}
				}
			}
		}

	}
	
	
}
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] stats;
	static int min;
	static boolean[] isStart;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N명의 사람
		N = sc.nextInt();
		
		// 능력치 S_ij는 i번 사람과 j번 사람이 같은 팀일 때, 팀에 더해지는 능력치
		// S_ij랑 S_ji는 다를 수 있고 둘 다 더해진다
		stats = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				stats[i][j] = sc.nextInt();
			}
		}
		
		// 스타트팀 능력치와, 링크팀 능력치의 차이를 최소로 -> 이때 최솟값
		min = Integer.MAX_VALUE;
		
		// N/2명과 N/2명을 조합으로 구한다
		isStart = new boolean[N + 1];
		visited = new boolean[N + 1];
		combi(0, 1);
		
		System.out.println(min);
	}
	
	static void combi(int chosen, int curr) {
		// 스타트팀으로 고른 사람이 N/2명이라면
		if(chosen == N / 2) {
			int start = 0;
			int link = 0;
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					// i랑 j가 둘다 스타트팀이면 능력치 증가
					if(isStart[i] && isStart[j]) start += stats[i][j];
					
					// i랑 j가 둘다 링크팀이면 능력치 증가
					if(!isStart[i] && !isStart[j]) link += stats[i][j];
				}
			}
			
			// 두 능력치의 차이 최솟값 갱신
			min = Math.min(min, Math.abs(start - link));
		}
		
		// 조합이니까 고른사람 curr 다음부터
		for(int i = curr + 1; i <= N; i++) {
			// 고르기
			isStart[i] = true;
			visited[i] = true;
			combi(chosen + 1, i);
			
			// 원상복귀
			isStart[i] = false;
			visited[i] = false;
		}
		
	}
}
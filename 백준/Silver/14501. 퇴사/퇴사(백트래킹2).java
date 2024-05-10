import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N일동안 상담이 잡혀있다
		N = sc.nextInt();
		
		// 배열에 시간과 보수
		arr = new int[N][2];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 2; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// 최대 수익
		max = 0;
		
		// 그날 일할지 안 할지
		dfs(0, 0);
		
		System.out.println(max);
	}
	
	
	// 다른 방식의 백트래킹으로 풀었다
	static void dfs(int day, int cost) {
		
		
		// day가 N이이라면 cost로 최댓값 갱신
		if(day == N) {
			max = Math.max(max, cost);
			return;
		}
		
		// day일부터 그 이후의 일을 할 수 있다
		for(int i = day; i < N; i++) {
			
			// 일을 하고 그 다음 일할 수 있는 날로 점프
			// 돈 받는 날이 N이하면 된다
			if(i + arr[i][0] <= N) {
				dfs(i + arr[i][0], cost + arr[i][1]);
			}
			
			// N을 벗어난다면 cost로 최댓값 갱신하기
			else {
				max = Math.max(max, cost);
				
				// 돌아가는 게 아니라, 그 다음 날을 해봐야지
				// return;
			}
			
			// i날 일을 하고 돌아오면
			// i날은 일을 안 하고, i+1날 일을 하는 걸로 들어간다
			// 그래서 day날 일을 안 하는 걸 따로 해줄 필요가 없다
		}
		
	}
}

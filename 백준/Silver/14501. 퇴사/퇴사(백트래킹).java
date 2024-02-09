import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static int max;
	static int[] chosen;
	
	// 디피말고 백트래킹으로 한 번 풀어보자
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 남은 N일동안 최대한 많은 상담
    	N = sc.nextInt();
    	
    	// T기간 상담을 하면 P금액을 받을 수 있다
    	arr = new int[N + 1][2];
    	for(int i = 1; i <= N; i++) {
    		arr[i][0] = sc.nextInt();
    		arr[i][1] = sc.nextInt();
    	}
    	
    	
    	// 남은 N일은 15이하 - 모든 경우를 백트래킹으로 해보는 것도
    	// 백트래킹을 하더라도 이 방식은 좀 별로다
    	max = 0;
    	
    	for(int i = 1; i <= N; i++) {
    		chosen = new int[i];
    		combination(0, 1, i);
    	}
    	
    	System.out.println(max);
	}
	
	
	static void combination(int depth, int idx, int total) {
		
		// total개 만큼 골랐으면 수익 책정
		if(depth == total) {

			int sum = 0;
			int currD = 1;
			// 앞에서부터 하는 일에 우선순위를 두면서 책정
			for(int i = 0; i < total; i++) {
				
				// 고른 날부터
				int day = chosen[i];
				
				// 며칠 동안 일하고
				int duration = arr[day][0];
				
				// 얼마를 받는다
				int pay = arr[day][1];
				
				
				// 일이 끝난 currD 이전의 일이 아니라면
				if(day >= currD) {
					// 일을 N일 전에 끝낼 수 없으면 안 된다
					if(day + duration - 1 > N) {
						// 그 뒤에 있는 일도 할 필요 없다. 잘못 고른거니까
						break;
					}
					
					// 일을하고 날을 뒤로 미루고 돈을 받자
					currD = day + duration;
					sum += pay;
				}
			}
			
			// 최대 수익 갱신
			max = Math.max(max, sum);
			
			return;
		}
		
		
		// idx가 N을 넘으면 안 된다
		if(idx > N) return;
		
		// idx번째 일을 고르고
		chosen[depth] = idx;
		combination(depth + 1, idx + 1, total);
		
		// idx번째 일을 안 고르고
		combination(depth, idx + 1, total);
		
	}
}

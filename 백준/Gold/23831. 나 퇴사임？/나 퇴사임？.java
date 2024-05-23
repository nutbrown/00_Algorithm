import java.util.Scanner;

public class Main {
	static int N;
	static int A;
	static int B;
	static int[][] arr;
	static int[][][][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 자습을 총 N일
		N = sc.nextInt();
		
		// 요양 신청 최대 A회, 자습 필수 횟수 B
		A = sc.nextInt();
		B = sc.nextInt();
		
		// i번째 날에 얻는 만족도 : 정독실 소학습실 휴게실 요양 
		arr = new int[N + 1][4];
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < 4; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// 정독실, 소학습실, 휴게실, 방에서요양
		// 요양 신청은 최대 A회, 휴게실 2일 연속 금지,
		// 정독실+소학습실 B회 미만 금지
		
		// N일동안 얻을 수 있는 만족도의 합 최댓값을 저장한다
		// 1일차에 4가지, 4*4 16가지, 16*4 64가지 -> 4개씩 가지로 뻗어나간다
		// 그 모든 경우의 수에서 최댓값을 찾는 게 모든 조합 완전탐색
		
		// 어떤 날에 4가지 경우의 수가 있을 때, 4개 가지로 뻗어나갈 때,
		// 그 다음날의 최댓값만 가져와서 계산한다
		// 이래도 모든 경우의 수인 건데,
		// 여기서 모든 조합이 중요한 게 아니라, 쉬는날 공부날만 중요하니까
		// 쉬는날 공부날을 기준으로 최댓값을 저장하고 불러온다

		// 1일차에 공부할 거면,
		// 2일차 정독,				2일차 소학습
		// 3일차 정독, 3일차 소학습,	3일차 정독, 3일차 소학습
		// 이 4가지를 다 보는 게 아니라
		// 3일차에 공부횟수가 같다면 4가지는 같은 조건이니
		// 저 중에서 최댓값만 구해서 2일차 정독 소학습이랑 더해본다
		// 4번 부를 거 -> 1번만 부르기

		
		// 어렵네
		dp = new int[N + 1][2][A + 1][N];
			
		// 0일에서 뻣어나가는 4가지가지 마다
		// 최댓값을 구해서 올라왔을 때 맨 꼭대기 최댓값
		System.out.println(recur(0, 0, 0, 0));
		
	}
	
	
	
	// cur일에 정보가 이럴 때, 그 다음 4가지 조건에 따른 최댓값을 구한다
	static int recur(int cur, int prev, int rest, int study) {

		// N일이 되면 중단
		if(cur == N) {
			
			// 이리저리 내려갔을 때, N일이 됐는데
			// 공부를 B회 미만으로 했으면 안 된다
			// 얘를 호출해서 데려갈 건데, 최댓값 경쟁에서 이기면 안 된다.
			// 아무리 큰 만족도를 더해도 선택될 수 없는 매우 작은 값
			// 최고 만족도의 합이 100*100, 최소 만족도의 합이 100이니까
			// 올라가는 길에 100*100이 더해져도 100을 이겨야한다
			
			
			// 초기화하기 귀찮으니까 -> 불가능한 건 아예 가져오지 않는 방식으로 
			if(study < B) return -1;
			
			// 그게 아니라면 점회식 0항처럼 끝
			// 더하기를 0부터 시작하기
			// 경우의 수를 0일부터 가지 뻗어나가면서 생각하는데
			// 더하는 건 맨 끝 N일부터 더해서 최댓값을 구하면서 올라온다
			else return 0;
		}
		
		// 이미 구한 거면 반환하기
		if(dp[cur][prev][rest][study] != 0) return dp[cur][prev][rest][study];
		
		
		// 만족도의 합 최댓값 찾기
		// 불가능하면 다음에 선택 못 받게
		int max = -1;
		
		// 그 다음 가지가 불가능하면 불가능하니까 하지 않게
		int next = 0;
	
		
		// 휴게실 사용하기
		// 휴게실을 사용하지 않는 날이면, 휴게실 사용한 날에서도 올라올 수 있다
		if(prev == 0) {
			next = recur(cur + 1, 1, rest, study);
			if(next != -1) max = Math.max(max, next + arr[cur + 1][2]);
		}
					
		// 요양하기
		// 1일부터 cur일까지 요양횟수가 A미만이면 요양 가능
		if(rest < A) {
			// 이날 요양했을 때, 그 다음날은 rest + 1이니까
			// 그 아래 가지 중에서 최댓값을 갖고 올라온다
			next = recur(cur + 1, 0, rest + 1, study);
			if(next != -1) max = Math.max(max, next + arr[cur + 1][3]);
		}
					
		// 정독실 공부하기
		next = recur(cur + 1, 0, rest, study + 1);
		if(next != -1) max = Math.max(max, next + arr[cur + 1][0]);

		// 소학습실 공부하기
		next = recur(cur + 1, 0, rest, study + 1);
		if(next != -1) max = Math.max(max, next + arr[cur + 1][1]);
		
		// cur날, 휴식여부, 요양횟수, 공부횟수 의 최댓값은
		// 요양 해서 -> 다음날에는 (요양횟수+1, 공부횟수) 인 것들 중에서 최댓값 + 에서 요양한 것
		// 공부 해서 -> 다음날에는 (요양횟수, 공부횟수+1) 인 것들 중에서 최댓값 + 에서 공부한 것
		// ... 중에서의 최댒갑을 반환한다
		return dp[cur][prev][rest][study] = max;
	}
}
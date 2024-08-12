import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// **입력이 최대 500,000 + 500,000*3
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N개의 도시가 일직선으로 연결 (N <= 500,000)
		int N = Integer.parseInt(st.nextToken());
		
		// 차량 정보 M개 (M <= 500,000)
		int M = Integer.parseInt(st.nextToken());

		// i번이랑 i+1번 도시는 w차선 도로로 연결되어 있다
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 1번에서 N번 사이에 도로에 몇 개의 차가 지나는지 누적합 구하기
		// i의 값만큼 i번도시 ~i+1번도시 사이를 다닌다
		// 1부터 N-1번까지 저장하고 imos로 1부터 N까지 놓는다 
		int[] prefix = new int[N + 1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			// u번 도시에서 v번 도시를 다니는 차량이 x대
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			prefix[u] += x;
			prefix[v] -= x;
		}

		// imos 처음부터 누적합
		for(int i = 1; i <= N; i++) {
			prefix[i] += prefix[i - 1];
		}
		
		// i번도시부터 i+1번도시까지
		// prefix[i]개의 차량이 arr[i]의 차로 위로 간다
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N; i++) {
			
			// **차량 x대가 10^9를 넘지 않지만
			// 그걸 제곱하면 당연히 int를 넘지
			// 테스트케이스로 N=2, M=1, 1~2 1차선, 1~2 1,000,000,000대
			// 로 하면 바로 음수 뜨는데 해봤어야지
			// **제곱하면서 바로 넘으니까 cars lanes부터 long이어야지

			// 차량 대수를 차로 개수로 나눠서 최대한 분산시킨다
			long cars = prefix[i];
			long lanes = arr[i];
			long sum = 0;
			
			// 나머지 개수만큼은 몫+1
			sum += (cars / lanes + 1) *  (cars / lanes + 1)
					* (cars % lanes);
			
			// 나머지 개수제외는 몫 **이걸 왜 포문으로 했는지
			sum += (cars / lanes) *  (cars / lanes)
					* (lanes - cars % lanes);
			
			// i번 도시부터 i+1번 도시까지 도로부담
			sb.append(sum).append("\n");
			
		}

		// **500,000개 각각 출력하면 시간초과
		System.out.println(sb);
	}
}
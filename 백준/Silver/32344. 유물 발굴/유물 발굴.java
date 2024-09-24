import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// R행 C열 크기의 운동장
		// 유물의 조각들을 한 번에 묶을 수 있는 가장 작은 직사각형 - 크기
		// 제일 큰, 번호가 제일 작은 것을 먼저 발굴한다
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		// 1번부터 N번까지
		// (가장 큰 행 - 가장 작은 행 + 1) * (열)이 직사각형의 넓이다
		// 1234열에 저장하기
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N + 1][5];
		
		for(int i = 0; i < N; i++) {
			
			// a번 유물, v행 h열에 존재
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			// 최솟값이 초기화되지 않은 0이면 그냥 넣어주기
			// 최대최소 행 갱신
			arr[a][1] = Math.max(arr[a][1], v);
			arr[a][2] = arr[a][2] == 0 ? v : Math.min(arr[a][2], v);

			// 최대최소 열 갱신
			arr[a][3] = Math.max(arr[a][3], h);
			arr[a][4] = arr[a][4] == 0 ? h : Math.min(arr[a][4], h);
		}
		
		// 앞에 번호가 작은 것부터, 계산하면서 제일 큰 넓이 찾기 
		int num = 0;
		long max = 0;
		
		for(int i = 1; i <= N; i++) {
			
			// 곱할 때 앞에 long을 해줘야지 곱하면서 에러가 안 생긴다
			// 값이 없으면 1이 나오는데, 최솟값이니까 괜찮다
			// ***안 괜찮다. 번호가 작다고 앞에서 최댓값 1을 가져가니까
			if(arr[i][1] == 0) continue;
			
			long size = (long)(arr[i][1] - arr[i][2] + 1)
						* (long)(arr[i][3] - arr[i][4] + 1);
			if(size > max) {
				num = i;
				max = size;
			}
		}
		
		System.out.println(num + " " + max);
	}
}

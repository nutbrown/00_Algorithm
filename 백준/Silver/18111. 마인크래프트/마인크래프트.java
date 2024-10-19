import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 세로 N 가로 M 크기의 집터
		// 인벤토리에는 B개의 블럭이 있다
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		// 최소 높이, 최고 높이, 전체 블록 개수
		int min = Integer.MAX_VALUE;
		int max = 0;
		int blocks = 0;
		
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
				blocks += map[i][j];
			}
		}
		
		// 땅의 높이를 일정하게 바꾸자
		// 1. 블록을 제거해서 인벤토리에 넣기 -> 2초
		// 2. 하나 꺼내서 가장 위에 있는 블록 위에 -> 1초
		// 최소 시간과 그 경우 땅의 높이 출력
		int minT = Integer.MAX_VALUE;
		int minH = 0;
		
		// 높이 최대 256 * NM 최대 250,000 -> 시간 가능
		// 어떻게 해야할지 모르겠네. 완탐 같다
		// 최솟값, 최댓값을 찾아서 다 해보기
		// ***최대 높이가 256 이하다
		for(int h = min; h <= Math.min(max, 256); h++) {
			
			// 시간 계산
			int t = 0;

			// 이 높이를 채울 블록이 있으면
			if(N * M * h <= blocks + B) {

				for(int i = 0; i < N; i++) {
					for(int j = 0; j < M; j++) {
						
						// h보다 작은 건 넣으니까 * 1초
						if(map[i][j] < h) t += h - map[i][j];
						// h보다 큰 건 빼니까 * 2초
						else if(map[i][j] > h) t += (map[i][j] - h) * 2;
					}
				}
			}
			// 이 높이 못 채우면 뒤에꺼도 할 필요 없다
			else {
				break;
			}
			
			// 시간이 최소인지 보기
			// ***답이 여러 개가 있다면 땅의 높이가 높은 것을 출력하기
			if(t <= minT) {
				minT = t;
				minH = h;
			}
			
		}
		
		System.out.println(minT + " " + minH);
	}
}
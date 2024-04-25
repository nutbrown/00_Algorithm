import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int[] arr;
	static ArrayList<int[]> gap ;
	static int cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 레이스 길이 N, 심판 M명
		// 정해진 K곳에만 배치 가능 (K <= 50)
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();

		// 심판 위치랑 간격
		int[] arr = new int[K];
		for(int i = 0; i < K; i++) {
			arr[i] = sc.nextInt();
		}
		
		
		// 아 사전순때문에 그냥 그리디로 안 된다
		// 안 되니까 레이스 길이를 줬나보다....
		
		// 최소 간격을 정해두고, 이분탐색
		int left = 0;
		int right = N + 1;
		String ans = "";
		
		while(left < right) {
			int mid = (left + right) / 2;

			// 이 최소거리로 세울 수 있는지
			StringBuilder sb = new StringBuilder();
			
			// 사전순이어서 최대한 왼쪽부터 채운다
			int cnt = 1;
			sb.append(1);
			
			// 마지막으로 세운 위치
			int last = arr[0];
			
			for(int i = 1; i < K; i++) {
				// 심판 다 채웠으면 0채우기
				if(cnt == M) {
					sb.append(0);
					continue;
				}

				// 최소 간격에 부합하면 심판 세우기
				// 부합하지 않으면 안 세우고 넘어가기
				if(arr[i] - last >= mid) {
					last = arr[i];
					cnt++;
					sb.append(1);
				} else {
					sb.append(0);
				}
			}

			// 끝까지 했는데 심판 다 채웠으면 가능, 아니면 안 가능
			// 가능가능가능|불가능불가능, 가능의 rightbound 구하기 어떻게 했더라
			// 가능하면 최소간격을 늘려보고, 불가능하면 아예 제외하고 올린다
			if(cnt == M) {
				left = mid + 1;

				// 우선 한 번하면 답 저장
				ans = sb.toString();

			} else {
				right = mid;
			}
		}
		
		System.out.println(ans);
	}
}

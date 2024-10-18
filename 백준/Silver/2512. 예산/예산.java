import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 지방의 수 N, 요청된 예산
		int N = sc.nextInt();
		int[] arr = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		// 전체의 합, 전체의 최댓값 -> 누적합
		int[] prefixSum = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			prefixSum[i] = prefixSum[i - 1] + arr[i];
		}
		
		// 총 예산 M은 1,000,000,000 이하
		int M = sc.nextInt();
		
		// 전체합이 예산을 안 넘으면 출력
		if(prefixSum[N] <= M) {
			System.out.println(arr[N]);
			return;
		}
		
		// 아니면 정렬해서 이분탐색
		// 110 120 140 150 이 있을 때
		// 최댓값을 ~120으로 잡으면 M을 안 넘고
		// 최댓값을 140~으로 잡으면 M을 넘는다
		// M을 넘는 첫번째 140을 구하고 -> M 이하 제외
		// 110 120 120 140 150 을 생각했을 때
		// 120이랑 140 사이를 찾아야하니까 upper를 찾아야한다
		
		// 120 ~ 140 사이에서 합이 M을 넘는 값 중 제일 작은 값 upper를 찾는다
		
		int st = 1;
		int ed = N + 1;
		
		while(st <= ed) {
			
			// 인덱스
			int mid = (st + ed) / 2;
			
			// 그때의 전체 합
			// mid 전까지는 그냥 더하고,
			// mid 부터는 mid로 더한다
			int sum = prefixSum[mid - 1] + arr[mid] * (N - mid + 1); 
			
			// 이 합이 M이하면 제외
			if(sum <= M) {
				st = mid + 1;
			}
			
			// 이 합이 M보다 크며 유지, 가 아니라 한 칸 내려오기
			else if(sum > M) {
				// 답을 찾았을 때 -1로 빼도
				// 그 왼쪽에서 st ed가 만났을 때 M 이하면
				// 한칸 오른쪽으로 올라와서 st가 답을 반환한다
				// 여기서 ed = mid를 하면
				// st ed mid가 정답에서 만나서 while를 못 빠져나온다
				ed = mid - 1;
			}
		}
		
		// 이때 st는 M을 넘는 인덱스다
		int upper = st;

		
		// 이때 다 더해도 M 이하인건 위에서 처리해서 st가 N을 넘지 않는다
		// st부터 적용할 최댓값을 찾자
		// 120과 140사이에서, 127은 M이하인데 128은 M을 넘는다
		// M보다 큰 upper를 찾고 그거보다 작게한다. M이랑 같은 값이 없을 수도 있으니
		st = arr[upper - 1];
		ed = arr[upper];
		
		while(st <= ed) {
			int mid = (st + ed) / 2;
			int sum = prefixSum[upper - 1] + (N - upper + 1) * mid;
			
			// 이 합이 M이하면 제외
			if(sum <= M) {
				st = mid + 1;
			}
			
			// 이 합이 M보다 크면 한 칸 내려오기 
			else if(sum > M) {
				ed = mid - 1;
			}
		}
		
		// 이때 나온 값보다 하나 작은 게 최댓값이다
		System.out.println(st - 1);
		
		
		
	}
}
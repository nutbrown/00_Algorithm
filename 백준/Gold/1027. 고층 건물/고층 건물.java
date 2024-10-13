import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 빌딩 N개
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 빌딩A에서 빌딩B를 보려면, 사이를 잇는 선분에 다른 빌딩이 닿지 않아야 한다
		// 즉 A-B 기울기보다 다른 기울기가 작아야 한다
		// 헷갈리니까 진짜 기울기로 하자
		// 가장 많은 고층 빌딩이 보이는 빌딩 -> 빌딩의 수
		int max = 0;
		
		// 그냥 완전탐색인데 왜 골4지
		for(int i = 0; i < N; i++) {
			// 해당 빌딩 카운트
			int cnt = 0;

			// 왼쪽으로 가면서 기울기 저장
			// Double.MAX MIN은 제일 큰수 작은수가 아니다
			double left = (double)Integer.MAX_VALUE;
			for(int j = i - 1; j >= 0; j--) {
				
				// 기울기를 정수로하면 안 되지...
				double gradient = (double)(arr[i] - arr[j]) / (double)(i - j);
				
				// 현재 최대 기울기보다 크면 보이는 것, 최댓값 갱신
				if(gradient < left) {
					cnt++;
					left = gradient;
				}
			}

			
			// 오른쪽으로 가면서 기울기 저장
			double right = (double)Integer.MIN_VALUE;
			for(int j = i + 1; j < N; j++) {
				
				double gradient = (double)(arr[j] - arr[i]) / (double)(j - i);
				if(gradient > right) {
					cnt++;
					right = gradient;
				}
			}
			
			// 이렇게 다 세어본 거, 최댓값 갱신
			max = Math.max(max, cnt);
		}
		
		
		System.out.println(max);
		
	}
}
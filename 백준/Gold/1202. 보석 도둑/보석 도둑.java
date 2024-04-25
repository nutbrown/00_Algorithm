import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 보석 N개, 가방 K개
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		// 보석 무게와 가격
		int[][] gems = new int[N][2];
		for(int i = 0; i < N; i++) {
			gems[i][0] = sc.nextInt();
			gems[i][1] = sc.nextInt();
		}
		
		// 담을 수 있는 최대 무게, 가방에는 1개의 보석만
		int[] bags = new int[K];
		for(int i = 0; i < K; i++) {
			bags[i] = sc.nextInt();
		}
		
		
		// (과제 정렬과 비슷한 문제)
		// 보석 가격이 큰 순서대로 고르는데,
		// 무게를 담을 수 있는 가방이 있으면, 최소 무게 가방을 고른다
		// 이러면 시간 복잡도 -> 보석300,000*가방300,000 (1초 1억번)

		// 우선순위 큐 사용
		// 가방 무게가 작은 것부터 탐색하면서, 그때 제일 가격이 높은 보석을 고른다
		// 가방 무게마다 정렬이 아닌게 -> 작은 가방에 들어가면 큰 가방에는 무조건 들어가니까
		
		
		// 무게 작은 순서대로, 가격 큰 순서대로
		Arrays.sort(gems, new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
				
				if(o1[0] != o2[0]) {
					// 무게가 다르면 무게 작은 거 먼저
					return o1[0] - o2[0];
				} else {
					// 무게가 같으면 가격이 큰 거 먼저
					return o2[1] - o1[1];
				}
			}
		});
		
		// 가방은 무게 작은 것부터
		// Arrays.sort(bags, Collections.reverseOrder())
		// 를 쓰려면 int는 안 되고 Integer와 같은 Wrapper클래스만 된다
		Arrays.sort(bags);
		
		// 우선순위큐는 가격이 큰 순서대로, 내림차순
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		
		// 보석 가격의 합의 최댓값
		// 보석 가격이 1,000,000 보석 개수가 300,000 -> 최대 300,000,000,000
		long sum = 0;
		
		// 또 안 넣게 보석 인덱스 저장
		int g = 0;
		
		// 가방 무게 작은 것부터
		for(int i = 0; i < K; i++) {
			// 보석 다 넣었는데, 피큐도 비어있으면 아웃 (이거 넣어서 시간초과 탈출. 왜?)
			if(g == N && pq.isEmpty()) break;
			
			// 가방 무게보다 작은 보석 넣기
			for(int j = g; j < N; j++) {
				if(gems[j][0] <= bags[i]) {

					// 보석 넣고 인덱스 보정
					pq.add(gems[j][1]);
					g++;
				} else {
					break;
				}
			}
			
			// 보석들 중 가격 높은 거 넣기
			if(!pq.isEmpty()) {
				sum += pq.poll();
			}
		}
		
		System.out.println(sum);
	}
}

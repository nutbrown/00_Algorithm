import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		// 도시 개수
		int N = sc.nextInt();
		
		// 연결하는 도로 길이
		int[] length = new int[N];
		for(int i = 1; i < N; i++) {
			length[i] = sc.nextInt();
		}
		
		// 주유소 리터당 가격
		int[] cost = new int[N];
		for(int i = 0; i < N; i++) {
			cost[i] = sc.nextInt();
		}
		
		// 지나온, 과거에 구매할 수 있었던 주유소 가격 저장한다
		// 제일 작은 비용을 꺼내서 그때그때 구매한다
		// 자바는 -> 작은수가 우선순위가 높다(**헷갈리면 찍어봐) 
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(cost[0]);
		
		// ㅜㅜㅜㅜ정확하게 풀라고ㅗㅗㅗㅗㅗㅗㅗㅗㅗ
		// 총 거리가 최대 1,000,000,000고
		// 리터당 가격 최대 1,000,000,000이면
		// 총 비용 최대 1,000,000,000,000,000,000 -> 10^18이잖아아아ㅏㅏㅏ
		long sum = 0;
		
		for(int i = 1; i < N; i++) {
			
			// i번째 마을에 도착하기까지 필요한 길이 * 최소 가격만큼 더한다
			// 여기 곱해질 때부터 long이잖아ㅏㅏㅏㅏㅏㅏ
			sum += (long)length[i] * (long)pq.peek();
			
			// i번째 마을 주유소 가격 더하기
			pq.add(cost[i]);
		}
		
		
		System.out.println(sum);
	}
}
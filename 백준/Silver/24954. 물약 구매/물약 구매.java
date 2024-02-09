import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static int[] costs;
	static ArrayList<Integer>[] sales;
	static int minCost;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// N 종류의 물략을 모두 구매하려고 한다 (0번부터 N-1번)
		N = sc.nextInt();
		
		// 물략의 가격
		costs = new int[N];
		for(int i = 0; i < N; i++) {
			costs[i] = sc.nextInt();
		}
		
		// 물약의 할인 정보 - 할인되어도 최소 동전 1개
		sales = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			sales[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			
			// 할인되는 물약 p개
			int p = sc.nextInt();
			for(int j = 0; j < p; j++) {
				
				// 물약 번호와, 할인되는 가격을 그냥 쭉 넣는다
				// 1개째 물약은 0에 번호, 1에 가격
				// 2개째 물약은 2에 번호, 3에 가격
				// n개째 물약은 2*n-2에 번호, 2*n-1에 가격
				
				int num = sc.nextInt();
				int cost = sc.nextInt();
				sales[i].add(num - 1);
				sales[i].add(cost);
			}
		}
		
		
		// 최소 구매 금액
		minCost = Integer.MAX_VALUE;
		
		// 물약 사는 순서 - 순열
		visited = new boolean[N];
		permutation(0, 0);
		
		System.out.println(minCost);
		
	}
	
	
	static void permutation(int depth, int cost) {
		
		// 물약 N개의 순서를 다 골랐다면
		if(depth == N) {
			
			// 최소 금액 갱신
			minCost = Math.min(minCost, cost);
			return;
		}
		
		
		// 순열
		for(int i = 0; i < N; i++) {
			
			// 앞에서 안 나온 거
			if(visited[i]) continue;
			
			// 고르고 구매하고 방문 처리
			// 금액이 1보다 작아도 최소 금액은 1이다
			cost += Math.max(costs[i], 1);
			visited[i] = true;
			
			// i번째 물약에 해당하는 할인 내역을 적용한다
			int t = 0;
			for(int j = 0; j < sales[i].size() / 2; j++) {
				
				// 2개씩 빼면 그게 물약번호, 할인금액
				int a = sales[i].get(t++);
				int d = sales[i].get(t++);
				
				// 여기서는 마이너스까지 표시해야 한다
				costs[a] -= d;
			}
			
			
			// 다음 순번 고르려고 내려간다
			permutation(depth + 1, cost);
			
			
			// 이 순번에 i넣는 거 다 하고 올라왔으면
			// 원상복귀하고 이 순번에 다음 i넣는 거 한다
			cost -= Math.max(costs[i], 1);
			visited[i] = false;

			// i번째 물약에 해당하는 할인 내역을 적용 취소한다
			t = 0;
			for(int j = 0; j < sales[i].size() / 2; j++) {
				
				int a = sales[i].get(t++);
				int d = sales[i].get(t++);
				
				costs[a] += d;
			}
			
		}
	}
	
}

import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	static int[] cost;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int min;
	
	public static void main(String[] args)  {
		Scanner sc =  new Scanner(System.in);
		
		// 학생이 N명, 관계수 M, 가지고 있는 돈 k
		int N = sc.nextInt();
		int M = sc.nextInt();
		int k = sc.nextInt();
		
		// 각각의 학생이 원하는 친구비
		cost = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			cost[i] = sc.nextInt();
		}
		
		// v와 w가 친구다. 자기 자신과 친구일 수도 있다
		list = new ArrayList[N + 1];
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		// 친구관계 입력
		for(int i = 0; i < M; i++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			
			list[n1].add(n2);
			list[n2].add(n1);
		}
		
		// 모든 학생을 친구로 만들 때, 최소비용 또는 불가능인지
		int sum = 0;
		
		// 친구의 친구는 친구다
		// 이 친구들 중에서, 제일 친구비가 낮은 사람을 찾는다
		visited = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			
			// 이미 했으면 안 한다
			if(visited[i]) continue;
			
			// 얘네들 중에서 최솟값
			// 우선 i를 먼저 방문하면서 시작한다
			visited[i] = true;
			min = cost[i];
			
			// 친구들 한 번 훑은 다음에, 최솟값 더하기
			DFS(i);
			sum += min;
		}

		// k를 넘으면 친구 못하고, 아니면 최소가격
		if(sum > k) System.out.println("Oh no");
		else System.out.println(sum);

	}
	
	static void DFS(int node) {
		
		// 연결된 친구 탐방
		for(int next : list[node]) {
			if(!visited[next]) {
				
				visited[next] = true;
				min = Math.min(min, cost[next]);
				DFS(next);
			}
		}
		
	}
}

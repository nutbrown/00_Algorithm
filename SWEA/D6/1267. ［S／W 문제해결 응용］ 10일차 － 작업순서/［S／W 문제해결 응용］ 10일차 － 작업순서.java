import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{

        Scanner sc = new Scanner(System.in);
		
		for(int t = 1; t <= 10; t++) {
			
			// 정점의 개수 V
			int V = sc.nextInt();
			
			// 간선의 개수 E
			int E = sc.nextInt();
			
			// 인접 행렬 + 진입 차수
			int[][] adj = new int[V + 1][V + 1];
			int[] in = new int[V + 1];
			for(int i = 0; i < E; i++) {
				int st = sc.nextInt();
				int ed = sc.nextInt();
				
				// 유향 그래프 연결 표시
				adj[st][ed] = 1;
				
				// 각 노드 진입차수 증가
				in[ed]++;
			}
			//System.out.println(Arrays.toString(in));
			
			// 큐에 진입차수가 0인 노드 추가 : 방문해도 됨
			// 큐에서 나왔다는 건 : 노드에 방문한다는 의미 -> 결과에 추가
			// 노드에 방문을 했으면 진입차수를 줄이면서 다음 노드에 진입
			Queue<Integer> queue = new LinkedList<>();
			String result = "";
			
			// 초기 진입차수가 0인 노드 추가
			for(int i = 1; i <= V; i++) {
				if(in[i] == 0) queue.offer(i);
			}
			
			// 큐가 다 빌 때까지 : 방문가능한 노드 다 방문할 때까지
			while(!queue.isEmpty()) {
				// 큐에서 숫자 하나 빼서
				int num = queue.poll();
				result += num + " ";
				
				// 인접한 노드 방문 + 진입차수 줄이기
				for(int i = 1; i <= V; i++) {
					if(adj[num][i] == 1) {
						// 진입차수 줄이기
						in[i]--;
						// 진입차수가 0이라면 방문가능하니까 큐에 삽입
						if(in[i] == 0) queue.offer(i);
					}
				}
				//System.out.println(queue.toString());
			}
			
			System.out.printf("#%d %s\n", t, result);
		}
        
	}
}
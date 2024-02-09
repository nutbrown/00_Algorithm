import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 수빈 위치 N, 동생 위치 K
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		
		// 제일 먼저 도착한 경우가 최단경로니까
		// 제일 먼저 도착한 경우에만 어디서 왔는지 저장하면 된다 
		int[] from = new int[100002];
		
		// 몇 번 안에 도착했는지
		int[] visited = new int[100002];
		Arrays.fill(visited, -1);
	
		// 탐색 시작 
		Queue<Integer> q = new LinkedList<>();
		visited[N] = 0;
		from[N] = N;
		q.add(N);
		
		while(!q.isEmpty()) {
			// 현재 위치랑 걸린 시간
			int cur = q.poll();
			int cnt = visited[cur];
			
			// 동생한테 도달했으면 아웃
			if(cur == K) break;

			// 범위 안에 있고 방문하지 않았다면
			if(cur - 1 >= 0 && visited[cur - 1] == -1) {
				visited[cur - 1] = cnt + 1;
				from[cur - 1] = cur;
				q.add(cur - 1);
			}
			if(cur + 1 <= 100001 && visited[cur + 1] == -1) {
				visited[cur + 1] = cnt + 1;
				from[cur + 1] = cur;
				q.add(cur + 1);
			}
			if(cur * 2 <= 100001 && visited[cur * 2] == -1) {
				visited[cur * 2] = cnt + 1;
				from[cur * 2] = cur;
				q.add(cur * 2);
			}
		}
		
		// 도착했으면 최단시간과 경로출력
		StringBuilder sb = new StringBuilder();
		sb.append(visited[K]).append("\n");
		
		Stack<Integer> stk = new Stack<>();
		int idx = K;
		stk.add(idx);
		while(idx != N) {
			idx = from[idx];
			stk.add(idx);
		}
		
		while(!stk.isEmpty()) {
			sb.append(stk.pop()).append(" ");
		}
		
		System.out.println(sb);
		
	}
}

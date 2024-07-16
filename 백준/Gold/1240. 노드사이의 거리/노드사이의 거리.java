import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N개의 노드로 이루어진 트리
		int N = sc.nextInt();

		// M개의 두 노드의 쌍 - 사이의 거리 출력
		int M = sc.nextInt();
		
		// 트리 상에 연결된 두 점과 거리
		// 트리를 몰라서 우선 그래프인 것처럼
		ArrayList<Integer>[] arrList = new ArrayList[N + 1];
		for(int i = 0; i <= N; i++) {
			arrList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			int dist = sc.nextInt();
			
			// 배열.get(점)에
			// 이어진 점과 거리를 0과 1에 넣는다
			arrList[n1].add(n2);
			arrList[n1].add(dist);

			arrList[n2].add(n1);
			arrList[n2].add(dist);
		}
		
		for(int t = 0; t < M; t++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			
			Queue<Integer> q = new LinkedList<>();
			int[] visited = new int[N + 1];
			Arrays.fill(visited, -1);
			
			// 시작점 입력
			q.add(n1);
			visited[n1] = 0;
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				int curDist = visited[cur];

				// 도착했으면 아웃
				if(cur == n2) {
					System.out.println(curDist);
					break;
				}
				
				// 연결된 점이 6개면 01 23 45
				for(int i = 0; i < arrList[cur].size() / 2; i++) {
					int next = arrList[cur].get(i * 2);
					int nextDist = arrList[cur].get(i * 2 + 1);
					
					if(visited[next] == -1) {
						visited[next] = curDist + nextDist;
						q.add(next);
					}
				}
			}
			
		}
	}
}
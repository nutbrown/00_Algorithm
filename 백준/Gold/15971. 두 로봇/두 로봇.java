import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);
		
		// 동굴에는 총 N개의 방이 존재한다
		int N = sc.nextInt();
		
		// 로봇 위치한 방 번호
		int r1 = sc.nextInt();
		int r2 = sc.nextInt();
		
		// N개 방은 최대 100,000
		// 통로 개수는 N-1개
		// 통로 거리는 최대 1,000
		ArrayList<int[]>[] list = new ArrayList[N + 1];
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<int[]>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			// 통로 시작점, 끝점, 거리
			int S = sc.nextInt();
			int E = sc.nextInt();
			int D = sc.nextInt();
			
			list[S].add(new int[] {E, D});
			list[E].add(new int[] {S, D});
		}
		
		// 두 로봇이 임의의 통로 양 끝에 위치하면 통신 가능이다
		// 통신하기 위해서 이동해야하는 거리 합 최소
		// 가면서 제일 긴 구간을 기록하고, 전체에서 뺀다
		
		// 사용하는 큐랑, visited에 최소 거리 저장하기
		// 어짜피 최댓값을 갖고 다녀야하네
		Queue<int[]> q = new LinkedList<>();
		int[] visited = new int[N + 1];
		Arrays.fill(visited, -1);
		
		// 처음 시작값 입력
		visited[r1] = 0;
		q.add(new int[] {r1, 0});
		
		// 임의의 두 방 사이를 이동할 때
		// 중복해서 가지 않으면서 가는 방법은 유일하다
		// 2 -> 4가 10인데
		// 2 -> 3 -> 4가 8인 경우를 생각하지 않아도 된다
		// 이럴 때 중간에 하나 없앨 max도 고려해야해서 굉장히 어려워진다
		
		// 경로가 무조건 1개라고 했으니까
		// 총 경로는 20인데 최대가 10여서 결국 10인거랑
		// 총 경로는 18인데 최대가 3이어서 결국 15인걸
		// 비교할 필요 없다
		// ----> 그런데 이렇게 한다고 하면 어떻게 풀어야할까
		// ----> 그래프 공부좀 하자
		
		
		while(!q.isEmpty()) {
			int cur = q.peek()[0];
			int max = q.poll()[1];
			
			// 끝점 r2에 도착하면
			if(cur == r2) {
				// 지금까지 거리에서 최댓값을 빼고 끝
				System.out.println(visited[cur] - max);
				return;
			}
			
			for(int i = 0; i < list[cur].size(); i++) {
				int next = list[cur].get(i)[0];
				int dist = list[cur].get(i)[1];
				
				// 경로가 하나라고 했으니까
				// 이미 방문한 지점이면 안 가고
				if(visited[next] != -1) continue;
				
				// 아니라면 그 지점까지
				visited[next] = visited[cur] + dist;
				q.add(new int[] {next, Math.max(max, dist)});
			}
		}
		
		

	}
}

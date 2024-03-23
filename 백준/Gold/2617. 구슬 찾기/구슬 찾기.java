import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 구슬 N개 중에서 무게가 (N+1)/2번째 구슬을 찾으려고 한다
		// M개의 쌍을 골라서 양팔 저울로 판정해서
		// 가운데 구슬이 전혀 아닌 건 제외
		
		//       5 ->
		// 4 -> 2 -> 1
		//  -> 3
		
		int N = sc.nextInt();
		int M = sc.nextInt();

		// 인접리스트
		// ArrayList<Integer>[] arr = new ArrayList[N + 1];
		
		// 역방향도 하기 위한 인접행렬
		int[][] arr = new int[N + 1][N + 1];
		
		for(int i = 0; i < M; i++) {
			// 앞 구슬이 뒷 구슬보다 무겁다
			int heavy = sc.nextInt();
			int light = sc.nextInt();
			
			// 무거운 구슬 -> 가벼운 구슬
			arr[heavy][light] = 1;
		}
		
		
		// 1 2 "3" 4 5
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			
			// 자기보다 작은 구슬의 개수가
			// (N+1)/2 개 이상이면 제외
			int cnt = 0;
			int cur = i;
			
			// 중복 방지를 위한 visited
			boolean[] visited = new boolean[N + 1];

			// 현재 구슬보다 작은 구슬 넣는 큐
			Queue<Integer> q = new LinkedList<>();

			// 초기값 입력
			visited[cur] = true;
			q.add(cur);
			
			while(!q.isEmpty()) {
				cur = q.poll();
				
				for(int j = 1; j <= N; j++) {
					if(arr[cur][j] == 1 && !visited[j]) {
						q.add(j);
						visited[j] = true;
						cnt++;
					}
				}
			}
			if(cnt >= (N + 1) / 2) {
				ans++;
				continue;
			}
			
			
			// 자기보다 큰 구슬의 개수가
			// (N+1)/2 개 이상이면 제외
			// 동시일 수는 없어서 개수만 세어준다
			cnt = 0;
			cur = i;
			
			// 중복 방지를 위한 visited
			visited = new boolean[N + 1];

			// 현재 구슬보다 작은 구슬 넣는 큐
			q = new LinkedList<>();

			// 초기값 입력
			visited[cur] = true;
			q.add(cur);
			
			while(!q.isEmpty()) {
				cur = q.poll();
				
				for(int j = 1; j <= N; j++) {
					if(arr[j][cur] == 1 && !visited[j]) {
						q.add(j);
						visited[j] = true;
						cnt++;
					}
				}
			}
			if(cnt >= (N + 1) / 2) ans++;
		}
		
		
		// 이게 얼마만의 알고리즘이지
		// 풀이가 이상할 수 있으니 답을 보고 확인하자
		System.out.println(ans);
		
	}
}
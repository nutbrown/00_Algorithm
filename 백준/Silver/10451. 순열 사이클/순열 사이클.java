import java.util.HashSet;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			
			// 순열의 크기
			int N = sc.nextInt();
			
			// i는 pi로 간선을 그어서 그래프를 만들 수 있다
			int[] arr = new int[N + 1];
			for(int i = 1; i <= N; i++) {
				arr[i] = sc.nextInt();
			}
			
			// i로 시작했을 때, 지나온 점을 만나면 사이클
			int cycle = 0;
			boolean[] visited = new boolean[N + 1];
			
			for(int i = 1; i <= N; i++) {

				// 앞에서 거친 정점이면 안 하기 
				if(visited[i]) continue;
				
				// 지금 시작했을 때 지나온 정점들
				// 6모양으로 중간에 만나면 사이클인데
				// 예전에 방문했던 걸 만나면 사이클이 아니다
				HashSet<Integer> hs = new HashSet<>();
				hs.add(i);
				visited[i] = true;
				
				// i에서 처음 이어진 정점
				int cur = arr[i];
				
				// i에서 시작해서 다시 i로 오는지
				while(true) {
					// cur이 hs에 있으면 사이클
					if(hs.contains(cur)) {
						cycle++;
						break;
					}
					
					// cur이 예전에 방문했던 곳이면 중단 
					if(visited[cur]) break;
					
					// 처음 가는 곳이면 가기
					//if(!visited[cur]) {

					// 이제 cur이 기존 사이클에 있는지 없는지 확인 끝났으니까
					hs.add(cur);
					visited[cur] = true;
						
					// 그 다음 cur로 이동하고 확인
					cur = arr[cur];
						
					// 1 > 2 > 3 갔다가 3 > 3이어도 위에 올라가서 사이클
				}
			}
			
			System.out.println(cycle);
		}
	}
}
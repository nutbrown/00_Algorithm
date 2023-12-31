import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 수빈 위치 N, 동생 위치 K
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		// +1에서 내려올 수 있으니 하나 더 올려준다
		// 0열에는 횟수 1열에는 그 횟수로 몇 번 가는지 넣어준다
		int[][] visited = new int[100002][2];
		
		 
		// 이동 횟수는 0
		// 경우의 수는 1
		queue.offer(N);
		visited[N][0] = 0;
		visited[N][1] = 1;
		
		// 처음에 동생을 찾았다면
		if(N == K) {
			System.out.println("0\n1");	// 이거 0 0 아닌거 찾는데 오래걸림...
			return;
		}
		
		// 첫 경우의 수 넣어줌
		if(N - 1 >= 0) queue.add(N - 1);
		if(N + 1 <= 100001) queue.add(N + 1);
		if(N * 2 <= 100001) queue.add(N * 2);
		
		while(!queue.isEmpty()) {
			
			// 큐에서 꺼내서 확인
			int cur = queue.poll();
//			System.out.println(cur);
//			for(int i = 0; i < 20; i++) {
//				System.out.print(i + " ");
//				for(int j = 0; j < 2; j++) {
//					System.out.print(visited[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("==========");
			
			// 첫 방문에만 경우의 수를 해줌
			if(visited[cur][1] != 0) continue;
			
			// 동생에게 올 수 있는 방법은 -1에서 +1에서 /2에서다
			// 3 위치를 확인해서 이미 방문해서 경우의 수가 있다면 
			// 그 중 최소 이동 횟수를 구해서 입력
			int[] check = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
			if(cur - 1 >= 0 && visited[cur - 1][1] != 0) check[0] = visited[cur - 1][0];
			if(cur + 1 <= 100001 && visited[cur + 1][1] != 0) check[1] = visited[cur + 1][0];
			if(cur % 2 == 0 && visited[cur / 2][1] != 0) check[2] = visited[cur / 2][0];
			int min = Math.min(check[0], Math.min(check[1], check[2]));

			// 한 번 더 간 거니까 1을 더해줌
			visited[cur][0] = min + 1;

			// 이동 횟수가 최소인 거만 경우의 수를 더함
			if(check[0] == min) visited[cur][1] += visited[cur - 1][1];
			if(check[1] == min) visited[cur][1] += visited[cur + 1][1];
			if(check[2] == min) visited[cur][1] += visited[cur / 2][1];

			// 만약 위치가 K라면 출력
			if(cur == K) {
				System.out.println(visited[cur][0] + "\n" + visited[cur][1]);
				return;
			} else {
				if(cur - 1 >= 0) queue.add(cur - 1);
				if(cur + 1 <= 100001) queue.add(cur + 1);
				if(cur * 2 <= 100001) queue.add(cur * 2);
			}
		}
		
		
	}
}

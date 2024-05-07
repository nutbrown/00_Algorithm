import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 스타트링크는 총 F층
		int F = sc.nextInt();
		
		// 강호가 지금 있는 곳 S층
		int S = sc.nextInt();

		// 스타트링크의 위치 G층
		int G = sc.nextInt();
		
		// 위로 U층을 가는 버튼
		// 아래로 D층을 가는 버튼
		int U = sc.nextInt();
		int D = sc.nextInt();
		
		// 위아래 버튼만 있을 때 강호가 G층에 도착하려면 버튼을 몇 번 눌러야하는지
		int cnt = -1;
		
		
		// bfs 문제
		Queue<Integer> q = new LinkedList<>();
		q.add(S);

		// visited를 이동횟수로 사용해보자
		int[] visited = new int[F + 1];
		Arrays.fill(visited, -1);
		visited[S] = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			// G층에 도착했으면 끝
			if(cur == G) {
				cnt = visited[cur];
				break;
			}
			
			// 위버튼 삽입
			if(cur + U <= F && visited[cur + U] == -1) {
				visited[cur + U] = visited[cur] + 1;
				q.add(cur + U);
			}
			
			// 아래버튼 삽입
			if(cur - D > 0 && visited[cur - D] == -1) {
				visited[cur - D] = visited[cur] + 1;
				q.add(cur - D);
			}
		}
		
		
		// 도착 못하면 계단 써라
		if(cnt == -1) System.out.println("use the stairs");
		else System.out.println(cnt);
	}
	
}

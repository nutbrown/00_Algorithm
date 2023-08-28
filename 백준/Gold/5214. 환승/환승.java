import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		// 역의 수 N개
		// 1번 역에서 N번 역으로 가는데 방문하는 최소 역 개수
		int N = sc.nextInt();

		// 하이퍼튜브 M개
		// 하이퍼튜브 하나는 역 K개를 서로 연결한다
		int K = sc.nextInt();
		int M = sc.nextInt();

		// 인접리스트로 저장하되 하이퍼튜브도 노드로 본다
		// 1부터 N까지는 일반 노드
		// N + 1부터 N + M 까지는 하이퍼튜브로 본다
		ArrayList<Integer>[] arr = new ArrayList[N + 1 + M];
		for(int i = 0; i < N + 1 + M; i++) {
			arr[i] = new ArrayList<Integer>();
		}

		
		// 하이퍼튜브가 연결하는 역의 정보
		for(int i = 0; i < M; i++) {
			for(int j = 0; j  < K; j++) {
				// 첫번째가 1 2 3 이라면 첫번째 하이퍼튜브와 1 2 3을 연결
				// 실제 역은 1 ~ N
				// 하이퍼튜브의 인덱스는 N + 1 + i
				
				// 역을 입력 받고
				int in = sc.nextInt();
				// 하이퍼튜브에 역 연결
				arr[N + 1 + i].add(in);
				// 해당 역에도 하이퍼튜브 연결
				arr[in].add(N + 1 + i);
			}
		}
		
		for(int i = 1; i <= N; i++) {
			//System.out.println(arr[i].toString());
		}
		
		
		// 이렇게 역이 연결되어 있을 때
		// 1에서 N까지 가는 최솟값
		
		// 방문배열은 하이퍼튜브 포함
		int[] visited = new int[N + 1 + M];
		// 1에서 시작
		visited[1] = 1;
		int min = N + 1;
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {1, 1});
		
		while(!queue.isEmpty()) {
			int node = queue.peek()[0];
			int len = queue.poll()[1];
			//System.out.println("node " + node + " len " + len);
			
			// 최솟값을 넘겼다면 돌아가기
			if(len > min) continue;
			
			// 도착했다면 최솟값 갱신
			if(node == N) {
				if(len < min) min = len;
			}
			
			// 인접하는 노드들 방문
			for(int nextNode : arr[node]) {
				// -> 하이퍼튜브도 재방문하면 안 됨
				if(visited[nextNode] == 0) {
					visited[nextNode] = 1;

					// 하이퍼튜브라면 길이는 증가 안 시킴
					if(nextNode > N) {
						queue.offer(new int[] {nextNode, len});
					}
					// 하이퍼튜브가 아니라면 길이 증가
					else {
						queue.offer(new int[] {nextNode, len + 1});
					}
					
					// 아니면 노드끼리 직접적으 연결되어 있지 않으니까
					// 굳이 하이퍼튜브냐 아니냐 구분하지 않고
					// 나중에 길이를 반으로 나눠도 됨
				}
			}
		}
		
		// 1부터 N까지 모든 노드를 거쳐서 간다면
		// 방문하는 역의 개수는 N개다.
		// 만약 결국 도착하지 못했다면
		// 값이 갱신되지 않고 N + 1
		if(min == N + 1) min = -1;
		
		System.out.println(min);
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N;
	static int M;
	static ArrayList<Integer>[] com;
	static int[] cnt;
	static boolean[] visited;
	static Queue<Integer> queue;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 컴퓨터 수 (10,000)
		N = Integer.parseInt(st.nextToken());
		
		// 간선 수 (100,000)
		M = Integer.parseInt(st.nextToken());

		// 인접행렬이 아니라 인접리스트
		// 인접행렬은 노드 개수가 적을 때, 인접리스트는 간선 개수 적을 때
		com = new ArrayList[N + 1];
		for(int i = 0; i <= N; i++) {
			com[i] = new ArrayList<>();
		}
		
		// a가 b를 신뢰하는 경우
		// b를 해킹하면 a를 해킹할 수 있다
		// com[b].add(a) -> 시간초과
		// 4 ➝ 5 ➝ 3 ➝ 1
		//			 ➝ 2
		
		// a는 b에게 해킹당할 수 있다 -> 시간초과 안 남
		// 4 ← 5 ← 3 ← 1
		//			 ← 2
		// 4가 5에게 해킹당할 수 있으니까
		// 5가 해킹할 수 있는 컴퓨터 cnt[5] 증가
		// 다시 5가 3에게 해킹당할 수 있으니까
		// 3이 해킹할 수 있는 컴퓨터 cnt[3] 증가
		// 다시 5에서 시작할 때
		// 5가 3에게 해킹당할 수 있으니까
		// 3이 해킹할 수 있는 컴퓨터 cnr[3] 증가되어서 2가 됨
		// 이렇게 3이 해킹할 수 있는 컴퓨터는 2개가 된다.
		
		// 즉, 컴퓨터 n에서 시작해서 거슬러 올라갈 때
		// 그 n을 해킹할 수 있는 모든 컴퓨터를 만나서
		// 걔네가 다 n을 1개 해킹할 수 있다고 카운트를 올려줌
		// n개의 컴퓨터를 다 돌면 제일 많이 해킹할 수 있는 컴퓨터를 알 수 있음
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			com[a].add(b);
		}
		
		// 어떤 점에서 시작해서 끝까지 갔을 때
		// 어디서 시작하는 게 최댓값
		// 이 최댓값을 가진 컴퓨터 번호를 오름차순으로
		cnt = new int[N + 1];
		
		visited = new boolean[N + 1];
		queue = new LinkedList<>();

		// 각각 컴퓨터에서 시작
		for(int i = 1; i <= N; i++) {
			bfs(i);
		}
		
		// 최댓값 구하기
		max = 0;
		for(int i = 1; i <= N; i++) {
			if(cnt[i] > max) max = cnt[i];
		}
		//System.out.println(max);

		// 최댓값 가진 정점 출력
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 1; i <= N; i++) {
			if(cnt[i] == max) bw.write(i + " ");
		}
		bw.flush();
		bw.close();
	}
	
	public static void bfs(int i) {
		// 방문 배열 재활용
		Arrays.fill(visited, false);
		
		// 큐 재 생성하지말고 재활용
		queue.clear();
		
		visited[i] = true;
		queue.offer(i);
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			// node는 j에게 해킹당할 수 있다
			for(int j : com[node]) {
				if(!visited[j]) {
					visited[j] = true;
					queue.offer(j);
					// j가 node를 해킹할 수 있는 것
					// j가 해킹할 수 있는 컴퓨터 수 증가
					cnt[j]++;
				}
			}
		}

	}
}


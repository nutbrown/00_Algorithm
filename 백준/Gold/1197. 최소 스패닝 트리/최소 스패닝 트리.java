import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int[] p;
	
	// findset의 대푯값은 연결된 간선을 타고타고 올라간
	// 맨위의 부모 노드를 반환함 
	static int findset(int x) {
		// 타고타고 올라가서 대푯값이 나 자신인 부모가 나오면
		if(p[x] == x) return x;
		else return p[x] = findset(p[x]);
		// **경로압축 : 34퍼에서 시간초과가 난다면 이걸 안 해서
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 시간초과 -> 스캐너가 문제가 아니라 경로압축을 했어야
		
		// 최소 스패닝 트리 : 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중
		// 가중치(거리)의 합이 최소인 트리
		
		// 정점의 개수 V
		int V = sc.nextInt();
		// 간선의 개수 E
		int E = sc.nextInt();
		
		// 간선을 [시작정점, 끝정점, 가중치]로 저장
		int[][] edges = new int[E][3];
		// A정점과 B정점이 가중치 C인 간선으로 연결되어 있음(음수 가능)
		for(int i = 0 ; i < E; i++) {
			edges[i][0] = sc.nextInt();
			edges[i][1] = sc.nextInt();
			edges[i][2] = sc.nextInt();
		}
		
		// 가중치 작은 순서대로 정렬
		// Comparator는 양수면 자리 바꿈
		// o1이 o2보다 크면 자리를 바꾸겠다
		// 작은 순서대로 커지게 오름차순
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		// 대푯값, 부모노드(parent)를 나타냄
		p = new int[V + 1];
		for(int i = 1; i <= V; i++) {
			p[i] = i;
		}
		
		// 간선이 이어지면 대푯값이 본인이 아니라 연결된 선이 됨
		// 대푯값은 계속 find-set을 해서 x = p[x]인 x가 대표
		
		// 가중치의 합
		long ans = 0;
		// 뽑은 간선 수
		int pick = 0;
		
		// 사이클 확인 union-find 
		for(int i = 0; i < E; i++) {
			// findset의 결과인 대푯값이 다를 때만 합치기(사이클X)
			if(findset(edges[i][0]) != findset(edges[i][1])) {
				// 연결했으면 대푯값의 대푯값 갱신 (**이걸 잘못해서 틀림)
				p[findset(edges[i][0])] = findset(edges[i][1]);
				ans += edges[i][2];
				pick++;
			}
			
			if(pick == V - 1) break;
			// **간선 말고 정점 개수를 기준으로 -> 간선할 거면 왜 break하니
			// **break를 꼭 해야할까 -> 안 하면 시간초과 
		}
		
		System.out.println(ans);
	}
}

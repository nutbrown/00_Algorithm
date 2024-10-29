import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<Integer>[] list;
	static int cnt;
	static boolean[] visited;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 컴퓨터 수
    	int N = sc.nextInt();
    	list = new ArrayList[N + 1];
    	for(int i = 0; i <= N; i++) {
    		list[i] = new ArrayList<>();
    	}
    	
    	// 연결 간선 수
    	int M = sc.nextInt();
    	
    	for(int i = 0; i < M; i++) {
    		int node1 = sc.nextInt();
    		int node2 = sc.nextInt();

    		// 간선 양쪽에 추가
    		list[node1].add(node2);
    		list[node2].add(node1);
    	}
    	
    	// 1번 컴퓨터가 걸렸을 때, 바이러스 걸리는 컴퓨터 수
    	cnt = 0;
    	visited = new boolean[N + 1];
    	
    	// DFS 문제에 넣으셨으니까 DFS로 하자
    	visited[1] = true;
    	DFS(1);
    	
    	System.out.println(cnt);
    	
    }
    
    static void DFS(int node) {
    	
    	// 연결된 노드 중에서
    	// 방문하지 않은 곳에 방문
    	for(int next : list[node]) {
    		
    		if(!visited[next]) {
    			visited[next] = true;
    			cnt++;
    			DFS(next);
    		}
    	}
    	
    }
    
}
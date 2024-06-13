import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 도시 개수 N, 도로 개수 M
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		// 거리 정보 K, 도시 번호 X
		int K = sc.nextInt();
		int X = sc.nextInt();
		
		// 도시 배열
		ArrayList<Integer>[] adj = new ArrayList[N + 1];
		for(int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			adj[A].add(B);
		}
		
		// 도착 최단 거리 저장
		int[] length = new int[N + 1];
		Arrays.fill(length, -1);
		
		// 최단 거리가 K인 도시 저장
		ArrayList<Integer> list = new ArrayList<>();
		
		Queue<Integer> q = new LinkedList<>();
		length[X] = 0;
		q.add(X);
		
		while(!q.isEmpty()) {
			int x = q.poll();
			
			for(int i : adj[x]) {
				if(length[i] == -1) {
					length[i] = length[x] + 1;
					q.add(i);
					
					// 거리가 K면 저장하기
					if(length[i] == K) list.add(i);
				}
			}
		}

		
		Collections.sort(list);
		if(list.isEmpty()) System.out.println(-1);
		else {
			for(int i : list) {
				System.out.println(i);
			}
		}
	}
}
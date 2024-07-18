import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 1:20~
		
		// N개의 강의
		int N = sc.nextInt();
		
		// 강의번호 시작시간 종료시간
		int[][] arr = new int[N][2];
		for(int i = 0; i < N; i++) {
			// 강의 번호 안 중요해
			sc.next();
			
			// 시작시간 종료시
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		
		// 최대한 적은 수의 강의실을 사용하고 싶다
		// 사용되는 강의실의 최댓값을 갱신한
		int max = 0;
		
		// 시작시간 오름차순 + 종료시간은 안중요 정렬
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		
		// 강의가 시작되면 종료시간을 큐에 넣는다
		// 큐의 크기가 동시에 필요한 강의실 수
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			int st = arr[i][0];
			int ed = arr[i][1];
			
			// 지금 강의 시작할 건데
			// st보다 작아서 진작에 끝난 강의들 없애주기
			while(!pq.isEmpty() && pq.peek() < st) {
				pq.poll();
			}
			
			// 이때 최댓값 갱신했는지 확인
			max = Math.max(max, pq.size());
					
			// 시작시간에 끝나는 강의 있으면 없애기
			if(!pq.isEmpty() && pq.peek() == st) pq.poll();
			
			// 지금 강의 시작해서 큐에 종료시간 넣어주기
			pq.add(ed);
			
			// 이때도 최댓값 갱신했는지 확인
			max = Math.max(max, pq.size());
		}
		
		
		System.out.println(max);
	}
}
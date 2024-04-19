import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N개의 수업. Si에 시작해서 Ti에 끝난다
		int N = sc.nextInt();

		int[][] arr = new int[N][2];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 2; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// 시작하는 시간 순서대로 정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			// ArrayList<자료형>()처럼

			@Override
			public int compare(int[] o1, int[] o2) {
				
				if(o1[0] != o2[0]) {
					// o1 o2대로 커지게 두려면
					return o1[0] - o2[0];
				} else {
					// 시작시간이 같으면 빨리 끝나는 걸로
					return o1[1] - o2[1];
				}
			}
		});
		
		
		// (아래 알고리즘 분류를 봤다)
		// 그냥 우선순위큐는 작은 숫자 먼저 나온다
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		// 이렇게 하면 큰 숫자 먼저 나온다
		PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());
		
		
		// 로직 :
		// 최소의 강의실을 사용해서 모든 수업을 가능하게
		// 수업 N개를 차례대로 확인하기
		for(int i = 0; i < N; i++) {
			
			// 큐가 비어있으면 강의를 큐에 넣음으로써 강의실 배정을 한다
			// 이때 큐에는 강의가 끝나는 시간을 넣는다
			if(pq.size() == 0) pq.add(arr[i][1]);
			
			// i번째 강의를 시작할 때
			// 큐에서 그 강의 시작시간 이하로 끝나는 시간이 있는지 확인
			// 피큐로 제일 작은 숫자를 꺼내고, 끝나는 시간 새로 넣어준다
			else if(pq.peek() <= arr[i][0]) {
				pq.poll();
				pq.add(arr[i][1]);
			}
			
			// 없으면 기존의 강의실에 배정을 못하니까 피큐에 새로 넣는다
			else {
				pq.add(arr[i][1]);
			}
		}
		
		// 끝났을 때 피큐에 들어있는 숫자 개수 구하기
		System.out.println(pq.size());
		
		
	}
}

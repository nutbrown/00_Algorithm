import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		
		// N개의 대학에서 강연 요청
		int N = sc.nextInt();
		
		// 제일 나중 날짜 찾기
		int date = 0;
		
		// p만큼 강연료를 지불, d일 안에 와서 강연을 해주면
		int[][] arr = new int[N][2];
		for(int i = 0; i < N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
			date = Math.max(date, arr[i][1]);
		}
	
		// 하루 한 곳에서만 강연 가능. 최대로 벌 수 있는 돈.
		int max = 0;
		
		// 제일 돈 많이 주는 곳에 가고 싶지만,
		// 그건 나중에 가도 된다면, 지금 갈 수 있는 곳을 가는 게 더 낫다
		// 1일 : 1일안 2일안 3일안 
		// 2일 : 2일안 3일안
		// 3일 : 3일안		-> 에 갈 수 있는 곳을 갈 수 있다 
		

		// 날짜 내림차순으로 정렬
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});
		
		// N일부터 1일까지, 받을 수 있는 보수를 넣고
		// 각 날마다 제일 높은 금액꺼를 간다
		// ****아오 기본이 작은 수부터, 거꾸로가 큰 수부터
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		// (보수, 날짜) 인덱스
		int idx = 0;
		
		for(int i = date; i >= 1; i--) {
			
			while(idx < N) {
				// 인덱스 날이 현재 날짜 이상이라면 넣기
				if(arr[idx][1] >= i) {
					pq.add(arr[idx][0]);
					idx++;
				}
				
				// 인덱스 날이 현재 날짜보다 작아지면 다음 날짜
				else {
					break;
				}
			}
			
			// 제일 높은 금액 꺼내기
			if(!pq.isEmpty()) {
				max += pq.poll();
			}
		}
		
		System.out.println(max);
	}
}
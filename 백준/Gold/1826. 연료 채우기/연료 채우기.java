import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 주유소 N개
		int N = sc.nextInt();
		
		// 주유소까지 거리, 채울 수 있는 연료 양
		int[][] arr = new int[N][2];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 2; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// 거리가 가까운 순서대로 정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		// 성경이 위치에서 마을까지 거리 L
		// 트럭에 원래 있던 연료 양 P
		int L = sc.nextInt();
		int P = sc.nextInt();
		
		
		// 1km를 가면서 1L를 쓰는데
		// 가면서 주유소를 들르지 않아도 들렸다고 치고, 그만큼을 저장한다
		// 가다가 기름이 부족해지면 그 중에서 제일 큰 걸 꺼내서 쓴다. 오.
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		// 주유소 들르는 횟수
		int cnt = 0;

		// 주유소 인덱스
		int g = 0;
		
		
		// 주유소 기준으로 안하고, 거리 기준으로 해도 시간초과 안 날 거 같다
		// 범위를 L까지 해야한다***
		for(int i = 1; i < L; i++) {
			// 일키로 갔으면 기름 1리터
			P--;
			
			// 주유소 있으면 우선 기름 저장
			if(g < N && arr[g][0] == i) {
				pq.add(arr[g][1]);
				g++;
			}
			
			// 기름이 0 되었으면 기름 채우기
			if(P == 0) {
				if(!pq.isEmpty()) {
					// 기름이 있으면 채우고
					P += pq.poll();
					cnt++;
					
				} else {
					// 없으면 도착을 못한다
					cnt = -1;
					break;
				}
			}
		}
		
		System.out.println(cnt);
	}
}

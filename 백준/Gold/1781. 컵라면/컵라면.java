import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 문제 N개
		int N = sc.nextInt();

		// 데드라인과 풀면 받을 수 있는 컵라면 수
		int[][] arr = new int[N][2];
		for(int i = 0; i < N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		
		
		// 그리디 정렬 문제는 비슷하구나
		// 문제집이 있다 "이 문제들은 반복적으로 풀어야 하고, 사고 과정과 접근법을 기억해야 함"
		
		// 과제랑 똑같은 문제인데 이번에는 -> 우선순위큐로 풀어보자
		// 6일에는 데드라인이 6일 전인 문제들 중에서 컵라면수가 제일 많은 걸 해야한다
		// 보석도둑에서 가방 무게가 작은 것부터 한 이유는
		// 가방 무게가 작은 거에 들어가는 게 큰 거에도 들어갈 수 있고 '중복'되어서
		// 데드라인을 작은 것부터 늘리면 풀 수 있는 문제를 나중에는 못 푼다
		// 그래서 과제가 데드라인을 큰 것부터 했는데.. 아 똑같이 우선순위큐에 넣으면 되지
		
		
		// 데드라인 큰 순, 컵라면 수는 상관없다
		Arrays.sort(arr, new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
				return o2[0] - o1[0];
			}
		});
		
		// 데드라인이 '해당일' 이하인 문제들 컵라면 수
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		
		// 문제 인덱스 저장
		int p = 0;
		
		// 받을 수 있는 컵라면 수
		int sum = 0;
		
		// 데드라인 큰 거부터 작은 거로
		// 데드라인 큰 문제는, 작은 데드라인에서도 할 수 있게 '중복'된다
		for(int i = arr[0][0]; i > 0; i--) {

			// 데드라인이 i일 이상인 문제들 넣기
			for(int j = p; j < N; j++) {
				if(arr[j][0] >= i) {
					pq.add(arr[j][1]);
					p++;
				} else {
					break;
				}
			}

			// 풀 수 있는 문제 있으면 빼서 풀기
			if(!pq.isEmpty()) {
				sum += pq.poll();
			}
		}
		
		System.out.println(sum);
	}
}

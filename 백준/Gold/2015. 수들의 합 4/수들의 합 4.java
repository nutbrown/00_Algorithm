import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();
		
		// 경우의 수
		long cnt = 0;
		
		// 누적합
		// 배열과 map에 동시에 넣음
		int[] sum = new int[n + 1];

		// map에는 키를 누적합, 벨류를 인덱스로 넣음
		// 모두 0이라고 가정하면 경우의 수는 Long
		HashMap<Integer, Long> map = new HashMap<>();
		// 첫번째 인덱스도 확인하기 위해서 (0, 1) 넣음
		map.put(0, 1L);
		
		for(int i = 1; i <= n; i++) {
			sum[i] = sum[i - 1] + sc.nextInt();
		}
		
		// 누적합 - k가 앞에 있다면 부분합이 k인 것
		// 누적합은 점점 커지는게 아니니까 앞에 있는 것만 찾아야 함
		for(int i = 1; i <= n; i++) {
			if(map.containsKey(sum[i] - k)) {
				cnt += map.get(sum[i] - k);
			}
			
			// 본인에 해당하는 건 [i] - [i - 1] 이기 때문에
			// 본인 인덱스는 나중에 추가 (0일 때 문제)
			// 앞에 같은 값이 여러개 있을 수 있기 때문에 map에 개수로 추가
			if(map.containsKey(sum[i])) {
				map.put(sum[i], map.get(sum[i]) + 1);
			} else {
				map.put(sum[i], 1L);
			}
		}
		
		
		// map이 sum[i]를 갖고 있으면 키로 가져오지만
		// 아니면 0을 가져오는 메소드
		// map.getOrDefault(sum[i], 0);
		
		System.out.println(cnt);
	}
}
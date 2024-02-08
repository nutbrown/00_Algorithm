import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 멀티탭 구멍의 개수
		int N = sc.nextInt();
		
		// 전기 용품의 총 사용횟수 (전기 용품은 1~K번)
		int K = sc.nextInt();
		int[] arr = new int[K];
		for(int i = 0; i < K; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 콘센트
		ArrayList<Integer> list = new ArrayList<>();
		
		// 몇 번 빼는지
		int cnt = 0;
		
		for(int i = 0; i < K; i++) {
			
			// 1. 이미 꽂혀 있으면 통과
			if(list.contains(arr[i])) continue;
			
			// 2. 자리가 남아있으면 꽂는다
			if(list.size() < N) {
				list.add(arr[i]);
				continue;
			}
			
			// 3. 다른 걸 빼야한다면 뒤에 다시 나올 때 제일 뒤에 나오는 거나 없는 걸로
			int farthest = 0;
			int farthestIdx = 0;
			
			// 이미 꽂혀있는 전자기기 중에서
			for(int j = 0; j < list.size(); j++) {
			
				// 뒤로 가서 봤을 때 언제 나오는지
				int found = 0;
				
				for(int k = i + 1; k < K; k++) {
					if(arr[k] == list.get(j)) {
						found = k;
						break;
					}
				}
				
				// 계속 안 나오면 얘를 빼자
				if(found == 0) {
					farthestIdx = j;
					break;
				}
				
				// 그게 아니면 얼마나 먼지 갱신
				if(found > farthest) {
					farthest = found;
					farthestIdx = j;
				}
			}
			
			// 콘센트 빼고 꽂기
			list.remove(farthestIdx);
			cnt++;
			list.add(arr[i]);
		}
		
		
		System.out.println(cnt);
		
	}
}

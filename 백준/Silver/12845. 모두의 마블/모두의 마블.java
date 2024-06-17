import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 카드 개수 N
		// 인접한 카드 합성하면, 레벨의 합만큼 골드를 받는다
		// 카드를 합치면 큰 수로 레벨이 되고, 새로 생긴 카드만 쓸 수 있다 
		
		// 골드를 많이 받으려면 큰 숫자들을 합쳐야한다
		// 합쳐도 숫자가 큰 걸로 남고 더 커지지 않는다
		// 제일 큰 숫자로만 다 합친다

		// 카드 개수
		int N = sc.nextInt();
		
		// 제일 큰수, 인덱스
		int max = 0;
		int idx = 0;
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
			
			// 제일 큰 수 찾기
			if(arr[i] > max) {
				max = arr[i];
				idx = i;
			}
		}
		
		// 제일 큰 수 100,000가 1,000번 더해지면 -> 100,000,000
		// 나머지 1,000개가 100,000로 1번씩 더해지면 -> 100,000,000 (int)
		int gold = 0;
		for(int i = 0; i < N; i++) {
			if(i == idx) continue;
			
			gold += max + arr[i];
		}
		
		System.out.println(gold);
	}
}
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 식탁의 길이 N, 햄버거를 선택할 수 있는 거리 K
		int N = Integer.parseInt(sc.next());
		int K = Integer.parseInt(sc.next());
			
		// 사람과 햄버거 위치
		char[] arr = sc.next().toCharArray();
		
		// 최대 사람 수
		int max = 0;
		
		// 완전탐색해도 20,000 * 20 = 400,000 괜찮다
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == 'P') {

				// 앞뒤 10 거리에 가능한지
				for(int j = Math.max(0,  i - K); j <= Math.min(i + K, arr.length - 1); j++) {
					if(arr[j] == 'H') {
						// 왼쪽부터 햄버거 먹으면 없애주기
						arr[j] = 'N';
						max++;
						break;
					}
				}
			}
		}
		
		System.out.println(max);
	}
}
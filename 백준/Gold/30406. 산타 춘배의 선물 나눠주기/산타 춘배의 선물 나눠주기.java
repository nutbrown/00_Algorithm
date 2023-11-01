import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.next());
		int[] price = new int[4];
		
		for(int i = 0; i < N; i++) {
			price[sc.nextInt()]++;
		}
		
		// 가격은 0 이상 3 이하의 정수
		// 나머지는 같은 원소는 0 다른 원소는 
		//   0
		//   1
		// 1 0
		// 1 1

		// 0 1 -> 1
		// 0 2 -> 2
		// 0 3 -> 3
		// 1 2 -> 3
		// 1 3 -> 2
		// 2 3 -> 1
		
		int result = 0;

		// 우선 (0, 3)과 (1, 2)를 빼준다
		int min1 = 0;
		int idx1 = 0;
		if(price[0] < price[3]) {
			min1 = price[0];
			idx1 = 3;
		} else {
			min1 = price[3];
			idx1 = 0;
		}
		price[idx1] -= min1;
		result += 3 * min1;

		int min2 = 0;
		int idx2 = 0;
		if(price[1] < price[2]) {
			min2 = price[1];
			idx2 = 2;
		} else {
			min2 = price[2];
			idx2 = 1;
		}
		price[idx2] -= min2;
		result += 3 * min2;

		// 남은 숫자 idx1 idx2 안에서 매칭
		result += (idx1 ^ idx2) * Math.min(price[idx1], price[idx2]);
		
		
		System.out.println(result);
			
	}
	
	
}
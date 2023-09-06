import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		
		// 1의 약수의 합부터 n까지 약수의 합이다
		// 숫자 하나하나 약수의 합을 구하는 게 아니라
		// 1부터 시작해서 -> 이 수를 약수로 가지는 수가 몇 개인지 세어본다
		
		// 실질적 약수 -> 1과 본인을 제외
		int sum = 0;
		
		for(int i = 2; i <= n / 2; i++) {
			// 3이라고 예시를 들면
			// n이 8이라고 하면		3 6
			// n이 9라고 하면 		3 6 9
			// 약수가 i라고 할 때 n/i 개수만큼 있다
			// 여기서 본인은 제외해서 1을 뺴준다
			
			// 나눠지는가를 확인하는데
			// 10일 경우 5 * 2가 한계지 6 * 1.몇몇몇일 수 없다
			// 11일 경우 5 * 2가 한계지 6 * 1.몇몇몇일 수 없다
			sum += (n / i - 1) * i % 1000000; 
			sum %= 1000000;
		}
		
		System.out.println(sum);
		
	}
}
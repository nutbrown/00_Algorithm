import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// N!에서 뒤에서부터 처음 0이 아닌 숫자가 나올 때까지 0의 개수
		// 1부터 N까지 2의 개수랑 5의 개수가 몇 개 들어있는지
		
		int N = sc.nextInt();
		int two = 0;
		int five = 0;
		
		for(int i = 1; i <= N; i++) {
			
			int num = i;
			
			// 2로 다 나눠질 때까지 2로 나눠준다
			while(num % 2 == 0) {
				num /= 2;
				two++;
			}
			
			// 5로 다 나눠질 때까지 5로 나눠준다
			while(num % 5 == 0) {
				num /= 5;
				five++;
			}
			
		}
		
		System.out.println(Math.min(two, five));
	}
}
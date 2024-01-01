import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 양은 사료를 a그램씩, 염소는 사료를 b그램씩 먹는다
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		// 양 + 염소 전체 n마리
		// 하루동안 소비한 전체 사료량 w
		int n = sc.nextInt();
		int w = sc.nextInt();
		
		// 그래서 양과 염소수
		int sheep = 0;
		int goat = 0;
		
		// 완전탐색
		// 1 ≤ a ≤ 1,000, 1 ≤ b ≤ 1,000
		// 2 ≤ n ≤ 1,000, 2 ≤ w ≤ 1,000,000
		// 양 1마리부터 n마리까지
		for(int i = 1; i < n; i++) {
			// 총 소비 사료가 w라면
			if(i * a + (n - i) * b == w) {
				// 그런데 이미 해가 있어서 해가 2개라면
				if(sheep != 0 || goat != 0) {
					System.out.println(-1);
					return;
				}
				
				// 가능한 양과 염소수 입력 
				sheep = i;
				goat = n - i;
			}
		}
		
		// 만약에 해가 없다면
		if(sheep == 0 || goat == 0) {
			System.out.println(-1);
		} else {
			System.out.println(sheep + " " + goat);
		}
	}
}
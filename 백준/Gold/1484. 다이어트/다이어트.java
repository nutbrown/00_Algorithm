import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int G = sc.nextInt();
		// 몸무게 G 키로나 더 쪘어! 라는 뜻은
		// 현재 몸무게 제곱 - 기억하고 있는 몸무게 제곱
		
		// G가 15라면
		// [16 - 1] 또는 [64 - 49]가 될 수 있다
		// 4^2 - 1^2 = (4 - 1)(4 + 1)
		// 8^2 - 7^2 = (8 - 7)(8 + 7)
		
		// G가 16이라면
		// 1 16 (17 15) -> 안 나눠져서 안 됨
		// 2 8 (10 6) -> 3이랑 5
		
		// G가 35라면
		// 1 35 (36 34) 18이랑 17
		// 5 7 (12 2) 6이랑 1
		
		// 특정 수가
		// A * B가 된다면 (A + B / A - B)에
		// (A + B)/2 제곱이랑 (A - B)/2 제곱의 차이다
		
		
		StringBuilder sb = new StringBuilder();
		// 소인수분해 하기 
		for(int i = (int) Math.floor(Math.sqrt(G)); i > 0; i--) {
			if(G % i == 0) {
				// 두 수 -> i랑 G/i
				if(Math.abs(i - G / i) != 0 && 
						(i + G / i) % 2 == 0 && Math.abs(i - G / i) % 2 == 0) {
					sb.append((i + G / i) / 2).append("\n");
				}
				
			}
		}
		
		if(sb.length() == 0) System.out.println(-1);
		else System.out.println(sb);
	}
}
package allProblems;

import java.util.Scanner;

public class BJ_2839 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// 주어진 수를 3으로 나눈 몫보다 1단계 위부터 내려오기
		int n3 = N / 3;		
		int[] num = new int[(n3 + 1) * 5 + 1];
		for(int i = 1; i <= (n3 + 1) * 5; i++) {
			num[i] = -1;
		}
		
		// 나눈 몫보다 1단계 위부터 / 5n 부터 3n까지 같은 갯
		for(int i = n3 + 1; i > 0; i--) {
			for(int j = i * 5; j >= i * 3; j-= 2) {
				num[j] = i;
			}
		}
		System.out.println(num[N]);
	}
}

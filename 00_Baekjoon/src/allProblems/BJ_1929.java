package allProblems;

import java.util.Scanner;

public class BJ_1929{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		int[] dcm = new int[N + 1];
		
		dcm[1] = 0;
		for(int i = 2; i <= N; i++) {
			dcm[i] = 1;
		}
		for(int i = 2; i <= (int)Math.sqrt(N); i++) {
			for(int j = 2; j <= N / i; j++) {
				dcm[i * j] = 0;
			}
		}
		
		int count = 0;
		for(int i = M; i <= N; i++) {
			if(dcm[i] == 1) {
				count++;
//				System.out.println(i);
			}
		}
		System.out.println(count);
	}
}
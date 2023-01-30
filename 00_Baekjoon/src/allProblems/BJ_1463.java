package allProblems;

import java.util.Scanner;

public class BJ_1463 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		if(N == 1) {System.out.println(0);}
		if(N == 2) {System.out.println(1);}
		if(N == 3) {System.out.println(1);}
		
		if(N >= 4) {
			int[] seq = new int[N + 1];
			seq[1] = 0;
			seq[2] = 1;
			seq[3] = 1;
			for(int i = 4; i <= N; i++) {
				seq[i] = seq[i-1] + 1;
				if(i % 2 == 0) {
					seq[i] = Math.min(seq[i], seq[i/2] + 1);
				}
				if(i % 3 == 0) {
					seq[i] = Math.min(seq[i], seq[i/3] + 1);
				}
			}
			System.out.println(seq[N]);
		}
	}
}

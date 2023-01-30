package allProblems;

import java.util.Scanner;

public class BJ_15988 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
	
		int num = 0;
		for(int i = 0; i < T; i++) {
			num = Integer.parseInt(sc.nextLine());
			int[] seq = new int[num + 1];
			
			if(num < 4) {
				switch(num) {
					case 1: System.out.println(1); break;
					case 2: System.out.println(2); break;
					case 3: System.out.println(4); break;
				}
			} else {
				seq[1] = 1;
				seq[2] = 2;
				seq[3] = 4;
				for(int j = 4; j <= num; j++) {
					seq[j] = seq[j - 1] + seq[j - 2] + seq[j - 3];
				}
				long mod = seq[num] % 1000000009;
				System.out.println(mod);
			}
		}
			
	   }

}

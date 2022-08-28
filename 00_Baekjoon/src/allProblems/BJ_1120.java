package allProblems;

import java.util.Scanner;

public class BJ_1120 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();

		int dif = b.length() - a.length() + 1;
		int[] count = new int[dif];
		
		for(int i = 0; i < dif; i++)
//			count[i] = 0;
			for(int j = 0; j < a.length(); j++) {
				if(a.charAt(j) != b.charAt(j + i)) {
					count[i]++;	
			}
		}
	
		int min = count[0];
		for(int i =0; i < count.length; i++) {
			if(min > count[i]) {
				min = count[i];
			}
		}
		System.out.println(min);
	}	

}

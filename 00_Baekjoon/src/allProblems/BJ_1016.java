package allProblems;

import java.util.Scanner;

public class BJ_1016 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int min = sc.nextInt();
		int max = sc.nextInt();
		
		int[] arr = new int[max + 1];
		for(int i = 1; i <= max; i++) {
			arr[i] = 1;
		}
		
		for(int i = 2; i <= (int)Math.sqrt(max); i++) {
			int j = 1;
			while((int) Math.pow(i, 2) * j <= max) {
				arr[(int) Math.pow(i, 2) * j] = 0;
				j++;
			}
		}
		
		int count = 0;
		for(int i = min; i <= max; i++) {
			if(arr[i] == 1) {
				count++;
			}
		}
		System.out.println(count);
	}
}

package allProblems;

import java.util.Scanner;

public class BJ_4948 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 1;
		while(n != 0) {
			n = sc.nextInt();
			if(n == 0) {
				break;
			}
			System.out.println(dec(n));
		}
	}
	
	public static int dec(int n) {
		int[] arr = new int[2 * n + 1];
		for(int i = 2; i <= Math.sqrt(2 * n); i++) {
			for(int j = 1; j <= 2 * n / i; j++) {
				arr[i * j] = 1;
			}
		}
		int count = 0;
		for(int i = n + 1; i <= 2 * n; i++) {
			if(arr[i] == 0) {
				count++;
			}
		}
		return count;
	}
}

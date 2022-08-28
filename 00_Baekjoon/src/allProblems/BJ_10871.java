package allProblems;

import java.util.Scanner;

public class BJ_10871 {
	public static void method() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int X = sc.nextInt();	
		int[] arrA = new int[N];

		for(int i = 0; i < N; i++) {
			arrA[i] = sc.nextInt();
			if(arrA[i] < X) {
			System.out.print(arrA[i] + " ");
			}
		}
	}

	public static void main(String[] args) {
		method();
	}

}



package allProblems;

import java.util.Scanner;

public class BJ_7568 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] size = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 2; j++) {
				size[i][j] = sc.nextInt();
			}
		}
		
		
		for(int m = 0; m < N; m++) {
		int rank = 1;
			for(int i = 0; i < N; i++) {
				if(size[i][0] > size[m][0] && size[i][1] > size[m][1]) {
						rank = rank + 1;
					}
				}
		System.out.print(rank + " ");
		}
	
		
	}

}

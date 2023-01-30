package allProblems;

import java.util.Scanner;

public class BJ_12865 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[][] ks = new int[2][n];

		for(int i = 0; i < n; i++) {
			ks[0][i] = sc.nextInt();
			ks[1][i] = sc.nextInt();
		}
		for(int i = 0; i < n - 1; i++) {
			for(int j = i + 1; j < n; j++) {
				if(ks[0][i] < ks[0][j]) {
					int temp = ks[0][i];
					ks[0][i] = ks[0][j];
					ks[0][j] = temp;
				}
			}
		}
		
		// 현재무게,현재가치 - 1부터 무게 - 1부터 물건갯
		int[][] worth = new int[k + 1][n + 1];
		for(int i = 1; i <= k; i++) {
			worth[i][0] = 0;
		}
		
//		for(int stuff = 1; stuff <= n; stuff++) {
//			for(int weight = 1; weight <= k; weight++) {
//				if(worth)
//			}
//		}
//			for(int i = 0; i < n; i++) {
//				if(weight <= ks[0][i]) {
//					if(worth <= )
//					worth += ks[1][i];
//				}
//				
//			}
//		}
//		System.out.println(worth);
		
	}

}

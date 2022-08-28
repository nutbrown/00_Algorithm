package allProblems;

import java.util.Scanner;

public class BJ_2503 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String[][] num = new String[n][3];
		
		for(int i = 0;i < n; i++) {
			for(int j = 0; j < 3; j++) {
				num[i][j] = sc.next();
			}	
		}
		
		int count = 0;
		int[] cases = new int[n];
		int[] con1 = new int[n];
		int[] con2 = new int[n];
		
		for(int int_ans = 111; int_ans < 999; int_ans++) {
			String ans = Integer.toString(int_ans);
			if((ans.charAt(0) == ans.charAt(1) || ans.charAt(1) == ans.charAt(2) || ans.charAt(2) == ans.charAt(0)) == true) {
			} else if(ans.charAt(0) == '0' || ans.charAt(1) == '0' || ans.charAt(2) == '0') {
					  continue;
			}
			else {
				for(int i = 0; i < n; i++) {
					con1[i] = Integer.parseInt(num[i][1]);
					con2[i] = Integer.parseInt(num[i][2]);
					int strike = 0;
					int total = 0;
					
					// 같은 자리 같은 숫자
					for(int j = 0; j < 3; j++) {
						if(ans.charAt(j) == num[i][0].charAt(j)) {
							strike++;
						}	
					}
					// ans숫자 1개씩 num숫자 일치하는 거 갯수 다 구하기
					for(int j = 0; j < 3; j++) {
						for(int k = 0; k < 3; k++) {
							if(ans.charAt(j) == num[i][0].charAt(k)) {
								total++;
							}
						}
					}
					//strike ball 일치하면 0 불일치 1
					int ball = total - strike;	
					if(strike == con1[i] && ball == con2[i]) {
						cases[i] = 0;
					} else {
						cases[i] = 1;
					}
				}
				
				// 모든 줄 조건 일치하면 합이 0 이면 경우의수 +1
				int sum = 0;
				for(int i = 0; i < n; i++) {
					sum += cases[i];
				}
				if(sum == 0) {
					count++;
				}
			}
		}
			System.out.println(count);
	
	}
}

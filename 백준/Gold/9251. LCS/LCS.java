import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str1 = sc.next();
		String str2 = sc.next();
		
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		
		// 2차원 배열을 만듬 (행 열)
		int[][] dp = new int[ch1.length + 1][ch2.length + 1];
		
		// 배열 안에 들어가는 값은
		// 각각의 문자들 사이 LCS 길이
		
		// 예시
		// ACAYKP
		// CAPCAK
		
		// 해당 칸의 문자가 각각 같다면 숫자를 증가시키는데, 
		// AC'A' C'A' 에서 A가 추가될 때 증가된다면
		// 양쪽 문자열에서 길이가 하나 적을 때보다 길이가 1증가 되는 것이므로
		// 왼쪽 대각선 위의 공통 길이에 1이 증가된다
		
		// 해당 칸의 문자가 다르다면 숫자가 증가되지 않는다
		// 위쪽 문자열 길이 -1 과 아래쪽 문자열 길이 -1에서
		// 공통 부분이 더 많은 걸 가져온다
		// ACA CA -> 2
		// AC CAP -> 1
		// ACA CAP -> 2가 
		
		for(int i = 1; i <= ch1.length; i++) {
			for(int j = 1; j <= ch2.length; j++) {
				if(ch1[i - 1] == ch2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		System.out.println(dp[ch1.length][ch2.length]);
		
		
	}
}
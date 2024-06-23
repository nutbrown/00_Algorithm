import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 임한수의 영어 이름
		String str = sc.next();
		
		// (byte)로 형변환하면 아스키코드
		//System.out.println((byte)'A'); 65
		//System.out.println((byte)'Z'); 90
		
		// 펠린드롬 만들기 - 사전순으로 앞서는 거
		int[] arr = new int[91];
		
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			arr[ch]++;
		}
		
		// 펠린드롬 쌓거나 짝수 판별
		String s1 = "";
		String s2 = "";
		
		// 홀수는 1개만 허용
		int idx = -1;
		
		for(int i = 65; i <= 90; i++) {
			// 홀수면 저장 또는 아웃
			if(arr[i] % 2 == 1) {
				if(idx == -1) {
					idx = i;
				} else {
					System.out.println("I'm Sorry Hansoo");
					return;
				}
			}
			
			// 짝수나 첫번째 홀수면 문자 잇기
			if(arr[i] > 0) {
				for(int j = 0; j < arr[i] / 2; j++) {
					s1 += (char)i;
					s2 = (char)i + s2;
				}
			}
		}
		
		// 가운데 홀수 처리
		if(idx != -1) System.out.println(s1 + (char)idx + s2);
		else System.out.println(s1 + s2);
	}
}
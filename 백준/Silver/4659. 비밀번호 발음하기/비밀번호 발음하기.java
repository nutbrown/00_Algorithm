import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String pw = sc.next();
			if(pw.equals("end")) break;
			
			// 모음 a e i o u 반드시 포함
			// 모음 또는 자음 3개 연속 안 된다
			// 같은 글자 2개 연속 안 된다. ee oo 제외
			String result = "";
			
			// 연속 모음 개수
			// 연속 자음 개수
			int consonants = 0;
			int vowels = 0;
			
			// 모음 나왔는지
			boolean flag = false;
			
			for(int i = 0; i < pw.length(); i++) {
				char ch = pw.charAt(i);
				
				// 모음이면
				if(ch == 'a' || ch == 'e' || ch == 'i'
						|| ch == 'o' || ch == 'u') {
					// 모음 나왔다고 체크
					flag = true;
					
					// 연속된 자음 끊고
					vowels = 0;
					
					// 연속된 모음 증가시키기
					consonants++;
					
					// 연속된 모음이 3개가 됐으면 아웃
					if(consonants == 3) {
						result = "not ";
						break;
					}
				}
				
				// 자음이면
				else {
					// 위와 마찬가지
					consonants = 0;
					vowels++;
					if(vowels == 3) {
						result = "not ";
						break;
					}
				}
				
				// 같은 문자 두 번 연속이면 안 된다
				if(i > 0 && ch != 'e' && ch != 'o') {
					if(pw.charAt(i - 1) == ch) {
						result = "not ";
						break;
					}
				}
			}
			
			// 다 확인했는데 모음이 없으면 안 된다
			if(!flag) {
				result = "not ";
			}
			
			// aceptable이 아니라 acceptable***
			System.out.println("<" + pw + "> is " + result + "acceptable.");
		}
		
	}
}
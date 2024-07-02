import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 파일의 개수 N
		int N = Integer.parseInt(sc.next());
		
		// 패턴. 별표는 무조건 가운데에 있다
		// Dangling meta character '*' near index 0 -> \\ 붙여주기
		String[] pattern = sc.next().split("\\*");
		
		// 패턴1, 패턴2 길이
		int l1 = pattern[0].length();
		int l2 = pattern[1].length();
		
		for(int i = 0; i < N; i++) {
			String name = sc.next();
			
			// 길이가 더 짦으면 아니다
			if(name.length() < l1 + l2) {
				System.out.println("NE");
				continue;
			}
			
			// 앞에가 일치하고, 뒤에가 일치하면 통과
			if(name.substring(0, l1).equals(pattern[0])
					&& name.substring(name.length() - l2, name.length()).equals(pattern[1])) {
				System.out.println("DA");
			
			// 아니면 안통과
			} else {
				System.out.println("NE");
			}
			
		}
		
	}
}
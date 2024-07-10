import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str1 = sc.next();
		String str2 = sc.next();
		int max = 0;
		
		// contains 써서 투포인터로 풀기
		int l = 0;
		int r = 1;
		
		// 오른쪽 끝이 끝까지 갈 때까지
		while(r <= str1.length()) {
			String s = str1.substring(l, r);
			
			// 2번 문자열이 부분문자열 갖고 있으면, 늘리기
			if(str2.contains(s)) {
				// 최댓값 갱신 먼저 하고 ***순서
				max = r - l;
				r++;
			}
			
			// 2번 문자열이 부분문자열 안 갖고 있으면, 오른쪽으로 이동
			// 최대길이를 구하는거여서 - 가로세로 길이를 줄일 필요는 없다
			else {
				l++;
				r++;
			}
		}
		
		System.out.println(max);

		
		// 완전 탐색을 하면 어떻게 되지 -> 11퍼에서 멈춘
//		for(int i = 0; i < str1.length(); i++) {
//			// i부터 시작해도 최댓값을 못 넘으면 그만
//			if(str1.length() - i <= max) break;
//			
//			for(int j = 0; j < str2.length(); j++) {
//				// j부터 시작해도 최댓값을 못 넘으면 그만
//				if(str2.length() - j <= max) break;
//				
//				// i인덱스 시작점이랑, j인덱스 시작점이 같으면
//				if(str1.charAt(i) == str2.charAt(j)) {
//					// 어디 길이까지 같은지 확인한다
//					int l = 1;
//					
//					while(i + l <= str1.length() && j + l <= str2.length()) {
//
//						// 늘려서 확인했더니 같으면 늘리고
//						if(str1.substring(i, i + l + 1).equals(str2.substring(j, j + l + 1))) {
//							l++;
//						}
//						
//						// 다르면 안 늘리고 끝
//						else {
//							break;
//						}
//					}
//					
//					// 최댓값 갱신
//					max = Math.max(max, l);
//				}
//			}
//		}
//		
//		System.out.println(max);
	}
}
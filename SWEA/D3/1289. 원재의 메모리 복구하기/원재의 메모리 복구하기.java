
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		
        Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.nextLine());
		for(int t = 0; t < T; t++) {
			
			// (charAt 왼쪽부터) 숫자가 0이면 check -> false 
			// 왼쪽부터 가다가 1을 만나면 check -> true 하고 수정 횟수 1 증가
			// 다시 다른 check를 만나면 상태 바꾸고 수정 횟수 1 증가

			// 모두 0인 상태에서 시작 -> 처음 check = false
			boolean check = false;
			String mem = sc.nextLine();
			int count = 0;
			
			// 숫자가 바뀔 때 count 증가
			for(int j = 0; j < mem.length(); j++) {
				// 이전이 0 인데 1만났을 때
				if(!check && mem.charAt(j) == '1') {
					check = !check;
					count++;
				}
				
				// 이전이 1인데 0만났을 때
				if(check && mem.charAt(j) == '0') {
					check = !check;
					count++;
				}
			}
			
			System.out.printf("#%d %d\n", t + 1, count);
		}
        
        
	}
}

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
		
		// 테스트케이스
		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			int N = sc.nextInt();
			
			// N개의 정수 배열에 저장
			int[] arr = new int[N];
			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			// 수를 2개 곱하고 단조증가인지 확인한다
			// 최댓값을 최신화하면 전체탐색
			int max = 0;
			
			// 숫자 2개씩 곱함
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {
					
					// 단조 증가인지 확인하기 위해서 자료형을 문자로 변경
					String num = String.valueOf(arr[i] * arr[j]);
					
					// 단조 증가 수인지 확인
					boolean incr = true;
					for(int k = 0; k < num.length() - 1; k++) {
						if(num.charAt(k) > num.charAt(k + 1)) {
							// 연속 숫자가 감소했다면
							// increase를 flase로 하고 break
							incr = false;
							break;
						}
					}
					
					// 단조 증가 수라면 최댓값 최신화
					if(incr) {
						if(Integer.parseInt(num) > max) {
							max = Integer.parseInt(num);
						}
					}
					
				}
			}
			
			// 단조 증가 수가 없어서 max가 처음과 똑같이 0이라면
			// -1을 출력하기 위해서 max = -1
			if(max == 0) max = -1;
			
			System.out.printf("#%d %d\n", t + 1, max);

		}
        
        
        
	}
}
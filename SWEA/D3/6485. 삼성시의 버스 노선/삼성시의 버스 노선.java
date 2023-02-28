
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
		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			StringBuilder sb = new StringBuilder();

			// 버스 노선 N개
			int N = sc.nextInt();
			
			// 버스 노선은 A이상 B이하 정류장을 다닌다
			// 정류장에 노선이 있다는 표시로
			// A이상 B이하 위치에 노선 개수를 증가시킨다
			int[] route = new int[50001];
			for(int i = 0; i < N; i++) {
				int A = sc.nextInt();	// A 이상
				int B = sc.nextInt();	// B 이하 정류장을 다닌다
				
				for(int j = A; j <= B; j++) {
					route[j]++;
				}
			}
			
			// 버스 정류장 P개 : 0 정류장 위치 1 노선 개수
			int P = sc.nextInt();
			for(int i = 0; i < P; i++) {
				int busstop = sc.nextInt();

				// route에서 정류장 위치에
				// 지나가는 노선이 몇 개 있는지 찾는다
				sb.append(route[busstop] + " ");
			}
			
			
			System.out.printf("#%d %s\n", t + 1, sb);
		}
        
        
	}
}
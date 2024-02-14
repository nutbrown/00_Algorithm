import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// <x, y>의 다음 해를 구할 때
		// x < M이면 x는 증가하지만, 그렇지 않으면 1이 된다
		// y < N이면 y는 증가하지만, 그렇지 않으면 1이 된다
		
		// M = 10, N = 12
		// 1 번째 해 <1:1>
		// 10번째 해 <10:10>
		// 11번째 해 <1:11>
		// 13번째 해 <3:1>
		// 60번째 해 <10:12> 마지막 해
		
		// n번째 해 <n % 10 : n % 12>
		// 둘의 최소공배수를 지나면 반복된다
		// 따라서 카잉 달력의 마지막 해 <M:N>은 최소공배수 LCM(M, N)
		
		int T = sc.nextInt();
		loop:
		for(int t = 0; t < T; t++) {
			
			// 카잉달력 <M:N>이 마지막 해일 때
			int M = sc.nextInt();
			int N = sc.nextInt();

			// <x:y>가 몇 번째 해인지
			int x = sc.nextInt();
			int y = sc.nextInt();
			

			// M으로 나눈 나머지가 x인 수 중에서
			// N으로 나눈 나머지가 y인 수를 찾는다
			
			// M = 10, N = 12, <7:2> 라면
			// 1  2  3  4  5  6  7  8  9  10 
			// 11 12 13 14 15 16 17 18 19 20
			// 나머지가 7인 7 17 27중에서 나머지가 2인 걸 찾는다
			
			// 만약 <10:2>라면 10부터 10을 더한 20 30중에서 나머지가 2인 걸
			// 만약 <10:12>라면 10부터 나머지가 0인 걸 찾는다
			
			// x가 M이면 나누는 거 상관없이 M부터 M을 더해가면 된다
			// if(x == M) x = 0;
			// y가 N이면 나머지가 0인 걸 찾는 것이다
			if(y == N) y = 0;
			
			// M * N까지가 아니라 LCM(M, N)까지 해야한다
			for(int i = x; i <= M * N; i += M) {
				
				if(i % N == y) {
					System.out.println(i);
					continue loop;
				}
			}
			
			// 표현되는 해가 유효하지 않으면 -1 출력
			System.out.println(-1);
			
		}
	}
}

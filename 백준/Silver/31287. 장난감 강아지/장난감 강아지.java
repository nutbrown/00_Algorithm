import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 길이 N의 문자열 S
		// 문자열 S를 K번 이어붙인 문자열을 T
		int N = sc.nextInt();
		int K = sc.nextInt();
		String S = sc.next();
		
		// 원점에서 시작해서 문자를 하나씩 읽으면서
		// U D L R 위 아래 왼쪽 위로 움직이기
		
		// T를 따라서 움직이는 동안 원점을 다시 방문하는지
		// 원점에서 시작 **2000번을 10^9만큼 하면 int가 아니다
		long x = 0;
		long y = 0;
		
		String ans = "NO";
		loop:
		for(int i = 0; i < Math.min(K, N); i++) {
			for(int j = 0; j < N; j++) {
				char cmd = S.charAt(j);
				
				if(cmd == 'U') y++;
				else if(cmd == 'D') y--;
				else if(cmd == 'L') x--;
				else if(cmd == 'R') x++;
				
				//System.out.println(cmd + " " + x + " " + y);
				// 다시 원점이라면 YES
				if(x == 0 && y == 0) {
					ans = "YES";
					break loop;
				}
			}
		}
		
		System.out.println(ans);
	}
}

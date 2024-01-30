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
			// 그냥 시간초과 안 날 정도만 횟수를 제한하는 사람도 있다
			// ?
			// 1번 돌았을 때 원점으로 돌아오지 않는다면 원점에서 일정하게 멀어진다
			// 그래도 그 중간에 다시 돌아오는 과정에서 원점을 지날 수 있어서 K번을 반복한다
			// 이때 중간에 다시 돌아온다고 해도 최대 N만큼 돌아올 수 있다
			// 원점에서 최소 1씩 멀어졌을 때 N번을 반복하면 N만큼 멀어진다.
			// 1번 반복할 때 아무리 원점으로 가까이 와봤자 N만큼만 가까이 올 수 있으니까
			// N번 이후로는 반복할 필요가 없다. 다시 원점으로 돌아올 수 없다.
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

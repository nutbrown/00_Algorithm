import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		// 집 위치 X, Y (양수)
		long X = sc.nextInt();
		long Y = sc.nextInt();
		
		// 한 블록 소요시간, 대각선 소요시간
		long W = sc.nextInt();
		long S = sc.nextInt();
		
		// 도로를 가로, 세로, 대각선으로 움직이기
		// 집으로 가는데 걸리는 최소시간
		long sum = 0;
		
		
		// 가로세로가는 게 대각선으로 가는 것보다 빠르면
		if(S >= W * 2) {
			sum = (X + Y) * W;
		}
		
		// 가로세로 2칸보다 대각선이 빠르지만
		else if(S < W * 2) {
			// 둘 중에 큰 거 작은 거
			long big = Math.max(X, Y);
			long small = Math.min(X, Y);
			
			// W나 S에 도달할 때까지 대각선
			sum += small * S;
			
			
			// 가로세로 1칸이 대각선보다 빠르면
			// 대각선으로 최대한 가고 나머지는 한 칸씩
			if(W <= S) {
				sum += (big - small) * W;
			}
			
			// 그런데 가로세로 1칸보다 대각선이 더 빠르면
			// 2칸씩은 대각선으로 가고, 나머지는 한칸
			else {
				if((big - small) % 2 == 1) sum += W;
				sum += ((big - small) / 2 * S * 2);
			}
			
		}
		
		
		System.out.println(sum);
	}
}

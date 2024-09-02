import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// N개의 조약돌
		int N = Integer.parseInt(sc.next());

		// 까만색 조약돌 B개 이하, 흰색 조약돌 W개 이상
		int B = Integer.parseInt(sc.next());
		int W = Integer.parseInt(sc.next());
		
		// N개의 조약돌 정보
		char[] stones = new char[N];
		String in = sc.next();
		for(int i = 0; i < N; i++) {
			stones[i] = in.charAt(i);
		}
		
		// 가장 긴 구간의 길이
		int max = 0;

		// 시작점 끝점 검은돌 흰돌
		int st = 0;
		int ed = 0;
		int black = 0;
		int white = 0;
		
		// 0부터 W개 이상인 최소 구간으로 시작
		while(ed < N) {
			
			// ed까지 개수를 세고
			if(stones[ed] == 'W') white++;
			else black++;

			// white가 W개 이상이면 끝
			if(white >= W) break;
			
			// 아니면 다음꺼
			// (여기서 N으로 넘어가면 뒷 로직을 안 하고 0을 출력) 
			ed++;
		}
		
		
		// B개 보다 크면 줄이기, B개 이하면 계속 늘이기
		while(ed < N) {
			
			// 조건 맞으면 max 갱신
			if(black <= B && white >= W) {
				max = Math.max(max, ed - st + 1);
			}
			
			// ed가 N-1이면 끝
			if(ed == N - 1) break;
			
			// B개 이하면 ed를 늘인다
			if(black <= B) {
				ed++;
				if(stones[ed] == 'W') white++;
				else black++;
			
			// B개 보다 크면 st를 좁힌다
			} else {
				if(stones[st] == 'W') white--;
				else black--;
				st++;
			}
		}
		
		
		System.out.println(max);
		
	}
}
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 고객들 수 N
		int N = sc.nextInt();
		
		// 고객들 위치 
		int[] x = new int[N];
		int[] y = new int[N];
		for(int i = 0; i < N; i++) {
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
		}
		
		// 안테나랑 마찬가지로
		// 맨 구석에 있을 때 거리 합이 제일 크다가
		// x y가 증가하는 방향으로 갈수록 증가하는 방향의 집 개수만큼 증가한다
		// 그러다가 반대쪽 방향에 집이 더 많아질 때 거리 합이 다시 감소한다
		
		// 1 99 100이 있고
		// 위치가 1:	0 + 98 + 99 = 197
		// 위치가 2:	1 + 97 + 98 = 196 ...
		// 위치가 98:	97 + 1 + 2 = 100
		// 위치가 99:	98 + 0 + 1 = 99
		// 위치가 100:99 + 1 + 0 = 99
		// 위치가 101:100 + 2 + 1 = 103
		
		// 평균인지 중앙값인지
		// 오른쪽으로 1칸 이동할 때마다 오른쪽 집 개수만큼 감소한다
		// 그러다가 왼쪽 집이 더 많아지면 왼쪽 집 개수만큼 증가한다
		// 왼쪽 집이 더 많아지기 전 중앙값으로 해야한다 (x따로 y따로)
		
		// 정렬해서 중앙값
		Arrays.sort(x);
		Arrays.sort(y);
		
		// x 중앙값, y 중앙값 구하기
		int medianX = 0;
		int medianY = 0;
		
		if(N % 2 != 0) {
			medianX = x[N / 2];
			medianY = y[N / 2];
		} else {
			medianX = x[N / 2 - 1];
			medianY = y[N / 2 - 1];
		}
		
		// 최소 거리의 합 구하기 **좌표 최댓값이 1,000,000니까 long
		long sum = 0;
		for(int i = 0; i < N; i++) {
			sum  += Math.abs(x[i] - medianX);
			sum  += Math.abs(y[i] - medianY);
		}
		
		System.out.println(sum);
		
	}
}
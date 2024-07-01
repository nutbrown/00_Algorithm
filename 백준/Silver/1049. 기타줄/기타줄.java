import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 끊어진 기타줄의 개수 N과 기타줄 브랜드 M개
		// 각 브랜드 기타줄 6개 패키지 가격, 낱개 가격
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		// 패키지 최소 가격, 낱개 최소 가격
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		
		for(int i = 0; i < M; i++) {
			min1 = Math.min(min1, sc.nextInt());
			min2 = Math.min(min2, sc.nextInt());
		}
		
		// 필요한 돈의 최솟값
		int sum = 0;
		
		// 패키지 가격이 더 싸다면 최대한 패키지
		if(min1 < min2 * 6) {
			sum += N / 6 * min1;
			N %= 6;
		} 
		
		// 남은 거 다 패키지 하는 게 나으면 패키지
		if((N / 6 + 1) * min1 < N * min2) {
			sum += (N / 6 + 1) * min1;
		}
		// 아니면 남은 거 다 낱개 구매
		else {
			sum += N * min2;
		}
		
		System.out.println(sum);
		
	}
}
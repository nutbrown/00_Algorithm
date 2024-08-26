import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 연병장 크기 N, 조교 수 M
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		// 연병장 높이
		// 100,000에 100,000명이 다 10,000만큼 증가시키면 -> 1,000,000,000(int 범위)
		int[] height = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			height[i] = sc.nextInt();
		}
		
		// 변화량 기록
		int[] imos = new int[N + 2];
		
		// 조교 지시
		for(int i = 0; i < M; i++) {
			// a칸부터 b칸까지 흙 변화시키기
			int a = sc.nextInt();
			int b = sc.nextInt();
			int k = sc.nextInt();
			
			imos[a] += k;
			imos[b + 1] -= k;
		}

		// 변화량 누적합 하면서 높이 변화시키고 바로 출력
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= N; i++) {
			
			// 변화량 누적합
			imos[i] += imos[i - 1];
			
			// 높이 변화시키고 출력
			height[i] += imos[i];
			sb.append(height[i]).append(" ");
		}
		
		System.out.println(sb);
		
	}
}
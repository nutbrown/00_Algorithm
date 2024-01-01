import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 사탕 N개
		int N = sc.nextInt();
		
		// 남는 사탕은 없음
		// 0개 받는 사람은 없음
		// 택희가 받는 사탕 수는 짝수
		// 남규는 영훈이보다 2개 더 받음
		
		// 세 사람에게 분배하는 경우의 수
		int cnt = 0;

		for(int i = 2; i < N; i += 2) {
			for(int j = 1; j < N; j++) {
				// 택희 i개, 영훈이 j개일 때
				// 남규가 영훈이보다 2개 더 받는지 확인
				if(N - i - j >= j + 2) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);

	}
}
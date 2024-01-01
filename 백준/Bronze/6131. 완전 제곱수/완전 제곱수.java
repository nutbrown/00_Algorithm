import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// A이 제곱은 B의 제곱보다 N만큼 크다
		// 1 <= N <= 1000, 1 ≤ B ≤ A ≤ 500
		// A와 B의 쌍의 개수 구하기
		int N = sc.nextInt();

		// 완전탐색을 하고자 노력 (500 제한이 없다고 생각해보자)
		// AA-BB를 만족하는 건 1개의 A당 1개의 B다
		
		// A를 1부터 증가시킴
		// B를 1부터 AA-BB 해서 N인지 보는데
		// AA-BB가 N보다 작아지면 -> 다음 A 탐색

		// AA-1 >= N 인 N으로 시작해야 한다
		// Math.ceil(Math.sqrt(N + 1))
		int cnt = 0;
		int a = (int) Math.ceil(Math.sqrt(N + 1));
		
		while(true) {
			// 바로 작은 수를 뺐는데 그 차이가 N보다 크면 그만
			if(a * a - (a - 1)*(a - 1) > N) break;
			
			for(int b = 1; b <= a; b++) {
				// AA-BB를 만족하면 카운트 증가
				if(a * a - b * b == N) {
					cnt++;
					break;
				} else if(a * a - b * b < N) {
					break;
				}
			}
			a++;
		}
		
		System.out.println(cnt);
		
	}
}

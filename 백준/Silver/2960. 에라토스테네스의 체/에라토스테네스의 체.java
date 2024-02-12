import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int K = sc.nextInt();
		
		// 2부터 N까지의 수를 에라토스테네스의 체 했을 때 - K번째 지우는 수
		int x = 0;
		
		// 소수를 찾는 게 아니어서 sqrt까지 하는 게 아니다
		boolean[] erased = new boolean[N + 1];
		for(int i = 2; i <= N; i++) {
			
			// 아직 지우지 않은 수중 가장 작은 수 P
			if(!erased[i]) {
				
				for(int j = 1; i * j <= N; j++) {
					if(!erased[i * j]) {
						erased[i * j] = true;
						x++;

						if(x == K) {
							System.out.println(i * j);
							return;
						}
					}
				}
			}
		}
		
	}
}
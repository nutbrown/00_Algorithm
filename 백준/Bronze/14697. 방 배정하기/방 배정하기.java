import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] ppl = new int[3];
		for(int i = 0; i  < 3; i++) {
			ppl[i] = sc.nextInt();
		}
		
		int N = sc.nextInt();

		// 모든 경우의 수를 완전탐색
		for(int i = 0; i <= N/ppl[0]; i++) {
			for(int j = 0; j <= N/ppl[1]; j++) {
				for(int k = 0; k <= N/ppl[2]; k++) {
					if(N - i * ppl[0] - j * ppl[1] - k * ppl[2] == 0) {
						System.out.println(1);
						return;
					}
				}
			}
		}
		
		System.out.println(0);
	}	
}
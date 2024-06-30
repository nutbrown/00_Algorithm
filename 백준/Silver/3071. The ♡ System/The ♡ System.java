import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 3으로 나눴을 때 2이상이 나오면 9-3으로 해야한다
		// 9로 나눴을 때 2이상이 나오면 27-9로 해야한다
		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			long num = sc.nextLong();
			
			// 저장
			long[] digit = new long[31];

			// 와 이거 엄청 오래 풀었다
			// 3진법으로 만들기 (음수도)
			for(int i = 30; i >= 0; i--) {
				if(num / (long)Math.pow(3, i) != 0) {
					digit[i] = num / (long)Math.pow(3, i);
					num -= (long)Math.pow(3, i) * digit[i];
				}
			}

			// 2가 있으면 1과 -1로 만들기
			for(int i = 0; i <= 30; i++) {
				if(digit[i] == 2) {
					digit[i + 1]++;
					digit[i] = -1;
				} else if(digit[i] == 3) {
					digit[i + 1]++;
					digit[i] = 0;
				}
				
				// (음수도)
				if(digit[i] == -2) {
					digit[i + 1]--;
					digit[i] = 1;
				} else if(digit[i] == -3) {
					digit[i + 1]--;
					digit[i] = 0;
				}
			}
			
			
			StringBuilder sb = new StringBuilder();
			for(int i = 30; i >= 0; i--) {
				if(digit[i] != 0) {
					for(int j = i; j >= 0; j--) {
						if(digit[j] == -1) sb.append("-");
						else sb.append(digit[j]);
					}
					break;
				}
			}
			
			// 0일 때 빈칸 출력해서 틀렸다
			if(sb.length() == 0) sb.append("0");
			System.out.println(sb);
		}
		
	}
}
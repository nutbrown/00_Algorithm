import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		String S = sc.next();
		
		int[] num = new int[N];
		for(int i = 0; i < S.length(); i++) {
			num[i] = Character.getNumericValue(S.charAt(i));
		}
		
		// 1일이상 641일이하 가능
		// 3자리 수가 가능하면 무조건 3자리 수
		// 6이하여야함 + 
		
		// 병사수
		int cnt = 0;
		// 현재 전역일 시작 인덱스
		int idx = 0;
		
		while(idx < N) {
			boolean flag = false;
			// 0 1 2 3 [4]
			// 3자리 없애기
			if(N - idx >= 3) {
				boolean flag2 = true;
				// 3개를 없앴을 때 그 다음 숫자가 있는데, 0이면 안 된다
				if(N - idx > 3 && num[idx + 3] == 0) {
					flag2 = false;
				}
				
				else if(flag2 && num[idx] < 6) {
					cnt++;
					idx += 3;
					flag = true;
				} else if(num[idx] == 6) {
					if(num[idx + 1] < 4) {
						cnt++;
						idx += 3;
						flag = true;
					} else if(num[idx + 1] == 4) {
						if(num[idx + 2] <= 1) {
							cnt++;
							idx += 3;
							flag = true;
						}
					}
				}
			}
			
			// 3자리 없앴으면 다음 3자리 찾기
			if(flag) continue;

			if(N - idx >= 2) {
				boolean flag2 = true;
				// 2개를 없앴을 때 다음 숫자가 있는데, 0이면 안 된다
				if(N - idx > 2 && num[idx + 2] == 0) {
					flag2 = false;
				}
				else if(flag2) {
					cnt++;
					idx += 2;
					flag = true;
				}
			}
			
			// 2자리 없앴으면 다음 2자리 찾기
			if(flag) continue;
			
			// 3자리 2자리 다 안 되면 1자리 없애기
			cnt++;
			idx++;
		}
		
		System.out.println(cnt);
	}
}

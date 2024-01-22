import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		String S = sc.next();
		
		ArrayList<Integer> num = new ArrayList<>();
		for(int i = 0; i < S.length(); i++) {
			num.add(Character.getNumericValue(S.charAt(i)));
		}
		
		// 1일이상 641일이하 가능
		// 3자리 수가 가능하면 무조건 3자리 수
		// 6이하여야함 + 
		
		int cnt = 0;
		while(!num.isEmpty()) {
			boolean flag = false;
			if(num.size() >= 3) {
				boolean flag2 = true;
				if(num.size() > 3 && num.get(3) == 0) {
					flag2 = false;
				}
				if(flag2 && num.get(0) < 6) {
					cnt++;
					num.remove(0);
					num.remove(0);
					num.remove(0);
					flag = true;
				} else if(num.get(0) == 6) {
					if(num.get(1) < 4) {
						cnt++;
						num.remove(0);
						num.remove(0);
						num.remove(0);
						flag = true;
					} else if(num.get(1) == 4) {
						if(num.get(2) <= 1) {
							cnt++;
							num.remove(0);
							num.remove(0);
							num.remove(0);
							flag = true;
						}
					}
				}
			}
			
			// 3자리 없앴으면 다음 3자리 찾기
			if(flag) continue;

			if(num.size() >= 2) {
				boolean flag2 = true;
				if(num.size() > 2 && num.get(2) == 0) {
					flag2 = false;
				}
				if(flag2) {
					cnt++;
					num.remove(0);
					num.remove(0);
					flag = true;
				}
			}
			
			// 2자리 없앴으면 다음 2자리 찾기
			if(flag) continue;
			
			if(num.size() >= 1) {
				cnt++;
				num.remove(0);
			}
		}
		
		System.out.println(cnt);
	}
}

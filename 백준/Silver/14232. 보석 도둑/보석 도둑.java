import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long k = sc.nextLong();
		
		// 훔쳐올 보석의 개수
		long cnt = 0;
		
		// 보석의 무게 넣는
		ArrayList<Long> list = new ArrayList<>();

		
		// k가 1이 될 때까지
		// 작은 소수부터 나눠본다 (2번씩)
		// 소수만 골라서 하려다가 long이 번거로워서 그냥
		
		long i = 2;
		while(k > 1) {
			// 루트k를 넘었는데도 1이 되지 않았다면
			// 그 수는 더이상 나눠질 수 없기 때문에 멈춘다
			if(i > Math.sqrt(k)) {
				cnt++;
				list.add(k);
				break;
			}
			
			if(k % i == 0) {
				cnt++;
				k /= i;
				list.add(i);
			} else {
				i++;
			}
		}
		
		System.out.println(cnt);
		StringBuilder sb = new StringBuilder();
		for(int j = 0; j < list.size(); j++) {
			sb.append(list.get(j)).append(" ");
		}
		System.out.println(sb);
		
	}
}

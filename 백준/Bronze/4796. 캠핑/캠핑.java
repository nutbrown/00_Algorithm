import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 캠핑장은 연속하는 P일 중, L일동안만 사용할 수 있다.
		// 강산이는 V일짜리 휴가를 시작했다
		
		int t = 0;
		
		while(true) {
			int L = sc.nextInt();
			int P = sc.nextInt();
			int V = sc.nextInt();
			
			if(L == 0) break;
			else t++;
			
			System.out.print("Case " + t + ": ");
			System.out.println(L * (V / P) + Math.min(V % P, L));
		}
	}
}
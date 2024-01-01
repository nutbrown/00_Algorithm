import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 버섯 먹기를 멈추면 더 먹을 수 없이 끝
		int sum = 0;

		for(int i = 0; i < 10; i++) {
			int in = sc.nextInt();
			// 최대한 100에 가깝도록 + 먹을지 말지
			// 똑같을 경우 큰 수를 출력하니까 >= 이하
			if(Math.abs(100 - sum) >= Math.abs(100 - sum - in)) sum += in;
			else break;
            
		}
		
		System.out.println(sum);	
	}
}

import java.util.Scanner;

public class Main {
	
	static int count;
	static StringBuffer sb = new StringBuffer();

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		// 원판 n개를 어디서 어디로 이동할지
		hanoi(N, 1, 3);
		
		// count를 먼저 출력하려면 StringBuilder 사용
		System.out.println(count);
		System.out.println(sb);
	}
	
	
	// 귀납적인 사고를 해야한다.
	// 어디서 어디로 어떤번호를 이동할지가 아님
	// 원판 n개를 어디서 어디로 이동할지

	
	// 원판 n개를 from에서 to로 옮기는 함수
	static void hanoi(int num, int from, int to) {
		
		// 원판 1개 옮기는 건 함수 호출 안하고 그냥 옮기면 된
		if(num == 1) {
			// 제일 작은 1번 원판을 옮길 때마다 온다
			// 여기서 count 출력하면 여러번 출력된다

			// 맨 처음 1개 이동한 건 여기서 출력
			count++;
			sb.append(from + " " + to + "\n");
			
			return;
		}
		
		// 원판 n-1 개를 from에서 나머지로 옮긴다
		hanoi(num - 1, from, 6-from-to);
		
		// 굉장히 헷갈렸던 점 !
		// n-1개를 from에서 나머지로 옮기는 위에서 호출한 함수 안에서
		// 1개 옮길 때마다 count print가 다 되어서 나온다.

		// 원판 n을 하나 from에서 to로 옮긴다
		// 이 함수에서는 이것만! 옮기는 거니까
		// 여기서 count 증가 및 프린트 한다
		count++;
		sb.append(from + " " + to + "\n");
		
		// 원판 n-1개를 나머지에서 to로 옮긴다
		hanoi(num-1, 6-from-to, to);
		
	}
	
}

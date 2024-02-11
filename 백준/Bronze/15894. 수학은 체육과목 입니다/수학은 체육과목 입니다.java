import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 가장 아랫부분의 정사각형이 n개일 때
		// n, n-1, n-2 ...1 개로 쌓여있을 때
		// 이 도형의 둘레의 길이
		
		int n = sc.nextInt();
		long sum = 0;
		
		// 위에서 본, 아래에서 본 가로 높이
		sum += n * 2;
		
		// 양 옆에서 본 세로 높이
		sum += n * 2;
		
		System.out.println(sum);
	}
}
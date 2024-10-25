import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);
		
		
		// 123456789 10 11 12 13 ...
		// N번째 숫자 찾기
		int N = sc.nextInt();
		
		// 1자리 수, 2자리 수, 3자리 수... 빼기
		// 1 * 9
		// 10 * 9
		// 100 * 9
		int len = 1;
		
		// Math.pow를 그냥 하면 10.0 이렇게 나오는 거 아는데
		// len*9 했을 때 어짜피 없어지지 않나.........아오오 3시반부터 1시간을 했네
		while((N - len * 9 * (long)Math.pow(10, len - 1)) > 0) {
			
			N -= len * 9 * (long)Math.pow(10, len - 1);
			len++;
		}

		// 9까지 1의자리
		// 9 + 10~19 ... 90~99 90 -> 9 + 2*90 까지 2의자리
		// 9 + 2*90 + 3*900 2889까지 3의자리
		
		// len 자리 수에서 N번째 수를 찾는다
		// 3자리 수라고 하면 100 101 102 103 이렇게 3개씩 늘어난다
		// N이 len 자리수 중에서 몇 번째인지 찾고 + len 중에서 어떤 수인지 찾기
		
		// 123 456 789 보다 012 345 678 이 더 구하기 쉽다
		// long number = (long)Math.pow(10, len - 1) + (long)(N - 1) / len;
		// -> 이게 왜 안 먹는지는 모르겠는데 -> Math.pow에 너무 큰 수를 넣었다
		// 1000000000000 + 15라고 했을 때 앞을 굳이 더할 필요 없다

		// 길이 1일 때는 따로
		if(len == 1) {
			System.out.println(N);
			return;
		}
			
		
		
		// (N-1)/len 번째 수에서, (N-1)%len 번째 숫자
		int number = (N - 1) / len;
		
		String strNumber = String.valueOf((long)Math.pow(10, len - 1) + number);
		System.out.println(strNumber.charAt((N - 1) % len));
	}
}

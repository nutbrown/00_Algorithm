import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 해당 숫자는 약수의 개수만큼 뒤집힌다. (1포함)
		// 제곱수를 제외하면 약수의 개수는 짝수개다
		// 짝수개면 다시 청색이다
		// 홀수개면 뒤집혀서 백색이다
		// 제곱수의 개수를 구하는 것
		
		long N = sc.nextLong();
		int sum = 0;
		
		int i  = 1;
		while(Math.pow(i, 2) <= N) {
			sum++;
			i++;
		}
		
		System.out.println(sum);
	}
}

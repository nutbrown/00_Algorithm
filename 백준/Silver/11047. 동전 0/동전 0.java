import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 동전 N종류를 사용해서 합을 K로 만드려고 한다
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		// 동전 종류 입력받고 정렬
		Integer[] arr = new Integer[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr, Comparator.reverseOrder());
		
		// 동전 개수의 최솟값
		int min = 0;
		
		for(int i = 0; i  < N; i++) {
			
			// 동전 큰 것부터 최대한 채우기
			min += K / arr[i];
			K %= arr[i];
			
			// K가 0이 되면 끝
			if(K == 0) break;
		}
		
		System.out.println(min);
	}
}

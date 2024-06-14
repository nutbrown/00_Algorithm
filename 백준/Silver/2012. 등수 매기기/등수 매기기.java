import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N명의 성적 매기기
		int N = sc.nextInt();
		
		// 예상 등수
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 작은 순서대로 정렬해서 작은 순서대로 준다
		Arrays.sort(arr);
		
		// 500,000명이 최대로 틀리면
		// 1부터 500,000까지의 합인데
		// int 범위가 2,147,483,647이니까 long이네
		
		long sum = 0;
		for(int i = 0; i < N; i++) {
			sum += Math.abs(arr[i] - (i + 1));
		}

		// 1학생부터 N학생까지 전체 순열 -> 시간복잡도를 알아야지 -> 안 되네
		//permutation(1, 0);
		
		System.out.println(sum);
	}
}
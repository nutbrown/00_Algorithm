import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 배열1과 배열2
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] arr1 = new int[N];
		for(int i = 0; i < N; i++) {
			arr1[i] = sc.nextInt();
		}

		int[] arr2 = new int[M];
		for(int i = 0; i < M; i++) {
			arr2[i] = sc.nextInt();
		}

		// 합친 배열
		int[] arr = new int[N + M];
		
		// 인덱스
		int idx = 0;
		int idx1 = 0;
		int idx2 = 0;
		
		while(idx1 < N && idx2 < M) {
			// 추가한 숫자, 첫번째 배열, 두번째 배열 중에 작은 걸 추가
			if(arr1[idx1] <= arr2[idx2]) {
				arr[idx++] = arr1[idx1++];
			} else {
				arr[idx++] = arr2[idx2++];
			}
			//System.out.println(idx + " " + idx1 + " " + idx2 + " " + arr[idx - 1]);
		}
		
		
		// 첫번째나 두번째가 배열이 끝났다면 (하나 증가된 상태로 끝나니까 N또는 M)
		if(idx1 == N) {
			while(idx < N + M) {
				arr[idx++] = arr2[idx2++];
			}
		} else {
			while(idx < N + M) {
				arr[idx++] = arr1[idx1++];
			}
		}
		
		
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N + M; i++) {
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb);
	}
}
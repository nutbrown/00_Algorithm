import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 길이가 N인 수열 
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 왼쪽에서 증가하는 긴 수열 길이와
		int[] dpL = new int[N];
		// 왼쪽부터 i까지 중에서 증가하는 최대길이
		for(int i = 0; i < N; i++) {
			int maxL = 0;
			
			// i에서 왼쪽으로 가면서
			// 어디에서 와서 i를 포함해서 증가하는 최대인지 찾기
			for(int j = i - 1; j >= 0; j--) {
				if(arr[i] > arr[j] && dpL[j] > maxL) {
					maxL = dpL[j];
				}
			}
			dpL[i] = maxL + 1;
		}
		
		// 오른쪽에서 증가하는 긴 수열 길이를 구하고
		int[] dpR = new int[N];
		// 오른쪽부터 i까지 중에서 증가하는 최대길이
		for(int i = N - 1; i >= 0; i--) {
			int maxR = 0;
			
			// i에서 오른쪽으로 가면서
			// 어디에서 와서 i를 포함해서 증가하는 최대인지 찾기
			for(int j = i + 1; j < N; j++) {
				if(arr[i] > arr[j] && dpR[j] > maxR) {
					maxR = dpR[j];
				}
			}
			dpR[i] = maxR + 1;
		}
		
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(dpL));
//		System.out.println(Arrays.toString(dpR));
		
		// 0부터 N-1까지 모든 인덱스 찾아보기
		int max = 1;
		for(int i = 0; i < N; i++) {
			// 왼쪽에서 최대증가길이 dpL[i] + 
			// 오른쪽에서 arr[i] 숫자보다 작은 숫자 찾기
			// 그 숫자에서 최대증가길이 dpR[]를 더한 거 -> 최댓값
			
			for(int j = i + 1; j < N; j++) {
				if(arr[j] < arr[i]) {
					if(max < dpL[i] + dpR[j]) {
						max = dpL[i] + dpR[j];

					}
				}
			}
		}
		
		System.out.println(max);
	}
}
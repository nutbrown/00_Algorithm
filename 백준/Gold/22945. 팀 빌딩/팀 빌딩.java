import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 개발자 수 N
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		
		// 1 6 2 5 4 7
		// left랑 right이 있을 때
		// 최대한 멀게 하면 가운데 사람은 최대다

		// 가운데 사람수를 줄이면서 (-) 최댓값을 찾는데
		// 가운데 사람수를 줄일 때
		// 왼쪽 오른쪽 중 최솟값이 있는 곳을 줄인다
		// 최솟값이 커질 가능성이 있으니까 (+)
		// (둘 중 최댓값을 옮겨봤자 최솟값이 증가하지 않는다)
		
		// 그렇게 둘 중 최솟값을 줄이면서
		// 가운데 사람이 1이 될 때까지 해보자
		
		int max = 0;
		int left = 0;
		int right = N - 1;

		while(right - left > 1) {
			max = Math.max(max, Math.min(arr[left], arr[right]) * (right - left - 1));
			
			// 왼쪽이 최솟값면 왼쪽을 당긴다
			if(arr[left] < arr[right]) {
				left++;

			// 오른쪽이 최솟값이면 오른쪽을 당긴다
			} else {
				right--;
			}
		}
		
		System.out.println(max);
	}
}
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// N개의 정수 
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 정렬
		Arrays.sort(arr);
		
		// 최대의 합 
		long sum = 0;
		
		// 왼쪽 인덱스와 오른쪽 인덱스
		int left = 0;
		int right = N - 1;
		
		// 양수면 오른쪽에서 2개씩 묶어서 곱하기
		while(right - 1 >= 0 && arr[right - 1] > 0) {
			// 그런데 1이면 안 묶는게 좋다. 중단
			if(arr[right] == 1 || arr[right - 1] == 1) break;
			
			sum += arr[right] * arr[right - 1];
			right -= 2;
		}
		
		// right부터 숫자가 남아있으면 다 더해준다
		while(right >= 0 && arr[right] > 0) {
			sum += arr[right];
			right--;
		}
		
		// 음수면 왼쪽에서 2개씩 묶어서 곱하기
		while(left + 1 < N && arr[left + 1] < 0) {
			sum += arr[left] * arr[left + 1];
			left += 2;
		}
		
		// 오른쪽에 숫자가 안 남아있거나, 음수는 1개만 남아있거나
		// 오른쪽에 숫자가 있는데, 음수 1개랑 0이 있으면 0을 더해준다
		if(left + 1 < N && arr[left + 1] == 0) {
			sum += 0;
		}
		
		// 오른쪽에 숫자가 있는데, 음수 1개가 있으면 더해준다
		else if(left < N && arr[left] < 0){
			sum += arr[left];
		}
		
		System.out.println(sum);
	}
}

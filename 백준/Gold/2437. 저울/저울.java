import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 저울추의 개수
		int N = sc.nextInt();

		// 저울추의 무게 입력 후 정렬
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
			
		}
		Arrays.sort(arr);

		// 누적합
		int[] prefix = new int[N];
		prefix[0] = arr[0];
		for(int i = 1; i < N; i++) {
			prefix[i] = prefix[i - 1] + arr[i];
		}
		
		// 주어진 추들로 측정할 수 없는 양의 정수 무게 중 최솟값
		// 1 1 2 3 6  7  30  - 추의 무게 
		// 1 2 4 7 13 20 50  - 누적합
		
		// 누적합까지의 무게를 만들 수 있다, 누적합보다 큰 무게를 만들 수 없다
		
		// 누적합이 2면 2까지 만들 수 있고, 그 다음 누적합이 4이면 4까지 만들 수 있는데
		// 중간에 3을 만들 수 있는지 확인한다
		// 새로운 숫자가 2인데 새로운 추를 사용하고 남은 (3 - 2)인 1이
		// 현재 만들 수 있는 제일 큰 무게인 2보다 작아서 3을 만들 수 있다.
		
		// 누적합이 13면 13까지 만들 수 있고, 그 다음 누적합이 20이면 20까지 만들 수 있는데
		// 중간에 14를 만들 수 있는지 확인한다
		// 새로운 숫자가 7인데 새로운 추를 사용해서 (14 - 7)인 7이
		// 지금까지 가능한 수 13보다 작아서 앞에 존재한다. 14를 만들 수 있다.
		
		// 누적합이 20이면 20까지 만들 수 있고, 그 다음 누적합이 50이면 50까지 만들 수 있는데
		// 중간에 21을 만들 수 있는지 확인한다
		// 새로운 숫자가 30인데 새로운 추를 사용해서 (21 - 30)이 0보다 작아서 안 된다.
		
		
		// 만들 수 있는 무게
		int sum1 = 0;
		
		for(int i = 0; i < N; i++) {
			
			// i-1번째 누적합 이후부터 i번째 누적합 전까지 만들 수 있는지 
			int sum2 = prefix[i];
			
			// sum1 + 1부터 sum2 - 1까지 만들 수 있는지 확인
			for(int n = sum1 + 1; n <= sum2 - 1; n++) {
			
				// 확인하고자 하는 숫자에서 새로운 추인 i번째 무게추를 뺐을 때
				// 이전 누적합 이하면 만들 수 있다 -> 아니면 못 만들고
				if(n - arr[i] < 0 || n - arr[i] > sum1) {
					// 만들 수 없으면 그 숫자부터 측정할 수 없다
					System.out.println(n);
					return;
				}
			}
			
			// 다해서 통과했으면 sum1 갱신
			sum1 = sum2;
		}
		
		// 끝까지 왔다면 누적합 + 1을 측정할 수 없다 **빼먹어서 틀렸다
		System.out.println(sum1 + 1);
		
	}
}

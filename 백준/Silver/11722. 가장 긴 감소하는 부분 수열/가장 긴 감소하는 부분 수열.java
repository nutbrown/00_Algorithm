import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
        // 길이가 1일때 생각하면 1로 시작해야
		int max = 1;
		int[] length = new int[N];
		length[0] = 1;
		
		// 1000개가 다 1000번을 해도 될 거 같다
		for(int i = 1; i < N; i++) {
			// 되돌아가서 본인보다 큰 거 중에서 제일 긴거 가져오기
			int tempMax = 0;
			
			for(int j = 0; j < i; j++) {
				if(arr[j] > arr[i]) tempMax = Math.max(tempMax, length[j]);
			}
			
			// +1로 길이 갱신
			length[i] = tempMax + 1;
			
			// 전체 최댓값 갱신
			max = Math.max(max, length[i]);
		}
		
		System.out.println(max);
		
	}
}
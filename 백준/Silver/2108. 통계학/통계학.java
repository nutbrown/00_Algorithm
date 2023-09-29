import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		
		// 초기값을 위해서 첫번째 수 입력
		int N = sc.nextInt();
		int[] arr = new int[N];
		arr[0] = sc.nextInt();
		
		// 총합
		double sum = arr[0];
		
		// 최댓값 최솟값
		int max = arr[0];
		int min = arr[0];
		
		// 최빈값 기록용
		// 숫자는 -4000부터 +4000까지
		// 숫자 + 4000을 저장함
		int[] cnt = new int[8001];
		cnt[arr[0] + 4000]++;

		
		// 입력받고 정렬
		for(int i = 1; i < N; i++) {
			arr[i] = sc.nextInt();
			
			// 평균용 총합
			sum += arr[i];
			
			// 최댓값 최솟값 
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
			
			// 최빈값
			cnt[arr[i] + 4000]++;
		}
		Arrays.sort(arr);
		
		// 산술평균 : N개의 수들의 합을 N으로 나눈 값
		System.out.println(Math.round(sum / N)); 
		
		// 중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
		System.out.println(arr[(N + 1) / 2 - 1]);
		
		
		// 최빈값 : N개의 수들 중 가장 많이 나타나는 값
		// 1개가 아니라면 그 다음 작은값을 출력
		
		// 제일 많은 개수를 구하고
		int modeMax = 0;
		for(int i = 0; i <= 8000; i++) {
			modeMax = Math.max(modeMax, cnt[i]);
		}
		
		// 작은값부터 찾아서 올라가는데, 두번째로 찾으면 브레이크
		int modeNum = -4001;
		for(int i = 0; i <= 8000; i++) {
			if(cnt[i] == modeMax) {
				// 최빈값을 넣어주는데
				// 만약 처음이 아니어서 -1이 아니라면 아웃
				if(modeNum != -4001) {
					modeNum = i - 4000;
					break;
				} else {
					modeNum = i - 4000;
				}
			}
		}
		System.out.println(modeNum);
		

		// 범위 : N개의 수들 중 최댓값과 최솟값의 차이
		System.out.println(max - min);

		

		
	}
}
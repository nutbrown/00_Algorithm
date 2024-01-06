import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 숫자 N개 입력
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 숫자 리스트 정렬
		Arrays.sort(arr);
		
		// 뒤에서부터 세수의 합인지 확인
		// 답은 항상 존재한다
		
		// 뒤에서부터 2개 수의 합을 넣어주기
		// 그 합이 뭔지 알 필요 없으니 리스트로 만들어줌
		ArrayList<Integer> sum = new ArrayList<>();
		// 같은 수를 더해도 된다 !!!
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= i; j++) {
				sum.add(arr[i] + arr[j]);
			}
		}
		Collections.sort(sum);
		
		loop: 
		for(int i = N - 1; i > 0; i--) {
			// 세수의 합이 arr[i]가 되는지 거꾸로 확인해보자
			
			// 3개 중 제일 큰 수 j
			for(int j = i - 1; j >= 0; j--) {
				// 마지막은 이분탐색으로 찾는다
				int num = arr[i] - arr[j];

				// 이진 탐색 시작
				int start = 0;
				int end = sum.size() - 1;

				while(true) {
					int mid = (start + end) / 2;
					
					if(start == end) {
						if(num == sum.get(mid)) {
							// 탐색을 마치면 결과 출력
							System.out.println(arr[i]);
							break loop;
						} else {
							break;
						}
					}
					
					if(num == sum.get(mid)) {
						// 탐색을 마치면 결과 출력
						System.out.println(arr[i]);
						break loop;
					} else if(num > sum.get(mid)) {
						// (start + end)에 괄호를 안 해줬네.........
						start = mid + 1;
					} else {
						end = mid;
					}
					
					
				}
			}
			
		}
	}
}

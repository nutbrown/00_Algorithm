import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 눈덩이의 지름 N개
		int N = sc.nextInt();
		
		// 입력 후 정렬
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);

		// 눈사람의 키 차이 중 최솟값
		int min = Integer.MAX_VALUE;
		
		// 눈사람을 하나씩 만듬
		for(int i = 0; i < N - 1; i++) {
			for(int j = i + 1; j < N; j++) {
				// 첫 번째 눈사람 무게
				int w1 = arr[i] + arr[j];
				
				int left = 0;
				int right = N - 1;

//				System.out.println(i + " " + j);
//				System.out.println(left + " " + right);
				
				while(left < right) {
					// i랑 j가 아닌 걸로 눈사람을 만듬
					if(left == i || left == j) left++;
					else if(right == i || right == j) right--;
					
					else {
						// 두 번째 눈사람 무게
						int w2 = arr[left] + arr[right];
						
						// 최솟값 갱신
						if(Math.abs(w1 - w2) <= min) {
							min = Math.abs(w1 - w2);
						}
						
						// 첫번째 눈사람보다 무겁다면 조금 더 가벼워져야
						if(w2 > w1) right--;
						// 첫번째 눈사람보다 가볍다면 조금 더 무거워져야
						// 같더라도 움직여야지 만나서 while문을 탈출함
						else left++;

//						System.out.println(w1 + " " + w2);
//						System.out.println();
					}
					
				}
			}
		}
		
		System.out.println(min);
	}
}
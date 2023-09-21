import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 숫자 카드 N개
		int N = sc.nextInt();
		
		int[] arr1 = new int[N];
		for(int i = 0; i < N; i++) {
			arr1[i] = sc.nextInt();
		}
		Arrays.sort(arr1);
		
		
		// 갖고 있는지 판단 M개
		int M = sc.nextInt();
		int[] arr2 = new int[M];
		int[] arr3 = new int[M];
		for(int i = 0; i < M; i++) {
			arr2[i] = sc.nextInt();
			arr3[i] = arr2[i];
		}
		Arrays.sort(arr2);

		
		// -10 2 3 6 10
		// -10 -5 2 3 4 5 9 10
		
		// 인덱스를 옮겨가며 있는지 없는지 판단
		// 맵에 숫자랑 결과를 넣었다가 나중에 빼기
		HashMap<Integer, Integer> map = new HashMap<>();
		int idx1 = 0;
		int idx2 = 0;
		
		while(idx2 < M && idx1 < N) {
			// 숫자가 같으면 추가하고 둘다 이동
			if(arr1[idx1] == arr2[idx2]) {
				map.put(arr2[idx2], 1);
				idx1++;
				idx2++;
			
			// 배열1이 더 작다면 배열1을 다음으로 이동
			} else if(arr1[idx1] < arr2[idx2]) {
				idx1++;
			// 배열2가 더 작다면 배열2를 다음으로 이동
			} else if(arr1[idx1] > arr2[idx2]) {
				map.put(arr2[idx2], 0);
				idx2++;
			}
		}
		
		// 만약 idx2가 남았다면
		if(idx2 < M) {
			while(idx2 < M) {
				map.put(arr2[idx2], 0);
				idx2++;
			}
		}
		
		
		// 주어진 숫자 순서대로 맵에서 빼서 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			sb.append(map.get(arr3[i])).append(" ");
		}
		
		System.out.println(sb);
	}
}
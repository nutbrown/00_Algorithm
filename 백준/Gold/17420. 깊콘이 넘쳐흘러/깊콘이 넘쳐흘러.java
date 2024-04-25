import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 기프티콘 N개
		int N = sc.nextInt();
		
		// 기프티콘 남은 기한 a일, 기프티콘 사용하는 날 b일
		int[][] arr = new int[N][2];
		for(int j = 0; j < 2; j++) {
			for(int i = 0; i < N; i++) {
				arr[i][j] = sc.nextInt();
			}
		}

		Arrays.sort(arr, new Comparator<int[]>() {
			
			public int compare(int[] o1, int[] o2) {
				// 사용하는 날이 빠른순으로
				return o1[1] - o2[1];
			}
		});
		
		// 최소 횟수로 30일 기한연장을 하면서 다 쓰기
		long cnt = 0;
		
		// b일에 그 기프티콘을 쓸 건데, 그게 제일 기한이 적게 남아야한다
		for(int i = 0; i < N; i++) {
			
			// 기프티콘을 쓸 건데 기한이 오늘 이전이야
			while(arr[i][0] < arr[i][1]) {
				arr[i][0] += 30;
				cnt++;
			}
			
			// 이 기프티콘 기한이 가장 작아야한다
			for(int j = i + 1; j < N; j++) {
				// 기한이 같을 때는 상관없다
				if(arr[j][1] == arr[i][1]) continue;
				
				// 뒤에꺼가 더 작으면, 이상이 되도록 연장시키기
				while(arr[j][0] < arr[i][0]) {
					arr[j][0] += 30;
					cnt++;
				}
			}
			
			// 이제 그 기프티콘 사용하고 넘어가기
//			System.out.println(arr[i][1] + " " + cnt);
//			for(int j = 0; j < 2; j++) {
//				for(int ii = 0; ii < N; ii++) {
//					System.out.print(arr[ii][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		System.out.println(cnt);
	}
}

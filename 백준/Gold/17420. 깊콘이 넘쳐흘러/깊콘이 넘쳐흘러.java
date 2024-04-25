import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 기프티콘 N개
		int N = Integer.parseInt(br.readLine());
		
		// 기프티콘 남은 기한 a일, 기프티콘 사용하는 날 b일
		int[][] arr = new int[N][2];
		for(int j = 0; j < 2; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Arrays.sort(arr, new Comparator<int[]>() {
			
			public int compare(int[] o1, int[] o2) {
				if(o1[1] != o2[1]) {
					// 사용하는 날이 빠른순으로
					return o1[1] - o2[1];
				} else {
					// 사용하는 날이 같으면 기한이 빠른순으로
					return o1[0] - o2[0];
				}
			}
		});
		
		
		// 최소 횟수로 30일 기한연장을 하면서 다 쓰기
		long cnt = 0;
		
		// 마지막 기한 저장
		int limit = 0;
		
		// 같은 구간 기한 저장
		int templimit = 0;
		
		// b일에 그 기프티콘을 쓸 건데, 그게 제일 기한이 적게 남아야한다
		for(int i = 0; i < N; i++) {
			
			// 기프티콘을 쓸 건데 기한이 오늘 이전이야
			// while을 안 쓴다고 빨라지지 않던데...
			if(arr[i][0] < arr[i][1]) {
				int x = (arr[i][1] - arr[i][0]) / 30;
				if((arr[i][1] - arr[i][0]) % 30 != 0) x++;
				arr[i][0] += 30 * x;
				cnt += x;
			}
			
			// 마지막 기한보다 커야한다 
			if(arr[i][0] < limit) {
				int x = (limit - arr[i][0]) / 30;
				if((limit - arr[i][0]) % 30 != 0) x++;
				arr[i][0] += 30 * x;
				cnt += x;
			}
			
			// 이제 그 기프티콘 사용하고 넘어갈 건데
			if(i != N - 1 && arr[i + 1][1] == arr[i][1]) {
				// 뒤에 같은 날이 있으면 구간에만 저장
				templimit = Math.max(templimit, arr[i][0]);
			} else {
				// 뒤에 다른 날이 있으면 기한 갱신
				limit = Math.max(arr[i][0], templimit);
			}
			
			
			
//			System.out.println(arr[i][1] + " " + cnt + " " + limit);
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

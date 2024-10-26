import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);

		// N개의 도미노
		// a 초과, a+l 이하의 도미노 중 제일 왼쪽이 무너진다
		int N = sc.nextInt();
		
		int[][] arr = new int[N][2];
		for(int i = 0; i < N; i++) {
			
			// 위치랑 높이
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}
		
		// 도미노의 개수는 500,000개여서 한 번 돌 수 있는데
		// 전체 길이는 1,000,000,000 이어서 다 돌면 1억번을 넘는다
		Arrays.sort(arr, new Comparator<int[]>() {

			// 좌표 작은 순서대로 정렬
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		// 처음 1개는 무너뜨리면서 시작
		// 모든 도미노가 무너지기 위해, 처음 몇 개를 무너뜨려야 하는지
		int cnt = 1;
		
		// 어디까지 도미노가 닿아있는지
		int cur = arr[0][0] + arr[0][1];
		
		for(int i = 1; i < N; i++) {
			
			// 현재 인덱스 도미노 위치가
			// cur 이하면 자동으로 넘어가고
			if(arr[i][0] <= cur) {
				
			// 넘기지 못하면 하나 추가로 넘어뜨리기
			} else {
				cnt++;
			}

			// 이러나 저러나 넘어가야한다
			// 이때 얘가 아무리 길어도, 맨 왼쪽만 넘어간다
			cur = arr[i][0] + arr[i][1];
		}
		
		System.out.println(cnt);
		
	}
}

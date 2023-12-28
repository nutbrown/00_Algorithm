import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N 마리의 소 구조대원
		int N = sc.nextInt();
		
		// 구조대원의 근무 시간
		int[][] shift = new int[N][2];
		
		// 0부터 1000 사이에 일함
		int[] time = new int[1000];
		
		// 모두가 일한다면 총 시간
		int total = 0;
		
		for(int i = 0; i < N; i++) {
			// 근무 시간 저장하고
			shift[i][0] = sc.nextInt();
			shift[i][1] = sc.nextInt();
			
			// 전체 입력
			for(int j = shift[i][0]; j < shift[i][1]; j++) {
				// 증가시킨 게 겹쳐서 2이상이 아니라 1이면 total 증가시킴
				if(++time[j] < 2) total++;
			}
		}
		
		// 최댓값 찾기
		int max = 0;
		
		// 한 마리씩 빼봄
		for(int i = 0; i < N; i++) {
			int cur = total;
			
			for(int j = shift[i][0]; j < shift[i][1]; j++) {
				// 뺐더니 0이 되면 총 길이에서 빼줌
				if(time[j] - 1 == 0) cur--;
			}
			
			// 최댓값 갱신
			max = Math.max(max, cur);
		}
		
		System.out.println(max);
	}
}
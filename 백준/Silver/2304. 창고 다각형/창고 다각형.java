import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 1. 최대 높이를 찾고
		//		최대 높이의 왼쪽은 왼쪽부터 오른쪽은 오른쪽부터 읽으면서
		//		이제까지의 최댓값을 계속 답에 더하기
		// 2. 각 위치마다 min(왼쪽 최대, 오른쪽 최대) 계속 더하기 [난 이거]
		// 3. 각 높이별로 그 높이 이상의 기둥만 남겼을 때
		//		제일 오른쪽 - 제일 왼쪽
		
		// 기둥 높이 N개
		int N = sc.nextInt();
		
		// 왼쪽 오른쪽 둘 중 하나로 내려가면 되는데
		// 왼쪽 오른쪽 앞에 있는 높이 중에서 최댓값이 차지하는 높이다
		// 왼쪽 오른쪽 둘 중 그 최댓값이 작은 걸 고르면 됨
		
		// --> 왼쪽에서 오는 최댓값이랑
		// 오른쪽에서 오는 최댓값 중에서 최솟값을 구한다
		
		// 위치랑 높이 입력 : 전체입력
		int[] map = new int[1001];
		for(int i = 0; i < N; i++) {
			// 기둥 위치 L, 높이 H
			map[sc.nextInt()] = sc.nextInt();
		}
		
		// 왼쪽부터 최댓값, 오른쪽부터 최댓값
		int[] maxLeft = new int[1001];
		int[] maxRight = new int[1001];
		
		int max = 0;
		for(int i = 1; i <= 1000; i++) {
			max = Math.max(max, map[i]);
			maxLeft[i] = max;
		}
		max = 0;
		for(int i = 1000; i > 0; i--) {
			max = Math.max(max, map[i]);
			maxRight[i] = max;		}
		
		// 면적 구하기
		// 왼쪽 최댓값고 오른쪽 최댓값 중에 작은 것
		int sum = 0;
		for(int i = 1; i <= 1000; i++) {
			sum += Math.min(maxLeft[i], maxRight[i]);
		}
		
		System.out.println(sum);
	}	
}
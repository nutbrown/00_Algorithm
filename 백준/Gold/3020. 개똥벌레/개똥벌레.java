import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 동굴 길이 N미터, 높이 H미터
		int N = sc.nextInt();
		int H = sc.nextInt();
		
		// 높이가 H미터
		// 바닥을 1미터라고 하면 맨 위는 H미터다
		// 석순이 a미터이면 1부터 a까지고
		// 종유석이 b미터이면 (H - b + 1)부터 H까지다
		
		// 동굴 높이 H미터에서 파괴해야하는 장애물
		int[] arr = new int[H + 1];
		
		// 동굴길이 N은 항상 짝수
		for(int i = 0; i < N / 2; i++) {
			// 석순
			int a = sc.nextInt();
			
			// x 부터 y까지 1씩 더하려면
			// x에 1을 넣고 y + 1에 -1을 넣은 뒤
			// 나중에 누적합을 구하면 된다
			arr[1] += 1;
			arr[a + 1] += -1;
			
			// 종유석
			int b = sc.nextInt();
			arr[H - b + 1] += 1;
		}
		
		// 최솟값과 그러한 구간의 수
		int min = N;
		int cnt = 0;
		
		// 누적합으로 더해보자
		for(int i = 1; i <= H; i++) {
			arr[i] = arr[i - 1] + arr[i];
			
			// 최솟값이 갱신됐다면
			if(arr[i] < min) {
				min = arr[i];
				cnt = 1;
			// 최솟값 구간이 하나 더 있다면
			} else if(arr[i] == min) {
				cnt++;
			}
		}

		
		System.out.println(min + " " + cnt);
		
	}
}
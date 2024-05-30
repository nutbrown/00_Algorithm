import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.next());
		
		// 농부가 내는 패
		char[] arr = new char[N + 1];

		// H S P를 냈을 때 Bessie가 이기는 경우의 수 누적합
		int[][] prefixWin = new int[N + 2][3];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = sc.next().charAt(0);
			
			// 이기는 경우 저장
			if(arr[i] == 'H') prefixWin[i][2] = 1;
			else if(arr[i] == 'S') prefixWin[i][0] = 1;
			else if(arr[i] == 'P') prefixWin[i][1] = 1;
			
			// 3개 다 누적합
			for(int j = 0; j < 3; j++) {
				prefixWin[i][j] += prefixWin[i - 1][j];
			}
		}

		// 맨 마지막 누적합
		for(int j = 0; j < 3; j++) {
			prefixWin[N + 1][j] += prefixWin[N][j];
		}

		
		// Hoof > Scissors > Paper > Hoof
		// Bessie가 중간에 한 번만 바꿀 때
		// 최대한 이길 수 있는 경기 수
		int max = 0;
		
		// 3가지로 시작할 때
		for(int j = 0; j < 3; j++) {
			// 언제 바꾸는지에 따라 최댓값 구하기
			for(int i = 0; i <= N; i++) {
				// 0 ~ i -> j를 고르고
				// i+1 ~ N -> 다른 걸 고른다
				
				for(int k = 0; k < 3; k++) {
					if(j == k) continue;
					
					int sum = prefixWin[i][j] + 
							prefixWin[N + 1][k] - prefixWin[i][k];
					max = Math.max(max, sum);
				}
			}
		}
		
		
		System.out.println(max);
	}
}

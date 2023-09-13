import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 세로 길이 H, 가로 길이 W
		int N = sc.nextInt();
		int W = sc.nextInt();

		// 높이 저장
		int[] height = new int[W];
		for(int i = 0; i < W; i++) {
			height[i] = sc.nextInt();
		}
		
		// 왼쪽부터 최댓값
		int maxLeft = 0;
		int[] left = new int[W];
		for(int i = 0; i < W; i++) {
			if(height[i] > maxLeft) maxLeft = height[i];
			left[i] = maxLeft;
		}

		// 오른쪽부터 최댓값
		int maxRight = 0;
		int[] right = new int[W];
		for(int i = W - 1; i >= 0; i--) {
			if(height[i] > maxRight) maxRight = height[i];
			right[i] = maxRight;
		}
		
		int sum = 0;
		// 왼쪽 최댓값이랑 오른쪽 최댓값 중에서 더 작은 걸로
		for(int i = 0; i < W; i++) {
			sum += Math.min(left[i], right[i]) - height[i];
			//System.out.println("i " + i + " sum " + sum);
		}
		
		System.out.println(sum);
		
		
	}
}
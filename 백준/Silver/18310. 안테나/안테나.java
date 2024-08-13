import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 집 N채 
		int N = sc.nextInt();
		
		// 안테나로부터 모든 집까지의 거리의 총 합이 최소가 되게
		// 안테나가 좌우로 이동할 때
		// 좌측 우측에 있는 집만큼 거리가 플러스,마이너스 된다
		
		// 아 평균으로 못하는구나... 안테나를 집에 설치해서
		int[] arr = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		// 특정 집에 안테나를 두고,
		// 오른쪽으로 이동하면 왼쪽에 있는 집의 개수만큼 거리가 증가한다
		// 왼쪽으로 이동하면 오른쪽에 있는 집의 개수만큼 거리가 증가한다
		// -> 가운데에 있는 집에 설치해야한다
		if(N % 2 != 0) System.out.println(arr[N / 2 + 1]);
		else System.out.println(arr[N / 2]);
	}
}

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 범위 N x M 일 때
		// 지나가는 선의 개수가 K개인 선분의 수
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();

		// 두 점이 있을 때 (1, 3) (4, 9)
		// 차이 3, 6의 공약수는 1이랑 3이다
		// 3인 경우 -> (1, 2, 3, 4) (3, 5, 7, 9)
		// 1인 경우 -> (1, 4) (3, 6)
		
		// 두 점이 있을 때 (1, 3) (5, 11)
		// 차이 4, 8의 공약수는 1, 2, 4다
		// 4인 경우 -> (1, 2, 3, 4, 5) (1, 3, 5, 7, 9, 11)
		// 2인 경우 -> (1, 3, 5) (3, 7, 11)
		// 1인 경우 -> (1, 5) (3, 11)
		
		// 최대공약수로 지나가는 점의 개수를 구한다
		// 차이가 a, b일 경우 최대공약수가 GCD라면
		// GCD + 1개의 점을 지난다
		int cnt = 0;
		
		// 완전탐색 : 아래에 있는 점
		for(int x1 = 0; x1 <= N; x1++) {
			for(int y1 = 0; y1 <= M; y1++) {
				for(int x2 = 0; x2 <= N; x2++) {
					for(int y2 = 0; y2 <= M; y2++) {
						// 좌표가 일치하면 안 됨
						if(x1 == x2 && y1 == y2) continue;
						
						// 각 좌표의 차이 xDiff yDiff 중에서
						// 큰 걸 a, 작은 걸 b로 함
						int a = Math.abs(x2 - x1);
						int b = Math.abs(y2 - y1);
						
						if(b > a) {
							int temp = a;
							a = b;
							b = temp;
						}
						
						// 한 좌표가 같다면 나머지 좌표 차이가 K일 때 카운트
						if(b == 0) {
							if(a + 1 == K) cnt++;

							continue;
						} 

						// GCD(a, b) = GCD(b, a - b)
						// 뺄 수 있을 때까지 뺀다
						while(a % b != 0) {
							int temp = a % b;
							a = b;
							b = temp;
						}
						
						int GCD = b;
						if(GCD + 1 == K) cnt++;
					}
				}
			}
		}
		
		// 양 끝 점에서 개수를 세니까 나누기 2 
		System.out.println(cnt / 2);
	}
}
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 책 N권, 세준이가 한 번에 들 수 있는 책의 개수 M개
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		// 책이 위치
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);

		// 이게 무슨 문제인지 파악이 되지 않는다... 20
		// 멀리 있는 책부터 M개씩 갖다주면 되는 거 아닌가
		int sum = 0;
		
		// 음수 왼, 음수 오, 양수 왼, 양수 오 찾기
		int[] m = {-1, -1};
		int[] p = {-1, -1};
		
		// 편도니까 가장 먼거리는 저장해뒀다가 한 번 빼준다
		int max = 0;
				
		// 제일 작은 숫자가 음수면 음수 시작
		if(arr[0] < 0) {
			m[0] = 0;
			m[1] = 0;
			
			// 음수 끝 찾기
			for(int i = 1; i < N; i++) {
				if(arr[i] < 0) m[1] = i;
				else break;
			}

			// 음수 시작점부터 M개씩 끊어서 더해주기
			for(int i = m[0]; i <= m[1]; i += M) {
				sum += arr[i] * 2 * -1;
			}
			
			max = arr[0] * -1;
		}
		
		// 음수가 있든없든 다음부터 양수 찾기
		// 음수만 있는 게 아니라면
		if(m[1] + 1 < N) {
			p[0] = m[1] + 1;
			p[1] = N - 1;

			// 양수 끝점부터 M개씩 끊어서 더해주기
			for(int i = p[1]; i >= p[0]; i -= M) {
				sum += arr[i] * 2;
			}
			
			max = Math.max(max, arr[N - 1]);
		}
		
		
		System.out.println(sum - max);
	}
}

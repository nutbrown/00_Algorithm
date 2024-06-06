import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 배열의 크기 N
		int N = sc.nextInt();
		
		// 배열 ABCD
		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
			B[i] = sc.nextInt();
			C[i] = sc.nextInt();
			D[i] = sc.nextInt();
		}
		
		
		//  15  15  16
		// -16 -15 -15
		// 이 경우에는 15랑 -15 4쌍을 다 셀 수 없다
		// 처음 왼쪽 15랑 오른쪽 -15로 0이 됐을 때
		// 15 개수랑 -15 개수를 세어서 더해주고
		// 그 다음으로 아예 넘겨주자. 자료구조 여러 개 하기 싫다.
		
		// 시간초과이려나. 생각해보자. 배열 길이 16,000,000
		// 한 칸씩 앞으로 가거나 뒤로 가도
		// 원래 투포인터로 갈 거였으니까 그냥 투포인터랑 똑같이 2N이다!
		
		
		// 2개씩 전체 경우의 수 더하기
		long[] AB = new long[N * N];
		long[] CD = new long[N * N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				AB[i * N + j] = A[i] + B[j];
				CD[i * N + j] = C[i] + D[j];
			}
		}
		Arrays.sort(AB);
		Arrays.sort(CD);
		
		// 2개의 합이 0이 되는 쌍의 개수
		// 모든 숫자가 0이어서 모든 경우의 수라면 -> 256 000 000 000 000
		// int가 아니라 long이네 ***67% 틀린이유
		long cnt = 0;
		
		// AB는 작게, CD는 크게 시작해서 -> 투포인터 
		int st = 0;
		int ed = N * N - 1;
		
		while(st < N * N && ed >= 0) {
			long sum = AB[st] + CD[ed];

			// 둘이 더했을 때 0보다 크면 ed를 낮춘다
			if(sum > 0) ed--;
			
			// 둘이 더했을 때 0보다 작으면 st를 키운다
			else if(sum < 0) st++;
			
			// 0이면 카운트 증가
			else if(sum == 0) {
				long stNum = AB[st];
				long edNum = CD[ed];
				
				// 여기도 16,000,000 * 16,000,000 될 수 있다
				long stN = 0;
				long edN = 0;
				
				// **범위 주의 67% 인덱스 런타임 에러
				while(st < N * N && AB[st] == stNum) {
					stN++;
					st++;
				}
				while(ed >= 0 && CD[ed] == edNum) {
					edN++;
					ed--;
				}
				
				cnt += stN * edN;
			}
		}
			
			
		System.out.println(cnt);
	}
}
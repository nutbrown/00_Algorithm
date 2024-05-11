import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 정수 N
		long N = sc.nextLong();
		
		// 그냥 소수 구하듯이 하면 안 되나
		long cnt = 0;
		
		// 1로만 이루어진 숫자
		// 2 3 5 7 11 13 17로 미리 만들어두자
		int[] length = {2, 3, 5, 7, 11, 13, 17};
		long[] num = new long[7];
		
		for(int i = 0; i < 7; i++) {
			// a문자 반복하는 함수 
			num[i] = Long.parseLong("1".repeat(length[i]));
		}
		
		
		// 숫자 하나씩 해보기
		for(int i = 0; i < 7; i++) {
			
			// 숫자가 N보다 크면 아웃
			if(num[i] > N) break;
			
			// N을 111로 나눈 몫이 싫어하는 수잖아
			// 그런데 거기 11로 이미 싫어한 수가 들어있을 수도 있다
			// 111 * 11 이렇게 2개가 곱해져 있는 수들이 그렇다
			// 그러면 각각의 수들을 111로 나눈 다음, 거기서 11로 나눠지는 걸 빼줘야한다
			// 2 3 5 7 11 13 17 -> 너무 많은데
			
			// 2는 그냥 나눠서 더한다
			if(i == 0) {
				cnt += N / num[0];
			}
			
			// 3은 2만 제외한다
			else if(i == 1) {
				long minus = N / num[1] / num[0];
				cnt += N / num[1] - minus;
			}
			
			// 5는 2와 3의 합집합을 제외한다
			else if(i == 2) {
				long minus = N / num[2] / num[0]
						+ N / num[2] / num[1]
						- N / num[2] / (num[0] * num[1]);
				cnt += N / num[2] - minus;
			}
			
			// 7은 2 3 5의 합집합을 제외한다
			else if(i == 3) {
				long minus = N / num[3] / num[0]
						+ N / num[3] / num[1]
						+ N / num[3] / num[2]
						- N / num[3] / (num[0] * num[1])
						- N / num[3] / (num[0] * num[2])
						- N / num[3] / (num[1] * num[2])
						+ N / num[3] / (num[0] * num[1] * num[2]);
				cnt += N / num[3] - minus;
			}
			
			
			// 4중 벤다이어그램은 복잡하기도 하고 이후에는 몫이 확 작아져서
			// 그 다음부터는 포문으로 한다
			else {
				long minus = 0;
				boolean[] temp = new boolean[(int)(N / num[i] + 1)];
				
				// 앞에 있는 수로 나눠져서 빼야하는 것들
				for(int j = 0; j < i; j++) {
                    
                    // 여기 int long 문제
					
					// 여기부터 앞에 있는 수로 나눠 볼 몫인
					// N/num[i]의 자릿수가 굉장히 작아진다
					// 11 -> 18자리 - 11자리 = 8자리
					// 13 -> 18자리 - 13자리 = 6자리
					// 17 -> 18자리 - 17자리 = 2자리
					
					// 그러면 사실상 int범위 안에 있는 2 3 5 7 애들만 체크하게 된다
					// 그리고 포문에서 대소비교는 long으로 해도 되는구나 ***확인필요
					for(int k = 1; k * num[j] <= N / num[i]; k++) {
						if(!temp[(int)num[j] * k]) {
							temp[(int)num[j] * k] = true;
							minus++;
						}
					}
				}
				
				cnt += N / num[i] - minus;
			}
		}
			
			
		// 오 답은 나왔어 재밌는 문제
		System.out.println(cnt);
	}
}

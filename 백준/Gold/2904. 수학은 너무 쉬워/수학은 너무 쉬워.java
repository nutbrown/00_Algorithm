import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 종이에 적혀있는 수 N개
		int N = sc.nextInt();

		int[] arr = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// A와 B를 고르고
		// A를 나눌 수 있는 소수 X를 고르고
		// A를 지우고 A/X를 쓰고
		// B를 지우고 B*X를 쓴다
		// -> 무한히 하고, 모든 수의 최대공약수가 점수, 를 얻으려면 몇 번 해야하는지
		
		// A = a*b*c 고
		// B = d*e 면
		// 1번 하면 A의 소수 c가 B로 옮겨간다
		// 최대공약수는 모든 수에게 공통적으로 있는 소수들이니까
		// 모든 수를 소인수분해하고, 소수의 개수를 N개로 나눈다
		
		// 1부터 1_000_000까지 소수의 개수는 78498개
		int[] primeTemp = new int[1_000_001];
		
		// 수는 1,000,000보다 작으니까 거기까지 소수리스트를 만든다
		// 에라토스테네스의체 - 우선 풀고 나중에 확인
		for(int i = 2; i <= 1000; i++) {
			for(int j = i; i * j <= 1000000; j++) {
				
				// 처음 곱하는 숫자 i가 아직 소수인 경우만 해준다
				if(primeTemp[i] == -1) continue;
				
				// 소수가 아니면 -1로 바꿔준다
				primeTemp[i * j] = -1;
			}
		}

		// 소수 모으기
		ArrayList<Integer> primeList = new ArrayList<>();
		for(int i = 2; i <= 1000000; i++) {
			if(primeTemp[i] != -1) primeList.add(i);
		}
		
		// 0열에 소수가 몇 개 있는지
		// 1부터 N열 까지 소수가 몇 개 있는지 입력
		int[][] prime = new int[primeList.size()][N + 1];
		
		// 소인수분해 + 총 개수 세기
		for(int i = 1; i <= N; i++) {
			int num = arr[i];
			
			// 소수로 최대한 나누기
			for(int j = 0; j < primeList.size(); j++) {
				int p = primeList.get(j);
				
				// 나눠진다면 계속 나누기
				while(num % p == 0) {
					// 전체 개수 증가, i번째 증가
					prime[j][0]++;
					prime[j][i]++;
					num /= p;
				}
				
				// num이 1이 되면 끝
				if(num == 1) break;
			}
		}
		// 가장 큰 점수, 몇 번 만에
		int max = 1;
		int cnt = 0;
		
		// 총 개수를 N으로 나눈 몫이 최대공약수에 들어간다
		for(int i = 0; i < primeList.size(); i++) {
			
			// N개 이상이면 점수에 추가
			if(prime[i][0] / N > 0) {
				max *= Math.pow(primeList.get(i), (prime[i][0] / N));
				
				// prime[i][0]/N 개씩 있어야 하는데
				// 그거보다 적으면 차이만큼 이동시킨다
				int t = prime[i][0] / N;
				for(int j = 1; j <= N; j++) {
					if(prime[i][j] < t) {
						cnt += t - prime[i][j];
					}
				}
			}
		}
		
		System.out.println(max + " " + cnt);
		// 모든 수가 1,000,000보다 작으니까 int고
		// 연산은 최대 1_000_000 * 1000이니까 괜찮다
		
	}
}
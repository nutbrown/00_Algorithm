import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = Integer.parseInt(sc.next());
		char[] arr = sc.next().toCharArray();
		int mod = 1_000_000_007;
		
		// 앞에서부터 W 개수
		int[] prefixW = new int[N];
		
		if(arr[0] == 'W') prefixW[0] = 1;
		for(int i = 1; i < N; i++) {
			if(arr[i] == 'W') prefixW[i] = 1;
			prefixW[i] += prefixW[i - 1];
		}
		
		// 뒤에서부터 E 개수
		int[] suffixE = new int[N];
		if(arr[N - 1] == 'E') suffixE[N - 1] = 1;
		for(int i = N - 2; i >= 0; i--) {
			if(arr[i] == 'E') suffixE[i] = 1;
			suffixE[i] += suffixE[i + 1];
		}
		
		// 인덱스가 H인 걸 찾아서
		// 앞에 W개수 * 뒤에 E(2이상 조합) 개수
		// E 5개	-> 5C2 + 5C3 + 5C4 + 5C5
		// E n개	-> nC2 + nC3 + .... nCn
		// 		-> n개의 돌이 있을 때, 고를지말지 고를지말지 고를지말지 하다가
		// 		-> 다 안 고른 거 1가지, 1개 고른 거 n가지 빼주기
		
		// 이러면 Math.pow(2, e) - e - 1에서 int를 초과한다
		// E가 n개에서 n+1개가 된다면 ->
		// 새로 들어온 거 1개 고르고 + 1개씩 고르기 -> n개
		// 새로 들어온 거 1개 고르고 + 나머지 원래대로 고르기 -> e개
		// 새로 들어온 거 안 고르고 + 나머지 원래대로 고르기 -> e개
		// 200,000개 만들어두지뭐
		long[] combi = new long[200001];
		for(int i = 2; i <= 200000; i++) {
			combi[i] = (combi[i - 1] * 2 + (i - 1)) % mod;
		}
		
		long cnt = 0;
		for(int i = 1; i + 2 < N; i++) {
			if(arr[i] == 'H') {
				// 곱할 때는 mod하면 안 되지
				cnt += (prefixW[i] % mod) * combi[suffixE[i]];
				cnt %= mod;
			}
		}
		
		System.out.println(cnt);
	}
}
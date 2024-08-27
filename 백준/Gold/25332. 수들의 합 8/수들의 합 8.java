import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// A B의 개수 N <= 200,000
		// 1초 1억번 100,000,000 - O(N^2) 불가
		int N = sc.nextInt();

		// Ai, Bi <= 10,000
		// 합은 최대 2,000,000,000 - int 범위
		int[] sumA = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			sumA[i] = sumA[i - 1] + sc.nextInt();
		}
		int[] sumB = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			sumB[i] = sumB[i - 1] + sc.nextInt();
		}
		
		// A i~j합 B i~j합이 같은 걸 찾기
		// sumAj - sumA(i-1) == sumBj - sumB(i-1) 는 O(N^2)이다
		// sumAj - sumBj = sumA(i-1) - sumB(i-1) 로 바꿔서 변수 2개를 돌지 않게 한다
		// 이걸 어떻게 생각해내는지
		
		// j는 1~N이고 i-1는 0~N-1이다. j랑 i-1은 j가 더 큰 서로 다른 두 수다
		// (sumA0 - sumB0)부터 (sumA_N-1 - sumB_N-1)까지 넣어두고
		// (sumA1 - sumB1)부터 (sumA_N - sumB_N)까지 같은 게 있나 확인한다
		
		// sumAi - sumBi 수열
		// 위에서 Stringtokenizer st1, st2로 미리 받아놓으면 한 번에 할 수 있다
		// 누적합에서 sumA0이랑 sumB0은 0으로 빼주니까, 여기서도 C0은 0으로 넣어준다
		int[] C = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			C[i] = sumA[i] - sumB[i];
		}
		
		// sumAi - sumBi의 값이 몇개 나왔는지
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		// 경우의 수는 N(N + 1)/2니까
		// 20,000,000,000 - int범위 초과
		long cnt = 0;

		// 같은 쌍의 개수 앞에서부터 찾기
		for(int i = 0; i <= N; i++) {
			
			// C[i]랑 같은 값이 있으면 더해주기
			// i가 0일 때는 값만 넣어주고, i가 N일 때는 같은 개수 더하기만 한다
			cnt += hm.getOrDefault(C[i], 0);
			
			// 값이 나왔다고 넣어주기
			hm.put(C[i], hm.getOrDefault(C[i], 0) + 1);
		}
		
		
		System.out.println(cnt);
		
	}
}
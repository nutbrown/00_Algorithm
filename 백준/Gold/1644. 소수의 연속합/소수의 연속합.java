import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();	// 자연수 N
		
		// 자연수 N까지 소수를 구한다
		// 뒤에서부터 투포인터로 연속된 소수의 합으로 나타낼 수 있는데 세어본다
		
		// 소수인 건 false
		boolean[] prime = new boolean[N + 1];
		for(int i = 2; i <= Math.sqrt(N); i++) {
			// 소수 검증 안 된 것만
			if(!prime[i]) {
				for(int j = i; i * j <= N; j++) {
					prime[i * j] = true;
				}
			}
		}
		
		// 소수 저장 리스트 
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 2; i <= N; i++) {
			if(!prime[i]) list.add(i);
			
		}
		Collections.sort(list);
		//System.out.println(list);
		
		// 시작점과 끝점과 합과 경우의 수
		int s = 0;
		int e = 0;
		int cnt = 0; 

		// 자연수 1은 소수의 합으로 나타나는 가짓수 0개
		if(N == 1) {
			cnt = 0;
		} else {
			int sum = list.get(0);
			while(e < list.size()) {
				if(sum < N) {
					if(e == list.size() - 1) break;	// 끝점이면 탈출
					sum += list.get(++e);
				} else if (sum == N) {
					cnt++;
					if(e == list.size() - 1) break;
					sum += list.get(++e);
				} else if (sum > N) {
					sum -= list.get(s++);
				}
			}
		}
		
		System.out.println(cnt);
		
	}
}
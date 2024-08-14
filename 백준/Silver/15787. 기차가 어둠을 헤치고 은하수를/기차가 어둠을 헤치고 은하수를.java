import java.util.HashSet;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 기차 N개
		int N = sc.nextInt();
		
		// 기차 20개의 좌석에 앉은 사람들 기록
		int[] trains = new int[N + 1];
		
		// 명령 M개
		int M = sc.nextInt();
		for(int t = 0; t < M; t++) {
			
			int type = sc.nextInt();
			
			// i번째 기차 x번 좌석에 사람을 태운다
			if(type == 1) {
				int i = sc.nextInt();
				int x = sc.nextInt();
				
				// 1의 자리가 1번좌석, 20자리가 20번좌석
				// 연산자 우선순위 : 산술연산자 > 비트시프트 > 비트논리곱 등등
				// (a|b OR 연산. 둘 중 하나라도 1이면 1)
				// (a&b OR 연산. 둘 다 1이면 1)
				
				// x자리를 1로 만들어서 OR 연산. 무조건 1이 된다
				trains[i] |= 1 << x;
			}

			// i번째 기차 x번 좌석 사람을 하차시킨다
			else if(type == 2) {
				int i = sc.nextInt();
				int x = sc.nextInt();
				
				// x자리를 0으로 만들고 그 아래를 다 1로 만들어서
				// AND연산을 하면 x자리는 0이 되고, 그 아래는 다 그대로
				trains[i] &= ~(1 << x);
			}

			// i번째 기차 사람들이 모두 한 칸씩 뒤로 가고, 맨 뒤 사람 하차
			else if(type == 3) {
				int i = sc.nextInt();

				// 20번 사람 0으로 만들고
				trains[i] &= ~(1 << 20);
				
				// 한 칸씩 뒤로 보내기
				trains[i] <<= 1;
			}
			
			// i번째 기차 사람들이 모두 한 칸씩 앞으로 가고, 맨 앞 사람 하차
			else if(type == 4) {
				int i = sc.nextInt();
				
				// 1번 사람 0으로 만들고
				trains[i] &= ~(1 << 1);
				
				// 한 칸씩 앞로 보내기
				trains[i] >>= 1;
			}
		}

		// 은하수 건널 수 있는 기차 수
		int cnt = 0;
		
		// 이미 있는 조합 안 받기
		HashSet<Integer> hs = new HashSet<>();
		for(int i = 1; i <= N; i++) {
			if(!hs.contains(trains[i])) {
				hs.add(trains[i]);
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
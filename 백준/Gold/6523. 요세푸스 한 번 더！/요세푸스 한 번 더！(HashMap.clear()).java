import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		
		// 현재 선택된 사람의 번호가 x라면, 다음 사람의 번호는 ax^2+b mod N번이다
		// 두 번 걸리면 술 마심 + 어떤 사람이 세 번 걸렸으면 게임 끝
		// 처음 시작하는 사람 번호 0번
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 이거 Long, Integer로 하면 메모리 초과
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 2 ≤ N ≤ 109
				if(N == 0) break;						// 입력 마지막줄에는 0이 주어짐
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 아... 두 번째로 걸린 사람이 나왔으면 다시 원점이니까
			// 한 번 걸린 사람들이 그대로 두 번 걸린다.......
			// 굳이 세 번 걸린 사람까지 기다릴 필요 없다

			// 해시맵에 있다는 건 1번 걸린 사람이 또 걸렸다는 것
			// 처음 2번 걸린 사람이 나중에 3번 걸려서 집 갈 거니까
			// 술 마시는 사람은
			// 처음 2번 걸린 사람이 <1번 걸렸을 때부터 ~ 지금까지 걸린 사람>
			
			// n번 사람이 10번째로 처음에 걸린 다음에
			// 			22번째로 다시 걸렸다고 하면
			//			34번째에 세 번 걸려서 집 가겠지
			// 그러면 11번째 ~ 22번째 걸린 사람들이 술 마시는 사람들
			
			// 첫 사람이 술을 마시기 위한, 두 번 걸리기까지 단계 수는 10^6보다 작다
			// 그러면 int로 해도 10mb
			

			// 총 N명 : 0번부터 N-1번
			// i행의 사람이 0번 걸린 상태
			int p = 0;

			// 몇 번째 걸렸는지 카운트
			int cnt = 1;
			
			// 마지막 사람이 3번 걸렸으면 그만
			while(true) {
				
				// 다음 사람이 걸림
				if(p == 0) {
					p = b % N;
				} else {
					p = (int)((((((long)a % N) * (p % N) % N) * (p % N)) % N) + b) % N;
				}

				
				if(hm.containsKey(p)) {
					System.out.println(N - (cnt - hm.get(p)));
					// 해시맵 클리어하고 재사용
					hm.clear();
					break;
				// 걸린 사람은 해시셋에 넣고 순서 증가
				} else {
					hm.put(p, cnt++);
				}
			}
			
		}
	}
}

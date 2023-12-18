import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
	static int N;
	static int a;
	static int b;
	
	public static void main(String[] args) throws IOException {
		
		// 현재 선택된 사람의 번호가 x라면, 다음 사람의 번호는 ax^2+b mod N번이다
		// 두 번 걸리면 술 마심 + 어떤 사람이 세 번 걸렸으면 게임 끝
		// 처음 시작하는 사람 번호 0번
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			N = sc.nextInt();		// 2 ≤ N ≤ 109
				if(N == 0) break;	// 입력 마지막줄에는 0이 주어짐
			a = sc.nextInt();
			b = sc.nextInt();
			
			// 아... 두 번째로 걸린 사람이 나왔으면 다시 원점이니까
			// 한 번 걸린 사람들이 그대로 두 번 걸린다.......
			// 굳이 세 번 걸린 사람까지 기다릴 필요 없다
			
			// 해시셋에 있다는 건 1번 걸린 사람이 또 걸렸다는 것
			// 처음 2번 걸린 사람이 나중에 3번 걸려서 집 갈 거니까
			// 술 마시는 사람은
			// 처음 2번 걸린 사람이 <1번 걸렸을 때부터 ~ 지금까지 걸린 사람>
			
			// n번 사람이 10번째로 처음에 걸린 다음에
			// 			22번째로 다시 걸렸다고 하면
			//			34번째에 세 번 걸려서 집 가겠지
			// 그러면 11번째 ~ 22번째 걸린 사람들이 술 마시는 사람들

			// long으로 바꾸니까 숫자가 제대로 나온다
			// 그 안에서 int long 문제로 뭐가 안 됐나봐
			HashSet<Integer> hs = new HashSet<>();

			// 총 N명 : 0번부터 N-1번
			// i행의 사람이 0번 걸린 상태
			int p = 0;

			// 몇 번째 걸렸는지 카운트
			int cnt = 1;
			
			// 몇 번째 사람이 걸렸는지 기록
			int out = 0;
			
			// 마지막 사람이 3번 걸렸으면 그만
			while(true) {
				// 다음 사람이 걸림
				p = getNext(p);
				
				if(hs.contains(p)) {
					out = p;
					break;
				// 걸린 사람은 해시셋에 넣고 순서 증가
				} else {
					hs.add(p);
					cnt++;
				}
			}
			
			// 걸린 사람 앞에 몇 명이 있는지 확인
			int cnt2 = 0;
			
			// p 초기화
			p = 0;

      // 메모리를 아끼지만 시간이 더 오래걸림
      // HashSet도 clear하면 메모리를 더 적게 씀
			while(true) {
				// 다음 사람이 걸림
				p = getNext(p);

				// 순서 증가
				cnt2++;
				
				// 사람 찾으면 아웃
				if(p == out) {
					System.out.println(N - (cnt - cnt2));
					break;
				}
			}
		}
	}
	
	static int getNext(int p) {
		if(p == 0) {
			return b % N;
		} else {
			// 계산 중간에 long이 될 수도 있는 것이기 때문에
			// 저장 변수 자체를 long으로 할 필요 없음(메모리 아끼기)
			// 처음 숫자 a를 long으로 하면 뒤에 모든 계산이 long으로 이뤄짐
			// 마지막에 int으로 변환함
			return (int)((((((long)a % N) * (p % N) % N) * (p % N)) % N) + b) % N;
		}
	}
}

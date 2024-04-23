import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 테스트케이스 T
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t < T; t++) {
			
			
			// 열려 있는 스택에 원소 삽입 - 바닥으로 떨어진다
			// 닫혀 있는 스택을 열면 - 쌓여있던 원소들이 바닥으로 떨어진다
			// 초기에는 U스택만 바닥이 열려있고, D스택 P스택 바닥은 닫혀있다
			
			// 순열 연산 2가지
			// 순열 가장 앞 원소를 세 스택 중 하나에 삽입 (순열이 안 비어있으면)
			// 바닥이 열려 있는 스택을 닫고, 바닥이 닫혀 있는 스택을 하나 골라 연다
			// 먼저 떨어진 순서대로 원소를 나열하는데 -> 오름차순이 가능한지
			
			// 순열의 길이 N
			int N = sc.nextInt();
			
			// 1이상 N이하의 서로 다른 정수로 이루어진 순열
			int[] arr = new int[N];
			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			
			// 앞 뒤로 볼 수 있게 UDP 덱
			Deque<Integer> du = new ArrayDeque<>();
			Deque<Integer> dd = new ArrayDeque<>();
			Deque<Integer> dp = new ArrayDeque<>();
			
			// peekFirst() peekLast() : 원소를 확인하는데, 비어있으면 null리턴
			// getFirst() getLast() : 원소를 확인하는데, 비어있으면 예외 발생
			// offerFirst() offerLast() : 원소를삽입하는데, 용량을 초과하면 false리턴
			// addFirst() addLast() : 원소를 삽입하는데, 용량을 초과하면 예외 발생
			
			// 그냥 똑같이 앞에서 보고 빼는 건 peek() poll()
			// 그냥 똑같이 뒤에서 넣는 건 add() offer()
			
			
			// 결과
			String ans = "YES";
			
			// 포닉스 마지막 숫자 ***0으로 시작해야 다음에 1이 온다
			int last = 0;
			
			// 순열에서 확인하고 있는 인덱스
			int idx = 0;
			
			// 두 번째 연산이 스택 1개를 닫고 1개를 연다
			// 그러면 스택은 무조건 1개만 열려있는 상태로 유지된다
			// 덱 3개중에서 하나는 계속 열어두자 -> U덱
			
			// 우선, 순열 끝까지 확인할 때까지
			while(idx < arr.length) {
				
				// last 바로 다음 숫자로 이어줄 수 있으면 이어주기
				// 열린 스택으로 통과시켜서 바로 이어주기
				if(arr[idx] == last + 1) {
					last = arr[idx++];
					
				} else if(!dd.isEmpty() && dd.peek() == last + 1) {
					// 스택을 다 열어서 내려준다
					// 스택은 이미 오름차순으로 들어있다
					last = dd.peekLast();
					dd.clear();
					
				} else if(!dp.isEmpty() && dp.peek() == last + 1) {
					last = dp.peekLast();
					dp.clear();
				}
				
				
				// 포닉스를 잇지 못했지만, 순열 숫자를 바로 이어서 넣어줄 수 있는지
				else if(!dd.isEmpty() && dd.peekLast() == arr[idx] - 1) {
					dd.add(arr[idx++]);
				} else if(!dp.isEmpty() && dp.peekLast() == arr[idx] - 1) {
					dp.add(arr[idx++]);
				}
				
				// 숫자가 이어지지 않는다면 우선 빈 스택에 넣는다
				else if(dd.isEmpty()) {
					dd.add(arr[idx++]);
				} else if(dp.isEmpty()) {
					dp.add(arr[idx++]);
				}
				
				
				// ***1부터 N까지 서로 다른 정수여서
				// 오름차순이면 1 2 3 4... N으로 이어져있어야 한다
				// 위의 경우 제외하면, 순열 숫자를 이어지게 이어붙일 수 없으면, 다 NO다 
				else {
					ans = "NO";
					break;
				}
				
				// ***이 경우가 존재치 않는다
//				// 넣으려는 숫자가 last보다 크다면
//				else if(arr[idx] > last) {
//					// 두 덱 안에서 이어지는 수면 넣어준다
//					if(!dd.isEmpty() && arr[idx] == dd.peekLast() + 1) {
//						dd.add(arr[idx++]);
//					} else if(!dp.isEmpty() && arr[idx] == dp.peekLast() + 1) {
//						dp.add(arr[idx++]);
//					}
//
//					// 두 덱 중 하나가 비어있으면 넣어준다
//					else if(dd.isEmpty()) {
//						dd.add(arr[idx++]);
//					} else if(dp.isEmpty()) {
//						dp.add(arr[idx++]);
//					}
//					
//					// 비어있는덱이 없어서 뭐라도 내려야한다면
//					else {
//						// 어딜 넣어도 이어지는 수가 아니면 2개 덱 맨 앞 숫자랑 비교해서
//						// 제일 작은 수를 내린다
//						int min = arr[idx];
//						if(!dd.isEmpty()) min = Math.min(min, dd.peek());
//						if(!dp.isEmpty()) min = Math.min(min, dp.peek());
//						
//						// 최솟값이 순열 숫자라면
//						if(min == arr[idx]) {
//							last = arr[idx++];
//						}
//						// 최솟값이 덱 앞 숫자라면 덱을 클리어
//						else if(!dd.isEmpty() && min == dd.peek()) {
//							last = dd.peekLast();
//							dd.clear();
//						} else if(!dp.isEmpty() && min == dp.peek()) {
//							last = dp.peekLast();
//							dp.clear();
//						}
//						
//						// 만약 여기서 순열 숫자가, 덱 처음숫자랑 덱 마지막숫자 사이라면
//						// 다음 턴에서 last보다 순열 숫자가 아웃이 돼서 NO된다
//					}
//				}
//				
//				// 마지막 숫자가 순열 숫자보다 크다면 -> 안 된다
//				else if(last > arr[idx]) {
//					ans = "NO";
//					break;
//				}
			}			
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
}

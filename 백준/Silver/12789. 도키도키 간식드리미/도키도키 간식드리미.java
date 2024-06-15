import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 앞에 서있는 학생 수
		int N = sc.nextInt();
		
		// 현재 들어가야하는 학생
		int cur = 1;
		
		// 옆에 있는 줄
		Stack<Integer> stk = new Stack<>();
		
		for(int i = 0; i < N; i++) {

			// 줄 서있는 친구 처리하기
			int in = sc.nextInt();
			
			// 줄에 있는 학생이 들어갈 수 있으면 들어가기
			if(in == cur) cur++;
			
			// 아니면 옆줄에 넣기
			else stk.add(in);

			
			// 옆 줄에 있는 학생이 들어갈 수 있을 때까지 들어가기
			// 스택이 비어있지 않은데, 앞에 있는 peek가 cur인 동안에
			// ***조건문 주의 왜그러니
			while(!stk.isEmpty() && stk.peek() == cur) {
				stk.pop();
				cur++;
			}
		}
		
		// 옆 줄에서 최대한 다 넣었는데, 아직 남아있다면
		if(!stk.isEmpty()) System.out.println("Sad");
		else System.out.println("Nice");
	}
}
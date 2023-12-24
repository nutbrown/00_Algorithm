import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// L : 커서를 왼쪽으로 한 칸 옮김
		// D : 커서를 오른쪽으로 한 칸 옮김
		// B : 커서 왼쪽에 있는 문자를 삭제함
		// P $ : $라는 문자를 커서 왼쪽에 추가함
		// 처음에는 커서가 문장 맨 뒤에 위치
		
		// LinkedList는 시간복잡도 O(N)
		// 스택은 모든 연산이 시간복잡도 O(1)
		// 가운데 커서를 두고 스택들 2개 둠
		Stack<Character> stk1 = new Stack<>();
		Stack<Character> stk2 = new Stack<>();
		
		// 문자열
		String str = br.readLine();
		for(int i = 0; i < str.length(); i++) {
			stk1.add(str.charAt(i));
		}
		
		// 명령 M개
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			if(cmd.equals("L")) {
				// 왼쪽꺼 빼서 오른쪽으로 옮김
				if(!stk1.empty()) stk2.add(stk1.pop());
			}
			else if(cmd.equals("D")) {
				// 오른쪽꺼 빼서 왼쪽으로 옮김
				if(!stk2.empty()) stk1.add(stk2.pop());
			}
			else if(cmd.equals("B")) {
				// 왼쪽 꺼 삭제함
				if(!stk1.empty()) stk1.pop();
			}
			else if(cmd.equals("P")) {
				Character ch = st.nextToken().charAt(0);
				stk1.add(ch);
			}
		}
		
		StringBuilder sb = new StringBuilder(); 
		while(!stk1.empty()) {
			stk2.add(stk1.pop());
		}
		while(!stk2.empty()) {
			sb.append(stk2.pop());
		}
		System.out.println(sb);
		
	}
}
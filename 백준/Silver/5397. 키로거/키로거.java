import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			
			String str = sc.next();
			
			// 백스페이스 -, 화살표는 < >, 나머지는 문자
			// 에디터랑 같은 방식으로 풀이
			// 가운데 커서를 두고 양쪽에 스택을 위치시킴
			Stack<Character> stk1 = new Stack<>();
			Stack<Character> stk2 = new Stack<>();
			
			for(int i = 0; i  < str.length(); i++) {
				char ch = str.charAt(i);
				if(ch == '<') {
					if(!stk1.isEmpty())
						stk2.add(stk1.pop());
				} else if(ch == '>') {
					if(!stk2.isEmpty())
						stk1.add(stk2.pop());
				} else if(ch == '-') {
					if(!stk1.isEmpty())
						stk1.pop();
				} else {
					stk1.add(ch);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			while(!stk1.isEmpty()) {
				stk2.add(stk1.pop());
			}
			while(!stk2.isEmpty()) {
				sb.append(stk2.pop());
			}
			
			System.out.println(sb);
		}
		
	}
}
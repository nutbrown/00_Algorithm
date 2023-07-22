import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			// 입력 받고
			String str = sc.nextLine();
			// 탈출 조건
			if(str.equals(".")) break;
			
			// 결과와 스택
			String result = "yes";
			Stack<Character> stk = new Stack<>(); 
			
			for(int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				
				// 왼쪽 괄호가 들어오면 넣고
				if(ch == '(' || ch == '[') stk.add(ch);
				
				// 오른쪽 괄호가 들어오면 빼서 일치하는지 확인
				else if(ch == ')') {
					if(stk.isEmpty() || stk.pop() != '(') {
						result = "no";
						break;
					}
				}
				else if(ch == ']') {
					if(stk.isEmpty() || stk.pop() != '[') {
						result = "no";
						break;
					}
				}
			}
			
			// 다 돌린다음 스택이 비어있어야
			if(!stk.isEmpty()) result = "no";
			
			System.out.println(result);
			
		}
		
	}
}
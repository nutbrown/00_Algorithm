import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		
		// () 괄호의 값은 2
		// [] 괄호의 값은 3
		// 포함하고 있으면 곱하고
		// 연결되어 있으면 더함
		// 입력이 올바르지 못하면 0을 출력

		// 중위표기식 후위표기식 하기 싫었는데
		// https://velog.io/@eromit/알고리즘-스택-활용-중위표기식과-후위표기식
		
		// 중위 -> 후위
		// 우선순위는 곱셈 > 덧셈 > 괄호가 2, 1, 0점
		// 피연산자는 결과에, 연산자는 스택에
		// 연산자를 넣을 때 우선순위가 높은 게 들어있으면 없을 때까지 빼줌
		// (우선순위가 높은 걸 먼저 계산해야하니까)
		// 오른쪽 괄호가 나오면 왼쪽 괄호가 나올 때까지 연산자 팝
		// 다 끝나면 모든 연산자 팝
		
		// 후위 -> 중위
		// 피연산자가 나오면 스택에 추가
		// 연산자가 나오면 숫자 2개를 팝해서 연산 수행하고
		// 결과를 스택에 추가

		// 입력
		// 행렬에 숫자, 여는괄호1 닫는괄호2로 입력 
		String str = sc.next();
		int[][] arr = new int[str.length()][2];
		for(int i = 0; i  < str.length(); i++) {
			char ch = str.charAt(i);
			if(ch == '(') {
				arr[i][0] = 2; arr[i][1] = 1;
			}
			if(ch == ')') {
				arr[i][0] = 2; arr[i][1] = 2;
			}
			if(ch == '[') {
				arr[i][0] = 3; arr[i][1] = 1;
			}
			if(ch == ']') {
				arr[i][0] = 3; arr[i][1] = 2;
			}
		}
		
		// 괄호 성립하는지 해결
		Stack<Character> stk1 = new Stack<>(); 
		for(int i = 0; i  < str.length(); i++) {
			char ch = str.charAt(i);
			if(ch == '(' || ch == '[') stk1.add(ch);
			else if(ch == ')') {
				if(stk1.isEmpty() || stk1.peek() != '(') {
					System.out.println(0);
					return;
				}
				else {
					stk1.pop();
				}
			}
			else if(ch == ']') {
				if(stk1.isEmpty() || stk1.peek() != '[') {
					System.out.println(0);
					return;
				}
				else {
					stk1.pop();
				}
			}
		}
		if(!stk1.isEmpty()) {
			System.out.println(0);
			return;
		}
		stk1.clear();
		
		// 1. 후위표기식 작성
		String postfix = "";
		
		// 첫번째 숫자 입력
		postfix += arr[0][0];
		
		for(int i = 1; i  < str.length(); i++) {
			
			// 열린괄호가 나왔는데
			if(arr[i][1] == 1) {
				// 이전도 열린 괄호면 괄호 열고 곱해줌
				if(arr[i - 1][1] == 1) {
					//infix += "*(" + arr[i][0];
					stk1.add('*');
					stk1.add('(');
					postfix += arr[i][0];
				}
				// 이전에 닫힌 괄호면 더하기하고 숫자
				else {
					//infix += "+" + arr[i][0];
					while(!stk1.isEmpty() && stk1.peek() == '*') {
						postfix += stk1.pop();
					}
					stk1.add('+');
					postfix += arr[i][0];
				}
			}
			
			// 닫힌 괄호가 나왔는데
			else {
				// 이전이 열린 괄호면
				if(arr[i - 1][1] == 1) {
					// 다른 숫자면 에러
					// 같은 숫자면 더하기인데 넘어감
				}
				// 이전이 닫힌 괄호면 괄호 닫기
				else {
					//infix += ")";
					while(stk1.peek() != '(') {
						postfix += stk1.pop();
					}
					stk1.pop();
				}
			}
		}
		
		while(!stk1.isEmpty()) {
			postfix += stk1.pop();
		}
		
		// 중위표기식 infix : 2*(2+3*(3))+2*(3)
		// 후위표기식 postfix : 2233*+*23*+
		//System.out.println(postfix);
		
		// 2. 후위표기식 연산
		Stack<Integer> stk2 = new Stack<>(); 
		for(int i = 0; i < postfix.length(); i++) {
			char ch = postfix.charAt(i);
			
			// 피연산자는 스택에 추가
			if(ch == '2' || ch == '3') stk2.add(Character.getNumericValue(ch));
			// 연산자가 나오면 2개 꺼내서 연산 후 다시 스택에 넣음
			else {
				int n2 = stk2.pop();
				int n1 = stk2.pop();
				
				if(ch == '*') stk2.add(n1 * n2);
				else if(ch == '+') stk2.add(n1 + n2);
			}
		}
		
		System.out.println(stk2.peek());
		
	}
}
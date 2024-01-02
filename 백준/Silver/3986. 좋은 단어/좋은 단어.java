import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 단어의 수 N
        int N = sc.nextInt();
        
        int ans = 0;
        for(int n = 0; n < N; n++) {
        	
        	// A끼리, B끼리 쌍을 지었을 때 선이 교차하지 않으면 좋은 단어
        	// 괄호랑 비슷하다. 순서대로 들어가서 나올 때 짝이 맞으면 됨
        	Stack<Character> stk = new Stack<>();
        	String str = sc.next();

        	for(int i = 0; i < str.length(); i++) {
        		char ch = str.charAt(i);
        		
        		// 같은 문자가 있으면 뺀다
        		if(!stk.isEmpty() && stk.peek() == ch) stk.pop();
        		
        		// 다른 문자라면 넣어줌
        		else stk.add(ch);
        	}
        	
        	// 끝났을 때 큐가 비어있으면 됨
        	if(stk.isEmpty()) ans++;
        	
        }
        System.out.println(ans);
    }
}
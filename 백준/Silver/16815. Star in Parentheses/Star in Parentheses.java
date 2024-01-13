import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		// 왼쪽 괄호가 열리면 +1
		// 오른쪽 괄호가 닫히면 -1
		// 균형잡힌 괄호이기 때문에 스택이 필요 없다 !
		int flag = 0;
		
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			if(ch == '(') {
				flag++;
			} else if(ch == ')') {
				flag--;
			} else { // 별이라면
				System.out.println(flag);
				return;
			}
				
		}
	}
}
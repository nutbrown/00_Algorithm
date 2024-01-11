import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		Stack<Character> word = new Stack<>();
		Stack<Character> tag = new Stack<>();
		
		// 태그중인지 아닌지
		boolean is_tag = false;
		
		// 출력
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			// 태그가 아직 안 끝났다면
			if(is_tag) {
				tag.add(ch);
				
				// 그런데 >가 나와서 태그가 끝났다면
				if(ch == '>') {
					is_tag = false;
					
					// 태그를 다 출력
					String tag_str = "";
					while(!tag.isEmpty()) {
						tag_str = tag.pop() + tag_str;
					}
					sb.append(tag_str);
				}
				continue;
			}
			
			// 태그가 나온다면 태그 스택에 삽입
			if(ch == '<') {
				tag.add(ch);
				is_tag = true;
				
				// 태그가 나오기 전에 문자가 있었다면 출력
				while(!word.isEmpty()) {
					sb.append(word.pop());
				}
			}
			
			// 태그가 아닌데 띄어쓰기가 나오면 출력
			else if(ch == ' ') {
				while(!word.isEmpty()) {
					sb.append(word.pop());
				}
				sb.append(' ');
			}
			
			// 그게 아니라 문자라면
			else {
				word.add(ch);
			}
		}
		
		// 남아있는 문자 출력
		while(!word.isEmpty()) {
			sb.append(word.pop());
		}
		
		System.out.println(sb);
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 탑의 수 N
		int N = Integer.parseInt(br.readLine());
		
		// 탑이 왼쪽으로 레이저를 송신할 때
		// 어떤 탑이 신호를 받는지
		
		// 높이랑 탑 번호를 동시에 스택에 넣음
		Stack<Integer> height = new Stack<>();
		Stack<Integer> num = new Stack<>();
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			// 탑번호 i, 탑높이 h
			int h = Integer.parseInt(st.nextToken());
			
			// 탑을 스택에 넣을 때
			// 본인보다 높은 탑이 나올 때까지 넣음
			while(true) {
				// 스택이 비어있으면 왼쪽에 레이저 받을 탑 없음
				if(height.isEmpty()) {
					sb.append("0 ");
					height.add(h);
					num.add(i);
					break;
				}
				// 본인보다 크거나 같은 탑 만나면
				else if(height.peek() >= h) {
					sb.append(num.peek()).append(" ");
					height.add(h);
					num.add(i);
					break;
				}
				// 왼쪽에 본인보다 작은 탑이 있으면
				else {
					height.pop();
					num.pop();
				}
			}
		}
		
		System.out.println(sb);
	}
}
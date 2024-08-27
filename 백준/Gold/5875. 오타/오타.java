import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 괄호쌍 입력
		String str = sc.next();
		
		// 스택으로 하던 걸 생각해보면
		// 좌괄호가 나오면 스택에 넣고, 우괄호가 나오면 빼는데
		// - 우괄호가 나왔을 때 좌괄호가 없으면 안 된다
		// - 다 끝났을 때 좌괄호가 스택에 남아있으면 안 된다 
		
		// '좌괄호 개수랑 우괄화 개수가 같으면 올바르다'
		// 경우의 수를 구해야하니까 스택에 넣고 빼는 게 아니라
		// 앞에 있는 좌괄호 우괄호 개수를 알아야 한다
		// 우괄호가 나왔는데 right가 left보다 크다면 ->
		// 앞에 나온 우괄호 하나를 좌괄호로 바꿔준다
		// 그리고 이후에는 다 맞아야 한다
		// 마지막에 좌괄호 개수가 2개 더 많다면 ->
		// 앞에 나온 좌괄호 하나를 우괄호로 바꿔준다
		// 그런데 그때도 앞에서 쭉 봤을 때 우괄호가 더 많아야 한다
		// 그래서 그냥 같은 방식으로 오른쪽부터 탐색해주기
		// (이때 맨 왼쪽은 좌괄호, 맨 오른쪽은 우괄호여야 한다)

		// 좌괄호 우괄호 개수
		int left = 0;
		int right = 0;
		
		// 답이 나왔으면 초기화
		int ans = -1;
		
		// 왼쪽부터 탐색
		for(int i = 0; i < str.length(); i++) {
			// 좌괄호 우괄호 개수 입력
			if(str.charAt(i) == '(') left++;
			else right++;
			
			// 우괄호가 더 많으면
			if(right > left) {
				// 이미 하나 바꿨으면 안 되니까 경우의 수 0개하고 끝
				if(ans != -1) {
					ans = 0;
					break;
				}
				
				// 아직 안 바꿨으면 바꾸기
				else {
					// 맨 왼쪽이 우괄호면 그거 바꾸기
					if(str.charAt(0) == ')') ans = 1;
					
					// 아니라면 지금 우괄호 포함 하나 바꿔준다
					// 우괄호의 개수가 경우의 수
					else ans = right;
					
					// 우괄호 증가 좌괄호 감소
					right--;
					left++;
				}
			}
		}
		
		// 우괄호를 하나 바꿔서 답이 나왔는데
		// 좌괄호 우괄호 개수가 다르면 안 된다
		if(ans != -1) {
			if(left != right) ans = 0;
		}
		
		// 답이 위에서 안 나왔는데
		// 좌괄호 - 우괄호가 2가 아니면 답이 0이다
		else if(left - right != 2) ans = 0;
		
		// 답이 위에서 안 나왔는데
		// 좌괄호가 우괄호보다 2개 더 많다면 - 오른쪽부터 탐색해본다 
		else if(left - right == 2) {
				
			// 다시 좌괄호 우괄호 개수
			left = 0;
			right = 0;
			
			for(int i = str.length() - 1; i >= 0; i--) {
				// 좌괄호 우괄호 개수 입력
				if(str.charAt(i) == '(') left++;
				else right++;
				
				// 좌괄호가 더 많으면
				if(left > right) {
					// 이미 하나 바꿨으면 안 되니까 경우의 수 0개하고 끝
					if(ans != -1) {
						ans = 0;
						break;
					}
					
					// 아직 안 바꿨으면 바꾸기
					else {
						// 맨 오른쪽이 좌괄호면 그거 바꾸기
						if(str.charAt(str.length() - 1) == '(') ans = 1;
						
						// 아니라면 지금 좌괄호 포함 하나 바꿔준다
						// 좌괄호의 개수가 경우의 수
						else ans = left;
						
						// 좌괄호 증가 우괄호 감소
						left--;
						right++;
					}
				}
			}
		}
			
		// 좌괄호를 하나 바꿔서 답이 나왔는데
		// 좌괄호 우괄호 개수가 다르면 안 된다
		if(ans != -1) {
			if(left != right) ans = 0;
		}
		
		// 답이 위에서 안 나왔다면
		// 답이 없고 경우의 수는 0이다
		else ans = 0;
		
		System.out.println(ans);
		
	}
}
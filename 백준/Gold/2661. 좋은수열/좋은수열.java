import java.util.Scanner;

public class Main {
	static int N;
	static String ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 123으로만 이뤄진 수열 중에서
		// 인접한 두 개의 부분 수열 중 동일한 것이 있으면 나쁜 수열
		// 좋은 수열 중에서 제일 작은 수를 출력
		N = sc.nextInt();

		dfs(0, "");
		System.out.println(ans);
		
	}
	
	
	static void dfs(int depth, String num) {

		// 답이 앞에서 나와서 ans가 null이 아니면 그만
		if(ans != null) return;
		
		// M개를 다 골랐으면 답으로 입력
		if(depth == N) {
			ans = num;
			return;
		}
		
		// 1부터 3까지 넣어준다
		for(int i = 1; i <= 3; i++) {

			// depth에 i를 고른게 tempNum
			String tempNum = num + i;

			// 뒤에 새로 넣어준 수를 기준으로
			// 1자리, 2자리... 3자리까지 확인해서 괜찮은지
			boolean flag = true;
			for(int l = 1; depth - l - l + 1 >= 0; l++) {
				
				// 뒤에서 앞으로 생각할 때
				// depth ~ depth - l + 1
				// (depth - l) ~ (depth - l) - l + 1 까지 일치하면 아웃
				
				// 길이 l의 범위는 
				// 1부터 depth-l-l+1이 0이상일 때까지
				
				// 일치하면 나쁜 수열이니까 탈출
				if(tempNum.substring(depth - l - l + 1, depth - l + 1)
						.equals(tempNum.substring(depth - l + 1, depth + 1))) {
					flag = false;
					break;
				}
				
			}
			
			// 괜찮으면 고르고 넘어가기
			if(flag) dfs(depth + 1, tempNum);
			
			// 안 괜찮으면 다음 숫자 하고,
			// 다했는데 안 괜찮아서 돌아오면 이전으로 돌아가기
			
		}
		
		
	}
}
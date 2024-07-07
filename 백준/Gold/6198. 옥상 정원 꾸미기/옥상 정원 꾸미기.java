import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 빌딩 개수 N
		int N = sc.nextInt();
		
		// 뒤에 있는 빌딩이 본인보다 작으면 확인할 수 있다
		// ****80,000개 1,000,000,000면 long이다
		long cnt = 0;
		
		// 3 10 5 7 4 12 2
		// 10은 오른쪽에 본인보다 작은 건물인 3 7 4를 볼 수 있다
		// 4는 왼쪽에 본인보다 큰 건물인 10 7이 볼 수 있다
		// 	왼쪽에 7이 볼 수 있다
		// 	그 왼쪽에 7보다 작은 건 7에 막혀서 못 보니까
		// 	7보다 큰 10만 볼 수 있다
		// 10 7 4 12
		//	왼쪽을 봤을 때 오름차순으로 점점 커지는 빌딩만 볼 수 있다
		
		Stack<Integer> stk = new Stack<>();
		for(int i = 0; i < N; i++) {
			int h = sc.nextInt();
			
			// 왼쪽 스택에 현재 건물보다 작은 건물이 있으면
			// 현재 h빌딩에 가려서 오른쪽 새로운 건물을 못 보니까 추가하지 않고 없앤다
			// 내림차순으로 스택에 들어있어서 작거나 같은 게 나오면 없애기
			while(!stk.isEmpty() && stk.peek() <= h) {
				stk.pop();
			}

			// 스택에 내림차순으로 있는 건물들은
			// 현재 h빌딩을 볼 수 있다
			cnt += stk.size();
			
			// 현재 높이 넣기
			stk.add(h);

		}
		
		
		System.out.println(cnt);
	}
}
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static int K;
	static ArrayList<Integer> list;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N을 1, 2, 3의 합으로 나타내는 방법 중 K번째로 오는 식
		N = sc.nextInt();
		K = sc.nextInt();
		
		// 고른 식
		list = new ArrayList<>();
		
		dfs(N);
		
		
		// 다해서 왔는데 k번째 식이 없는 경우 -1 출력
		if(K != 0) System.out.println(-1);

	}

	
	static void dfs(int sum) {

		// K번째를 찾았으면 그만한다
		if(K == 0) return;
		
		// sum이 0이면 성공
		if(sum == 0) {
			// K가 0이 될 때까지 감소시키기
			K--;
			
			// 이게 K번째 방법까지 해서 list에 저장했으면
			// 여기서 리스트 출력
			if(K == 0) {
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < list.size(); i++) {
					sb.append(list.get(i));
					
					if(i == list.size() - 1) break;
					else sb.append("+");
				}
				System.out.println(sb);
			}
			
			return;
		}
		
		
		// 1부터 넣어주기
		for(int i = 1; i <= 3; i++) {
			
			// 남아있는 수에 i를 넣을 수 있으면 넣고
			if(sum >= i) {
				list.add(i);
				dfs(sum - i);
				list.remove(list.size() - 1);
			}
			
			// 1+1+1+1을 이미하고 돌아와서 1+1+1인데
			// sum이 1인데 2를 넣어줄 수 없으니까
			// 다시 돌아가서 1+1이 되게 한다
			else {
				return;
			}
		}
		
		
	}
}

import java.util.Scanner;

public class Main {
	static int N;
	static int[] arr;
	static int[] cmd;
	static int max;
	static int min;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
			
		// 숫자 입력
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		// 연산자 입력
		cmd = new int[4];
		for(int i = 0; i < 4; i++) {
			cmd[i] = sc.nextInt();
		}
		
		// 연산자 N-1개 다 사용한 수 최댓값, 최솟값
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		// 연산자 몇 개 사용했는지, 계산한 현재 수가 뭔지
		dfs(0, arr[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	static void dfs(int depth, int num) {
		if(depth == N - 1) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}
		
		// 연산자 4개 앞에서부터
		for(int i = 0; i < 4; i++) {
			// 연산자 개수가 1개 이상이면
			if(cmd[i] > 0) {
				cmd[i]--;
				if(i == 0) dfs(depth + 1, num + arr[depth + 1]);
				else if(i == 1) dfs(depth + 1, num - arr[depth + 1]);
				else if(i == 2) dfs(depth + 1, num * arr[depth + 1]);
				else if(i == 3) {
					// 음수를 양수로 나눌 떄는
					if(num < 0) dfs(depth + 1, num * -1 / arr[depth + 1] * -1);
					// 음수를 양수로 바꾼 뒤, 몫을 취하고, 다시 음수로 바꿈
					else dfs(depth + 1, num / arr[depth + 1]);
				}
				cmd[i]++;
			}
		}
		
	}
}

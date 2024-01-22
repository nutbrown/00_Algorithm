import java.util.Arrays;
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
			// if(cmd[i] > 0)
			// 0 0 3 1 일 때, i를 0 1 2 탐색하다가 i가 2일 때 3이니까 들어간다 * 0 0 2 1
			// 0 0 2 1 일 때, i를 0 1 2 탐색하다가 i가 2일 때 2니까 들어간다 ** 0 0 1 1
			// 0 0 1 1 일 때, i를 0 1 2 탐색하다가 i가 2일 때 1이니까 들어간다 *** 0 0 0 1
			// 0 0 0 1 일 때, i를 0 1 2 3 탐색하다가 i가 3일 때 1이니까 들어간다 ***/ 0 0 0 0
			// depth를 다 썼으니까 return ***
			// 원상복귀 0 0 0 1인데, i가 3으로 함수 끝에 왔으니까 return **
			// 원상복귀 0 0 1 1인데, i가 2니까 다음, i가 3일 때 1이니까 들어간다 **/ 0 0 1 0
			// 0 0 1 0 일 때, i를 0 1 2 탐색하다가 i가 3일 때, 1이니까 들어간다 **/* 0 0 0 0
			// depth를 다 썼으니까 return **/
			// 원상복귀 0 0 1 0인데, i가 2일 때 원상복귀하고, i가 3일 때 0이어서 함수 끝이니까 return **
			// 원상복귀 0 0 1 1인데, i가 3으로 함수 끝에 왔으니까 return *
			// 원상복귀 0 0 2 1인데, i가 2일 때 탐색 끝난 상태니까, i가 3일 때 1이니가 들어간다 */ 0 0 2 0
			// ... 이렇게
			
			// while(cmd[i] != 0)으로하면 i가 2일 때 원상복귀로 돌아왔을 때
			// 다음 i가 3일 때는 하는 게 아니라 다시 i가 2일 때를 한다 -> 무한루프
			
			// 그런데 이것보다는 연산자를 쭉 넣어서 하는 게 낫겠다. ++**/ 이렇게 리스트로
			
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

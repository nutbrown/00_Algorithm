import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			
			// 수업의 개수 N (범위 10^5)
			int N = sc.nextInt();
			int[] arr = new int[N];
			
			// 누적합
			arr[0] = sc.nextInt();
			for(int i = 1; i < N; i++) {
				arr[i] = arr[i - 1] + sc.nextInt();
			}
			
			// 각 수업시간에 존 횟수를 합해서 같은 숫자로 만들려고 함
			// 1 2 3 1 1 1 의 누적합은
			// 1 3 6 7 8 9 로 나오는데
			// 건너건너 선택해서 같은 숫자로 건너뛰면 된다
			// - 3 6 - - 9 선택하지 않고 건너뛴 만큼 합한다
			
			// 2 2 3 의 누적합은
			// 2 4 7 로 나오는데
			// - - 7 일 수 밖에 없기 때문에 결과는 2다
			
			
			// min 최솟값을 찾는다
			int min = N;
			
			// 1항에서 시작, 2항에서 시작, 3항에서 시작... 을 따로 해봄
			for(int i = 0; i < N; i++) {
				// BFS로 풀어보자
				// 큐에는 <현재 인덱스 + 앞에서 몇 번을 더했는지 + 간격이 얼마인지> 들어간다
				Queue<int[]> q = new LinkedList<>();

				// 1번째 항의 경우에는
				// <앞에 1개가 있어서 1 + 0부터 arr[i]까지의 값>이 들어감
				
				// 이미 앞에서 나온 min이 뒤에서 시작하는 것보다 작으면 할 필요 없음
				// i가 min보다는 작아야지 넣을 필요 있음
				if(i < min) {
					q.add(new int[] {i, i, arr[i]});
				}

				while(!q.isEmpty()) {
					int idx = q.peek()[0];
					int cnt = q.peek()[1];
					int interval = q.poll()[2];
					
					// 꺼냈더니 최소값보다 크다면 할 필요 없음
					if(cnt >= min) continue;
					
					// 꺼냈더니 idx가 맨 마지막이라면 최소랑 비교
					if(idx == N - 1) {
						if(cnt < min) min = cnt;
						// 하고 다음으로 넘어감. 더 할 필요 없음
						break;
					}
					
					// 현재 값에서 interval 만큼 더한 값이 있다면
					for(int j = idx + 1; j < N; j++) {
						
						// 탐색하는 인덱스의 값이 더한 값보다 커지면 안 됨
						if(arr[j] > arr[idx] + interval) {
							break;
						}
						if(arr[j] == arr[idx] + interval) {
							// 뒷 간격을 찾았다면 큐에 넣음
							q.add(new int[] {j, cnt + (j - idx - 1), interval});
							// 찾았으면 뒤에는 없으니까 탐색할 필요 없음
							break;
						}
					}
					
				}
			}
			
			
			System.out.println(min);
		}
	}
}

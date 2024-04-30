import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 체커의 x좌표와 y좌표가 주어진다
		int N = sc.nextInt();
		
		// k개의 체커가 같은 칸에 모이도록, 체커를 이동해야 하는 최소 횟수
		// k개의 조합으로 k개의 체커를 뽑고, 그 중앙값을 구하는 건 안 된다
		// 중앙값들의 후보를 정하고,
		// 중앙값마다 가장 가까운 k개의 체커들까지의 거리의 합을 구하고,
		// 가장 작은 걸 답으로 출력한다
		
		// . . . o . o .		, . , o . o ,
		// . . o . . . .		, . o , . , ,
		// . . . . . . .		. . . . . . .
		// o . . . . . o		o . , , . , o
		// . . . o . . .		, . , o . , ,
		
		// 이렇게 있을 때 2개의 체커를 모은다고 하면
		// 2개의 체커가 최소 횟수로 만날 수 있는 방법이 여러가지가 있지만
		// 2개의 체커의 같은 행과열 선상에서 최소 횟수로 무조건 만날 수 있다
		// 체커들과 같은 선상에 있는 점들이 중앙값들의 후보다
		
		
		// 체커 N개의 x좌표와 y좌표
		int[][] arr = new int[N][2];
		
		// 중앙값 후보 찾기
		HashSet<Integer> xSet = new HashSet<>();
		HashSet<Integer> ySet = new HashSet<>();
		
		// 입력
		for(int i = 0; i < N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
			
			xSet.add(arr[i][0]);
			ySet.add(arr[i][1]);
		}
		
		// Set to List !!
		ArrayList<Integer> xList = new ArrayList<>(xSet);
		ArrayList<Integer> yList = new ArrayList<>(ySet);
		
		// k개의 체커가 같은 칸에 모이는 최소 횟수
		int[] min = new int[N + 1];
		Arrays.fill(min, Integer.MAX_VALUE);
		
		// 그동안 나온 x과 y가 만나는 지점인 중앙값 후보
		for(int i = 0; i < xList.size(); i++) {
			for(int j = 0; j < yList.size(); j++) {
				int x = xList.get(i);
				int y = yList.get(j);
				
				// 이 중앙값으로부터 N개의 체커까지의 거리
				int[] dist = new int[N];
				for(int k = 0; k < N; k++) {
					dist[k] = Math.abs(arr[k][0] - x) + Math.abs(arr[k][1] - y);
				}
				
				// 거리 작은순으로 정렬
				Arrays.sort(dist);
				
				// 총 이동횟수니까 누적으로 더해준다
				for(int k = 1; k < N; k++) {
					dist[k] += dist[k - 1];
				}

				// 1개부터 N개가 모이는 최소 횟수 갱신
				for(int k = 0; k < N; k++) {
					min[k] = Math.min(min[k], dist[k]);
				}
			}
		}
		
		// 최소 횟수 출력
		for(int k = 0; k < N; k++) {
			System.out.print(min[k] + " ");
		}
		
	}
}

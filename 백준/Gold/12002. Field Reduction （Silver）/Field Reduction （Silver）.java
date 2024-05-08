import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] minmax;
	static int[][] cows;
	static ArrayList<int[]> list;
	static int min;
	static int[] chosen;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 소 N마리 (5부터 10,000)
		N = sc.nextInt();
		
		// 소 위치 (범위는 1부터 40,000)
		cows = new int[N][3];
		for(int i = 0; i < N; i++) {
			cows[i][0] = sc.nextInt();
			cows[i][1] = sc.nextInt();
			
			// 소 인덱스 저장
			cows[i][2] = i;
		}
		
		// 소의 제일 작은행, 큰행, 작은열, 큰열: 큰순서 작은순서대로 4개씩 저장
		minmax = new int[4][4];
		
		// 이들 3개 * 4가지 점 중에서 3개를 고르면 된다
		list = new ArrayList<>();
		
		// 기존 로직 활용하기 위해서 인덱스를 따로 저장한다
		HashSet<Integer> set = new HashSet<>();
		
		// 행 정렬
		Arrays.sort(cows, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		for(int i = 0; i < 4; i++) {
			minmax[0][i] = cows[i][0];
			minmax[1][i] = cows[N  - 1 - i][0];

			if(i < 3) {
				if(!set.contains(cows[i][2])) {
					set.add(cows[i][2]);
					list.add(new int[] {cows[i][0], cows[i][1]});
				}
				if(!set.contains(cows[N - 1 - i][2])) {
					set.add(cows[N - 1 - i][2]);
					list.add(new int[] {cows[N - 1 - i][0], cows[N - 1 - i][1]});
				}
			}
		}
		
		// 열 정렬
		Arrays.sort(cows, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		for(int i = 0; i < 4; i++) {
			minmax[2][i] = cows[i][1];
			minmax[3][i] = cows[N  - 1 - i][1];
			
			if(i < 3) {
				if(!set.contains(cows[i][2])) {
					set.add(cows[i][2]);
					list.add(new int[] {cows[i][0], cows[i][1]});
				}
				if(!set.contains(cows[N - 1 - i][2])) {
					set.add(cows[N - 1 - i][2]);
					list.add(new int[] {cows[N - 1 - i][0], cows[N - 1 - i][1]});
				}
			}
		}
		
		
		// 울타리를 제일 작게 만들기
		// 소가 가장자리에 있을 수 있다
		// 소가 같은 선상에 있으면 면적은 0일 수도 있다
		// 소를 3마리까지 팔 수 있다

		// 울타리 면적의 최솟값 찾기
		min = (minmax[1][0] - minmax[0][0]) * (minmax[3][0] - minmax[2][0]);
		
		// N개 중에서 제거할 소 3마리 고르기
		chosen = new int[3];
		combination(0, 0);
		
		System.out.println(min);
 	}

	
	static void combination(int depth, int cnt) {
		
		// cnt가 3이어서 세 마리를 골랐으면
		// 그 소가 최대최소 행과 열을 줄여줄지 확인
		if(cnt == 3) {

			// 최대최소 행과 열
			int[] mm = new int[4];
			
			// 이미 사용된 소인지 행과열 따로 확인
			boolean[][] used = new boolean[3][2];
			
			// 작은행 큰행 작은열 큰열 순서대로
			for(int k = 0; k < 4; k++) {

				
				// 그래서 최대최소행과열 idx가 뭔지
				for(int i = 0; i < 4; i++) {
					
					// 일치하는 걸 찾았는지
					boolean flag = false;
					
					// 최대최소행과열 과 일치하는 소가 있는지 chosen 3개 중에서 찾기 
					for(int j = 0; j < 3; j++) {
						if(minmax[k][i] == list.get(chosen[j])[k / 2] && !used[j][k / 2]) {
							used[j][k / 2] = true;
							flag = true;
							break;
						}
					}
					
					// 일치하는 걸 못 찾았으면 그 인덱스가 최대최소다
					// 012가 다 일치하는 걸 찾았으면 3에 못찾아서 3이 된다
					if(!flag) {
						mm[k] = minmax[k][i];
						break;
					}
				}
			}
			
			// 이때의 면적을 최솟값 갱신
			min = Math.min(min, (mm[1] - mm[0]) * (mm[3] - mm[2]));
			
			// 리턴 좀 해라....
			return;
		}
		
		// depth가 리스트 크기면 돌아가기
		if(depth == list.size()) return;
		
		
		// depth 소 고르기
		chosen[cnt] = depth;
		combination(depth + 1, cnt + 1);
		
		// depth 소 안 고르기
		combination(depth + 1, cnt);
		
	}
}

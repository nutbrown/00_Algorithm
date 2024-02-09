import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int n;
	static int m;
	static int k;
	static ArrayList<Integer>[] skills;
	static int[] quests;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 키의 개수 n, 퀘스트의 개수 m, 퀘스트 당 사용해야 하는 스킬 수 k
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		
		// 스킬은 1 ~ 2N까지 있고
		// 각 스킬에 해당하는 퀘스트를 1 ~ M에 저장하고
		skills = new ArrayList[2 * n + 1];
		for(int i = 1; i <= 2 * n; i++) {
			skills[i] = new ArrayList<>();
		}

		// 퀘스트마다 지금까지 깬 스킬 개수를 저장한다
		quests = new int[m + 1];
		
		// 각각의 퀘스트에서 사용해야 하는 스킬
		for(int i = 1; i <= m; i++) {
			for(int j = 0; j < k; j++) {
				
				// i번째 퀘스트가 사용하는 스킬
				int skill = sc.nextInt();
				skills[skill].add(i);
			}
		}
		
		// 최적의 키배치를 했을 때 최대로 깰 수 있는 퀘스트 수
		max = 0;
		
		// 스킬 2n개 중에서 n개 고르는 조합을 다 해본다
		combination(0, 1, 0);

		System.out.println(max);
	}
	
	static void combination(int depth, int idx, int cnt) {
		
		// N개를 다 골랐다면 최댓값 갱신
		if(depth == n) {
			max = Math.max(max, cnt);
			return;
		}
		
		// idx가 2N을 넘어가면 안 된다
		if(idx > 2 * n) return;
		
		// 지금까지 스킬을 depth개를 골랐는데, 나머지 n-depth로 다 퀘스트를 깨도
		// max보다 크지 않다면 할 필요 없다
		// 이건 하면 안 된다 -> 1개의 스킬로 모든 퀘스트를 깰 수 있어서  
		// 1개의 스킬이 모든 퀘스트에 포함되어 있어서
		// 마지막 스킬로 모든 퀘스트를 다 깰 수도 있다
		//if(cnt + (n - depth) <= max) return;
		
		
		// idx 스킬에 해당하는 퀘스트 스킬-1 해주기
		for(int q : skills[idx]) {
			quests[q]++;
			
			if(quests[q] == k) cnt++;
		}
		combination(depth + 1, idx + 1, cnt);
		
		// 돌아왔으면 원상복귀
		for(int q : skills[idx]) {
			if(quests[q] == k) cnt--;

			quests[q]--;
		}
		
		// idx 스킬을 안 고르고
		combination(depth, idx + 1, cnt);
		
	}
}

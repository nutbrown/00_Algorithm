import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int[] minNut;
	static int[][] nut;
	static int minCost;
	static ArrayList<Integer> minChosen;
	static ArrayList<Integer> chosen;
	static int[] currNut;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 식재료 개수 N
		N = sc.nextInt();
		
		// 단백질 지방 탄수화물 비타민 -> 최소 영양 성분
		minNut = new int[4];
		for(int i = 0; i < 4; i++) {
			minNut[i] = sc.nextInt();
		}
		
		// 각 식재료의 영양성분랑 가격
		nut = new int[N + 1][5];
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < 5; j++) {
				nut[i][j] = sc.nextInt();
			}
		}
	
		// 최소 비용
		minCost = Integer.MAX_VALUE;
		minChosen = new ArrayList<>();
		
		
		// 사전순이어서
		// 1, 1 2, 1 2 3, 1 3
		// 2, 2 3
		// 3 -> 이런순으로 고르고 싶다
		for(int idx = 1; idx <= N; idx++) {
			// 최소 비용 식재료
			chosen = new ArrayList<>();
			
			// 먼저 고르고
			chosen.add(idx);
			
			// 현재 영양정보랑 가격
			currNut = new int[5];
			for(int i = 0; i < 5; i++) {
				currNut[i] = nut[idx][i];
			}
			
			// 다음은 idx+1를 고르거나 안 고르면 되고
			combination(idx + 1);
		}
		
		// 출력
		if(minCost == Integer.MAX_VALUE) {
			// 답이 없으면 -1 출력
			System.out.println(-1);
		} else {
			System.out.println(minCost);
			for(int i = 0; i < minChosen.size(); i++) {
				System.out.print(minChosen.get(i) + " ");
			}
		}
	}
	
	
	static void combination(int idx) {
		
		// cost가 지금 minCost보다 크면 더 안 한다
		if(currNut[4] >= minCost) return;
				
		// cost가 작은데 조건을 만족했다면
		if(currNut[0] >= minNut[0] && currNut[1] >= minNut[1]
				&& currNut[2] >= minNut[2] && currNut[3] >= minNut[3]) {
			
			// 최소 가격과 고른 거 입력
			minCost = currNut[4];
			minChosen = new ArrayList<>();
			for(int i = 0; i < chosen.size(); i++) {
				minChosen.add(chosen.get(i));
			}
			
			// 그 이후로 사전순으로 커지는데, 가격이 증가하니까 할 필요 없다
			return;
		}
		
		
		// 다음 인덱스를 골라보자.
		// idx가 N을 넘으면 안 된다
		if(idx > N) return;
		
		// idx를 고른 거
		chosen.add(idx);
		for(int i = 0; i < 5; i++) {
			currNut[i] += nut[idx][i];
		}
		combination(idx + 1);
		
		// 나오면 마지막에 넣은 거 빼서 원상복귀하고
		chosen.remove(chosen.size() - 1);
		for(int i = 0; i < 5; i++) {
			currNut[i] -= nut[idx][i];
		}
		
		// idx를 안 고른 거
		combination(idx + 1);
	}
		
}

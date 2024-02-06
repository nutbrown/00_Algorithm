import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	static int N;
	static int[] arr;
	static ArrayList<Integer> diffList;
	static HashMap<Integer, Integer> diffMap;
	static int cnt;
	static int[] chosen;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 숫자가 4개에서 7개가 주어지는데
		// A, B, C, A+B, B+C, C+A, A+B+C 중에 하나다
		// 이때 가능한 (A, B, C)의 경우의 수
		
		// 0, A, B, C, A+B, B+C, C+A, A+B+C 로 만들 수 있는 차는
		// A, B, C, A+B, B+C, C+A, A+B+C
		// A+B-C, B+C-A, A+C-B, C-A, C-B, B-A
		// 28가지에서 중복을 제외해서 13가지다
		
		// 숫자가 4개면 5*4/2 10개
		// 숫자가 7개면 8*7/2 28개가 나온다
		// 왜인지 모르겠는데*** 이 안에 항상 A B C가 들어있다

		// 차이를 구해서 조합으로 A, B, C가 있다고 가정하고
		// 숫자 7개를 만든다음에, 주어진 수열이 여기에 포함되면 된다
		
		
		// 테스트 케이스
		int T = sc.nextInt();
		
		for(int t = 0; t < T; t++) {
			
			// 주어지는 숫자 개수 N
			N = sc.nextInt();
			arr = new int[N + 1];
			for(int i = 1; i <= N; i++) {
				arr[i] = sc.nextInt();
			}
			Arrays.sort(arr);
			
			// 차이 구하기 (숫자 저장, 숫자 개수 저장)
			diffList = new ArrayList<>();
			diffMap = new HashMap<>();
			
			for(int i = 0; i < N; i++) {
				for(int j = i + 1; j <= N; j++) {
					int diffNum = arr[j] - arr[i];
					
					// 숫자 종류 저장
					if(!diffList.contains(diffNum)) diffList.add(diffNum);
					
					// 숫자 개수 저장
					diffMap.put(diffNum, diffMap.getOrDefault(diffNum, 0) + 1);
				}
			}
			
			// 경우의 수
			cnt = 0;
			
			// 차이 중에서 3개 골라서 A, B, C라고 가정하기
			chosen = new int[3];
			combination(0, 0);
			
			System.out.println(cnt);
		}
	}
	
	static void combination(int depth, int idx) {
		
		// 3개를 다 골랐다면
		if(depth == 3) {
			// arr를 맵에 키로 넣는다
			HashMap<Integer, Integer> hm = new HashMap<>();
			for(int i = 1; i <= N; i++) {
				hm.put(arr[i], 1);
			}
			
			// 이 3개가 ABC라고 가정하고 만든 수 7개 중에서 몇 개가 겹치는지
			ArrayList<Integer> list = new ArrayList<>();
			if(!list.contains(chosen[0])) list.add(chosen[0]);
			if(!list.contains(chosen[1])) list.add(chosen[1]);
			if(!list.contains(chosen[2])) list.add(chosen[2]);
			if(!list.contains(chosen[0] + chosen[1])) list.add(chosen[0] + chosen[1]);
			if(!list.contains(chosen[0] + chosen[2])) list.add(chosen[0] + chosen[2]);
			if(!list.contains(chosen[1] + chosen[2])) list.add(chosen[1] + chosen[2]);
			if(!list.contains(chosen[0] + chosen[1] + chosen[2])) list.add(chosen[0] + chosen[1] + chosen[2]);

			// 주어진 N개의 수 안에, 만든 7개의 수가 있으면 빼준다 
			for(int i = 0; i < list.size(); i++) {
				if(hm.containsKey(list.get(i))) hm.remove(list.get(i));
			}
			
			
			// 다 빠져서 0이 되면 
			if(hm.size() == 0) {
				cnt++;
			}
			
			return;
		}
		
		
		// idx가 diffList 크기를 넘어가면 안 된다
		if(idx >= diffList.size()) return;
		
		// 1. depth에 idx를 선택한 것
		// 한 숫자가 여러 개 있다면, 여러 번 선택할 수 있도록 해야한다
		// **이걸 계속 놓쳤고 **구현이 어려웠다
		chosen[depth] = diffList.get(idx);
		
		// 1번 썼으니까 diffMap을 1 감소시켜 주는데
		diffMap.put(diffList.get(idx), diffMap.get(diffList.get(idx)) - 1);

		// 그래도 1개 **이상이 남았다면 이 idx를 한 번 더 해도 된다
		if(diffMap.get(diffList.get(idx)) > 0) combination(depth + 1, idx);
		else combination(depth + 1, idx + 1);
		
		// 올라오면 숫자 쓴 거 원상복귀
		diffMap.put(diffList.get(idx), diffMap.get(diffList.get(idx)) + 1);
		
		// 2. depth에 idx를 선택하지 않은 것
		combination(depth, idx + 1);
		
	}
	
	
}

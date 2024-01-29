import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 숫자 N개로 이루어진 수열
    	// 숫자는 C보다 작거나 같다
    	int N = sc.nextInt();
    	int C = sc.nextInt();
    	
    	// 빈도가 높으면 앞에 위치, 빈도가 같으면 먼저 나온 것이 앞에 위치
    	// 빈도수 저장
    	HashMap<Integer, Integer> hm = new HashMap<>();
    	
    	// 키 저장 리스트 (list가 정렬되면서 바뀔까봐 기준 리스트 하나 더)
    	ArrayList<Integer> list = new ArrayList<>();
    	ArrayList<Integer> copy = new ArrayList<>();

    	for(int i = 1; i <= N; i++) {
    		int in = sc.nextInt();
    		// 키 저장
    		if(!list.contains(in)) {
    			list.add(in);
    			copy.add(in);
    		}
    		
    		// 빈도수 증가
    		hm.put(in, hm.getOrDefault(in, 0) + 1);
    	}

    	
    	// 나온 숫자들 리스트를 정렬하는데, 해시맵을 기준으로 
    	Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// 빈도수가 같은 경우 : 오름차순
				if(hm.get(o1) == hm.get(o2)) {
					return copy.indexOf(o1) - copy.indexOf(o2);
				}
				// 빈도수가 다른 경우 : 내림차순
				else {
					return hm.get(o2) - hm.get(o1);
				}
			}
    	
    	});
    	
    	
    	
    	// 출력
    	StringBuilder sb = new StringBuilder();
    	
    	for(int n : list) {
    		// 해당 숫자를 빈도수만큼 출력
    		for(int j = 0; j < hm.get(n); j++) {
    			sb.append(n + " ");
    		}
    	}
    	
    	System.out.println(sb);
    }
}

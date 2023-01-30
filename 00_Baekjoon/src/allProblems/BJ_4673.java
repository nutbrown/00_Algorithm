package allProblems;

import java.util.HashMap;

public class BJ_4673 {
	public static void main(String[] args) {
		
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		for(int i = 1; i < 10000; i++) {
			int sum = i;
			String istr = String.valueOf(i);

			for(int j = 0; j < istr.length(); j++) {
				sum += istr.charAt(j) - '0';
			}
			hm.put(sum, sum);
		}
		
		for(int i = 1; i <= 10000; i++) {
			if(!hm.containsKey(i)) {
				System.out.println(i);
			}
		}

	}

}

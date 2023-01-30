package allProblems;

import java.util.HashMap;
import java.util.Scanner;

public class BJ_1316 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int count = N;
		
		for(int i = 0; i < N; i++) {
			String str = sc.nextLine();
			HashMap<Integer, Character> hm = new HashMap<>();
			boolean group = true;
			
			for(int j = 0; j < str.length(); j++) {
				if(j > 0) {
					if(hm.containsValue(str.charAt(j)) && str.charAt(j - 1) != str.charAt(j)) {
						group = false;
					}
				}
				hm.put(j, str.charAt(j));
			}
			
			if(group == false) {
				count--;
			}
		}
		System.out.println(count);
	}
}

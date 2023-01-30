package allProblems;

import java.util.HashMap;
import java.util.Scanner;

public class BJ_1316_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int count = N;
		
		for(int i = 0; i < N; i++) {
			System.out.println("i = " + i);
			String str = sc.nextLine();
			System.out.println(str);
			HashMap<Integer, Character> hm = new HashMap<>();
			boolean group = true;

			
			for(int j = 0; j < str.length(); j++) {
				if(j > 0) {
					if(hm.containsValue(str.charAt(j)) && str.charAt(j - 1) != str.charAt(j)) {
						count--;
						System.out.print(count);
					}
				}
				hm.put(j, str.charAt(j));
			}
			System.out.println(" ");
		}
		System.out.println(N);
		
	}

}

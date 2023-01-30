package allProblems;

import java.util.Scanner;

public class BJ_16392 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		double t = 60 * Math.PI / 28 / 15;
		
		for(int i = 0; i < N; i++) {
			String word = sc.nextLine();
			int[] wordArr = new int[word.length()];
			double time = 0;
			
			for(int j = 0; j < word.length(); j++) {
				if(word.charAt(j) == '\'') {
					wordArr[j] = 28;
				} else if(word.charAt(j) == ' ') {
					wordArr[j] = 27;
				} else {
					wordArr[j] = (int)word.charAt(j) - 64;
				}
				
				if(j > 0) {
					time += t * Math.min(Math.abs(wordArr[j] - wordArr[j - 1]),
										 28 - Math.abs(wordArr[j] - wordArr[j - 1]));
				}
				time += 1;
			}
			System.out.println(time);
		}
	}

}

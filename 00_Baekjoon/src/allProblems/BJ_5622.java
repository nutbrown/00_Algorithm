package allProblems;

import java.util.Scanner;

public class BJ_5622 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		int total = 0;
		for(int i = 0; i < str.length(); i++) {
			char word = str.charAt(i);
			int t = 0;
			switch(word) {
			case 'A' : case 'B' : case 'C' : t = 3; break;
			case 'D' : case 'E' : case 'F' : t = 4; break;
			case 'G' : case 'H' : case 'I' : t = 5; break;
			case 'J' : case 'K' : case 'L' : t = 6; break;
			case 'M' : case 'N' : case 'O' : t = 7; break;
			case 'P' : case 'Q' : case 'R' : case'S' : t = 8; break; 
			case 'T' : case 'U' : case 'V' : t = 9; break;
			case 'W' : case 'X' : case 'Y' : case 'Z' : t = 10; break;
			}
			total += t;
		}
		System.out.print(total);
	}
}

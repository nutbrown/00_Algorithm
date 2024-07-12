import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.next();
		String t = sc.next();
		
		// 최소공배수말고 그냥 길이1*길이2만큼 이어붙인다
		if(s.repeat(t.length()).equals(t.repeat(s.length()))) System.out.println(1);
		else System.out.println(0);
		
	}
}
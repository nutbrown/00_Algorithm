
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int e = sc.nextInt();
		int f = sc.nextInt();
		
		System.out.print((c*e - b*f) / (a*e - b*d));
		System.out.print(" ");
		System.out.print((c*d - a*f) / (b*d - a*e));
	}
}

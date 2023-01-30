package allProblems;
import java.util.Scanner;

public class BJ{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[][] seq = new int[n + 1][n + 1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				seq[i][j] = seq[i][j - 1] + seq[i - 1][j] - seq[i - 1][j - 1] + sc.nextInt();
			}
		}
        
		
		for(int i = 0; i < m; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			
			System.out.println(seq[x2][y2] - seq[x1 - 1][y2] - seq[x2][y1 - 1] + seq[x1 - 1][y1 - 1]);
		}
	}
}
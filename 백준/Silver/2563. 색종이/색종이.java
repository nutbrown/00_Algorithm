import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		boolean[][] arr = new boolean[100][100];
		int cnt = 0;
		
		for(int t = 0; t < n; t++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for(int i = x; i < x + 10; i++) {
				for(int j = y; j < y + 10; j++) {
					if(!arr[i][j]) {
						arr[i][j] = true;
						cnt++;
					}
				}
			}
			
			
		}
		System.out.println(cnt);
	}
}

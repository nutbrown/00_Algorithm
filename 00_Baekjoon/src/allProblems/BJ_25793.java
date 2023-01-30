package allProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_25793 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < t; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st1.nextToken());
			int c = Integer.parseInt(st1.nextToken());
			
			long a = Math.min(r, c);
			long b = Math.abs((r - c));
			
			long white = (2 * a * (a + 1) * (a - 1) / 3) + a + a * a * b;
			long dark = (2 * a * (a + 1) * (a - 1) / 3) + a * a * b;

			System.out.print(white + " ");
			System.out.println(dark);
		}
	}
}

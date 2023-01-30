package allProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2869 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double a = Double.parseDouble(st.nextToken());
		double b = Double.parseDouble(st.nextToken());
		double v = Double.parseDouble(st.nextToken());
		
		System.out.println((int)Math.ceil((v - a) / (-b + a)) + 1);
	}
}

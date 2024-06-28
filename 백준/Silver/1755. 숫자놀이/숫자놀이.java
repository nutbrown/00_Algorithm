import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// M이상 N이하 정수
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		// 숫자랑 영어로 저장
		String[][] arr = new String[N - M + 1][2];
		for(int i = M; i <= N; i++) {
			
			String num = String.valueOf(i);
			String name = "";
			for(int j = 0; j < num.length(); j++) {
				if(num.charAt(j) == '0') name += "zero";
				else if(num.charAt(j) == '1') name += "one";
				else if(num.charAt(j) == '2') name += "two";
				else if(num.charAt(j) == '3') name += "three";
				else if(num.charAt(j) == '4') name += "four";
				else if(num.charAt(j) == '5') name += "five";
				else if(num.charAt(j) == '6') name += "siz";
				else if(num.charAt(j) == '7') name += "seven";
				else if(num.charAt(j) == '8') name += "eight";
				else if(num.charAt(j) == '9') name += "nine";
				
				name += " ";
			}
			arr[i - M][0] = num;
			arr[i - M][1] = name; 
		}
		
		Arrays.sort(arr, new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				return o1[1].compareTo(o2[1]);
			}
		});
		
		for(int i = 0; i < N - M + 1; i++) {
			System.out.print(arr[i][0] + " ");
			
			// 10개마다 끊어서
			if(i % 10 == 9) System.out.println();
		}
	}
}

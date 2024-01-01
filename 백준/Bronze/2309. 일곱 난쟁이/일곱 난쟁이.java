import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Integer> list = new ArrayList<>();
		int sum = 0;
		for(int i = 0; i < 9; i++) {
			int in = sc.nextInt();
			list.add(in);
			sum += in;
		}

		// 둘이 더해서 diff가 나오는 수를 찾는다
		int diff = sum - 100;
		int a = 0;
		int b = 0;
		for(int i = 0; i < 9; i++) {
			if(list.contains(diff - list.get(i))) {
				a = list.get(i);
				b = diff - a;
			}
		}
		
		Collections.sort(list);
		for(int i = 0; i < 9; i++) {
			if(list.get(i) != a && list.get(i) != b) {
				System.out.println(list.get(i));
			}
		}
	}
}

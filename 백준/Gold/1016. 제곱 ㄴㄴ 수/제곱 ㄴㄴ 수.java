import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long min = sc.nextLong();
		long max = sc.nextLong();
		
		HashMap<Long, Integer> hm = new HashMap<>();
		for(long i = min; i <= max; i++) {
			hm.put(i, 1);
		}

		for(long i = 2; i <= (int)Math.sqrt(max); i++) {
			long j = 0;
			if(min % (long)Math.pow(i, 2) == 0) {
				j = min / (long)Math.pow(i, 2);
			} else {
				j = min / (long)Math.pow(i, 2) + 1;
			}
			while((long)Math.pow(i, 2) * j <= max) {
				hm.put(((long) Math.pow(i, 2) * j), 0);
				j++;
			}
		}
		
		long count = 0;
		for(long i = min; i <= max; i++) {
			if(hm.get(i) == 1) {
				count++;
			}
		}
		System.out.println(count);
	}
}

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		// K의 개수가 S의 개수의 2배이면서
		// S와 K가 적어도 한 번은 등장하는 문자열을 SKK 문자열이라고 한다
		// 문자열 중 가장 길이가 긴 SKK 문자열을 찾는 프로그램
		
		// S는 2, K는 -1로 저장
		// 누적합이 0이 되는 구간이 K의 개수가 S의 개수의 2배가 되는 구간이다
		// LUKESKYWALKER
		// 0[ 0 -1 -1 1 0 0 0 0 0] -1 -1 -1
		// SUNGKYUNKWAN
		// 2 2 2 2 1 1 1 1 0 0 0 0
		int[] SKK = new int[str.length() + 1];
		
		// S랑 K가 하나도 없는 걸 방지하기 위해서
		// S랑 K가 나오면 1로 누적합
		int[] isSKK = new int[str.length() + 1];
		
		for(int i = 1; i <= str.length(); i++) {
			char ch = str.charAt(i - 1);
			
			isSKK[i] = isSKK[i - 1] + ((ch == 'S' || ch == 'K') ? 1 : 0);
			
			int add = 0;
			if(ch == 'S') add = 2;
			else if(ch == 'K') add = -1;
				
			SKK[i] = SKK[i - 1] + add;
		}
		
		// <숫자, 가 나온 인덱스>
		HashMap<Integer, Integer> hm = new HashMap<>();
		int max = -1;
		
		// 처음에 0 인덱스 삽입
		hm.put(0,  0);
		
		for(int i = 1; i <= str.length(); i++) {
			int num = SKK[i];
			
			// 나온 적 없는 숫자면
			if(!hm.containsKey(num)) {
				hm.put(num, i);
			}
			
			// 나온 적 있으면
			else {
				int idx = hm.get(num);
				// hm.get(num) + 1부터 i사이에 SKK가 있는지 확인
				if(isSKK[i] - isSKK[idx] != 0) {
					// hm.get(num) 부터 i까지면
					// 길이는 hm.get(num) - i다
					max = Math.max(max, i - idx);
				}
			}
		}
		
		System.out.println(max);
	}
}

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// N개의 수
		int N = Integer.parseInt(sc.next());
		
		// A부터 J가 자리수를 대신해서 쓰여있다
		// 0부터 9까지 10개의 인덱스에 차례대로 저장하자
		long[] cnt = new long[10];
		
		// 0으로 시작하는 수가 없다
		int[] startfirst = new int[10];
		
		for(int i = 0; i < N; i++) {
			String in = sc.next();
			int l = in.length();
			
			// 처음 시작하는 거 체크
			startfirst[in.charAt(0) - 'A'] = 1;
			
			// 0은 A부터 9는 J까지
			// 1, 10, 100...을 곱해서 넣어준다
			// ABC 길이가 3이면 10^2 10^1 10^0으로 넣어준다
			// 길이 - 1 - j로 넣어준다
			for(int j = 0; j < l; j++) {
				cnt[in.charAt(j) - 'A'] += Math.pow(10, l - 1 - j);
			}
		}
		
		// 합의 최댓값
		long sum = 0;
		
		// 만약에 모든 숫자가 나왔다면
		boolean allNum = true;
		for(int i = 0; i < 10; i++) {
			if(cnt[i] == 0) {
				allNum = false;
				break;
			}
		}
		
		// 처음에 오지 않은 숫자 중에서
		// 제일 작은 수한테 0을 준다
		if(allNum) {
			long min = Long.MAX_VALUE;
			int idx = 0;
			
			for(int i = 0; i < 10; i++) {
				if(startfirst[i] == 0) {
					if(cnt[i] < min) {
						min = cnt[i];
						idx = i;
					}
				}
			}
			
			// 제일 작은 수를 0으로 없앤다
			cnt[idx] = 0;
		}
		
		
		// 숫자 큰 순서대로 정렬해서 위에서부터 9를 배정
		Arrays.sort(cnt);
		
		for(int i = 9; i > 0; i--) {
			sum += cnt[i] * i;
		}
		
		
		System.out.println(sum);
	}
}

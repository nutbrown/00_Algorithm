import java.util.HashSet;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N개의 전구, t 2t 3t... 가 지난 시간에 반짝인다
		// T초가 지난 시각에 모든 전구가 동시에 반짝이도록
		// 전구 주기를 1초만큼 늘이거나 줄일 때 -> 몇 번 해야할지
		int N = sc.nextInt();
		int T = sc.nextInt();

		// 주기
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 모든 주기가 T의 약수여야한다
		// T의 약수를 전부 구한다음에
		// 주기를 +1 -1 시키면서 최소 횟수를 구한다
		
		// 1부터 T까지 약수
		HashSet<Integer> hs = new HashSet<>();
		for(int i = 1; i <= T; i++) {
			// T가 i로 나눠진다면 약수다
			if(T % i == 0) hs.add(i);
		}
		
		// 최소 횟수 구하기
		// 1000개가 최대 1000만큼의 증감하니까 최대 1,000,000
		int min = 0;
		
		for(int i = 0; i < N; i++) {
			
			// 이 전구의 최소 횟수 찾기
			int cnt = 0;
			
			// 주기가 T의 약수가 아니라면
			while(T % arr[i] != 0) {
				
				// 증감을 늘이고
				cnt++;
				
				// 줄였을 때 약수가 되는지
				if(arr[i] - cnt >= 1 && T % (arr[i] - cnt) == 0) break;
				
				// 늘였을 때 약수가 되는지
				if(T % (arr[i] + cnt) == 0) break;
			}
			
			// 이 전구의 횟수를 더하기
			min += cnt;
			
		}
		
		System.out.println(min);
		
	}
}
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 블로그를 시작한지 N일
		// X일 동안 가장 많이 들어온 방문자 수
		int N = sc.nextInt();
		int X = sc.nextInt();
		
		// 방문자수는 최대 2,000,000,000 딱 int 범위
		// key방문자인 기간이 value개
		HashMap<Integer, Integer> hm = new HashMap<>();
		
		// 가장 많이 들어온 방문자 수
		int max = 0;

		// 현재 기간 방문자 수
		int cur = 0;
		
		// 나중에 빼기 위해서 방문자 수 저장
		int[] arr = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			// 방문자 수 입력
			arr[i] = sc.nextInt();
			
			// 어떻든간에 더해야한다.
			cur += arr[i];
			
			// X일이 되지 않았다면 더하기만 하기
			
			// X일이 되었다면, 더하고 최댓값 갱신
			if(i == X) {
				// 맵에 기간 개수 넣어주기
				hm.put(cur, 1);
				
				// 최댓값 입력
				max = cur;
			}
			
			// X일을 넘겼다면, 한칸 옮겨주고 최댓값 갱신
			else if(i > X) {
				cur -= arr[i - X];
				
				hm.put(cur, hm.getOrDefault(cur, 0) + 1);
				max = Math.max(max, cur);
			}
		}
		
		// 최대 방문자가 0명이면 SAD
		if(max == 0) System.out.println("SAD");
		
		// 최대 방문자가 0명이 아니라면, 기간이 몇 개 있는지
		else System.out.println(max + "\n" + hm.get(max));
		
	}
}
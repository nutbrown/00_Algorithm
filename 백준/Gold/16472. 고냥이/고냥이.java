import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 인식할 수 있는 알파벳의 종류
		int N = Integer.parseInt(sc.next());
		
		// 문자열
		char[] arr = sc.next().toCharArray();
		
		// 최대 길이
		int max = 1;
		
		// 알파벳 종류 저장
		HashMap<Character, Integer> hm = new HashMap<>();
		
		// 투포인터 문제인 걸 알고 나니까 ->
		// 시작점 끝점을 지정해서 N을 넘으면 앞에서 줄여주면서?
		int st = 0;
		
		// 처음 문자 넣기
		hm.put(arr[0], 1);
		
		// 한 번 돌때마다 끝점은 1개씩 증가한다
		// ***길이가 1개인 경우 조심, 두 번째 인덱스부터 넣고 앞에꺼 빼고
		for(int ed = 1; ed < arr.length; ed++) {
			
			// 알파벳 저장 개수가 N인데,
			// ed 문자가 없던 거면 앞에꺼 지우기
			if(hm.size() == N && !hm.containsKey(arr[ed])) {
				
				// N보다 작아질때까지 계속 빼야한다
				while(hm.size() == N) {
					// 1개면 키를 빼는데
					if(hm.get(arr[st]) == 1) {
						hm.remove(arr[st]);
						
					// 1개보다 크면 1개를 줄여준다
					} else {
						hm.put(arr[st], hm.get(arr[st]) - 1);
					}
					
					// 다음 st 확인
					st++;
				}
			}
			
			// N개면 1개 없애주고, 아니면 그냥 ed 추가하기
			hm.put(arr[ed], hm.getOrDefault(arr[ed], 0) + 1);
			

			// 길이 갱신
			max = Math.max(max, ed - st + 1);
		}
			
		System.out.println(max);
	}
}
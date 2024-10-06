import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 문제 개수 N
		int N = Integer.parseInt(br.readLine());
		
		// 가장 자주 등장한 태그 출력 (여러 개일 경우, -1 출력)
		// 태그 뭐 있는지 저장
		ArrayList<String> list = new ArrayList<>();
		HashMap<String, Integer> hm = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 문제 번호, 문제 태그 개수
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < t; j++) {
				// 태그 입력
				String tag = st.nextToken();
				
				// 태그가 이미 있으면 증가
				if(hm.containsKey(tag)) {
					hm.put(tag, hm.get(tag) + 1);
				}
				
				// 없으면 추가하기
				else {
					list.add(tag);
					hm.put(tag, 1);
				}
			}
		}

		// 가장 많이 등장한 태그 찾기
		// 최대 등장횟수 태그랑, 최대 등장횟수
		String ans = "";
		int max = 0;
		
		for(String tag : list) {
			
			// 최댓값이 갱신되면 cnt를 1로 초기화하기
			if(hm.get(tag) > max) {
				ans = tag;
				max = hm.get(tag);
			}
			
			// 최댓값 카드가 1개 더 생기면
			else if(hm.get(tag) == max) {
				// 더 큰게 나올 수 있으니까 max는 그대로 두되
				// 출력 못하니까 ans는 빈칸으로 두자
				ans = "";
			}
		}
		
		// 태그가 여러개여서 ans가 비어있으면 -1 출력, 아니면 태그 출력
		if(ans.length() == 0) System.out.println(-1);
		else System.out.println(ans);
		
		
	}
}
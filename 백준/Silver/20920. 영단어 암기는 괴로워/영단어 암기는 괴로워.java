import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// <단어, 나온횟수>
		HashMap<String, Integer> hm = new HashMap<>();
		
		for(int i = 0; i < N; i++) {
			// 단어 길이가 M이상인 것들만
			String word = br.readLine();
			if(word.length() < M) continue;

			// 단어 개수 세기
			hm.put(word, hm.getOrDefault(word, 0) + 1);
		}
		
		// [단어][나온횟수] 합쳐주기
		ArrayList<String> words = new ArrayList<>(hm.keySet());
		int[][] arr = new int[words.size()][2];
		
		for(int i = 0; i < words.size(); i++) {
			// 리스트에서 인덱스, 단어의 횟수
			arr[i][0] = i;
			arr[i][1] = hm.get(words.get(i)); 
		}
		
		// 규칙에 맞게 정렬
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// 자주 나오는 단어를 앞에
				if(o1[1] != o2[1]) {
					
					// 클수록 앞에, 내림차순으로
					return o2[1] - o1[1];
				}
				
				// 단어의 길이가 길수록
				// else { 할 필요 없다
				if(words.get(o1[0]).length() != words.get(o2[0]).length()) {
					
					// 단어의 길이가 길수록 앞에, 내림차순
					return words.get(o2[0]).length() - words.get(o1[0]).length();
				}
				
				// 단어 사전순으로
				// 오름차순 순서로 적으면, 사전순으로 정렬***
				return words.get(o1[0]).compareTo(words.get(o2[0]));
			}
		});
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < arr.length; i++) {
			
			sb.append(words.get(arr[i][0])).append("\n");
		}
		System.out.println(sb);
		
	}
}
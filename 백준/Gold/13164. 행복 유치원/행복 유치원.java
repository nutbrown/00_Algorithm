import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 유치원 원생 수 N, 나누는 조의 개수 K
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 티셔츠 비용은 조에서 가장큰 - 가장작은 키 차이
		// 티셔츠 만드는 비용의 합을 최소로 하기
		// 10^9를 넘기지 않으니까 맨 왼쪽 0에서 아무리 차이를 더하면서 올라와도
		// int를 넘기지 않는다
		
		// 5명있는데 3개조면, 2명을 조에 넣어야한다
		// 1 3 5 6 10이고 사이에 차이가
		//  2 2 1 4 라고하면 2명을 없애야해서 2개를 고른것
		// 차이를 다 골라서, 작은 거 순서대로 N-K개 골라서 더하기
		ArrayList<Integer> list = new ArrayList<>();
		
		// 키 순서대로 줄
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		
		// 차이 구하면서 첫번째 값 넣기
		arr[0] = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i < N; i++) {
			
			// 수 입력받고, 차이 넣기
			arr[i] = Integer.parseInt(st.nextToken());
			list.add(arr[i] - arr[i - 1]);
		}
		
		// 정렬하고 작은 거 더하기
		int sum = 0;
		Collections.sort(list);
		
		for(int i = 0; i < N - K; i++) {
			sum += list.get(i); 
		}
		
		System.out.print(sum);
		
	}
}
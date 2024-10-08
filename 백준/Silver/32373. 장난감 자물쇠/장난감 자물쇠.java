import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		
		// 수열의 길이 N, 교환할 수 있는 간격 K
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 장난감 자물쇠 수열의 초기상태
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 숫자가 2면 그건 2 인덱스에 있어야 한다
		// (원래 있어야하는 인덱스) - (현재 인덱스) 가 k로 나눠지면 통과
		boolean isOrdered = true;
		for(int i = 0; i < N; i++) {
			
			if(Math.abs(arr[i] - i) % K == 0) {
				continue;
			} else {
				isOrdered = false;
				break;
			}
			
		}
		
		// 불가능으로 뛰쳐나온거면 No, 다 통과한 거면 Yes
		if(isOrdered) System.out.println("Yes");
		else System.out.println("No");
		
	}
}
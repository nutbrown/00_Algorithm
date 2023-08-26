import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.next());
		
		for(int t = 0; t < T; t++) {
			// 수행할 함수
			String in1 = sc.next();
			char[] func = in1.toCharArray();
			
			// 입력받는 수
			int n = Integer.parseInt(sc.next());
			String in2 = sc.next();
			// 앞 뒤 괄호 빼고
			in2 = in2.substring(1, in2.length() - 1);
			String[] numIn = in2.split(",");
			
			// 입력받는 수 추출
			int head = 0;
			int tail = n - 1;
			boolean dir = true;
			
			int[] arr = new int[n];
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(numIn[i]);
			}

			// R이면 뒤집고 D는 첫번째를 버리고
			// 버릴게 없으면 error 출력
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < func.length; i++) {
				if(func[i] == 'R') {
					// 앞 뒤 순서를 바꾸고
					dir = !dir;
					int temp = head;
					head = tail;
					tail = temp;
				} else {
					// 숫자를 제거할 수 없다면 error
					if(n == 0) {
						sb.append("error");
						break;
					} else {
						// 총 개수 줄이기
						n--;
						// 맨 앞 수를 제거
						if(dir) head++;
						else head--;
					}
				}
			}
			
			if(sb.length() == 0) {
				sb.append("[");
				if(n != 0) {
					if(dir) {
						for(int i = 0; i < n - 1; i++) {
							sb.append(arr[head + i]).append(","); 
						}
						sb.append(arr[tail]);
					} else {
						for(int i = 0; i < n - 1; i++) {
							sb.append(arr[head - i]).append(","); 
						}
						sb.append(arr[tail]);
					}
				}
				sb.append("]");
			}
			
			System.out.println(sb);
		}
		
	}
}
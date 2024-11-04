import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);

    	// 문자열 길이 N, 문자열 T
    	int N = Integer.parseInt(sc.next());
    	char[] arr = sc.next().toCharArray();
    	
    	// 알파벳의 개수는 26개. 몇 개를 물어봐야하는지
    	int cnt = 0;
    	
    	// 길이가 4면 0 1(2), 길이가 5면 0 1(2)
    	for(int i = 0; i < N / 2; i++) {
    		
    		// i번째랑, N-1 - i번째랑 같아야한다
    		// 둘다 이미 정해져 있다면
    		if(arr[i] != '?' && arr[N - 1 - i] != '?') {
    			// 둘이 같으면 그냥 넘어가기
    			if(arr[i] == arr[N - 1 - i]) {
    				continue;
    			}
    			
    			// 이미 다르면 확인 안해도 되니까 0개
    			if(arr[i] != arr[N - 1 - i]) {
    				cnt = 0;
    				break;
    			}
    		}
    		
    		// 알파벳이 26개여서, 25번 물어봐서 다 아니면 나머지 1개인 걸 알 수 있다
    		// 둘다 물음표면 25개 물어보고 + 반대쪽 1번 물어보기
    		else if(arr[i] == '?' && arr[N - 1 - i] == '?') {
    			cnt += 26;
    		}
    		
    		// 둘 중에 하나만 물음표면 1번 물어보기
    		else {
    			cnt++;
    		}
    		
    		
    	}
    	
    	System.out.println(cnt);
    	
    }
}
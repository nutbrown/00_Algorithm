import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);

    	// 길이 N인 수열, 중 2개를 골라서 합이 S되는 경우의 수
    	int N = sc.nextInt();
    	int S = sc.nextInt();
    	
    	int[] arr = new int[N];
    	for(int i = 0; i < N; i++) {
    		arr[i] = sc.nextInt();
    	}
    	
    	// 앞에서부터 반까지 세어보기
    	// ***아니다.... 큰 수 2개를 합해야지 나오는 수 일수도 있잖니 
    	int cnt = 0;
    	for(int i = 0; i < N; i++) {
    		
    		// 2개 다른 수니까 자기 다음부터 고르기
    		int target = S - arr[i];
    		int st = i + 1;
    		int ed = N - 1;
    		
    		while(st <= ed) {
    			int mid = (st + ed) / 2;
    			
    			if(arr[mid] < target) {
    				st = mid + 1;
    			} else if(arr[mid] > target) {
    				ed = mid - 1;
    			} else {
    				cnt++;
    				break;
    			}
    		}
    	}
    	
    	System.out.println(cnt);
    	
    }
}
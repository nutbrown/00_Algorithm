import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// N 건초가 있고, Q개의 쿼리에 답을 한다
    	int N = sc.nextInt();
    	int Q = sc.nextInt();
    	
    	// 건초가 어디있는지 입력, 정렬
    	int[] hay = new int[N];
    	for(int i = 0; i < N; i++) {
    		hay[i] = sc.nextInt();
    	}
    	Arrays.sort(hay);
    	
    	// A이상 B이하 건초가 멏개인지
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < Q; i++) {
    		int A = sc.nextInt();
    		int B = sc.nextInt();
    		
    		// A보다 작은 걸 제거해서, 이상인 제일 작은 점 lowerbound
    		// lowerbound
    		int st = 0;
    		int ed = N - 1;
    		
    		while(st <= ed) {
    			int mid = (st + ed) / 2;
    			
    			if(hay[mid] < A) {
    				st = mid + 1;
    			} else {
    				ed = mid - 1;
    			}
    		}
    		
    		int left = st;
    		
    		// B보다 이하인 걸 제거해서, 초과인 upperbound를 찾고 -1
    		st = 0;
    		ed = N - 1;
    		
    		while(st <= ed) {
    			int mid = (st + ed) / 2;
    			
    			if(hay[mid] <= B) {
    				st = mid += 1;
    			} else {
    				ed = mid - 1;
    			}
    		}
    		
    		int right = st - 1;
    		
    		// 개수는 left부터 right인데
    		// 둘이 같으면 1개지만
    		// 전복되면 0개다
    		if(right < left) sb.append(0);
    		else sb.append(right - left + 1);
    		sb.append("\n");
    		
    	}
    	
    	System.out.println(sb);
    	
    }
}
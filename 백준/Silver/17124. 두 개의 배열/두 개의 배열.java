import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);

    	// 테스트 케이스의 수
    	int T = sc.nextInt();
    	for(int t = 0; t < T; t++) {
    		
    		// 수열 A 수열 B
    		int N = sc.nextInt();
    		int M = sc.nextInt();
    		
    		int[] A = new int[N];
    		for(int i = 0; i < N; i++) {
    			A[i] = sc.nextInt();
    		}
    		int[] B = new int[M];
    		for(int i = 0; i < M; i++) {
    			B[i] = sc.nextInt();
    		}
    		Arrays.sort(B);
    		
    		
    		// C[i]는 배열 B에 있는 값 중 A[i]에 가장 가까운 값
    		// 조건을 만족하는 값이 여럿일 경우, 가장 크기가 작은 값
    		int[] C = new int[N];
    		
    		// C 배열의 합 - long
    		long sum = 0;
    		
    		for(int i = 0; i < N; i++) {
    			
    			// A[i] 이상인 걸 찾고, 그보다 작은 거랑 비교
    			int target = A[i];
    			int st = 0;
    			int ed = M - 1;
    			
    			while(st <= ed) {
    				int mid = (st + ed) / 2;
    				
    				if(B[mid] < target) {
    					st = mid + 1;
    				} else {
    					ed = mid - 1;
    				}
    			}
    			
    			// 오른쪽 끝이면 그 아래 입력
    			if(st == M) {
    				C[i] = B[st - 1];
    			}
    			
    			// 왼쪽 끝이면 이거 입력
    			else if(st == 0) {
    				C[i] = B[st];
    			}
    			
    			// 그 사이면 비교해서 입력
    			else {
    				if(target - B[st - 1] <= B[st] - target) {
    					C[i] = B[st - 1];
    				} else {
    					C[i] = B[st];
    				}
    			}
    			
    			sum += C[i];
    		}
    		
    		System.out.println(sum);
    		
    	}
    	
    	
    }
}
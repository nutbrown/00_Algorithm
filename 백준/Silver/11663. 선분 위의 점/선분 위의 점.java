import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);

    	// 점 N개와 선분 M개
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    	
    	// upperbound 다시 풀기
    	int[] dots = new int[N];
    	for(int i = 0; i < N; i++) {
    		dots[i] = sc.nextInt();
    	}
    	Arrays.sort(dots);
    	
    	// 선분 위에 점이 몇 개 있는지
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < M; i++) {
    		int S = sc.nextInt();
    		int E = sc.nextInt();
    		
    		// S 이상인 lowerbound를 구하고
    		// E 초과인 upperbound를 구하고 -> E 이하인 점을 구한다
    		
    		// lowerbound
    		int st = 0;
    		int ed = N - 1;
    		
    		while(st <= ed) {
    			int mid = (st + ed) / 2;
    			
    			if(dots[mid] < S) {
    				st = mid + 1;
    			} else {
    				ed = mid - 1;
    			}
    		}
    		int lowerbound = st;
    		
    		// upperbound
    		// upperbound여도 맨 끝을 N-1 로 해두면
    		// 둘다 N-1 N-1 여서 맨 끝이 target 보다 크지 않으면
    		// st가 1 증가해서 N이 되면서 arr를 초과한 N을 반환하면서 끝난다
    		st = 0;
    		ed = N - 1;
    		
    		while(st <= ed) {
    			int mid = (st + ed) / 2;
    			
    			if(dots[mid] <= E) {
    				st = mid + 1;
    			} else {
    				ed = mid - 1;
    			}
    		}
    		int upperbound = st;
    		
    		
    		// 시작점 <= 끝점이라면 개수 세기
    		int cnt = 0;
    		if(lowerbound <= upperbound - 1) {
    			cnt = upperbound - 1 - lowerbound + 1;
    		} else {
    			cnt = 0;
    		}
    		
    		sb.append(cnt).append("\n");
    		
    	}
    	
    	System.out.println(sb);
    	
    }
}
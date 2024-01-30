import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 절단기 높이 H로 잘린 나무를 갖고 집을 간다
    	// 나무의 수 N, 집으로 가져가는 나무 길이 M
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    
    	// 나무 입력
    	int[] trees = new int[N];
    	
    	// 최대 나무 높이
    	int max = 0;
    	for(int i = 0; i < N; i++) {
    		trees[i] = sc.nextInt();
    		max = Math.max(max, trees[i]);
    	}
    	Arrays.sort(trees);
    	
    	// 절단기 설정 높이의 최댓값
    	int st = 0;
    	int ed = max;
    	int target = M;
    	
    	while(st < ed) {
    		int mid = (st + ed) / 2 ;
    		
    		// 집에 가져가는 나무 높이의 합
    		long sum = 0;
    		for(int i = 0; i < N; i++) {
    			if(trees[i] > mid) sum += trees[i] - mid;
    		}

    		// sum이 M이상이어야 한다
    		// st sum target ed 
    		if(sum < target) {
    			ed = mid;
    		} else {
    			st = mid + 1;
    		}
    	}
    	
    	// 이분탐색 결괏값 출력
    	System.out.println(st - 1);
    	
    	
    }
}

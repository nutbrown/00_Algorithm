import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// C개의 공유기를 집 N개에 적당히 설치해서
    	// 가장 인접한 공유기 사이의 거리를 최대로 하기
    	int N = sc.nextInt();
    	int C = sc.nextInt();
    
    	// 집 좌표
    	int[] arr = new int[N];
    	for(int i = 0; i < N; i++) {
    		arr[i] = sc.nextInt();
    	}
    	Arrays.sort(arr);

    	// 집 좌표 최대 
    	int max = arr[N - 1];

    	
    	// 이걸 매개변수 탐색으로 생각해보자
    	// 매개변수는 공유기 사이 최소거리인 t다
    	// 1 2 4 8 9
    	
    	// 앞에서부터 t거리 이상으로 설치하다가
    	// 집이 남지 않으면 안 된다
    	// 집이 남으면 괜찮다
    	// 1 2 3 4 5 6 7 8
    	// O O O O X X X X 인 경우 최대인 4를 찾자
    	
    	// 매개변수 탐색 범위
    	int st = 1;
    	int ed = max + 1;
    	
    	while(st < ed) {
    		int mid = (st + ed) / 2;
    		
    		// C개를 설치할 수 있는지
    		boolean flag = true;
    		
    		// 첫 번째 집부터 시작
    		int cnt = 1;
    		int idx = 0;
    		
    		while(true) {
    			// 다음 집의 최소 위치
    			int nHouse = arr[idx] + mid;
    			
    			// 다음 집 찾기
    			boolean foundHouse = false;
    			for(int i = idx + 1; i < N; i++) {
    				// 최소 위치 이상이라면
    				if(arr[i] >= nHouse) {
    					cnt++;
    					idx = i;
    					foundHouse = true;
    					break;
    				}
    			}
    			
    			// C개 집을 다 찾았으면 통과
    			if(cnt == C) break;
    			
    			// 다음집을 못 찾았으면 아웃
    			if(!foundHouse) {
    				flag = false;
    				break;
    			}
    		}

        	// O O O [O] X X X X
    		// O로 통과하면 st를 오른쪽으로 증가
    		// X로 아웃이면 ed를 왼쪽으로 감소 -> upperbound를 구한다
    		if(flag) st = mid + 1;
    		else ed = mid;
    	}
    	
    	
    	System.out.println(st - 1);
    }
}

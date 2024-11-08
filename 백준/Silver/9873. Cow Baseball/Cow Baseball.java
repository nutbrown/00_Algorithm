import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);

    	// 소가 일렬로 서있다. XYZ 소가 공을 던지는데
    	// YZ가 XY 1배이상, 2배이하
    	int N = sc.nextInt();
    	
    	int[] arr = new int[N];
    	for(int i = 0; i < N; i++) {
    		arr[i] = sc.nextInt();
    	}
    	Arrays.sort(arr);

    	// 그런 경우의수
    	int cnt = 0;
    	
    	for(int i = 0; i < N - 2; i++) {
    		for(int j = i + 1; j < N - 1; j++) {
    			
    			// X랑 Y가 주어진 상황에서
    			// Y + (Y-X) 이상인 lowerbound를 찾고
    			// Y + (Y-X)*2 초과인 upperbound를 찾고 이하인 걸 찾는다
    			int target = arr[j] + (arr[j] - arr[i]);
    			int st = j + 1;
    			int ed = N - 1;
    			
    			while(st <= ed) {
    				int mid = (st + ed) / 2;
    				if(arr[mid] < target) {
    					st = mid + 1;
    				} else {
    					ed = mid - 1;
    				}
    			}
    			int lowerbound = st;
    			
    			target = arr[j] + (arr[j] - arr[i]) * 2;
    			st = j + 1;
    			ed = N - 1;
    			
    			while(st <= ed) {
    				int mid = (st + ed) / 2;
    				
    				// 여기서 이하일 경우 st가 mid에서 1을 더해서
    				// 0~4를 넘겨서 5가 upperbound라고 할 때
    				// 시작 ed를 4로 해도 배열을 넘겨서 5를 반환한다 
    				if(arr[mid] <= target) {
    					st = mid + 1;
    				} else {
    					ed = mid - 1;
    				}
    			}
    			int upperbound = st;
    			
    			// 맨아래보다 맨위가 이상이라면
    			if(lowerbound <= upperbound - 1) {
    				cnt += (upperbound - 1) - lowerbound + 1;
    			}
    			
    			
    		}
    	}
    	
    	System.out.println(cnt);
    	
    }
}
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// N개의 원소를 가진 배열 A로
    	// 오름차순으로 정렬된 배열 B를 만들고
    	// M개의 질문, 정수 D가 제일 먼저 등장한 위치, 없으면 -1
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    	
    	int[] arr = new int[N];
    	for(int i = 0; i < N; i++) {
    		arr[i] = sc.nextInt();
    	}
    	Arrays.sort(arr);
    	
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < M; i++) {
    		int D = sc.nextInt();
    		
    		// 정수 D가 제일 먼저 등장한 위치
    		// lowerbound를 찾는다
    		int st = 0;
    		int ed = N - 1;
    		
    		while(st <= ed) {
    			int mid = (st + ed) / 2;
    			
    			// D보다 작으면 제외
    			if(arr[mid] < D) {
    				st = mid + 1;
    			}
    			
    			// D보다 같거나 크면 하나 내리기
    			else {
    				ed = mid - 1;
    			}
    			
    			// st는 D보다 같거나 큰 수다
    			// D랑 같으면 ed는 내려와서
    			// ed는 D보다 작은 걸로 내려오고
    			// 3  4  5 - 4를 찾는다고 할 때
    			// st ed - 하면 mid가 3이어서 st가 올라오고
    			// 3  4  5
    			//   sted - 하면 mid가 4여서 ed가 내려오고
    			// 3  4  5
    			// ed st - 이러면 역전되어서 종료되고 st가 lowerbound다 
    			
    		}
    		
    		
    		// D이상인게 없어서 범위 밖이거나, 존재하지 않으면 -1, 아니면 위치
    		if(st >= N || arr[st] != D) sb.append(-1);
    		else sb.append(st);
    		sb.append("\n");
    		
    		
    	}
    	
    	System.out.println(sb);
    	
    }
}
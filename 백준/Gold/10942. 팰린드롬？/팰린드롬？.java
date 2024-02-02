import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 수열의 크기 N
    	int N = sc.nextInt();
    	int[] nums = new int[N + 1];
    	for(int i = 1; i <= N; i++) {
    		nums[i] = sc.nextInt();
    	}
    	
    	// 미리 펠린드롬 판단
    	// 펠린드롬은 거꾸로 읽어도 똑같은 문장 낱말 숫자다
    	// i번째 수부터 j번째 수까지 펠린드롬인지
    	int[][] pel = new int[N + 1][N + 1];
    	
    	// 홀수 : i번째 수를 기준으로 양 옆으로 퍼져나가면서 확인
    	for(int i = 1; i <= N; i++) {
    		// 양 옆으로 얼마나 퍼지는지
    		int width = 0;
    		
    		// 범위 안에 있는 만큼
    		while(i - width > 0 && i + width <= N) {
    			// i - width랑 i + width가 같으면 괜찮다
    			if(nums[i - width] == nums[i + width]) {
    				pel[i - width][i + width] = 1;
    				width++;
    			
    			// 하나라도 아니면 바로 탈출
    			} else {
    				break;
    			}
    		}
    	}
    	
    	// 짝수 : i번째 수를 기준으로 양 옆 오른쪽으로 퍼져나가면서 확인
    	for(int i = 1; i <= N; i++) {
    		// 양 옆으로 얼마나 퍼지는지
    		int width = 0;
    		
    		// 범위 안에 있는 만큼
    		while(i - width > 0 && i + width + 1 <= N) {
                // i - width랑 i + width + 1가 같으면 괜찮다
    			// 3에서 시작하면 (3, 4) (2, 5) (1, 6) 이렇게
    			if(nums[i - width] == nums[i + width + 1]) {
    				pel[i - width][i + width + 1] = 1;
    				width++;
    				
    				// 하나라도 아니면 바로 탈출
    			} else {
    				break;
    			}
    		}
    	}
    	
    	// 질문의 개수 M
    	int M = sc.nextInt();
    	
    	// 대답
    	StringBuilder sb = new StringBuilder();
    	
    	for(int i = 0; i < M; i++) {
    		// S번째 수부터 E번째 수가 펠린드롬을 이루는지
    		sb.append(pel[sc.nextInt()][sc.nextInt()]).append("\n");
    	}
    	
    	System.out.println(sb);
    }
}

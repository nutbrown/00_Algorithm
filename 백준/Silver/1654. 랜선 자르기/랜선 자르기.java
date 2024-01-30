import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 갖고 있는 랜선 개수 K, 필요한 랜선 개수 N
    	int K = sc.nextInt();
    	int N = sc.nextInt();
    
    	// 랜선 길이
    	int[] arr = new int[K];

    	// 랜선 길이 최댓값 **이게 왜 int면 안되지
    	long max = 0;
    	
    	for(int i = 0; i < K; i++) {
    		arr[i] = sc.nextInt();
    		max = Math.max(max, arr[i]);
    	}
    	
    	// 랜선 길이 t인 경우
    	// 각 랜선 길이를 t로 나눈 몫의 합이 N개가 되어야 한다
    	// 길이가 길어지면 ---> 총 개수가 적어지는데, upperbound 구하기
    	// 5 4 4 [4] 3 3 2 1
    	
    	// 랜선 길이 매개변수 탐색 : 탐색 범위 최솟값은 1
    	long st = 1;
    	long ed = max + 1;
    	
    	while(st < ed) {
    		// st랑 ed가 int 범위면 여기 더할 때 오버플로우 발생
    		long mid = (st + ed) / 2;
    		
    		// t의 길이로 일정하게 자를 때 총 몇 개인지
    		long sum = 0;
    		for(int i = 0; i < K; i++) {
    			sum += arr[i] / mid;
    		}

        	// 5 4 4 [4] 3 3 2 1 - 증가하는 방향이 반대다
    		// sum이 N보다 같거나 크면 제외하면
    		// 연속 구간 맨 끝 인덱스 + 1이 나온다
    		if(sum >= N) st = mid + 1;
    		else if(sum < N) ed = mid;
    	}
    	
    	
    	System.out.println(st - 1);
    }
}

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 막걸리 주전자 개수 N, 은상이 포함 친구들의 수 K 
    	int N = sc.nextInt();
    	int K = sc.nextInt();
    
    	// 막걸리 용량
    	int[] arr = new int[N];

    	// 주전자 용량 최댓값
    	int max = 0;
    	
    	for(int i = 0; i < N; i++) {
    		arr[i] = sc.nextInt();
    		max = Math.max(max, arr[i]);
    	}
    	
    	// t용량으로 동등하게 나눌 경우
    	// 각 막걸리를 t로 나눈 몫의 합이 K명이 되어야 한다.
    	// 용량이 많아지면 ---> 먹을 수 있는 사람 수가 적어지는데, upperbound 구하기
    	// 5 4 4 [4] 3 3 2 1
    	
    	// 막걸리 용량 매개변수 탐색
    	long st = 0;
    	long ed = max + 1;
    	
    	while(st < ed) {
    		// st랑 ed가 int 범위면 여기 더할 때 오버플로우 발생
    		long mid = (st + ed) / 2;
    		
    		// mid가 0이 될 수 없는데 0이 나오면
    		// st를 하나 올려준다
    		if(mid == 0) {
    			st++;
    			continue;
    		}

    		// 막걸리 용량이 mid일 때 몇 명이서 나눠먹는지
    		int ppl = 0;
    		for(int i = 0; i < N; i++) {
    			ppl += arr[i] / mid;
    		}
    		
    		// ppl이 K보다 크거나 같으면 제외
    		// (mid + 1)을 한다는 건 mid에 해당하는 게 아예 아니라는 뜻이다
    		// 이렇게하면 st가 같은 숫자 구간 맨 오른쪽인덱스 +1이 된다. 이상인걸 제외했으니가
    		if(ppl >= K) st = mid + 1;
    		else if(ppl < K) ed = mid;
    	}
    	
    	System.out.println(st - 1);
    	
    	
    }
}

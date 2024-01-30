import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 가로세로 크기 N인 배열 A[i][j] = i×j
    	// 이 수를 일차원 배열 B에 넣고 오름차순 정렬했을 때, B[k]
    	long N = sc.nextLong();
    	long k = sc.nextLong();

    	// 1 2 3 4
    	// 2 4 6 8
    	// 3 6 9 12
    	// 4 8 12 16
    	
    	// 오른쪽 아래로 갈수록 커진다
    	// k가 n - 1줄까지의 합보다 크고, n줄까지의 합보다 작거나 같으면
    	// n 줄에서 "k - (n-1)줄까지의합" 번째로 작은 수다
    	// n줄에서는 좌표의 합이 n + 1이다
    	
    	// n줄까지의 합 구하기
    	// n이 N이하인 경우 -> n(n+1)/2
    	// n이 N초과인 경우 -> 2N - n으로 보정 
    	// 					N(N+1)/2 + [2N - n]를 N+1부터 n까지 더하기 

    	// ----> 이거 말고
    	// 구구단의 원리를 이용하면
    	// 1단, 2단, 3단 ... n단에서 8이하인 수는 8/n개 만큼 존재한다
    	// 3단을 생각해봤을 때 3*1, 3*2, 3*3... 이렇게 있는데
    	// 8안에 3이 2개가 들어가니까 8/3 = 2개가 8이하인 숫자의 개수다
    	// 숫자 t 이하인 수의 개수인데, 4로 예시를 들어보면
    	// 1단에서는 나누기 1을 했을 때 4개가 들어가서 1, 2, 3, 4 개다 

    	
    	// 숫자 t보다 이하인 수의 개수가 k개여야 한다
    	long st = 1;
    	long ed = N * N;

    	while(st < ed) {
    		long mid = (st + ed) / 2;
    		
    		// 숫자가 mid 이하인 숫자의 개수
    		// **최대 N*N개면 cnt 최대는 10^5^2여서 int는 안 된다
    		long cnt = 0;
    		for(int i = 1; i <= N; i++) {
    			// 그런데 한 줄에 최대 N개를 넘을 수 없다
    			cnt += Math.min(mid / i, N);
    		}
    	
    		// 숫자 : 1 2 2 3 3 4 4 4 6 6 8 9
    		// cnt : 1 3 3 5 5 8 8 8 10 10 11 12
    		// cnt가 k랑 같은 건 괜찮다
    		// cnt가 k보다 작으면 완전 제외
    		
    		// (3, 5), (4, 8)일 때 6번째를 찾는다면
    		// 작은 건 제외니까 mid가 3이 나와서 cnt가 5면 제외하고 위로 올라간다
    		// 같은 건 괜찮은데 mid가 4가 나와서 cnt가 8이면 ed를 mid로 내려준다
    		// 이걸 반복하다보면 -> 8보다는 작아서 ed가 줄어들고
    		// 				-> 5보다는 커서 st + 1 해준 값으로 시작점이 늘어나서
    		// 				-> 전복되어서 5일때 + 1로 수렴해서 while을 탈출한다

    		// 만약에 9번째를 찾고 있는데 매개변수 mid가 5라면
    		// mid가 5일 때, 5이하 숫자는 8개다
    		// 8은 9보다 작으니까 제외하고 st + 1
    		// 10일 때 9보다 크지만 제외는 아니니까 ed = mid
    		// 를 반복하면 클 때는 유지시키지만 작을 때만 제외시키고 있어서
    		// 9를 못찾고 10을 찾아서 mid가 6일 때의 결과를 도출한다
    		
    		if(cnt < k) {
    			st = mid + 1;
    		} else if(cnt >= k) {
    			ed = mid;
    		}
    		
    	}
    	
    	System.out.println(st);
    }
}

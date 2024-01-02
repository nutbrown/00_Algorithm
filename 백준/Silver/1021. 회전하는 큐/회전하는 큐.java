import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        
        // 첫번째 원소 뽑기 : 앞에서 하나 뺌
        // 왼쪽으로 한 칸 이동하기 : 앞에서 빼서 뒤로 넣어줌
        // 오른쪽으로 한 칸 이동하기 : 뒤에서 하나 빼서 앞에 넣어줌
        
        // 큐의 크기 N, 뽑아내려고 하는 개수 M
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        // 큐로 구현
        // 뽑으려고 하는 수의 인덱스 저장 + 순서대로 뽑아야
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
        	q.add(i);
        }
        
        // 이때 왼쪽 오른쪽 이동 연산의 최솟값
        int ans = 0;
        
        for(int i = 0; i  < M; i++) {
        	// 뽑아야 하는 수
        	int num = sc.nextInt();
        	
        	// 왼쪽 이동으로 숫자를 뽑기 위한 연산 횟수
        	int cnt = 0;
        	
        	// 뽑을 수가 나올 때까지
        	// 앞에서 빼서 뒤에 넣음 (왼쪽 한 칸 이동)
        	while(num != q.peek()) {
        		q.add(q.poll());
        		cnt++;
        	}
        	
            
            // 한쪽으로만 돌려봐도 된다
            // cnt로 한쪽으로 index에 있는 값이 나올 때까지 몇번 돌리는지 세면, 반대편은 N-cnt가 된다
            // 둘의 값을 비교해서 작은 값을 min에 넣으면 최솟값이 된다
            
        	// 1 2 [3] 4 5 6 이 있다고 하면
        	// 왼쪽으로 돌리면 2를 이동시키고
        	// 오른쪽으로 돌리면 4를 이동시킨다
        	// 숫자갯수 - 왼쪽이동횟수 = 오른쪽이동횟수 
        	ans += Math.min(cnt, q.size() - cnt);
        	
        	// 뽑을 수가 나왔으면 빼줌
        	q.poll();
        	
        }
        		
        System.out.println(ans);
    }
}
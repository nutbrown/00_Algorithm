import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();

        
        // 숫자 큰 거 먼저 나오는 우선순위큐
        PriorityQueue<Integer> pqReverse = new PriorityQueue<>(Collections.reverseOrder());
        // 숫자 작은 거 먼저 나오는 우선순위큐 
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 숫자가 작은 게 나오도록 우선순위 큐
        // 5개가 넘으면 숫자가 제일 작은 거를 뺀다
        // 이렇게 다 넣어보면 큰 숫자 N개가 남는다
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		pq.add(sc.nextInt());
        		
        		// N개를 넘었다면 1개 빼기
        		if(pq.size() > N) pq.poll();
        	}
        }
        
        // 출력하면 큰 숫자 N개 중에서 제일 작은 게 나온다
        System.out.println(pq.poll());
    }
}
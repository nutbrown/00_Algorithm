import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);


		// 1~2n 까지 카드
		// 놓여진 카드가 없다면, 원하는 카드 내기
		// 놓여진 카드가 있다면, 마지막 카드보다 큰 숫자가 적힌 카드
		// 낼 카드가 없으면 상대방 차례, 자리 카드 없어지기
		// 둘 중 한 명이라도 카드를 못 내면 게임 종료
		// 상대방이 가지고 있는 카드의 수.를 점수로 획득
		
		// 상근이 카드 입력
		int N = sc.nextInt();
		PriorityQueue<Integer> pq1 = new PriorityQueue<>(); 
		for(int i = 0; i < N; i++) {
			pq1.add(sc.nextInt());
		}
		
		// 근상이 카드 입력
		PriorityQueue<Integer> pq2 = new PriorityQueue<>(); 
		for(int i = 1; i <= 2 * N; i++) {
			if(!pq1.contains(i)) pq2.add(i);
		}
		
		// 놓여져있는 카드
		int cur = 0;
		
		// 둘 중 하나가 카드를 다 쓸 때까지
		while(!pq1.isEmpty() && !pq2.isEmpty()) {
			
			// 잠시 저장용 큐
			Queue<Integer> temp = new LinkedList<>();
			
			// 상근이 먼저
			// 낼 수 있는 카드 찾기
			while(!pq1.isEmpty() && pq1.peek() <= cur) {
				temp.add(pq1.poll());
			}
			
			if(!pq1.isEmpty()) {
				// 낼 수 있는 카드가 있으면 내기
				cur = pq1.poll();
			} else {
				// 낼 수 있는 카드가 없으면 카드 0으로
				cur = 0;
			}
			
			// 빼둔 카드 다시 넣기
			while(!temp.isEmpty()) {
				pq1.add(temp.poll());
			}
			
			// 상근이가 카드 없으면 끝
			if(pq1.isEmpty()) break;
			

			// 근상이 나중
			while(!pq2.isEmpty() && pq2.peek() <= cur) {
				temp.add(pq2.poll());
			}
			
			if(!pq2.isEmpty()) {
				cur = pq2.poll();
			} else {
				cur = 0;
			}
			
			while(!temp.isEmpty()) {
				pq2.add(temp.poll());
			}
			
		}
		
		// 상근이 점수
		// 근상이 점수
		System.out.println(pq2.size());
		System.out.println(pq1.size());
		
	}
}
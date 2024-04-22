import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 하루에 한 과제를 끝낼 수 있다. 마감일이 지나면 점수를 못 받는다
    	// 뒤에서부터 생각해보면 d일에는 과제마감일이 d이상인 것들만 할 수 있다
    	// -> d일에 과제마감일이 d이상인 것들 중, 점수 제일 큰 과제를 한다
    	// -> 로직이 다양한 거 같으니 문제 힌트는 무시하자(아니다 반대로하면 문제 힌트다)
    	
    	// 1  2  3  4  4  4  6
    	// 20 50 30 60 40 10 5
    	// 예시 문제 힌트 -> 30 50 40 60 5 : 185
    	// 뒤에서부터 -> 6일 5, 5일 0, 4일 60, 3일 40, 2일 50, 1일 30 : 185
    	
    	
    	// 과제 N개, 과제 저장 list, 과제 마지막날 last  
    	int N = sc.nextInt();
    	ArrayList<int[]> list = new ArrayList<>();
    	int last = 0;
    	
    	// 과제 마감일까지 남은 일수, 점수 입력
    	for(int i = 0; i < N; i++) {
			int day = sc.nextInt();
			int score = sc.nextInt();
			list.add(new int[] {day, score});
			last = Math.max(last, day);
    	}
    	
    	// 과제 마감일까지 남은 일수가 많은 순서대로, 점수가 높은 순서대로 정렬
    	Collections.sort(list, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// 점수가 같으면 마감일까지 남은 수 많은 순서대로
				if(o1[1] == o2[1]) {
					// 12를 순서대로 적으면 오름차순
					// 내림차순이니까 반대로 적기
					return o2[0] - o1[0];
				}
				
				// 아니면 점수가 높은 순서대로 내림차순
				else {
					return o2[1] - o1[1];
				}
			}
    	});
    	
    	// 우선순위 큐 - 정렬은 나중에 찾아보자
    	// PriorityQueue<Integer> pq = new PriorityQueue<>();
    	
    	
    	// 얻을 수 있는 점수
    	int sum = 0;
    	
    	// 과제 마지막날부터 날이 흘러가면서 점수 합산
    	for(int d = last; d >= 1; d--) {
    		// d일에 과제마감일이 d이상인 것들 중, 점수 제일 큰 과제를 한다
    		// 점수가 큰 순서대로 정렬해두고, 날짜가 지난 것들은 뺀다
    		
    		// 지금 몇 번째 과제를 보고 있는지
    		int idx = 0;
    		
    		// idx가 끝까지 갈 때까지 d날에 할 수 있는 과제가 있는지 확인한다
    		while(idx < list.size()) {
    			// 점수가 높은 순서대로 보는데, 마감일이 d이상이어서 할 수 있는 과제면 하기
    			if(list.get(idx)[0] >= d) {
    				// 점수 더하고
    				sum += list.get(idx)[1];
    				
    				// 뺐으니까
    				list.remove(idx);

    				// 과제를 했으면 다른 날 확인
    				break;
    			}
    			
    			// 마감일이 d보다 작아서 지금 못하는 거면, 지금 할 수 있는 거 다음 인덱스 찾기
    			else if(list.get(idx)[0] < d) {
    				idx++;
    			}
    		}
    	}
    	
    	System.out.println(sum);
    	
    }
}

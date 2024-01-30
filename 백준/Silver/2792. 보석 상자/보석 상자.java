import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 아이들의 수 N, 색상의 수 M
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    
    	// 보석 개수 입력
    	// idx 번호의 보석이 value개 있다
    	int[] gems = new int[M];
    	
    	// 제일 많은 보석 개수
    	int max = 0;
    	
    	for(int i = 0; i < M; i++) {
    		gems[i] = sc.nextInt();
    		max = Math.max(max, gems[i]);
    	}
    	
    	// 매개변수 탐색!!! 질투심을 매개변수로
    	int st = 0;
    	int ed = max;
    	
    	while(st < ed) {
    		int mid = (st + ed) / 2;
    		
    		// mid가 0이 될 수 없는데 0이 나오면
    		// st를 하나 올려준다
    		if(mid == 0) {
    			st++;
    			continue;
    		}
    		
     		// 질투심에 해당하는 학생수가 여러개일 수 있다
    		// 질투심이 작아질수록 학생이 많아지는데, 그 lowerbound 구하기
    		// 8 8 7 6 [5] 5 5 4
    		
    		// 질투심이 mid일 때 몇 명의 학생에게 주는지
    		// 이 학생의 최솟값을 구하기
    		int students = 0;
    		for(int i = 0; i < M; i++) {
    			students += gems[i] / mid;
    			if(gems[i] % mid != 0) students++;
    		}
    		//System.out.println(st + " " + ed + " "+ mid + " " + students);
    		
    		if(students > N) st = mid + 1;
    		else if(students <= N) ed = mid;
    	}
    	
    	System.out.println(st);
    	
    	
    }
}

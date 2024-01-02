import java.util.Arrays;
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
        
        // 처음 큐에서 인덱스 : 우선 구현
        int[] arr = new int[N + 1];
        for(int i = 1; i <= N; i++) {
        	arr[i] = i;
        }
        
        // 맨 앞 인덱스, 맨 뒤 인덱스, 총 개수
        int fidx = 1;
        int ridx = N;
        
        // 뽑으려고 하는 수의 인덱스 + 순서대로 뽑아야
        // 이때 왼쪽 오른쪽 이동 연산의 최솟값
        int cnt = 0;
        
        for(int i = 0; i  < M; i++) {
        	int num = sc.nextInt();
        	
        	// 1 2 [3] 4 5 6 이 있다고 하면
        	// 3 - 1 = 2만큼 앞으로 와야지 뺄 수 있고
        	// (6 - 3) + 1 = 4만큼 뒤로 가야지 뺼 수 있다
        	int idx = arr[num];
        	int moveFront = idx - fidx;
        	int moveBack = ridx - idx + 1;
        	
        	// 확인용 출력
//    		System.out.println("\nout : " + num + " " + idx);
//    		System.out.println(Arrays.toString(arr));
//    		System.out.println(fidx + " " + ridx);
//        	System.out.println(moveFront + " " + moveBack);
        	
        	// 바로 맨 앞 원소 삭제 가능하면
        	if(moveFront == 0) {
        		// 맨 앞 원소 삭제
        		arr[num] = 0;
        		fidx++;
        		continue;
        	}

        	// 다같이 인덱스 앞으로 이동
        	if(moveFront <= moveBack) {
        		for(int j = 1; j <= N; j++) {
        			// 이미 빠진 거면 안 해도 된다
        			if(arr[j] == 0) continue;
        			
        			// 인덱스가 맨 앞 인덱스보다 작아졌다면 cnt만큼 더해준다
        			arr[j] -= moveFront;
        			if(arr[j] < fidx) arr[j] += (ridx - fidx + 1);
        		}
        		// 맨 앞에 원소 삭제
        		arr[num] = 0;
        		fidx++;
        		
        		// 연산 횟수 증가 
        		cnt += moveFront;
        		
    		// 다같이 인덱스 뒤로 이동
        	} else {
        		for(int j = 1; j <= N; j++) {
        			// 이미 빠진 거면 안 해도 된다
        			if(arr[j] == 0) continue;
        			
        			// 인덱스가 맨 뒤 인덱스보다 커졌다면 cnt만큼 빼준다
        			arr[j] += moveBack;
        			if(arr[j] > ridx) arr[j] -= (ridx - fidx + 1);
        		}
        		// 맨 앞에 원소 삭제
        		arr[num] = 0;
        		fidx++;
        		
        		// 연산 횟수 증가 
        		cnt += moveBack;
        	}
        }
        
        		
        System.out.println(cnt);
    }
}

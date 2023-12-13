import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	static int[][] arr;
	static int min;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 버터 수 N
        int N = sc.nextInt();

        // 버터 개수 : 버터 좌표, 버터 높이, 버터 왼쪽, 버터 오른쪽
        arr = new int[N][4];

        // 버터가 놓인 좌표 xi와 버터의 높이 hi
        for(int i = 0; i < N; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            // 시작은 왼쪽 오른쪽 다 x좌표
        }

        // 버터 좌표로 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 걸린 시간
        min = Integer.MAX_VALUE;

        // 최대로 퍼졌을 때 좌우 값을 넣어줌
        for(int i = 0; i < N; i++) {
            arr[i][2] = arr[i][0] - (arr[i][1] - 1) / 2;
            arr[i][3] = arr[i][0] + (arr[i][1] - 1) / 2;
        }

        // 확인용 출력
//        for(int i = 0; i < N; i++) {
//        	for(int j = 0; j < 4; j++) {
//        		System.out.print(arr[i][j] + " ");
//        	}
//        	System.out.println();
//        }
        
        
        // 앞에서부터 값이 겹치는 최소값을 구함
        // 좌표1 높이11 + 좌표5 높이9인 경우
        // (-4, 6)와 (1, 9)이어서 겹친다
        // -4 -3 -2 -1 0 '1' 2 3 4  5  6
        //                1  2 3 4 '5' 6 7 8 9 
        // 이렇게 겹치는 중간에서 만난다
        // 각각 2, 4까지만 갈 수 있다
        // 1초 후에 한 번 퍼지니까 (3 - 1) 2초만 갈 수 있음
        // 1초 후에 한 번 퍼지니까 (4 - 5) 1초만 갈 수 있음
        // 높이가 낮은 걸 기준으로 시간 책정
        
        // 0 '1' 2
        //       2 3 4 '5' 6 7 8
        // 이렇게 다양한 경우가 있어서 규칙으로 파악할 수 없음
        
        // x1......a
        //     b.......x2 에서 만난다면
        // int gap = (a - b + 1) / 2
        // a - gap까지, b + gap까지 갈 수 있다
        // 시간은 (a - gap - x1) 아니면 (x2 - b - gap) 중에 작은 거
        
        // 오른쪽만 확인
        for(int i = 0; i < N - 1; i++) {
        	getMin(i, i + 1);
        }
        
        if(min == Integer.MAX_VALUE) System.out.println("forever");
        else System.out.println(min);
    }
    
    // 0 1 2 3 : 중앙좌표, 높이, 왼쪽경계, 오른쪽경계
    static void getMin(int idx1, int idx2) {
    	// 앞에 있는 오른쪽이 뒤에 있는 왼쪽 이상이라면
    	if(arr[idx1][3] >= arr[idx2][2]) {
    		// t를 1씩 증가시키면서 겹치기 전의 t를 구한다
    		// 이 t의 최소값을 구함
    		int t = 0;
    		int h1 = arr[idx1][1];
    		int right = arr[idx1][0];
    		int h2 = arr[idx2][1];
    		int left = arr[idx2][0];
    		
    		while(right < left) {
    			// 시간이 증가하면 한 칸씩 좁혀짐
    			t++;
    			if(h1 > 1) {
    				h1 -= 2;
    				right++;
    			}
    			if(h2 > 1) {
    				h2 -= 2;
    				left--;
    			}
    		}
    		// 이미 겹쳐서 while문을 돌지 않고 min은 t - 1
    		min = Math.min(min, t - 1);
    	}
    }
    
}
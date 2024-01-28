import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
    public static void main(String[] args) throws IOException {
        // 스캐너 써서 메모리초과
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 점들의 개수 N
        N = Integer.parseInt(br.readLine());

        // 만들고 싶은 직사각형의 가로길이 A, 세로길이 B
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        // 점들의 좌표 (-1,000,000,000이상 1,000,000,000이하)
        arr = new int[N][2];
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());

        	arr[i][0] = x;
        	arr[i][1] = y;
        }
        
        // 이차원 배열 정렬
        // x좌표 기준으로 정렬하고, x가 동일하면 y좌표 기준으로 정렬
        Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				
				// x좌표가 같다면 - y좌표 기준 오름차순
				if(o1[0] == o2[0]) return o1[1] - o2[1];
				// 첫번째 숫자인 x좌표 기준 오름차순
				else return o1[0] - o2[0];
			}
        });
        
        int cnt = 0;
        
        // 점 1개 (x, y)를 골랐을 때 (한 방향으로만)
        // (x, y + B)가 있는지
        // (x + A, y)가 있는지
        // (x + A, y + B)가 있는지
        for(int i = 0; i < N; i++) {
        	int x = arr[i][0];
        	int y = arr[i][1];
        	
        	if(binarySearch(x, y + B) && binarySearch(x + A, y) && binarySearch(x + A, y + B)) cnt++;
        }
        
        System.out.println(cnt);
    }
    
    static boolean binarySearch(int targetX, int targetY) {
    	int st = 0;
    	int ed = N - 1;
    	
    	while(st < ed) {
    		int mid = (st + ed) / 2;
    		
    		// st mid target ed
    		// mid가 타겟보다 작으면 제외
    		if(arr[mid][0] < targetX || (arr[mid][0] == targetX && arr[mid][1] < targetY)) {
    			st = mid + 1;
    		}
    		// st target mid ed
    		// mid가 타겟보다 크거나 같으면 ed를 내려준다
    		else {
    			ed = mid;
    		}
    	}
    	
    	// 결과로 나온 st가 맞는지
    	if(arr[st][0] == targetX && arr[st][1] == targetY) return true;
    	else return false;
    }
}

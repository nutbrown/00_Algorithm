import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int t = 0; t < T; t++) {

            // 점 개수 N
            int N = sc.nextInt();
            int[] arr = new int[N];
            for(int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);

            // 간격이 같은 점 개수
            int cnt = 0;

            // 두 점 arr[i], arr[j]가 주어졌을 때
            // 오른쪽에서 간격이 같은 점을 찾는다
            for(int i = 0; i < N - 2; i++) {
                for(int j = i + 1; j < N - 1; j++) {
                    int st = j + 1;
                    int ed = N - 1;
                    int target =  arr[j] + (arr[j] - arr[i]);

                    while(st < ed) {
                    	// 이걸 밖으로 빼놨던데 왜 그랬을까 과거의 나는?
                    	int mid = (st + ed) / 2;
                    	
                    	// mid가 target보다 작으면 제외
                        if(arr[mid] < target) {
                            st = mid + 1;
                            
                        // mid가 target보다 크거나 같으면 ed를 내려준다
                        } else if(arr[mid] >= target){
                            ed = mid;
                        }
                    }
                    
                    // 이분탐색 결과로 나온 st가 target이면 찾기 성공 
                    if(arr[st] == target) cnt++;
                }
            }

            System.out.println(cnt);
        }
    }
}

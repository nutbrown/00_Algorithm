import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        // 두 개 수를 합했을 때 0에 가까운
        // 두 수를 구해서 출력한다

        // 왼쪽 오른쪽 포인터
        int left = 0;
        int right = N - 1;
        int idx1 = left;
        int idx2 = right;

        // 최솟값과 수 2개
        int min = arr[left] + arr[right];

        while(left < right) {
            int sum = arr[left] + arr[right];
            // 최솟값 갱신
            if(Math.abs(sum) < Math.abs(min)) {
                min = sum;
                idx1 = left;
                idx2 = right;
            }
            // 왼쪽이 오른쪽으로 오면 더하는 수가 늘어서 합이 커진다
            // 오른쪽이 왼쪽으로 오면 더하는 수가 줄어서 합이 작아진다

            // 합이 0에 가깝길 원하니까
            // 합이 0보다 크다면 오른쪽을 움직임
            // 합이 0보다 작다면 왼쪽을 움직임
            if(sum > 0) right--;
            else left++;

        }

        System.out.println(arr[idx1] + " " + arr[idx2]);

    }
}

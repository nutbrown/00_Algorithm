import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// n번째 반복없는 수를 구하기
		// 1 <= n <= 1,000,000
		
		// 완전탐색 : 1,000,000개가 될 때까지 다 구하기
		ArrayList<Integer> list = new ArrayList<>();
		
		// i가 반복하는 수인지 확인
		int i = 1;

		// 0부터 9까지 숫자 체크
		int[] arr = new int[10];
		
		while(list.size() < 1000000) {
			// i가 반복되는 수가 아니라면 리스트에 입력
			int temp = i;
			
			// 0으로 초기화
			// 이렇게하지 않고 new int[]로 초기화하면 메모리초과
			Arrays.fill(arr, 0);
			
			// 각 자릿수 체크 : 마지막 한 자리 숫자를 나누기 10하면 0이 됨
			while(temp != 0) {
				// 숫자가 중복되었으면 아웃
				if(arr[temp % 10] == 1) {
					break;
				}
				else {
					arr[temp % 10] = 1;
					temp /= 10;
				}
			}
			
			// 반복되는 수가 아니라면 숫자 추가
			if(temp == 0) list.add(i);
			
			// 다음 i 탐색
			i++;
		}

		
		while(true) {
			// 입력값이 0일 경우 종료 
			int n = sc.nextInt();
			if(n == 0) break;
			
			System.out.println(list.get(n - 1));
		}
	}
}

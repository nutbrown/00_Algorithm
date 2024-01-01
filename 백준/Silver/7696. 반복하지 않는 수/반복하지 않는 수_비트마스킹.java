import java.util.ArrayList;
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

		// 비트연산자
		// 논리 연산자 ㅣ -> 논리합. 두 비트 중 하나가 1이여도 1이다
		// 논리 연산자 & -> 논리곱. 두 비트가 모두 1일 경우에만 1이다
		// 이동 연산자 << -> 정수 a의 각 비트를 b만큼 왼쪽으로 이동
		
		// 1 << 3 이면 ->  1000이됨 (8이지만 비트 자릿수가 중요)
		// 1 << 4 라면 -> 10000이됨
		// 그동안 3이랑 4가 나왔으면 -> visit는 11000

		// 새로운 숫자가 3이 나와서 겹친다면
		// 3 & visit는 1000, 11000중에서 모두 1일 경우 1인건데
		// 3이 겹치니까 1000이 되어서 0이 아님
		
		// 새로운 숫자가 5가 나와서 안 겹친다면
		// 5 & visit는 100000, 11000중에서 모두 1일 경우 1인건데
		// 하나도 안 겹치니까 0이 됨

		// 기존 visit에 새로 나온 숫자를 추가함
		// 5 | visit하면 100000, 11000중에서 하나라도 1이면 1이니까
		// 111000으로 추가됨 -> 3, 4, 5가 나온 걸 알 수 있음
		
		// 0부터 9까지 숫자 체크
		int[] arr = new int[10];
		
		while(list.size() < 1000000) {
			// i가 반복되는 수가 아니라면 리스트에 입력
			int num = i;
			
			// 비트연산자로 나온 숫자 저장
			int visit = 0;
			
			// 각 자릿수 체크 : 마지막 한 자리 숫자를 나누기 10하면 0이 됨
			while(num != 0) {
				// 그 자리의 숫자
				int digit = num % 10;
				
				// 3이면 쉬프트연산해서 1000으로
				int mask = 1 << digit;
				
				// 숫자가 중복되었으면 아웃
				// 기존 visit에 해당 숫자가 없다면 0이 나옴
				if ((visit & mask) != 0) {
					break;
				}
				
				else {
					// 기존 visit에 새로 나온 숫자 추가
					visit |= mask;
					num /= 10;
				}
			}
			
			// 반복되는 수가 아니라면 숫자 추가
			if(num == 0) list.add(i);
			
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

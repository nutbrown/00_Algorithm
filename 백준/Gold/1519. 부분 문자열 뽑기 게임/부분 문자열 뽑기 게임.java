import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int N;
	static int[] winNumber;
	static int min;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 백트래킹으로 디피 문제 2:30~3:30
		// 게임이론이 처음이어서 로직이 지저분하다
		
		// 게임판 수 N
		N = sc.nextInt();
		
		// 게임이론을 처음 풀어본다
		// 1번이 109에서 10을 고르면 99, 2번이 99에서 90을 고르면 9, 1번은 고를 수 없다
		// -> 99를 받는 사람은 이긴다
		// 1번이 109에서 9를 고르면 100, 2번이 100에서 1을 고르면 99,
		// -> 1번은 99를 받으니까 이긴다

		// 이 winNumber를 받았을 때 이기면 1, 지면 0
		winNumber = new int[N + 1];
		Arrays.fill(winNumber, -1);
		
		// 첫 턴을 가지는 플레이어가 이기기 위해서 골라야 하는 수
		// 가장 작은 것 출력, 못 이기면 -1 출력
		// winNumber[N]이 0이면 처음 플레이어는 못 이긴다
		// 0이 아니라면 진 부분 문자열 중에서 제일 작은 수 출력한다
		min = Integer.MAX_VALUE;
		
		if(recur(N) == 0) System.out.println(-1);
		else System.out.println(min);
		
	}
	
	
	// 현재 게임판의 수가 뭔지, 이 수를 받는 사람이 이기는지 지는지 반환한다 
	static int recur(int num) {
		// num의 진부분문자열을 빼서 상대에게 줬을 때 
		// 한 자리 수가 나와서 상대를 지게 할 수 있는 게 1개라도 있으면
		// 상대를 지게 만들 수 있고, 나는 이길 수 있다
		
		// 이미 구해둔 수면 바로 반환한다
		if(winNumber[num] != -1) return winNumber[num];
		
		// 숫자가 한 자리수면 지는거여서 0을 반환한다
		if(num / 10 == 0) return winNumber[num] = 0;
		
		// 숫자 num을 문자로 변환
		String str = String.valueOf(num);
				
		// 길이는 1부터 num 길이 전까지인데
		// 다를 때는 빨리 내려가고 싶으니까 긴 수부터 해야하는데
		for(int i = str.length() - 1; i >= 1; i--) {
					
			// 앞에서부터 길이대로 자르고
			for(int j = 0; j + i <= str.length(); j++) {
				String slice = str.substring(j, j + i);

				// 맨 앞에 숫자가 0이 아니면 확인
				if(slice.charAt(0) != '0') {
					
					// 양의 정수 M을 골랐을 때 상대방한테 주는 숫자가
					// 받으면 지는 숫자가 1개라도 있으면, 이 숫자 받으면 이기니까 1
					if(recur(num - Integer.parseInt(slice)) == 0) {
						
						// num이 N이 아니면 바로 반환한다
						if(num != N) {
							return winNumber[num] = 1;
						}
						
						// num이 N이면 고를 수 있는 수 중에서 최솟값을 찾고
						// 끝까지 다 해본다
						else if(num == N) {
							min = Math.min(min, Integer.parseInt(slice));
						}
					}
				}
			}
		}
		
		// 이길 수 있어도 min이 N이면 여기까지 온다
		// min이 초기화되어 있으면 이길 수 있다는 거니까
		if(num == N && min != Integer.MAX_VALUE) return winNumber[num] = 1;
		
		// 모든 부분문자열을 상대에게 줘봐도 다 상대가 이기면
		// 이 숫자를 받으면 내가 지는 거니까 0이다
		return winNumber[num] = 0;
	}
	
}

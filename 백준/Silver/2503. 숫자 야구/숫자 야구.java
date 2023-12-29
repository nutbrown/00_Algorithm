import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 숫자 123 ~ 987까지 다 확인
		// 케이스 N이 최대 100개
		int N = sc.nextInt();
		int[][] cmd = new int[N][5];
		
		for(int i = 0; i < N; i++) {
			int q = sc.nextInt();
			cmd[i][2] = q % 10;
			q /= 10;
			cmd[i][1] = q % 10;
			q /= 10;
			cmd[i][0] = q % 10;
			
			cmd[i][3] = sc.nextInt();
			cmd[i][4] = sc.nextInt();
		}
		
		// 가능성 있는 답의 수
		int ans = 0;
		
		for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 9; j++) {
				// 똑같은 수면 안 됨
				if(i == j) continue;
				for(int k = 1; k <= 9; k++) {
					// 똑같은 수면 안 됨
					if(i == k || j == k) continue;
					
					// 스트라이크 볼 맞는지 확인
					boolean flag = true;
					
					// 완전탐색
					for(int m = 0; m < N; m++) {
						int strike = 0;
						int ball = 0;
						
						// 각 자리마다 스트라이크인지 볼인지 확인
						if(i == cmd[m][0]) strike++;
						else if(i == cmd[m][1] || i == cmd[m][2]) ball++;
						if(j == cmd[m][1]) strike++;
						else if(j == cmd[m][0] || j == cmd[m][2]) ball++;
						if(k == cmd[m][2]) strike++;
						else if(k == cmd[m][0] || k == cmd[m][1]) ball++;
						
						// 스트라이크 볼 갯수가 다 같아야 함
						if(strike != cmd[m][3] || ball != cmd[m][4]) {
							flag = false;
							break;
						}
					}
					
					// 답이 된다면 카운트 추가
					if(flag) {
						ans++;
						//System.out.println(i +  " " + j + " " + k + " " + ans);
					}
				}
			}
		}
		
		System.out.println(ans);
	}	
}
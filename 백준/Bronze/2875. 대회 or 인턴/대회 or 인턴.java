import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N명의 여학생, M명의 남학생, K명은 인턴십에 참여해야 함
		// 인턴십에 참여하면 대회를 못 나가고
		// 대회는 2명의 여학생과 1명의 남학생이 팀을 이뤄서 나감
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();

		// 완전탐색 : 팀을 1개씩 늘려봄
		int team = 0;
		while(true) {
			// 여자 2명 뺄 수 있고, 남자 1명 뺄 수 있고, K명이 남는다면
			if(N - 2 >= 0 && M - 1 >= 0 && N + M - 3 >= K) {
				N -= 2;
				M -= 1;
				// 팀 하나 이뤄줌
				team++;
			} else {
				break;
			}
		}
		
		System.out.println(team);
		
	}
}

import java.util.Scanner;

public class Main {
	static int N;
	static int M;
	static int[][] arr;
	static int maxSongs;
	static int cntGuitar;
	static int[] songs;
	
	// 무슨 생각으로 테스트케이스 하나도 안 넣어봤는지
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 기타의 개수 N, 곡의 개수 M
		N = sc.nextInt();
		M = sc.nextInt();
		
		// 0번부터 N-1번의 기타가
		// 0번부터 M-1번의 곡을 연주할 수 있는지
		arr = new int[N][M];
		
		// 기타의 이름, 기타가 연주할 수 있는 곡의 정보
		for(int i = 0; i < N; i++) {
			
			// 기타 이름 날리기
			String str = sc.next();

			// 곡 연주 정보
			str = sc.next();
			
			for(int j = 0; j < M; j++) {
				char ch = str.charAt(j);
				if(ch == 'Y') arr[i][j] = 1;
			}
		}
		
		
		// 기타를 1개부터 N개까지 고를 때
		// 최대한 많은 곡을 연주할 수 있는, 필요한 기타의 최소 개수
		maxSongs = 0;
		cntGuitar = -1;
		
		// 해당 노래를 연주할 수 있는 기타 개수
		songs = new int[M];
		
		for(int i = 1; i <= N; i++) {
			
			combination(0, 0, 0, i);
		}
		
		System.out.println(cntGuitar);
		
	}
	
	
	static void combination(int depth, int idx, int cnt, int total) {
		
		// 기타를 total개 골랐으면
		if(depth == total) {
			
			// 최대 곡수 갱신
			if(cnt > maxSongs) {
				maxSongs = cnt;
				cntGuitar = total;
				
				// 만약에 최대 곡수가 M개면 그만하게 하면 좋겠다
			}
			return;
		}
		
		// 고르는 기타 idx가 N이 되면 안 된다
		if(idx >= N) return;

		
		// idx 기타를 고르고, 연주할 수 있는 곡 표시
		for(int i = 0; i < M; i++) {
			
			// 기타가 연주할 수 있으면, 있다고 표시
			if(arr[idx][i] == 1) {
				songs[i]++;

				// 증가했는데, 이미 가능한 기타가 없던 거면
				if(songs[i] == 1) cnt++;
			}
			
		}
		combination(depth + 1, idx + 1, cnt, total);

		// 돌아오면 원상복귀
		for(int i = 0; i < M; i++) {
			if(arr[idx][i] == 1) {
				
				songs[i]--;
				if(songs[i] == 0) cnt--;
			}
		}

		// idx 기타 안 고르고 넘어가기
		combination(depth, idx + 1, cnt, total);
		
	}
}

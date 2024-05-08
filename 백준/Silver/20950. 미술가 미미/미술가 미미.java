import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static int[] gom;
	static int min;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 물감의 개수 N
		N = sc.nextInt();
		
		// 물감의 R, G, B값
		arr = new int[N][3];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 3; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// 곰두리색의 R, G, B 값
		gom = new int[3];
		for(int i = 0; i < 3; i++) {
			gom[i] = sc.nextInt();
		}
		
		// 곰두리색에 제일 가까운 문두리색 찾기
		// 최대 7개의 물감을 고를 수 있고, 물감을 1개만 고르는 건 안 된다 
		min = Integer.MAX_VALUE;
		
		// N개 중에서 최대 7개 고르기
		combination(0, 0, new int[] {0, 0, 0});
		
		System.out.println(min);
 	}

	static void combination(int depth, int cnt, int[] rgb) {
		
		// 물감을 7개 골랐으면 diff를 구하고 되돌아가기
		// depth가 끝까지 갔는데 2개이상 골랐으면 diff 구하고 되돌아가기
		if(cnt == 7 || depth == N) {
			if(cnt >= 2) {
				int diff = 0;
				for(int i = 0; i < 3; i++) {
					diff += Math.abs(gom[i] - rgb[i] / cnt);
				}
				
				min = Math.min(min, diff);
			}
			
			return;
		}
		
		
		
		// depth 물감 고르고 내려가기
		combination(depth + 1, cnt + 1,
				new int[] {rgb[0] + arr[depth][0], rgb[1] + arr[depth][1], rgb[2] + arr[depth][2]});
		
		// depth 물감 안 고르고 내려가기
		combination(depth + 1, cnt, rgb);
		
	}
}

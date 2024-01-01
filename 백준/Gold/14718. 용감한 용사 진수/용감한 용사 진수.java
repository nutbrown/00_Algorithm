import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N명의 병사 수, 이겨야 할 K명의 병사 수
		// 적 병사보다 힘, 민첩, 지능이 크거나 같아야 함
		// K명의 병사를 이길 수 있는 최소한의 스탯 포인트
		int N = sc.nextInt();
		int K = sc.nextInt();
		int min = Integer.MAX_VALUE;
		
		int[][] stats = new int[N][3];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 3; j++) {
				stats[i][j] = sc.nextInt();
			}
		}
		
		// K명의 병사를 이긴다고 하면
		// 진수의 총 스탯포인트는 : K명 중에서 최대힘 + 최대민첩 + 최대지능이다
		// 따라서 진수의 힘,민첩,지능 스탯은 병사들의 힘,민첩,지능 스탯 중 하나를 가져온다

		// 어떤 병사의 스탯을 가져올 지
		// 완전탐색 : 모든 병사의 조합을 확인한다
		// 진수가 가질 수 있는 모든 스탯의 경우의 수를 확인
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					// 이제 진수의 스탯은
					// i번째 병사의 힘, j번째 병사의 민첩, k번째 병사의 지능이다
					int s1 = stats[i][0];
					int s2 = stats[j][1];
					int s3 = stats[k][2];
					
					// 이렇게 진수의 스탯이 각각 정해졌을 때
					// N명의 병사를 모두 확인해서
					// 진수가 이길 수 있는 병사의 수가 K이상인지 확인
					int cnt = 0;
					for(int a = 0; a < N; a++) {
						if(stats[a][0] <= s1 && stats[a][1] <= s2 && stats[a][2] <= s3) {
							cnt++;
						}
					}
					
					// 이길 수 있는 병사수가 K이상이면
					// 이 스탯포인트는 통과
					// 이중에서 최솟값을 구하기
					if(cnt >= K)
					min = Math.min(min, s1 + s2 + s3);
				}
			}
		}
		
		System.out.println(min);
	}
}
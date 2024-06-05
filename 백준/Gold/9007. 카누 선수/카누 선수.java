import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			
			// 보트의 특정 값 K, 각 반의 학생수 N
			int K = sc.nextInt();
			int N = sc.nextInt();
			
			// 4명이 노 젓는 C4
			// 몸무게는 1 ~ 10,000,000
			int[][] arr = new int[4][N];
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			// 스터디 해설 -> 앞2개, 뒤2개 모든 경우의 수 한 뒤 투포인터
			int[] front = new int[N * N];
			int[] back = new int[N * N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// (i * N) + j 번째 (제로인덱스)
					front[i * N + j] = arr[0][i] + arr[1][j];
					back[i * N + j] = arr[2][i] + arr[3][j];
				}
			}

			Arrays.sort(front);
			Arrays.sort(back);
			//System.out.println(Arrays.toString(front));
			//System.out.println(Arrays.toString(back));
			
			
			// 선수들의 몸무게 합이 특정 값 K에 근사하도록
			// 근사값이 여러 개면, 더 작은 값으로 -> 총합 출력
			int sum = Integer.MAX_VALUE;
			int diff = Integer.MAX_VALUE;

			
			// st는 제일 작게, ed는 제일 크게 시작
			int st = 0;
			int ed = N * N - 1;
			
			// K를 경계로 합이 위아래인 경우를 다 해봐서 최솟값을 찾아야한다
			// st는 제일 작게, ed는 제일 크게 시작해서, 다시 반대로 돌아가지 않는다
			// st를 올리면 합이 커지고, ed를 내리면 합이 작아진다
			
			// 합 < K 이면, 합이 K보다 커지도록 st를 올린다
			// K < 합 이면, 합이 K보다 작아지도록 ed를 내린다
			// st나 ed가 범위를 벗어나면 끝
			while(ed >= 0 && st < N * N) {
				
				// st랑 ed일 때 더한 값
				int tempSum = front[st] + back[ed];
				
				// 차이 최솟값 갱신
				if(Math.abs(tempSum - K) < diff) {
					diff = Math.abs(tempSum - K);
					sum = tempSum;
				}
				
				// 차이가 같으면 sum이 작은 걸로
				else if(Math.abs(tempSum - K) == diff) {
					sum = Math.min(sum, tempSum);
				}
				
				// 아직 제대로 이해한 거 같진 않다
				//System.out.println(front[st] + " " + back[ed] + " " + tempSum);

				// 투포인터!
				// 합이 K보다 작으면 K보다 커지게 st를 늘리자
				if(tempSum < K) st++;
				
				// 합이 K보다 크면 K보다 작아지게 ed를 줄이자
				else if(tempSum > K) ed--;
				
				// 합이 K랑 같으면 여기서 아웃인건가
				// 만약에 차이가 0이면 그만해도 된다
				if(diff == 0) break;
			}
			
			
			System.out.println(sum);
		}
	}
}
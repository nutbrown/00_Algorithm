import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] A;
	static int[][] B;
	static int[][] C;
	static int[][] D;
	static int[][] E;
	static int max;
	static boolean[] used;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// ABCDE 역할군에 각각 한 명 정하기
		// 후보자 N명 <= 20,000
		N = sc.nextInt();

		// 실력은 0~1000 정수
		A = new int[N][2];
		B = new int[N][2];
		C = new int[N][2];
		D = new int[N][2];
		E = new int[N][2];
		for(int i = 0; i < N; i++) {
			A[i][0] = i;
			B[i][0] = i;
			C[i][0] = i;
			D[i][0] = i;
			E[i][0] = i;
			A[i][1] = sc.nextInt();
			B[i][1] = sc.nextInt();
			C[i][1] = sc.nextInt();
			D[i][1] = sc.nextInt();
			E[i][1] = sc.nextInt();
		}
		
		// N명을 순열로 5명 뽑기 - N^5여서 완전탐색 안 된다
		// 검증은 나중에 하고 우선 감으로
		// ABCDE 각각 내림차순으로 정렬하고
		// 제일 최댓값을 고르는 방식으로 진행, 겹치면 다른 거
		Arrays.sort(A, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});
		Arrays.sort(B, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});
		Arrays.sort(C, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});
		Arrays.sort(D, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});
		Arrays.sort(E, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});
		
		// 최대 실력의 합 구하기
		max = 0;
		
		// 0~N-1 후보자를 사용했는지
		used = new boolean[N];
		
		// 그래도 여전히 순열이다
		permutation(0, 0);

		System.out.println(max);
			
	}
	
	
	public static void permutation(int depth, int sum) {
		
		// 5명 다 골랐으면 최댓값 갱신하고 돌아가기
		if(depth == 5) {
			max = Math.max(max, sum);
			return;
		}
		
		// 각 부문에서 최댓값을 가진 사람들이 겹치더라도
		// 부문이 5개여서 각각 5인덱스를 넘어서 볼 필요가 없다
		for(int i = 0; i < 5; i++) {
			
			if(depth == 0) {
				// i번째 큰 사람을 이미 사용했으면, 다른 사람 고르기
				if(used[A[i][0]]) continue;

				// depth부문에 i번째로 큰 사람을 고르기
				used[A[i][0]] = true;
				permutation(depth + 1, sum + A[i][1]);
				
				// 돌아오면 원상복귀
				used[A[i][0]] = false;
			}

			else if(depth == 1) {
				if(used[B[i][0]]) continue;
				used[B[i][0]] = true;
				permutation(depth + 1, sum + B[i][1]);
				used[B[i][0]] = false;
			} else if(depth == 2) {
				if(used[C[i][0]]) continue;
				used[C[i][0]] = true;
				permutation(depth + 1, sum + C[i][1]);
				used[C[i][0]] = false;
			} else if(depth == 3) {
				if(used[D[i][0]]) continue;
				used[D[i][0]] = true;
				permutation(depth + 1, sum + D[i][1]);
				used[D[i][0]] = false;
			} else if(depth == 4) {
				if(used[E[i][0]]) continue;
				used[E[i][0]] = true;
				permutation(depth + 1, sum + E[i][1]);
				used[E[i][0]] = false;
			}
		}
	}
	
	
	
}
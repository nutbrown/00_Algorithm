import java.util.Scanner;

public class Main {
	static int[][] A;
	static int min;
	static int[][] sum;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 배열 A
		A = new int[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				A[i][j] = sc.nextInt();
			}
		}
		
		// 매직 스퀘어로 변경하는 비용의 최솟값
		// 매직 스퀘어는 1부터 9까지 숫자가 한 번씩 나오고
		// 각 줄의 합이 15가 되어야 한다
		min = Integer.MAX_VALUE;
		
		// 				0 1 2 -> 몫이0
		// 				3 4 5 -> 몫이1
		// 				6 7 8 -> 몫이2
		// 	   나머지가 -> 0 1 2
		// 오른쪽 아래 대각선 -> 4로 나눴을 때 나머지 0
		// 왼쪽 아래 대각선 -> 2로 나눴을 때 나머지 0인데, 0 8 아닌 거
		// 0행에 3개, 1행에 3개, 2행에 2개 담기
		sum = new int[3][3];
		
		// 잘 안 떠오르고 3x3니까 수 9개 순서대로 고르기 완전탐색
		visited = new boolean[10];
		permutation(0, 0);
		
		System.out.println(min);
	}
	
	
	static void permutation(int depth, int cost) {
		
		// depth가 3, 6이어서 가로 한 줄을 마치고 왔는데
		// 그 줄의 합이 15가 아니면 안 된다
		if(depth == 3 || depth == 6) {
			if(sum[0][depth / 3 - 1] != 15) return;
		}
		
		// depth가 7, 8이어서 세로 한 줄을 마치고 왔는데
		// 그 줄의 합이 15가 아니면 안 된다
		if(depth == 7 || depth == 8) {
			if(sum[1][depth % 3 - 1] != 15) return;
		}
		
		// depth가 7이어서 왼쪽 대각선이 완성 됐는데
		// 그 줄의 합이 15가 아니면 안 된다
		if(depth == 7) {
			if(sum[2][1] != 15) return;
		}
		
		// depth가 9여서 다하고 왔는데
		// 걔가 포함된 3줄의 합이 15가 아니면 안 된다
		if(depth == 9) {
			if(sum[0][2] != 15 || sum[1][2] != 15 || sum[2][0] != 15) return;
			
			// 합이 15임을 다 통과했으면 비용 최솟갮 갱신
			min = Math.min(min, cost);
			return;
		}
		
		
		
		// depth 0부터 8까지 숫자 1부터 9까지를 넣어본다
		for(int i = 1; i <= 9; i++) {
			// 앞에서 쓴 숫자면 다른 숫자
			if(visited[i]) continue;
			
			// 숫자를 고르고 그에 맞는 sum을 갱신시켜준다
			// 몫은 영어로 quotient, 나머지는 영어로 remainder
			
			// 가로 세로
			sum[0][depth / 3] += i;
			sum[1][depth % 3] += i;
			
			// 오른쪽 아래 대각선, 왼쪽 아래 대각선
			if(depth % 4 == 0) sum[2][0] += i;
			if(depth % 2 == 0 && depth != 0 && depth != 8) sum[2][1] += i;
			
			// 수를 변경하는 비용 더하기
			int add = Math.abs(A[depth / 3][depth % 3] - i);
			
			// 다음 칸 숫자 정하러 내려가기
			visited[i] = true;
			permutation(depth + 1, cost + add);
			
			
			// 돌아오면 원상복귀
			visited[i] = false;
			if(depth % 4 == 0) sum[2][0] -= i;
			if(depth % 2 == 0 && depth != 0 && depth != 8) sum[2][1] -= i;
			sum[0][depth / 3] -= i;
			sum[1][depth % 3] -= i;
			
			// depth 자리에 다른 숫자 넣어보러 다음 for문
		}
	}
}

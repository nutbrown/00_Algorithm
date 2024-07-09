import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 굴다리의 길이 N
		int N = sc.nextInt();
		
		// 가로등은 높이가 h면 왼쪽h + 오른쪽h를 비춘다
		// 최소한의 예산이 들 높이
		// 양 끝, 가로등 사이 최대 간격/2 중 "최댓값"이 최소높이다
		
		// 가로등의 개수 M
		int M = sc.nextInt();
		
		// 맨 왼쪽에서 시작
		int cur = sc.nextInt();
		int h = cur;
		
		for(int i = 1; i < M; i++) {
			
			// 다음 가로등 위치 입력 받고
			int next = sc.nextInt();
			
			// 가로등 사이 간격/2 로 간격 최댓값 갱신
			h = Math.max(h, (int)Math.ceil((double)(next - cur) / 2));
			
			// 현재 가로등 위치 바꾸기
			cur = next;
		}
		
		// 현재 위치에서 오른쪽 끝으로 간격 최댓값 갱신
		h = Math.max(h, N - cur);
		
		// 이분탐색 풀려고 풀었는데 이분탐색 아니다
		System.out.println(h);
	}
}
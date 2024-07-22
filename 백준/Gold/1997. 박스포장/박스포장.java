import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 장식판의 종류
		int N = sc.nextInt();
		
		// 장식판과 박스의 너비, 높이
		int W = sc.nextInt();
		int B = sc.nextInt();
		
		// 각 박스에 쌓인 장식판의 높이
		ArrayList<Integer> list = new ArrayList<>();

		// 현재 상자 모양
		int[][] box = new int[B][W];
		
		// 현재 상자의 높이
		int height = 0;
		
		for(int i = 0; i < N; i++) {
			// 장식판의 높이
			int H = sc.nextInt();
			
			// 장식판
			int[][] board = new int[H][W];
			
			for(int j = 0; j < H; j++) {
				String str = sc.next();
				
				// .은 빈공간 X는 장식판
				for(int k = 0; k < W; k++) {
					char ch = str.charAt(k);
					
					// 0행이 바닥이다
					if(ch == '.') board[H - 1 - j][k] = 0;
					else board[H - 1 - j][k] = 1;
				}
				
			}
			
			// 확인용 출력
//			for(int r = H - 1; r >= 0; r--) {
//				for(int c = 0; c < W; c++) {
//					System.out.print(board[r][c] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			// 그냥 전체 비교하는데 - 시간초과나면 비트마스킹
			// 비교 시작점을 상자 0행부터 B-H행까지
			boolean pass = true;
			for(int j = 0; j <= B - H; j++) {
				
				// 시작할 때 true로 두고 시작
				pass = true;
				
				// 상자 0행부터 H-1행까지 가능한지 확인
				boxloop: 
				for(int r = 0; r < H; r++) {
					for(int c = 0; c < W; c++) {
						// 장식판이 1인데 상자도 1이면 안 된다
						if(board[r][c] == 1 && box[j + r][c] == 1) {
							pass = false;
							break boxloop;
						}
					}
				}
				
				// 만약에 이때 가능 true면 채워준다
				// 아니면 다음행에서부터 다시 해보기
				if(pass) {
					// 상자입력
					for(int r = 0; r < H; r++) {
						for(int c = 0; c < W; c++) {
							if(board[r][c] == 1) box[j + r][c] = 1;
						}
					}
					
					// 상자 높이 갱신
					height = Math.max(height, j + H);
					
					// 그리고 그만
					break;
				}
				
			}
			
			// 모든 시작점 확인했는데 안 된다면
			// 새로운 상자에 넣기
			if(!pass) {
				// 기존 상자 높이 기록하고
				list.add(height);
				
				// 상자 리셋
				box = new int[B][W];
				height = 0;
				
				// 상자입력
				for(int r = 0; r < H; r++) {
					for(int c = 0; c < W; c++) {
						if(board[r][c] == 1) box[r][c] = 1;
					}
				}
				height = H;
				
			}
			
			// 확인용 출력
//			for(int r = B - 1; r >= 0; r--) {
//				for(int c = 0; c < W; c++) {
//					System.out.print(box[r][c] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
		}
		
		// 마지막에 상자 높이 넣기
		list.add(height);
		
		// 장식판의 높이들을 출력
		for(int h : list) {
			System.out.print(h + " ");
		}
	}
}
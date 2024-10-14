import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 상근이 집에서, 맥주 한 박스 20개를 들고 출발
		// 50미터에 한 병씩 마신다
		// 편의점에 들리면 빈병은 버리고 새 맥주를 살 수 있다
		
		// 편의점 가면 맥주를 다 채울 수 있지. 1000미터 갈 수 있다
		// 시작점에서 편의점 건너뛰기로 도착할 수 있는지
		// visited로 한 번 방문한 건 방문 안 하기
		// 전체를 돌면서 1000미터 이내면 넘어가기, 도착하면 끝
		
		// 테스트 케이스 t개
		int T = sc.nextInt();
		loop:
		for(int t = 0; t < T; t++) {
			
			
			// 맥주를 파는 편의점 개수 N
			int N = sc.nextInt();
			
			// 아직 방문하지 않은 편의점 리스트
			ArrayList<int[]> list = new ArrayList<>();
			
			// 상근이 집
			int stX = sc.nextInt();
			int stY = sc.nextInt();
			
			// 편의점 좌표
			for(int i = 0; i < N; i++) {
				list.add(new int[] {sc.nextInt(), sc.nextInt()});
			}
			
			// 페스티벌 좌표
			int edX = sc.nextInt();
			int edY = sc.nextInt();
			
			
			// 도착하는지 BFS
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {stX, stY});
			
			while(!q.isEmpty()) {
				// 현재 점
				int cx = q.peek()[0];
				int cy = q.poll()[1];
				
				// (편의점 건너뛰기에 페스티벌을 넣어도 되겠다)
				// 도착 편의점에서 페스티벌 갈 수 있으면, 다음 테스트케이스
				if(Math.abs(cx - edX) + Math.abs(cy - edY) <= 1000) {
					System.out.println("happy");
					continue loop;
				}
				
				// 아직 방문하지 않은 것 중에서 1000미터 이내 있으면 가기
				// 방문하면서 뺄 꺼니까, 뒤에서부터
				for(int i = list.size() - 1; i >= 0; i--) {
					int nx = list.get(i)[0];
					int ny = list.get(i)[1];
					
					if(Math.abs(cx - nx) + Math.abs(cy - ny) <= 1000) {
						// 이건 객체 그대로 옮기고 빼도 되지만...안전하게 새로?
						q.add(list.get(i));
						list.remove(i);
					}
					
				}
			}
			
			
			
			System.out.println("sad");
		}
		
	}
}
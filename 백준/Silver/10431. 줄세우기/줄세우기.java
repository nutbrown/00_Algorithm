import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 자기 앞에 키 큰 학생이 없으면 맨 뒤에 선다
		// 자기 앞에 키 큰 학생이 한 명 이상 있으면
		// 키 큰 학생 중 제일 앞 학생 바로 앞에 서고, 나머지는 뒤로 물러선다
		
		int P = sc.nextInt();
		for(int p = 0; p < P; p++) {
			
			// 테스트케이스 번호
			int test = sc.nextInt();
			
			// 몇 번 물러서는지
			int cnt = 0;

			// 본인이 제일 큰 수가 아니면 다들 물러난다
			// 앞에 4명을 제끼면서 갔으면 4번 물러선 것
			LinkedList<Integer> list = new LinkedList<>();
			
			// 20개의 양의 정수
			for(int i = 0; i < 20; i++) {
				int h = sc.nextInt();
				
				// 제일 크면 뒤에 붙이기
				if(list.isEmpty() || list.get(list.size() - 1) < h) {
					list.add(h);
					continue;
				}
				
				// 제일 큰 수가 본인보다 크면 끼어든다
				for(int j = list.size() - 1; j >= 0; j--) {
					
					// [j]가 h보다 크면 걔가 뒤로 물러나고 앞으로 가야한다
					if(list.get(j) > h) {
						cnt++;
						
					// 아니면 그 뒤에 끼어들고 끝
					} else {
						list.add(j + 1, h);
						break;
					}
					
					// 인덱스0 보다 커서 여기 왔다면
					// 0에 끼어들기
					if(j == 0) {
						list.add(0, h);
					}
				}
				
			}
			
			System.out.println(test + " " + cnt);
		}
		
	}
}
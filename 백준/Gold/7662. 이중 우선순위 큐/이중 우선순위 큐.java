import java.util.Scanner;
import java.util.TreeMap;

public class Main {
public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = Integer.parseInt(sc.next());
		for(int t = 0; t < T; t++) {

			// 트리맵은 기본적으로 오름차순 정렬
			// TreeMap<key, value> map = new TreeMap<>();
			// treemap.firstKey() : 제일 작은 수
			// treemap.lastKey() : 제일 큰 수
			// 트리맵의 value 값은 그 수(key)의 개수를 나타낸다
			TreeMap<Integer, Integer> Q = new TreeMap<>();
			
			// 이중 우선순위 큐
			// 데이터 삽입 : I n은 n을 Q에 삽입
			// 데이터 삭제 : D 1은 Q에서 최댓값을 삭제
			// 데이터 삭제 : D -1은 Q에서 최솟값을 삭제
			// 최댓값 최솟값이 하나 이상이면 하나만 삭제
			
			// 정수 범위는
			// -2147483648 이상, 2147483648 미만
			
			// 연산 개수
			int K = Integer.parseInt(sc.next());
			
			for(int k = 0; k < K; k++) {
				char cmd = sc.next().charAt(0);
				int num = Integer.parseInt(sc.next());

				// 데이터 삽입
				// 넣으려는 숫자 키 삽입 또는 1 증가
				if(cmd == 'I') {
					Q.put(num, Q.getOrDefault(num, 0) + 1);
					
				}
				// I가 아니어서 D여서 삭제라면
				else {
					// 비어있으면 그냥 넘어감
					if(Q.isEmpty()) continue;
					
					// 데이터 최댓값 삭제
					// 오름차순 정렬이니까 마지막 키가 최댓값
					int tgt = num == 1 ? Q.lastKey() : Q.firstKey();
					
					// 1개 있으면 삭제
					if(Q.get(tgt) == 1) Q.remove(tgt);
					// 1개 이상 있으면 1개 줄이기
					else Q.put(tgt, Q.get(tgt) - 1);
					
				}
				//System.out.println(Q.keySet().toString());
			} // 연산 끝
			
		// 다하고 출력
		if(Q.isEmpty()) {
            System.out.println("EMPTY");
		} else {
			// =====================================
			// 아유 !!!!!!!!!! print하고 줄바꿈을 안 했네
			// =====================================
			System.out.println(Q.lastKey() + " " + Q.firstKey());
		}
			
		} // 테스트 케이스 끝
	}
}
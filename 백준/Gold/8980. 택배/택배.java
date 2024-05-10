import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static int max;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		 
		// 마을의 개수 N, 트럭의 용량 C
		int N = sc.nextInt();
		int C = sc.nextInt();
		
		// 박스 정보
		// 박스를 보내는 마을번호, 박스를 받을 마을번호, 보낼 박스의 개수 입력
		int M = sc.nextInt();
		
		// r마을에서 c마을에 보내는 상자의 개수로 저장(해시맵 배열)
		// map[from].put(to, map[from].getOrDefault(to, 0) + amount);
		
		int[][] boxes = new int[M][3];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < 3; j++) {
				boxes[i][j] = sc.nextInt();
			}
		}
		
		// 보내는 마을이, 받는 마을이 가까운 순 정렬
		Arrays.sort(boxes, new Comparator<int[]>() {
			
			public int compare(int[] o1, int[] o2) {
				if(o1[0] != o2[0]) return o1[0] - o2[0];
				else return o1[1] - o2[1];
			}
		});
		
		// 최대한 많은 박스들을 배송하려고 한다.
		int sum = 0;
		
		// 빨리 내릴 수 있는 것부터 실어야한다. 트럭이 잠깐 비더라도 상자 개수가 중요하니까
		// 1마을에서 2마을 3마을 4마을 상자를 싣고 2마을에 도착했으면
		// 2마을 기준으로 다시 빨리 내일 수 있는 상자 수를 갱신한다
		// 기존에 가져왔던 상자는 애초에 싣지 않은 척 버린다
		
		// 트럭에 실은 상자의 도착지에 따른 개수
		int[] truck = new int[N + 1];
		
		// 트럭 용량
		int truckN = 0;
		
		// 현재 상자 인덱스
		int idx = 0;
		
		// 1마을부터 시작
		for(int i = 1; i <= N; i++) {
			
			// 도착했으면 현재 마을에서 내릴 상자를 내린다
			if(truck[i] > 0) {
				sum += truck[i];
				truckN -= truck[i];
				truck[i] = 0;
			}
			
			// 기존 트럭에 있는 상자들, 현재 마을에서 실을 수 있는 상자들을 모아서
			// 새로 정렬하고 넘어가면 편할텐데, 시간초과가 걱정되니까, 그냥 하자
			int[] temp = new int[N + 1];

			// 기존 트럭에 있던 상자 넣기
			// 현재 마을 이후로 찾기
			for(int j = i + 1; j <= N; j++) {
				
				if(truck[j] > 0) {
					temp[j] += truck[j];
					truckN -= truck[j];
					truck[j] = 0;
				}
				
				// 모든 상자를 빼서 temp에 넣었으면 그만
				if(truckN == 0) break;
			}
			
			// 현재 마을에서 출발하는 상자 싣기
			while(idx < M) {
				
				// idx에서 보내는 마을이 현재 마을이면
				if(boxes[idx][0] == i) {
					temp[boxes[idx][1]] += boxes[idx][2];
					idx++;
				}
				
				// idx에서 보내는 마을이 현재마을보다 뒤면 나가고
				else if(boxes[idx][0] > i) break;
				
				// idx에서 보내는 마을이 현재마을보다 이전이면 idx를 증가시켜서 계속
				else if(boxes[idx][0] < i) idx++;
			}

			
			// 트럭 용량이 다 끝날 때까지 가까운 것부터 트럭에! 상자 싣기
			for(int j = i + 1; j <= N; j++) {
				
				// 트럭 용량 다 채웠으면 끝
				if(truckN == C) break;
				
				// 이 목적지 박스가 없으면 다음꺼
				if(temp[j] == 0) continue;
				
				// 남은 용량이 실으려는 상자수 이하면, 남은 용량만큼 싣고 끝
				if(C - truckN <= temp[j]) {
					truck[j] += C - truckN;
					
					// 트럭 남은 용량은 없고
					truckN = C;
					
					// 이 마을에서 더이상 실을 건 없다
					break;
				}
				
				// 남은 용량이 실으려는 상자수 초과이면,
				else {
					// 있는 상자를 다 넣고
					truck[j] += temp[j];

					// 트럭 용량 증가시키고
					truckN += temp[j];
				}
				
			}
		}
		
		
		
		System.out.println(sum);
	}
}

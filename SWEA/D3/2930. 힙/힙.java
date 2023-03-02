
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	
   // 최대힙은 부모 노드 >= 자식 노드
	// 연산 1. 자연수 x를 삽입
	// 연산 2. 최대 힙의 루트 노드의 키 값을 출력하고, 해당 노드를 삭제
	
	// x의 부모 노드 번호 = x / 2
	// x의 왼쪽 자식 노드 = x * 2
	// x의 오른쪽 자식 노드 = x * 2 + 1
	
	// 최대힙 이진트리 나타내는 배열
	static int[] arr;
	// 이진트리 마지막 인덱스
	static int lastIdx;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 테스트 케이스
		int T = sc.nextInt();
		for(int t = 0; t < T; t++) {
			// 제발 string builder는 안에 선언해
			StringBuilder sb = new StringBuilder();
			
			// 연산 개수
			int N = sc.nextInt();
			// 연산이 N개이므로 트리는 최대 N개 노드다
			arr = new int[N + 1];
			
			// 트리의 마지막 인덱스는
			// 노드가 하나도 없을 때 0으로 시작한다
			lastIdx = 0;
			
			for(int i = 0; i < N; i++) {
				// 연산 종류
				int type = sc.nextInt();
				
				// ============= 연산 1. 자연수 x를 삽입 =============
				if(type == 1) {
					// 최대 힙에 추가하는 수
					int num = sc.nextInt();
					// 최대힙 맨 마지막에 수를 추가한다
					arr[++lastIdx] = num;
					
					// 최대힙이므로 부모 노드랑 비교해서
					// 해당 노드가 부모 노드보다 크면 바꿔준다 swap
					// 직전에 있는 인덱스부터 위에 있는 부모랑 비교한다
					// 레벨을 하나씩 올려주고 루트에 도착하면 멈춘다
					for(int j = lastIdx; j > 1; j /= 2) {
						// 자식이 부모보다 크면
						if(arr[j] > arr[j / 2]) {
							swap(j, j / 2);
						} else {
							break;
						}
					}
					
					
				}
				
				// ======== 연산 2. 최대 힙의 루트 노드를 츌력하고 삭제 ========
				if(type == 2) {

					// 루트 노드를 출력한다
					// 만약 루트 노드가 없으면 -1 출력
					if(lastIdx == 0) {
						sb.append("-1" + " ");
						
					// 루트 노드가 있으면 삭제하고 최신화
					} else {
						sb.append(arr[1] + " ");

						// 루트 노드를 삭제하고 마지막 노드를 넣는다
						arr[1] = arr[lastIdx];
						arr[lastIdx--] = 0;
						
						// 루트 노드 레벨부터 리프 노드 레벨까지 검사
						int j = 1;
						while(j <= lastIdx / 2) {
							// 왼쪽 오른쪽 자식 중 큰 노드와 바꾼다
							int nj = 0;
							
							// 자식 노드 중에서 큰 걸 찾는다
							if(arr[j * 2] >= arr[j * 2 + 1]) {
								nj = j * 2;
							} else {
								nj = j * 2 + 1;
							}
							
							// 자식이 부모보다 크면 바꾼다
							if(arr[nj] > arr[j]) {
								swap(j, nj);
								// 바꿔진 노드를 다음에 검사
								j = nj;
							// 바꿀게 없으면 탐색 중지
							} else {
								break;
							}
						}
					}

				}
			}
			
			System.out.printf("#%d %s\n", t + 1, sb);
		}
	}
	
	// 두 노드를 바꾸는 swap 함수
	// 함수를 굉장히 이상하게 작성했다
	public static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
    
}
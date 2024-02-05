import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	static int N;
	static int[][] flowers;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 꽃들의 총 개수 N (100,000개 이하)
		N = sc.nextInt();
		
		// 꽃이 피는 월, 일, 꽃이 있다.
		// 모든 꽃은 같은 해에 피어서 같은 해에 진다.
		flowers = new int[N][4];
		for(int i = 0; i < N; i++) {
			flowers[i][0] = sc.nextInt();
			flowers[i][1] = sc.nextInt();
			flowers[i][2] = sc.nextInt();
			flowers[i][3] = sc.nextInt();
		}
		
		// 피는 날만 빠르게 정렬
		Arrays.sort(flowers, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				
				// 피는 월이 다르면 피는 월이 빠르게
				if(o1[0] != o2[0]) {
					return o1[0] - o2[0];
				}
				// 피는 월이 같으면 피는 날이 빠르게
				else {
					return o1[1] - o2[1];
				}
			}
		});
		
		
		// 3월 1일부터 11월 30일까지 매일 꽃이 1가지 이상 피어있도록
		// 정원에 심는 꽃을 적게 하도록
		
		// 심는 꽃의 개수
		int cnt = 1;
		
		// 꽃을 계속 심을지 말지
		boolean flag = true;
		
		// 3월 1일 전에 꽃 중에서 제일 늦게 지는 꽃을 시작으로
		// 현재 심은 꽃 idx
		int idx = getLatestIdx(-1, 3, 1);
		
		// 만약에 없다면 꽃을 선택할 수 없다
		if(idx == -1) flag = false;
		
		while(flag) {
			// 방금 심은 idx꽃이 지는 날이 11월 30일 이후면 통과
			// **1개만 심을 수도 있으니까 위치 여기
			if(flowers[idx][2] > 11) {
				flag = true;
				break;
			}

			// 현재는 idx 꽃이 지는 날에 끝난다
			idx = getLatestIdx(idx, flowers[idx][2], flowers[idx][3]);
			// 지는 날 전에 펴서 지는 꽃이 있다면 꽃 심고 카운트 증가
			// 아니면 꽃 못 심으니까 탈락
			if(idx != -1) {
				cnt++;
			} else {
				flag = false;
				break;
			}
			
		}
		
		// 꽃을 선택할 수 없으면 0
		if(!flag) cnt = 0;
		System.out.println(cnt);
		
	}
	
	
	// idx 꽃 다음에 심을 꽃을 찾는 함수
	// 피는날이 : month.day 포함해서 이전이면서
	// 지는날이 : 그 중에서 제일 늦은 꽃을 찾는다
	static int getLatestIdx(int idx, int month, int day) {
		
		// idx 다음 꽃부터 N 꽃까지
		// most late의 뜻이 latest라니
		int latestM = 0;
		int latestD = 0;
		int latestIdx = -1;
		
		// 다음 꽃부터 탐색
		for(int i = idx + 1; i < N; i++) {
			// 시작일이 month.day 포함 이전이라면
			if(flowers[i][0] < month || (flowers[i][0] == month && flowers[i][1] <= day)) {
				
				// 제일 늦은 지는날 갱신
				if(flowers[i][2] > latestM || (flowers[i][2] == latestM && flowers[i][3] > latestD)) {
					latestM = flowers[i][2];
					latestD = flowers[i][3];
					latestIdx = i;
				}
			}
		}
		
		return latestIdx;
	}
}

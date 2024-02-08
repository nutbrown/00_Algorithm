import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static int max;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 계란 개수 N
		N = sc.nextInt();
		
		// 계란 내구도와 무개
		arr = new int[N][2];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < 2; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// 왼쪽 계란을 들고 한 번씩 다른 계란을 쳐서
		// 깰 수 있는 계란의 최대 개수
		max = 0;
		
		// 그냥 깨는 모든 경우의 수를 해본다
		// egg(무슨 계란을 들었는지, 깨진 게 몇 개인지)
		egg(0, 0);
		
		System.out.println(max);
	}
	
	
	static void egg(int idx, int cnt) {
	
		
		// 가장 오른쪽 계란을 들었으면 종료
		if(idx == N) {
			max = Math.max(max, cnt);
			return;
		}
		
		// 손에 든 계란이 깨져있으면 다음 계란 깨기
		if(arr[idx][0] <= 0) {
			egg(idx + 1, cnt);
			
			// 이 아래는 다른 계란 깨는 거니까 안 한다
			return;
		}

		// 계란이 다 깨져있어서 못 깨는지
		// 아니면 아예 안 치고 넘어가기도 한다
		boolean notCracked = false;
		
		// **오른쪽말고 양옆전체 계란들 깨기
		for(int i = 0; i < N; i++) {

			// 본인 계란을 깨면 안 된다
			if(i == idx) continue;
			
			// 상대 계란이 이미 **모두 깨져있으면 통과
			if(arr[i][0] <= 0) continue;
			
			// 상대 무게로 내 내구도 줄이기
			arr[idx][0] -= arr[i][1];
			// 내 무게로 상대 내구도 줄이기
			arr[i][0] -= arr[idx][1];
			
			// 계란이 깨졌으면 카운트 증가
			if(arr[idx][0] <= 0) cnt++;
			if(arr[i][0] <= 0) cnt++;
			
			// 깰 수 있는 계란이 있으면 true
			notCracked = true;
			
			// 다음 계란 들고 깨러 가기
			egg(idx + 1, cnt);
			
			// idx계란이 i계란 깨는 걸 다 하고 나왔으면 원상복귀
			// idx계란이 i+1계란 깨는 걸 하려고 포문 돌기
			if(arr[idx][0] <= 0) cnt--;
			if(arr[i][0] <= 0) cnt--;
			arr[idx][0] += arr[i][1];
			arr[i][0] += arr[idx][1];
			
		}
		
		// 다 깨져있어서 통과해서 여기까지 왔으면 다음 계란 깨기
		// 깰 수 있는 계란이 하나도 없을 때만 **92퍼 틀릴 때
		if(!notCracked) egg(idx + 1, cnt);
		
	}
	
}

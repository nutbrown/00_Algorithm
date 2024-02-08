import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int N;
	static int length;
	static ArrayList<Integer> btn;
	static int minCnt;
	static int minNum = 0;
	static int chosen;
	static int chosenCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 이동하려는 채널 N
		N = sc.nextInt();
		length = Integer.toString(N).length();

		// 고장난 버튼 개수 M
		int M = sc.nextInt();
		boolean[] broken = new boolean[10];
		for(int i = 0; i < M; i++) {
			broken[sc.nextInt()] = true;
		}
		
		// 고장나지 않은 버튼 리스트
		btn = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			if(!broken[i]) btn.add(i);
		}

		// 100번 채널에서 N번 채널로 이동하기 위해 최소 몇 번 눌러야 하는지
		// N번에 제일 가까운 수를 찾아라
		
		// 숫자를 안 누르고 이동했을 때 누르는 횟수
		minCnt = Math.abs(N - 100);
		
		// 누를 숫자 버튼이 없으면 출력하고 끝
		if(btn.size() == 0) {
			System.out.println(minCnt);
			return;
		}
		
		// 누를 숫자 버튼이 0 1개면 0이랑만 비교하고 끝
		if(btn.size() == 1 && btn.get(0) == 0) {
			// 0을 누르는 거 1번 + N까지 N번 이동
			int cnt = 1 + N;
			minCnt = Math.min(cnt, minCnt);
			
			System.out.println(minCnt);
			return;
		}
		
		
		// n-1자리 제일 큰 숫자랑 비교
		// 길이가 1이면 길이가 0인 걸 할 수 없다
		if(length != 1) {
			String numStr = "";
			for(int i = 0; i < length - 1; i++) {
				numStr += btn.get(btn.size() - 1);
			}
			int num = Integer.parseInt(numStr);
			int cnt = Math.abs(N - num) + (length - 1);
			minCnt = Math.min(cnt, minCnt);
		}
		
		
		// n+1자리 제일 작은 숫자랑 비교
		String numStr = "";
		
		// 0만 있는 건 처리해서 없다
		// 제일 작은 숫자가 0이고, 숫자가 2개 이상이니까 시작은 그 다음 작은 숫자
		// 제일 작은 숫자가 0이 아니면, 그냥 그 숫자로 시작한다
		if(btn.get(0) == 0) numStr += btn.get(1);
		else numStr += btn.get(0);

		// 나머지는 가장 작은 숫자로 채우기
		for(int i = 0; i < length; i++) {
			numStr += btn.get(0);
		}
		int num = Integer.parseInt(numStr);
		int cnt = Math.abs(N - num) + (length + 1);
		minCnt = Math.min(cnt, minCnt);
		
		
		// 주어진 숫자를 여러번 고를 수 있고(중복순열), 0으로 시작하면 안 된다
		// n자리 숫자 중에서 제일 가까운 수를 찾자
		chosen = 0;
		chosenCnt = 0;
		combination(length - 1);
		
		System.out.println(minCnt);
	}
	
	
	// 최적화 안 하고 완전탐색으로 N자리 수를 다 만들어서 비교
	static void combination(int depth) {
		
		// 숫자를 0 1 ... length-1 큰 자리수부터 넣고 있다
		// depth가 0이 되었다면 비교
		if(depth < 0) {
			
			// 최소 차이 갱신
			chosenCnt = Math.abs(chosen - N) + length;
			minCnt = Math.min(minCnt, chosenCnt);
			return;
		}
		
		// 숫자 중복해서 넣기
		for(int i = 0; i < btn.size(); i++) {

			// 한 자릿수가 아닌데, 첫 번째 숫자고, 0이면 -> 안 된다
			if(length != 1 && depth == length - 1 && btn.get(i) == 0) continue;
				
			// 아니라면 선택 후 다음 꺼
			chosen += btn.get(i) * Math.pow(10, depth);
			chosenCnt = Math.abs(chosen - N) + length;
			
			// 자릿수 높은 숫자부터 채우고 있어서, 점점 큰 숫자를 넣고 있어서, 숫자를 채울수록 수가 커진다
			// 만약 지금 chosen 숫자가 N보다 큰데, minCnt보다 cnt가 크다면 중지
			if(chosen > N && chosenCnt >= minCnt) {
				chosen -= btn.get(i) * Math.pow(10, depth);
				return;
			}

			// 그게 아니라면 계속 이어가기
			combination(depth - 1);
			chosen -= btn.get(i) * Math.pow(10, depth);

			// 중복순열은 여러 개 for문 돌리는 거랑 똑같다
			// 한자리로 1000을 하고 결과를 내고 돌아왔으면
			// 같은 한자리로 1001을 넣고 들어가야해서
			// 그대로 다음 i로 포문을 돌아야한다
			//combination(depth);
		}
		
		
	}
}

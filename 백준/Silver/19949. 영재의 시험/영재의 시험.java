import java.util.Scanner;

public class Main {
	static int[] arr;
	static int cnt;
	static int score;
	static int[] chosen;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 시험 정답
		arr = new int[10];
		for(int i = 0; i < 10; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 5점 이상 경우의 수
		cnt = 0;
		
		// 현재 점수랑 고른 답
		score = 0;
		chosen = new int[10];
		combination(0);
		
		System.out.println(cnt);
	}
	
	static void combination(int depth) {
		
		if(depth == 10) {
			// 점수가 5점 이상이면
			if(score >= 5) cnt++;
			return;
		}
		
		
		// 중복순열로 1부터 5까지 가능
		for(int i = 1; i <= 5; i++) {
			
			// 3개 연속되게 숫자 i로 답을 하지 않는다
			if(depth >= 2 && chosen[depth - 2] == chosen[depth - 1]
					&& chosen[depth - 1] == i) continue;
			
			// 답을 하나 고르고
			chosen[depth] = i;
			
			// 답의 점수를 합산한다
			if(arr[depth] == i) score++;
			
			// 5점 이상이 될 수 없으면, 뒤에 문제(다음 depth)는 풀 필요 없다
			// 나머지 (9-depth) 문제를 다 맞아도 5보다 작다면
			// depth가 5까지해서 남은 문제가 6789라면
			if((score + (9 - depth)) < 5) {
				// 원상복귀하고 다른 답으로 넘어가기 **return이 아니라 continue
				// 만약 이 문제에서 12345가 다 안 되면
				// 함수에 끝으로 가서 return해서 이전 문제를 다시 풀겠지
				if(arr[depth] == i) score--;
				continue;
			}
			
			// 다음 문제 번호 고르기
			combination(depth + 1);
			
			// 나오면 원상복귀
			chosen[depth] = 0;
			if(arr[depth] == i) score--;
		}
		
	}
}

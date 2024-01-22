import java.util.Scanner;

public class Main {
	static int[][] gear;
	static int[][] idx;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		// 톱니바퀴 4개 1 2 3 4
		// 톱니바퀴가 다른 극으로 닿아있으면 회전시켰을 때 둘다 회전된다
		
		// 입력 (제로X)
		// 맨 위 12시방향부터 시계방향, N극 0, S극 1
		gear = new int[5][9];
		for(int i = 1; i <= 4; i++) {
			String in = sc.next();
			for(int j = 1; j <= 8; j++) {
				gear[i][j] = Character.getNumericValue(in.charAt(j - 1));
			}
		}
		
		// 오른쪽 극, 왼쪽 극이 뭔지 (제로X / 제로O)
		idx = new int[5][2];
		for(int i = 1; i <= 4; i++) {
			idx[i][0] = 3;
			idx[i][1] = 7;
		}
		
		// 회전횟수
		int K = sc.nextInt();
		for(int i = 0; i < K; i++) {
			// 회전시킨 톱니바퀴
			int target = sc.nextInt();
			// 1은 시계방향, -1은 반시계방향
			int dir = sc.nextInt();
			
			// 시계방향, 반시계 방향 + 왼쪽 오른쪽 확인, 처음인지 아닌지
			if(dir == 1) {
				clockwise(target, true, true);
				clockwise(target, false, true);
				
				// 처음에는 양 옆 다 학인하고나서 회전
				clockwise_rotate(target);
			}
			else {
				counter_clockwise(target, true, true);
				counter_clockwise(target, false, true);
				
				counter_clockwise_rotate(target);
			}

		}
		
		
		// 점수 계산
		int score = 0;
		for(int i = 1; i <= 4; i++) {
			// 12시 방향은 (0인덱스 - 2)
			// 만약 이게 1보다 작다면 8을 더해준다
			int twelve = idx[i][0] - 2;
			if(twelve <= 0) twelve += 8;
			
			if(gear[i][twelve] == 1) score += Math.pow(2, i - 1);
		}
		System.out.println(score);
		
	}
	
	// 톱니바퀴 시계방향으로 회전(어느 톱니를, 어느 방향에서 오는 건지)
	static void clockwise(int target, boolean from_left, boolean is_first) {
		// 회전시키기 전에
		// 왼쪽에서 왔다면 오른쪽 확인
		if(from_left) {
			// 현재톱니 오른쪽이랑 오른쪽톱니 왼쪽 NS라면 -> 오른쪽 톱니 반시계 
			if(target + 1 <= 4 && gear[target][idx[target][0]] != gear[target + 1][idx[target + 1][1]]) {
				counter_clockwise(target + 1, true, false);
			}
			
		}

		// 오른쪽에서 왔다면 왼쪽 확인
		else {
			// 현재톱니 왼쪽이랑 왼쪽톱니 오른쪽이 NS라면 -> 왼쪽 톱니 반시계
			if(target - 1 >= 1 && gear[target][idx[target][1]] != gear[target - 1][idx[target - 1][0]]) {
				counter_clockwise(target - 1, false, false);
			}
		}
			
		// 처음이 아니라면 회전
		if(!is_first) clockwise_rotate(target);
		
	}
	
	// 톱니바퀴 반시계방향으로 회전(어느 톱니를, 어느 방향에서 오는 건지)
	static void counter_clockwise(int target, boolean from_left, boolean is_first) {
		// 회전시키기 전에
		// 왼쪽에서 왔다면 오른쪽 확인
		if(from_left) {
			// 현재톱니 오른쪽이랑 오른쪽톱니 왼쪽 NS라면 -> 오른쪽 톱니 시계 
			if(target + 1 <= 4 && gear[target][idx[target][0]] != gear[target + 1][idx[target + 1][1]]) {
				clockwise(target + 1, true, false);
			}
			
		}

		// 오른쪽에서 왔다면 왼쪽 확인
		else {
			// 현재톱니 왼쪽이랑 왼쪽톱니 오른쪽이 NS라면 -> 왼쪽 톱니 시계
			if(target - 1 >= 1 && gear[target][idx[target][1]] != gear[target - 1][idx[target - 1][0]]) {
				clockwise(target - 1, false, false);
			}
		}
		
		
		if(!is_first) counter_clockwise_rotate(target);

	}
	
	
	static void clockwise_rotate(int target) {
		// 1이 아니라면, 인덱스는 1씩 감소
		// 1이 맞아서, 1에서 감소해서 0이 되면 8로 바꿔줌
		idx[target][0] = idx[target][0] != 1? idx[target][0] - 1 : 8; 
		idx[target][1] = idx[target][1] != 1? idx[target][1] - 1 : 8;
	}

	
	static void counter_clockwise_rotate(int target) {
		// 8이 아니라면, 인덱스는 1씩 증가
		// 8이 맞아서, 8에서 증가해서 9가 되면 1로 바꿔줌 
		idx[target][0] = idx[target][0] != 8? idx[target][0] + 1 : 1; 
		idx[target][1] = idx[target][1] != 8? idx[target][1] + 1 : 1;
	}
	
}

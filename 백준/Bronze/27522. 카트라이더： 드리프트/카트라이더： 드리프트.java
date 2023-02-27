
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ArrayList<Player> list = new ArrayList<>();
		
		// 8명의 기록 list에 Player 객체로 추가
		for(int i = 0; i  < 8; i++) {
			String[] str1 = sc.nextLine().split(" ");
			char team = str1[1].charAt(0);
			
			String[] str2 = str1[0].split(":");
			int m = Integer.parseInt(str2[0]);
			int ss = Integer.parseInt(str2[1]);
			int sss = Integer.parseInt(str2[2]);
			
			list.add(new Player(m, ss, sss, team));
		}
		
		// 기록 빠른 순서대로 정렬
		Collections.sort(list);
		for(int i = 0; i < list.size(); i++) {
		//	System.out.println(list.get(i).sss);
		}
		
		// 1등 팀 기록
		char firstTeam = list.get(0).team;
		
		// Blue Red 기록 합산(순위대로)
		int blueSum = 0;
		int redSum = 0;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).team == 'B') {
				blueSum += i;
			} else {
				redSum += i;
			}
		}

		// 순위 합 낮은 팀이 우승
		String result = null;
		if(redSum < blueSum) {
			result = "Red";
		} else if(blueSum < redSum) {
			result = "Blue";
		
		// 순위 합이 같다면 1등 있는 팀이 우승
		} else if(redSum == blueSum) {
			if(firstTeam == 'B') result = "Blue";
			if(firstTeam == 'R') result = "Red";
		}
		
		
		System.out.println(result);
	}
}

	class Player implements Comparable<Player> {
		public int m;
		public int ss;
		public int sss;
		public char team;
		
		public Player(int m, int ss, int sss, char team) {
			this.m = m;
			this.ss = ss;
			this.sss = sss;
			this.team = team;
		}

		@Override
		public int compareTo(Player o) {
			// compareTo
			// return값
			// - 양수 : 자리를 바꿈
			// - 음수 : 그대로
			// - 0 : 그대로
			
			if(o.m != this.m) {
				return this.m - o.m;
			} else if(o.ss != this.ss) {
				return this.ss - o.ss;
			} else {
				return this.sss - o.sss;
			}
		}
		
		
	}
	

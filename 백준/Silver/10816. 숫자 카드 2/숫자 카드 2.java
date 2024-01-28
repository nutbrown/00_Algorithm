import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int[] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 숫자 카드 N개
		int N = sc.nextInt();
		
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		// 갖고 있는지 판단 M개
		int M = sc.nextInt();

		// 숫자 하나하나 이분탐색
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < M; i++) {
			int target = sc.nextInt();

			// lowerbound 숫자는 작은 수를 제외한 것 -> target과 같거나 큰 
			int lb = lowerbound(0, N, target);
			// upperbound 숫자는 작거나 같은 수를 제외한 것 -> target보다 큰
			int ub = upperbound(0, N, target);
			// 
			
			// 해당 숫자가 없으면 lb와 ub는 같다
			sb.append(ub - lb).append(" ");
			
		}

		System.out.println(sb);
	}
	
	
	// 해당 숫자의 왼쪽 인덱스 찾기
	static int lowerbound(int st, int ed, int target) {
		while(st < ed) {
			int mid = (st + ed) / 2;
			if(arr[mid] >= target) {
				ed = mid;
			}
			
			// target보다 작은 건 '제외'여서
			// st를 mid보다 1 크게 증가시킨다
			else if(arr[mid] < target) {
				st = mid + 1;
			}
		}

		// st는 target 이상인 수가 된다
		return st;
	}
	
	static int upperbound(int st, int ed, int target) {
		// 맨 끝일 때를 생각해서 0 ~ N-1인 경우 ed를 N으로 시작해야 한다 
		while(st < ed) {
			int mid = (st + ed) / 2;
			if(arr[mid] > target) {
				ed = mid;
			}
			
			// target보다 작거나 같은 건 '제외'여서
			// st를 mid보다 1 크게 증가시킨다
			else if(arr[mid] <= target) {
				st = mid + 1;
			}
		}
		
		// st는 target보다 큰 수가 된다
		return st;
	}
}
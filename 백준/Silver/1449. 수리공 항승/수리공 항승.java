import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 실버는 30분 안에 정확하게 빨리 풀기 연습 7:11~7:20

		// 물이 새는 곳의 개수 N, 테이프 길이 L
		int N = sc.nextInt();
		int L = sc.nextInt();
		
		// 테이프를 겹쳐서 붙이는 것도 가능하다
		// 물을 막을 때 그 위치 좌우 0.5는 간격을 줘야한다
		// 한꺼번에 막는 위치가 a~b라고 했을 때
		// 좌우 0.5씩을 남겨두려면 테이프 길이는 b-a+1 이상이어야 한다
		// L >= b-a+1, a-1+L >= b
		// a위치부터 테이프를 붙일 때, b는 a-1+L 이하로만 된다, 넘기면 테이프 추가
		
		// 물 새는 위치가 순서대로가 아니다
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		// 테이프의 개수와 시작점
		int cnt = 1;
		int st = arr[0];
		
		for(int i = 1; i < N; i++) {
			int point = arr[i];
			
			// 지금 테이프로 붙일 수 있으면, 다음 꺼
			if(point <= st - 1 + L) continue;
			
			// 안 되면, 새로운 테이프
			else {
				cnt++;
				st = point;
			}
		}
		
		System.out.println(cnt);
	}
}

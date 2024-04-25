import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// N명의 사람
		int N = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 같은 용량을 원하는 사람이 있으면 줄에서 뺀다
		// 어떤 용량을 빼야지, 같은 용량 연속 구간 길이가 제일 길까 최댓값
		int max = 0;
		
		// 오랜만에 다시 -> 완전탐색 먼저
		// 인덱스 1부터 끝까지를 시작점으로
		for(int i = 0; i < N; i++) {
			// 앞에서부터 연속 구간 길이 증가
			// 중간에 걸리면 걸리는 숫자 기록하고 넘어가기
			
			int num = arr[i];
			int length = 1;
			int skip = -1;
			for(int j = i + 1; j < N; j++) {
				
				if(arr[j] == num) {
					// 숫자가 같으면 길이 증가
					length++;
				} else if(skip == -1) {
					// 다른 숫자인데 아직 넘긴 숫자가 없으면
					skip = arr[j];
				} else if(arr[j] == skip) {
					// 다른 숫자인데 넘기는 숫자랑 같으면
					continue;
				} else {
					// 다른 숫자인데, 넘기는 숫자랑 다르면 안 된다
					break;
				}
			}
			max = Math.max(max, length);
		}
		
		
		System.out.println(max);
	}
}

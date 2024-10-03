import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 화살 직육면체를 정의하는 6개의 정수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[7];
		for(int i = 1; i <= 6; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 과녁 직육면체를 정의하는 6개의 정수
		st = new StringTokenizer(br.readLine());
		int[] tar = new int[7];
		for(int i = 1; i <= 6; i++) {
			tar[i] = Integer.parseInt(st.nextToken());
		}
		
		// 몇 프레임 뒤에 최초로 충돌하는지
		// 면끼리 만나면 충돌이 아니다, 겹치는 부피가 0보다 커야한다
		
		// 안 겹치는 경우 - x나 y중 한개라도 벗어나면 안 된다
		// arrhi <= tarlo || tarhi <= arrlo
		// 안 겹치는 경우 - 화살이 이미 아래에 있을 때
		// arrhi <= tarlo

		// 화살은 1프레임이 증가할 떄마다, z가 1씩 감소한다
		// 위 경우 제외하면
		// arrlo - tarhi 일 때 만나고
		// arrlo - tarhi + 1 일 때 겹친다
		// 이미 겹쳐있어서 마이너스가 나오면 0프레임으로
		
		
		// 충돌하지 않으면 -1 출력
		if(arr[2] <= tar[1] || tar[2] <= arr[1] ||
				arr[4] <= tar[3] || tar[4] <= arr[3] ||
				arr[6] <= tar[5]) {
			
			System.out.println(-1);
			return;
		}
		
		// 충돌하는 경우
		else {
			// 이미 만나있어서 마이너스면, 0으로 출력
			System.out.println(Math.max(0, arr[5] - tar[6] + 1));
		}
		
		
	}
}
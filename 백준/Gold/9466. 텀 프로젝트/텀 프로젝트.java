import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static boolean[] done;
	static boolean[] visited;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 T
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			
			// 학생 수 n
			int N = Integer.parseInt(br.readLine());
			
			// 팀 편성 완료 사람들 저장
			done = new boolean[N + 1];
			
			// 방문배열
			visited = new boolean[N + 1];

			// 팀 이룬 사람 수
			cnt = 0;

			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[N + 1];
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());

				// 자기 자신이면 팀 편성 완료
				if(arr[i] == i) {
					done[i] = true;
					cnt++;
				}
			}
			
			// 학생 i가 팀을 이룰 수 있는지 확인
			for(int i = 1; i <= N; i++) {
				if(!done[i]) dfs(i);
			}
			
			sb.append(N - cnt).append("\n");
			
		}
		System.out.println(sb);
	}
	
	// 사이클이 돌아서 이미 방문한 걸 다시 만났을 때만
	// done처리를 하고 카운트를 올려줌
	// 이러면 1 - 3 - 4 - 7 - 3 중간에 만난 사이클도 체크 가능
	public static void dfs(int i) {
		// 이미 방문했으면, 팀 편성 완료 및 인원 증가
		if(visited[i]) {
			done[i] = true;
			cnt++;
		} else {
			visited[i] = true;
		}
		
		// 다음 학생이 팀 편성 안 했으면
		int next = arr[i];
		if(!done[next]) {
			dfs(next);
		}
		
		// 돌아왔으면
		// 해당 학생 방문 체크 해제, 팀 편성 여부 체크
		visited[i] = false;
		done[i] = true;
		
		
	}
}
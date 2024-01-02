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
			
			// 팀 편성 완료한 사람들 저장
			done = new boolean[N + 1];
			
			// 방문배열
			visited = new boolean[N + 1];

			// 팀 성공 사람 수
			cnt = 0;

			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[N + 1];
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());

				// 자기 자신이면 팀 편성 + 성공 완료
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
	
	// 팀을 이루는 사람들은 4 - 7 - 6 - 4
	// 그 안에서만 순환한다
	// 즉, 한 번 팀이 안 된 사람들은 팀이 안 되고
	// 팀이 되는 사람들 사이클을 한 번 확인하면 다시 확인 안 해도 된다
	// 팀 편성 여부가 done이 결정되면 다시 해볼 필요 없다.
	
	// dfs로 하면 1 - 3 - 4 - 7 - 3 과 같이
	// 중간에 만난 사이클의 뒤쪽을 팀 성공 처리하기가 편하다
	
	// 한 번 확인했다는 visited랑
	// 팀 편성여부가 결정된 done이랑 구분해야 한다
	
	// 이미 방문했던 걸 만나면 -> 팀 성공 처리를 하는데
	// 1 - 3 - 4 - 7 - 3에서 다시 3을 만났기 때문이다
	// 다시 4를 만나면 4도 사이클의 한 부분이니까 팀 성공
	// 다시 7을 만나도 7도 사이클의 한 부분이니까 팀 성공
	// 이때 3은 팀성공으로 팀 편성 완료니까 다시 dfs에 안 넣는다
	
	
	// 사이클이 돌아서 이미 방문한 걸 다시 만났을 때만
	// done처리를 하고 성공 카운트를 올려줌
	// 이러면 1 - 3 - 4 - 7 - 3 중간에 만난 사이클도 체크 가능
	public static void dfs(int i) {
		// 이미 방문했으면, 팀 편성 완료 및 인원 증가
		if(visited[i]) {
			done[i] = true;
			cnt++;
		// 방문 안 한 거면, 우선 방문
		} else {
			visited[i] = true;
		}
		
		// 다음 학생이 팀 편성 결정이 안 났으면
		// 다시 dfs로 들어가서 확인
		int next = arr[i];
		if(!done[next]) {
			dfs(next);
		}
		
		// 돌아왔으면
		// 해당 학생 방문 체크 해제
		// 다 확인했으니까 팀 편성 여부 체크
		visited[i] = false;
		done[i] = true;
		
		
	}
}
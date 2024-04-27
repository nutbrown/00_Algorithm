import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int K;
	static int[][] A;
	static int[][] map;
	static ArrayList<int[]> trees;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		// 가로세로 N 크기의 땅, (r, c)는 1부터 시작한다
		N = Integer.parseInt(st.nextToken());
		
		// 나무 M개 심기
		M = Integer.parseInt(st.nextToken());

		// K년 뒤 살아있는 나무의 개수
		K = Integer.parseInt(st.nextToken());

		// 추가되는 양분A
		A = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 양분 정보 저장 지도
		map = new int[N + 1][N + 1];

		// 처음에 양분은 모든 칸에 5만큼 들어있다 
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				map[i][j] = 5;
			}
		}
		
		// 나무 정보 저장
		int[][] arr = new int[M][3];
		for(int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처음에 나이 작은 순서대로 정렬
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});

		// 어떤 자료구조가 제일 빠를까
		// ***자료구조보다, 삽입 삭제가 시간이 오래걸린다는 걸 알아야한다
		trees = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			trees.add(new int[] {arr[i][0], arr[i][1], arr[i][2], 1});
		}
		
		// K년이 지나면
		seasonsGoBy(0);

		// 살아있는 나무의 개수
		System.out.println(trees.size());
	}
	
	
	static void seasonsGoBy(int year) {
		
		// K년이라면 돌아간다
		if(year == K) return;
		
		// 양분으로 바꿀 죽은 나무들 : 인덱스 저장
		ArrayList<Integer> deadTrees = new ArrayList<>();

		// 새로 번식한 나무 스택
		ArrayList<int[]> newTrees = new ArrayList<>();

		
		// 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이 1 증가
		// 본인 칸의 양분을 먹는데, 나무가 여러 개면 나이가 어린 나무부터
		// 자신 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다
		for(int i = 0; i < trees.size(); i++) {
			// 나무 정보
			int r = trees.get(i)[0];
			int c = trees.get(i)[1];
			int age = trees.get(i)[2];
			int alive = trees.get(i)[3];
			
			// 나무 죽어있으면 패스
			if(alive == 0) continue;
			
			// 아무리 스택이어도, 계속 나무를 삽입했다 삭제했다 하면
			// ***시간이 굉장히 많이 걸린다
			if(map[r][c] >= age) {
				// 양분 먹을 수 있는지 확인
				// 양분 먹고
				map[r][c] -= age;
				
				// 새 스택에는 나이가 적은게 안쪽으로 오게 넣는다
				// 나이 1 증가
				trees.set(i, new int[] {r, c, age + 1, 1});
				
			} else {
				// 양분 못 먹어서 나무 죽으면 마지막 3을 0으로 바꾼다
				// ***여기를 삽입 삭제를 하지 않는다
				trees.set(i, new int[] {r, c, age, 0});
				
				// 양분으로 만들 나무들 인덱스 저장
				deadTrees.add(i);
			}
		}
		
		
		// 여름에는 죽은 나무가 양분으로 변한다
		// 죽은 나무의 나이를 2로 나눈 값이 칸에 양분으로 추가
		for(int i = 0; i < deadTrees.size(); i++) {
			int r = trees.get(deadTrees.get(i))[0];
			int c = trees.get(deadTrees.get(i))[1];
			int age = trees.get(deadTrees.get(i))[2];
			
			map[r][c] += age / 2;
		}

		
		// 가을에는 나이가 5의 배수인 나무가 번식
		// 인접한 8개의 칸에 나이가 1인 나무가 생긴다
		int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
		int[] dc = {1, 1, 0, -1, -1, -1, 0, 1};
		
		// 기존에 있는 나무들 나이가 많은 순서대로 꺼낸다
		for(int i = 0; i < trees.size(); i++) {
			// 나무 정보
			int r = trees.get(i)[0];
			int c = trees.get(i)[1];
			int age = trees.get(i)[2];
			int alive = trees.get(i)[3];
						
			// 나무 죽어있으면 패스
			if(alive == 0) continue;

			// 나이가 5의 배수면 번식시킨다. 앞에 추가한다
			if(age % 5 == 0) {
				for(int d = 0; d < 8; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 범위 안이라면 작은 나무로 추가
					if(nr <= 0 || nr > N || nc <= 0 || nc > N) continue;
					newTrees.add(new int[] {nr, nc, 1, 1});
				}
			}
		}
		
		// 새로 심은 나무들 모아있는 곳에, 기존 나무 추가
		for(int i = 0; i < trees.size(); i++) {
			// 나무 정보
			int r = trees.get(i)[0];
			int c = trees.get(i)[1];
			int age = trees.get(i)[2];
			int alive = trees.get(i)[3];
						
			// 나무 죽어있으면 패스
			if(alive == 0) continue;
			
			newTrees.add(new int[] {r, c, age, 1});
		}
				
		// 새로 만든 나무리스트를 기존 트리가 참조하게
		trees = newTrees;
		
		// 겨울에는 기계가 땅을 돌아다니면서 A[r][c]만큼 양분을 추가
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				map[i][j] += A[i][j];
			}
		}
		
		
		// 끝나면 다음해로 넘어간다
		seasonsGoBy(year + 1);
	}
}

import java.util.Scanner;

public class Main {
	static int[][] map;
	static int[][] fishes;
	static int max;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// (r, c)에 -> 어떤 물고기 있는지
    	map = new int[4][4];
    	
    	// 물고기가 -> 어떤 (r, c, d)에 있는지
    	fishes = new int[17][3];
    	
    	// 한마리의 물고기는 1~16번호와 0~7방향
    	for(int i = 0; i < 4; i++) {
    		for(int j = 0; j < 4; j++) {
    			int fish = sc.nextInt();
    			int dir = sc.nextInt();
    			
    			map[i][j] = fish;
    			fishes[fish][0] = i;
    			fishes[fish][1] = j;
    			fishes[fish][2] = dir - 1;
    		}
    	}
    	
    	
    	// 처음에 (0, 0) 물고기를 먹는다
    	int first = map[0][0];
    	
    	// 상어 위치 정보와 방향 저장
    	map[0][0] = -1;
    	fishes[0][0] = 0;
    	fishes[0][1] = 0;
    	fishes[0][2] = fishes[first][2];
    	
    	// 잡힌 물고기는 방향이 -1이다 
    	fishes[first][2] = -1;

    	// 상어가 먹을 수 있는 물고기 번호의 합 최댓값
    	max = 0;
    	
    	// 상어 움직이는 걸 기준으로 함수 실행
    	moveShark(first);
    	
    	
    	System.out.println(max);
    	
    }
    
    
    // 물고기 움직이기
    static void moveFish() {
    	
    	// 번호 작은 물고기부터 순서대로 이동
    	for(int f = 1; f <= 16; f++) {
    		// 물고기 이미 죽었으면 안 한다
    		if(fishes[f][2] == -1) continue;
    		
    		// 물고기 현재 위치랑 방향
    		int cr = fishes[f][0];
    		int cc = fishes[f][1];
    		int cd = fishes[f][2];
    		
    		// 45도로 한 바퀴 돌려본다
    		for(int d = 0; d < 8; d++) {
    			// 다음 방향
    			int nd = rotate(cd, d);
    			
    			// 이동할 다음 위치
    			int nr = cr + dr[nd];
    			int nc = cc + dc[nd];
    			
    			// 이동할 수 있으면 이동한다
    	    	// 빈칸, 다른물고기있는칸 -> 가능
    	    	// 상어, 공간밖 -> 불가능
    			if(inRange(nr, nc) && map[nr][nc] != -1) {

    				// 물고기가 있으면 자리 바꿔주기
    				if(map[nr][nc] != 0) {
    					// 거기있는 물고기 여기로 옮기기
    					int f2 = map[nr][nc];
    					map[cr][cc] = f2;
    					
    					// 거기있는 물고기 정보 바꿔주기
    					fishes[f2][0] = cr;
    					fishes[f2][1] = cc;
    				}
    				
    				// 빈칸이면 그냥 거기로 이동
    				else if(map[nr][nc] == 0) {
    					// 현재 지도에서 물고기 지우기
    					map[cr][cc] = 0;
    					
    				}
    				
    				// 이러나저러나 현재 물고기 다음 위치로 옮겨주기
    				// 이동위치 지도에 물고기 심기
    				map[nr][nc] = f;
    				
    				// 물고기 사전에서 정보 바꿔주기
    				fishes[f][0] = nr;
    				fishes[f][1] = nc;
    				fishes[f][2] = nd;
    				
    				// 다음 물고기 이동하러가기
    				break;
    			}
    		}
    		
    		// 만약에 여기까지 왔으면 이동할 수 있는 칸이 없는 것 -> 이동 안하기
    	}
    }


    
    // 상어 움직이기 (먹은 물고기 번호의 합)
    static void moveShark(int sum) {
    	
    	// 물고기가 죽은 상태로 이동시키기 전에 지도 저장 ->
    	// 그냥 1마리 죽은 상태로 이동... 복잡하게 생각하는 게 아니라 당연히
    	// 되돌아가기 전에 moveFish로 움직인 map이랑 fishes를 원상복구 해야하네
		int[][] tempMap = new int[4][4];
    	for(int t = 0; t < 4; t++) {
    		tempMap[t] = map[t].clone();
    	}
    	int[][] tempFishes = new int[17][3];
    	for(int t = 0; t < 17; t++) {
    		tempFishes[t] = fishes[t].clone();
    	}
    	
    	
    	// 상어가 이동하기 전에 물고기를 이동시킨다
    	moveFish();

    	
    	// 상어 위치
    	int cr = fishes[0][0];
    	int cc = fishes[0][1];
    	int cd = fishes[0][2];
    	
    	// 방향으로 1, 2, 3칸만 이동할 수 있다
    	for(int i = 1; i < 4; i++) {
    		
    		// ㅜㅜㅜㅜ여기에 칸 수를 설정 안 했잖아
    		int nr = cr + dr[cd] * i;
    		int nc = cc + dc[cd] * i;
    		if(!inRange(nr, nc)) continue;
    		
    		// 물고기 있는 칸으로만 이동할 수 있다
    		if(map[nr][nc] != 0) {
    			// (nr, nc)에 있는 물고기 fish를 잡아먹는다
    			int fish = map[nr][nc];

    			// 물고기 지도에서 없애고 죽이기
    			int nd = fishes[fish][2];
    			fishes[fish][2] = -1;
    			
    			// 상어 정보 업데이트
    			map[cr][cc] = 0;
    			map[nr][nc] = -1;
    			fishes[0][0] = nr;
    			fishes[0][1] = nc;
    			fishes[0][2] = nd;
    			
    			// 다음 (물고기 움직이고) 상어 움직이기
    			moveShark(sum + fish);
    			
    			// i칸 움직인 거 하고 왔으면 원상복귀
    			// 상어 정보 되돌리기
    			fishes[0][0] = cr;
    			fishes[0][1] = cc;
    			fishes[0][2] = cd;
    			map[cr][cc] = -1;
    			
    			// 죽은 물고기 되살리기
    			map[nr][nc] = fish;
    			fishes[fish][2] = nd;
    			
    			// 한 마리 먹고 들어갔다가, 나와서 그 한 마리만 원상복귀
    			// 다른 거 먹고 들어갔다가, 나와서 그 한 마리만 원상복귀
    			// 이렇게 1마리씩은 원상복구가 간단하다
    		}
    	}
    	
    	// 상어 1~3칸 이동했으면 moveFish한 지도 원상복귀한다
    	for(int t = 0; t < 4; t++) {
    		map[t] = tempMap[t].clone();
    	}
    	for(int t = 0; t < 17; t++) {
    		fishes[t] = tempFishes[t].clone();
    	}
    	
    	
    	// 여기 왔다면 더이상 이동할 수 없는 것
    	// 최댓값 확인하고 돌아간다
    	max = Math.max(max, sum);
    	
    }
    
    
    
    
    // 범위 안에 있는지 확인
    static boolean inRange(int r, int c) {
    	if(r >= 0 && r < 4 && c >= 0 && c < 4) return true;
    	else return false;
    }
    
    // 방향 반시계 45도로 n번 바꾸기
    static int rotate(int d, int n) {
    	// d의 숫자를 n만큼 증가시키는데,
    	// 8보다 작으면 그대로 두고, 8이상이 되면 -8을 해준다
    	return d + n < 8 ? d + n : d + n - 8;
    }
    
}
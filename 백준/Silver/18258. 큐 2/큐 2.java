import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //// 구현을 해보자 -> 그리고 다른 풀이를 보자
        int N = Integer.parseInt(br.readLine());
        
        // 들어오는 숫자 전체 입력
        int[] arr = new int[N];
        
        // 큐는 앞이 front 뒤가 rear
        // 맨 앞 인덱스, 맨 뒤 인덱스
        int fidx = 0;
        int ridx = -1;
        
        // 맨 뒤 숫자
        int rear = 0;
        
        // push를 하면 ridx가 하나 증가한다
        // pop을 하면 fidx가 하나 증가한다
        // fidx == ridx이면 요소가 1개 있는 거고
        // ridx < fidx이면 큐가 비어있는 것

        for(int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());

        	// 명령어 입력
            String cmd = st.nextToken();
            
            if(cmd.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                arr[++ridx] = num;
                rear = num;
            } else if(cmd.equals("pop")) {
                if(ridx < fidx) sb.append(-1).append("\n");
                else sb.append(arr[fidx++]).append("\n");
            } else if(cmd.equals("size")) {
                sb.append(ridx - fidx + 1).append("\n");
            } else if(cmd.equals("empty")) {
                if(ridx < fidx) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            } else if(cmd.equals("front")) {
                if(ridx < fidx) sb.append(-1).append("\n");
                else sb.append(arr[fidx]).append("\n");
            } else if(cmd.equals("back")) {
                if(ridx < fidx) sb.append(-1).append("\n");
                else sb.append(rear).append("\n");
            }
        }
        System.out.println(sb);
    }
}
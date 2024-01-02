import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // 숫자 입력
        int N = sc.nextInt();
        
        // 덱 처음 써봄
        Deque<Integer> deque = new LinkedList<>();
        
        // 큐는 앞이 front 뒤가 rear
        // 맨 앞 인덱스, 맨 뒤 인덱스
        int fidx = 0;
        int ridx = -1;
        
        // 맨 앞 숫자, 맨 뒤 숫자
        int front = 0;
        int rear = 0;
        
        // push를 하면 ridx가 하나 증가한다
        // pop을 하면 fidx가 하나 증가한다
        // fidx == ridx이면 요소가 1개 있는 거고
        // ridx < fidx이면 큐가 비어있는 것

        for(int i = 0; i < N; i++) {
        	// 명령어 입력
            String cmd = sc.next();
            
            // 앞에 추가할 때는 addFirst
            if(cmd.equals("push_front")) {
                int num = sc.nextInt();
                deque.addFirst(num);
            }
            
            // 뒤에 추가할 때는 addLast
            else if(cmd.equals("push_back")) {
            	int num = sc.nextInt();
            	deque.addLast(num);
        	
        	// 앞에를 빼서 반환 시 pollFirst
            } else if(cmd.equals("pop_front")) {
                if(deque.isEmpty()) sb.append(-1).append("\n");
                else sb.append(deque.pollFirst()).append("\n");
                
            // 뒤에를 빼서 반환 시 pollLast
            } else if(cmd.equals("pop_back")) {
            	if(deque.isEmpty()) sb.append(-1).append("\n");
            	else sb.append(deque.pollLast()).append("\n");
            
                
            } else if(cmd.equals("size")) {
                sb.append(deque.size()).append("\n");
                
            } else if(cmd.equals("empty")) {
                if(deque.isEmpty()) sb.append(1).append("\n");
                else sb.append(0).append("\n");
                
            // 가장 앞 정수 출력 peekFirst
            } else if(cmd.equals("front")) {
                if(deque.isEmpty()) sb.append(-1).append("\n");
                else sb.append(deque.peekFirst()).append("\n");
                
            // 가장 뒤 정수 출력 peekLast
            } else if(cmd.equals("back")) {
                if(deque.isEmpty()) sb.append(-1).append("\n");
                else sb.append(deque.peekLast()).append("\n");
            }
        }
        System.out.println(sb);
    }
}
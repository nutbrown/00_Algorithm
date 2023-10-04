import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String S = sc.next();
        String T = sc.next();

        int result = 0;

        Queue<String> q = new LinkedList<>();
        q.add(S);

        while(!q.isEmpty()) {
            String str = q.poll();

            // T가 나왔다면 끝
            if(str.equals(T)) {
                result = 1;
                break;
            }

            // str + A
            // 또는 뒤집은 게 T에 들어있으면 추가
            String addA = str + "A";
            if(T.contains(addA) || T.contains(new StringBuilder(addA).reverse())) q.add(addA);

            // 뒤집은 str + B
            // 또는 뒤집은 게 T에 들어있으면 추가
            String addB = new StringBuilder(str).reverse() + "B";
            if(T.contains(addB) || T.contains(new StringBuilder(addB).reverse())) q.add(addB);
        }

        System.out.println(result);
    }
}

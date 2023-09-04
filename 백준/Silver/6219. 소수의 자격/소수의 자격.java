import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // a부터 b까지 숫자 d를 포함하는 소수의 개수
        int a = sc.nextInt();
        int b = sc.nextInt();
        int d = sc.nextInt();

        // 소수 찾기
        boolean[] prime = new boolean[b + 1];
        // 1은 소수가 아니다
        prime[1] = true;
        for(int i = 2; i <= Math.sqrt(b); i++) {
            // 소수면 false 소수가 아니면 true
            // 소수가 아니라고 판별 난 건 하지 않음 (6은 2랑 3이 이미 했음)
            if(!prime[i]) {
                for(int j = 2; i * j <= b; j++) {
                    prime[i * j] = true;
                }
            }
        }

       //System.out.println(Arrays.toString(prime));
        int cnt = 0;
        for(int i = a; i <= b; i++) {
            // int -> string 메서드 Integer.toString()
            if(!prime[i] && Integer.toString(i).contains(Integer.toString(d))) {
                cnt++;
            }
        }

        System.out.println(cnt);

    }
}

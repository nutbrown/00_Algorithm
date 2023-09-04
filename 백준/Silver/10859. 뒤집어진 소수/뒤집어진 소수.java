import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n1 = sc.nextLong();

        // 숫자가 어떻게 뒤집히는지
        // 0 1 2 5 8 -> 0 1 2 5 8
        // 6 9 -> 9 6
        // 3 4 7 -> 숫자아님

        // 숫자 뒤집기 (문자로 바꿔서)
        String strN1 = Long.toString(n1);
        String strN2 = "";

        // 결과
        String result = "yes";

        for(int i = strN1.length() - 1; i >= 0; i--) {
            // 뒤집힌 숫자를 앞에서부터 쌓을거면
            // 처음 숫자는 뒤에서부터 찾자
            char ch = strN1.charAt(i);

            if(ch == '3' || ch == '4' || ch == '7') {
                result = "no";
                break;
            } else if(ch == '0' || ch == '1' || ch == '2' || ch == '5' || ch == '8') {
                strN2 += ch;
            } else if(ch == '6') {
                strN2 += "9";
            } else if(ch == '9') {
                strN2 += "6";
            }
        }


        // 숫자로 뒤집혔다면
        if(result.equals("yes")) {
            long n2 = Long.parseLong(strN2);

            // 1 처리
            if(n1 == 1 || n2 == 1) result = "no";

            // 원래 수가 소수인지 확인하기
            if(result.equals("yes")) {
                for(int i = 2; i <= Math.sqrt(n1); i++) {
                    if(n1 % i == 0) {
                        // 나눠져서 소수가 아니면 no
                        result = "no";
                        break;
                    }
                }
            }

            // 뒤집힌 수가 소수인지 확인하기
            if(result.equals("yes")) {
                for (int i = 2; i <= Math.sqrt(n2); i++) {
                    if (n2 % i == 0) {
                        // 나눠져서 소수가 아니면 no
                        result = "no";
                        break;
                    }
                }
            }
        }

        System.out.println(result);

    }
}

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] arr = new char[5];

        String in = sc.next();
        String r1 = sc.next();
        String r2 = sc.next();
        arr[1] = r1.charAt(0);
        arr[2] = r1.charAt(1);
        arr[3] = r2.charAt(0);
        arr[4] = r2.charAt(1);

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        // 남쪽일 경우 0 1 2 3
        if (in.equals("S")) {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
        }
        // 동쪽일 경우 2 0 3 1
        else if (in.equals("E")) {
            a = 3;
            b = 1;
            c = 4;
            d = 2;
        }
        // 서쪽일 경우 1 3 0 2
        else if (in.equals("W")) {
            a = 2;
            b = 4;
            c = 1;
            d = 3;
        }
        // 북쪽일 경우 3 2 1 0
        else if (in.equals("N")) {
            a = 4;
            b = 3;
            c = 2;
            d = 1;
        }

        if (arr[a] == '.' && arr[b] == 'O' && arr[c] == 'P' && arr[d] == '.') {
            System.out.println("T");
        } else if (arr[a] == 'I' && arr[b] == '.' && arr[c] == '.' && arr[d] == 'P') {
            System.out.println("F");
        } else if (arr[a] == 'O' && arr[b] == '.' && arr[c] == '.' && arr[d] == 'P') {
            System.out.println("Lz");
        } else {
            System.out.println("?");
        }

    }
}
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 3개로 나누기
        // 0부터 i까지, i + 1부터 j까지, j + 1부터 n - 1까지
        
        // 길이는 3이상이다
        // i는 0부터 가능
        // i는 j - 1까지 가능. 즉, i는 n - 3까지 가능
        // j는 i + 1부터 가능
        // j + 1은 n - 1까지 가능. 즉, j는 n - 2까지 가능

        // 구간 3개는
        // 0부터 i까지, i + 1부터 j까지, j + 1부터 n - 1까지(i, j 헷갈리지 않기)
        
        // 입력받고 
        String str = sc.next();
        int n = str.length();

        // 우선 완전탐색해서 정렬
        ArrayList<String> list = new ArrayList<>();
        
        for(int i = 0; i < n - 2; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                // 문자열 자르기 substring(a, b)면 a부터 b전까지
                String str1 = str.substring(0, i + 1);
                String str2 = str.substring(i + 1, j + 1);
                String str3 = str.substring(j + 1, n);
                
                // 문자열 뒤집기
                list.add(reverse(str1) + reverse(str2) + reverse(str3));
            }
        }
        
        Collections.sort(list);
        System.out.println(list.get(0));
    }
    
    public static String reverse(String input) {
        StringBuffer sb = new StringBuffer(input);
        return sb.reverse().toString();
    }
}
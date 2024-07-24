import java.util.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
        // 분자/분모 분자/분모
        int n1 = sc.nextInt();
        int d1 = sc.nextInt();
        int n2 = sc.nextInt();
        int d2 = sc.nextInt();
        
        // 분모를 곱해서 더하기
        int numerator = n1 * d2 + n2 * d1;
        int denominator = d1 * d2;
        
        // 기약분수가 되도록 나누기
        // 분자분모 중에서 작은 거부터 - 최대 900,000,000
        for(int i = Math.min(denominator, numerator); i >= 2; i--) {
            
            // 큰 수부터 계속 나눠질때까지 **계속 1로 나눠서 시간초과났다
            while(numerator % i == 0 && denominator % i == 0) {
                numerator /= i;
                denominator /= i;
            }
            
            // 둘 중에 하나가 1되면 탈출
            if(numerator == 1 || denominator == 1) break;
        }
		
        // 백준에서 바로 풀기. 한 번에 통과했으면
		System.out.println(numerator + " " + denominator);
		
	}
}
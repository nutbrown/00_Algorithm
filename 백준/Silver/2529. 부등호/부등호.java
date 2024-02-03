import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int k;
	static char[] arr;
	static long min;
	static long max;
	static String minStr;
	static String maxStr;
	static boolean[] visited;
	static int[] chosen;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 부등호 문자 개수 k (2이상 9이하)
    	k = sc.nextInt();
    	arr = new char[k];
    	for(int i = 0; i < k; i++) {
    		arr[i] = sc.next().charAt(0);
    	}
    	
    	// k개의 부등호 순서를 만족하는 k+1 정수 중에서 최댓값과 최솟값
    	// 숫자 0 1 2 3 4 5 6 7 8 9 중에서 조합을 만들고
    	// 만족하는지 확인
    	
    	min = 9876543210L;
    	max = 0;
    	minStr = null;
    	maxStr = null;
    	
    	visited = new boolean[10];
    	chosen = new int[k + 1];
    	
    	permutation(0);
    	
    	System.out.println(maxStr);
    	System.out.println(minStr);
    }
    
    // 조합을 만드는 함수 - 아니다 순열이다
    public static void permutation(int depth) {
    	
    	// k+1개를 다 골랐다면
    	if(depth == k + 1) {
    		if(check()) {
    			// 고른 숫자배열을 숫자로 변환
    			long num = arrToInt(chosen);
    			
    			// 최솟값 최댓값 갱신
    			if(num < min) {
    				min = num;
    				minStr = arrToString(chosen);
    			}
    			if(num > max) {
    				max = num;
    				maxStr = arrToString(chosen);
    			}

    		}
    		
    		return;
    	}
    	
    	// depth번째 숫자에 idx를 고른 거, 안 고른거
    	for(int i = 0; i <= 9; i++) {
    		
    		if(visited[i]) continue;
    		
    		visited[i] = true;
    		chosen[depth] = i;
    		permutation(depth + 1);
    		
    		visited[i] = false;
    	}
    	
    }

    // 조합으로 만들어진 숫자가 부등호를 만족시키는지 확인하는 함수
    public static boolean check() {
    	
    	for(int i = 0; i < k; i++) {
    		// 부등호를 만족하지 않으면 NO
    		if(arr[i] == '<') {
    			if(!(chosen[i] < chosen[i + 1])) return false; 
    			
    		} else if(arr[i] == '>') {
    			if(!(chosen[i] > chosen[i + 1])) return false; 
    		}
    	}
    	
    	// 다 통과하면 OK
    	return true;
    }
    
    
    // 숫자 배열 쌓아서 숫자로 변환
    public static long arrToInt (int[] arr) {
		String numStr = "";
		for(int n : arr) {
			numStr += n;
		}
		
		return Long.parseLong(numStr);
    }
    
    
    // 숫자 배열 쌓아서 문자로 변환
    public static String arrToString (int[] arr) {
    	// 고른 숫자배열을 숫자로 변환
    	String numStr = "";
    	for(int n : arr) {
    		numStr += n;
    	}
    	
    	return numStr;
    }
    
}

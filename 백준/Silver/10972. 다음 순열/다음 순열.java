import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	int[] nums = new int[N];
    	for(int i = 0; i < N; i++) {
    		nums[i] = sc.nextInt();
    	}
    	
    	// 작아지는 기준 숫자 (1, 3, 2라면 3에서 1넘어갈 때 작아짐)
    	int flag = -1;
    	for(int i = N - 1; i > 0; i--) {
    		// i에서 i-1로 갈 때 숫자가 작아졌다면
    		if(nums[i] > nums[i - 1]) {
    			flag = i - 1;
    			break;
    		}
    	}
    	
    	// 1. 만약에 맨 첫번째 숫자까지 안 작아지면
    	// flag는 여전히 -1
    	if(flag == -1) {
    		System.out.println(-1);
    		
    	// 2. 아니라면 flag 증가시키고 이후 오름차순
    	} else {
    		// flag 인덱스부터 마지막 숫자까지 list 추가
    		ArrayList<Integer> list = new ArrayList<>();
    		for(int i = flag; i < N; i++) {
    			list.add(nums[i]);
    		}
    		
    		// flag 인덱스는 그 다음 숫자로 교체시켜주고
    		// 나머지는 오름차순으로 입력할거다
    		Collections.sort(list);
    		
    		// flag 숫자 다음 큰 숫자 찾기
    		for(int i = 0; i < list.size(); i++) {
    			// flag 숫자가 중복일 수도 있으니까
    			// 작은 것부터 탐색하면서 flag 숫자보다 커졌을 때
    			if(list.get(i + 1) > nums[flag]) {
    				// 다음 숫자 flag 인덱스에 넣어주기
    				nums[flag] = list.get(i + 1);
    				// 숫자는 제거하기
    				list.remove(i + 1);
    				// break를 안 해서 indexoutofbound 및 뒤에 로직 오류
    				break;
    			}
    		}
    		
    		// 그 뒤는 오름차순으로 교체
    		for(int i = flag + 1; i < N; i++) {
    			nums[i] = list.get(i - (flag + 1));
    		}
    		
    		// 결과 출력
    		StringBuilder sb = new StringBuilder();
    		for(int i = 0; i < N; i++) {
    			sb.append(nums[i]).append(" ");
    		}
    		System.out.print(sb);
    	
    		// 리트코드와 동일한 코드
    	}
    }
}
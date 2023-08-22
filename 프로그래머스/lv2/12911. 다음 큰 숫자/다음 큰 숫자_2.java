import java.util.*;

class Solution {
    public int solution(int n) {
        
        // 십진수를 이진수로 변환
        int[] two = tentotwo(n);
        System.out.println(Arrays.toString(two));
        
        // 앞에서부터 탐색하는데 0다음에 1이 나온다면
        // 1을 하나 옮겨주고 나머지 1을 내린다
        // 1의 개수를 유지하면서 숫자가 증가하지만 가장 작은 수여야 함
        int idx = 0;
        for(int i = 0; i < two.length - 1; i++) {
            if(two[i] == 1 && two[i + 1] == 0) {
                // 0을 1로 바꿔주어야 할 인덱스
                idx = i + 1;
                break;
            }
        }
        
        // 1을 올려주고 나머지 1을 내려줌
        two = fillin(two, idx);
        System.out.println(Arrays.toString(two));
        
        // 이진수를 십진수로 변환
        int answer = twototen(two);
        
        return answer;
    }
    
    public int[] tentotwo(int n) {
        // 범위 찾기 limit이 3이라면 3자리 이진수
        int limit = 0;
        while(n > Math.pow(2, limit)) {
            limit++;
        }
        System.out.println(limit);
        
        // 2로 나눈 나머지를 추가
        int[] two = new int[limit + 1];
        int idx = 0;
        while(n > 0) {
            two[idx] = n % 2;
            n /= 2;
            idx++;
        }
        return two;
    }
    
    public int twototen(int[] two) {
        int ten = 0;
        for(int i = 0; i < two.length; i++) {
            if(two[i] == 1) ten += Math.pow(2, i);
        }
        return ten;
    }
    
    // idx를 1로 바꾸고 아래를 다 밀기
    public int[] fillin(int[] arr, int idx) {
        arr[idx] = 1;
        
        // 1의 개수 세기
        int num = 0;
        
        for(int i = 0; i < idx - 1; i++) {
            if(arr[i] == 1) num++;
        }
        for(int i = 0; i < idx; i++) {
            if(i < num) arr[i] = 1;
            else arr[i] = 0;
        }
        
        return arr;
    }
}

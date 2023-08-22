class Solution {
    public int solution(int n) {
        // n의 1의 개수 측정
        int nOne = Integer.bitCount(n);
        
        // n보다 큰 수부터
        int answer = n + 1;
        while(true) {
            if(Integer.bitCount(answer) == nOne) break;
            answer++;
        }
        
        return answer;
    }
}
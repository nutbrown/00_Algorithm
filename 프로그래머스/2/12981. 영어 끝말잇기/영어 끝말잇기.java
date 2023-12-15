import java.util.HashMap;

class Solution {
    public int[] solution(int n, String[] words) {
        HashMap<Integer, String> hm = new HashMap<Integer, String>();
        int player = 0;
        int turn = 0;
        
        hm.put(0, words[0]);
        for(int i = 1; i < words.length; i++) {
            if(!hm.containsValue(words[i]) && words[i - 1].charAt(words[i - 1].length() - 1) == words[i].charAt(0)) {
                hm.put(i, words[i]);
            } else {
                player = i % n + 1;
                turn = i / n + 1;
                break;
            }
        }
        int[] answer = {player, turn};
        return answer;
    }
}
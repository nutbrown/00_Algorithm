package allProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

class P_숫자짝꿍 {
    public static void main(String[] args) {
    	
    	String X = "5532";
    	String Y = "53555";
    	       
        HashMap<Integer, Character> strX = new HashMap<>();
        ArrayList<String> common = new ArrayList<>();
        String ansStr = "";
        
        for(int i = 0; i < X.length(); i++) {
            strX.put(i, X.charAt(i));
        }

        for(int i = 0; i < Y.length(); i++) {
            if(strX.containsValue(Y.charAt(i))) {
                common.add(String.valueOf(Y.charAt(i)));
            }
        }
        
        if(common.isEmpty()) {
            ansStr = "-1";
        } else {
        	Collections.sort(common, Comparator.reverseOrder());
        	ansStr = String.join("", common);
        }
        int ansInt = Integer.parseInt(ansStr);
        String answer = String.valueOf(ansInt);
       
        System.out.println(answer);
    }

}

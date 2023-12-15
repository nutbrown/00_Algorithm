class Solution {
    public String intToRoman(int num) {
        
        // 1, 2, 3은 I, II, III
        // 4는 IV
        // 5는 V
        // 6, 7, 8은 VI, VII, VIII
        // 9는 IX    -> 여기까지가 일의 자릿수
        
        // 10, 20, 30은 X, XX, XXX
        // 40은 XL
        // 50은 L
        // 60, 70, 80은 LX, LXX, LXXX
        // 90은 XC   -> 여기까지가 십의 자릿수
        
        // 100, 200, 300은 C, CC, CCC
        // 400은 CD
        // 500은 D
        // 600, 700, 800은 DC, DCC, DCCC
        // 900은 CM  -> 여기까지가 백의 자릿수
        
        // 1000, 2000, 3000은 M, MM, MMM
        //           -> 여기까지가 천의 자릿수(3999까지만)
        
        // 규칙에 쓰이는 숫자를 배열에 넣기
        // 1역할, 5역할, 그다음자리1역할
        String[][] roman = {{"I", "V", "X"}, {"X", "L", "C"}, {"C", "D", "M"}, {"M"}};

        // 반환 로마자 숫자
        String result = "";
        
        for(int i = 1; i <= 4 ; i++) {
            // 작은 자릿수부터 10으로 나눠서 숫자를 구함 + 10의 몫으로 갱신해줌
            int digit = num % 10;
            num /= 10;
            
            // i번째 자릿수를 변환중이며, 배열에서는 i - 1에 해당
            // 9
            if(digit == 9) {
                result = roman[i - 1][0] + roman[i - 1][2] + result;
            }
            // 5, 6, 7, 8
            else if(digit >= 5) {
                String tmp = "";
                tmp += roman[i - 1][1];
                for(int j = 1; j <= digit - 5; j++) {
                    tmp += roman[i - 1][0];
                }
                result = tmp + result;
            }
            // 4
            else if(digit == 4) {
                result = roman[i - 1][0] + roman[i - 1][1] + result;
            }
            // 0, 1, 2, 3
            else {
                for(int j = 1; j <= digit; j++) {
                    result = roman[i - 1][0] + result;
                }
            }
            System.out.println(result);
        }
        
        return result;
    }
}
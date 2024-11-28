import java.util.List;
import java.util.ArrayList;

class Solution {
    public int solution(String name) {
        char[] arr = name.toCharArray();
        List<Integer> xList = new ArrayList();
        int upDown = 0;
        int len = arr.length;
        
        // updown 횟수 구하기
        for(int i = 0; i < len; i++) {
            char c = arr[i];
            if(c != 'A') {
                int asciiDiff = (int)c - 65;
                upDown += Math.min(asciiDiff, Math.abs(26 - asciiDiff));
                xList.add(i);
            }
        }
        
        // leftright 횟수 구하기
        int size = xList.size(); 
        int leftRight = 0;
        
        if(size > 0) leftRight = Math.min(xList.get(size - 1), len - xList.get(0));
        for(int i = 0; i < size; i++) {
            int x = xList.get(i);
            if(x <= len / 2 && i < size - 1) {
                leftRight = Math.min(leftRight, x * 2 + (len - xList.get(i + 1)));
            } else if(x > len / 2 && i > 0){
                leftRight = Math.min(leftRight, (len - x) * 2 + xList.get(i - 1));
            }
        }
        
        return upDown + leftRight;
    }
}
class Solution {
    public int solution(String s) {
        char buffer = ' ';
        int cur = 0, rest = 0, answer = 0;
        
        for(char c : s.toCharArray()) {
            if(buffer == ' ') { 
                buffer = c;
                ++cur;
            } else if(c == buffer) {
                ++cur;
            } else { 
                ++rest;
            }
            
            if(cur == rest) {
                ++answer;
                buffer = ' ';
                cur = 0;
                rest = 0;
            }
        }
        
        if(buffer != ' ') ++answer;
        
        return answer;
    }
}
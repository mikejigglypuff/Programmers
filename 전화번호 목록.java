import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        
        // sort를 활용하면 hash 자료구조를 사용하지 않아도 됨
        Set<String> phonePrefixes = new HashSet<>();
        
        for(String s : phone_book) {
            int len = s.length();
            
            for(int i = 1; i <= len; i++) {
                String prefix = s.substring(0, i);
                if(phonePrefixes.contains(prefix)) return false;
            }
            
            phonePrefixes.add(s);
        }
        
        return true;
    }
}
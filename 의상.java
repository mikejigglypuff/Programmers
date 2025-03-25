import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> clothesCategory = new HashMap<>();
        int answer = 1;
        
        for(String[] cloth : clothes) {
            String key = cloth[1];
            int count = 1;
            if(clothesCategory.containsKey(key)) {
                count = clothesCategory.get(cloth[1]) + 1;
            }
            clothesCategory.put(cloth[1], count);
        }
        
        for(Integer i : clothesCategory.values()) {
            answer *= i + 1;
        }
        
        return answer - 1;
    }
}
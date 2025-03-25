import java.util.Set;
import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        int maximumKindCount = nums.length / 2;
        Set<Integer> phoneMons = new HashSet<>();
        
        for(int i : nums) {
            phoneMons.add(i);
        }
        
        return Math.min(phoneMons.size(), maximumKindCount);
    }
}
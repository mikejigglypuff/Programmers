import java.util.*;

class Solution {
    static Map<String, int[]> FATIGUES = new HashMap<>();
    static int MIN_FATIGUE = 1250;
    
    public int solution(int[] picks, String[] minerals) {
        FATIGUES.put("diamond", new int[]{1, 5, 25});
        FATIGUES.put("iron", new int[]{1, 1, 5});
        FATIGUES.put("stone", new int[]{1, 1, 1});
        
        dfs(picks, 0, 0, minerals);
        return MIN_FATIGUE;
    }
    
    public void dfs(int[] picks, int fatigue, int index, String[] minerals) {
        int len = minerals.length;
        
        if(index >= len || picks[0] + picks[1] + picks[2] == 0) {
            MIN_FATIGUE = Math.min(MIN_FATIGUE, fatigue);
            return;
        }
        
        for(int i = 0; i < 3; i++) {
            if(picks[i] == 0) continue;
            int count = 0, curFatigue = fatigue;

            while(count < 5 && count + index < len) {
                curFatigue += FATIGUES.get(minerals[count + index])[i];
                ++count;
            }
            --picks[i];
            dfs(picks, curFatigue, count + index, minerals);
            ++picks[i];
        }
    }
}
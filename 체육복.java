import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(reserve);
        
        boolean[] lostClothes = new boolean[n];
        for(int i : lost) {
            lostClothes[i - 1] = true;
        }
        
        boolean[] isReserved = new boolean[n];
        for(int i : reserve) {
            int cur = i - 1;
            int prev = cur - 1, next = i;
            
            isReserved[cur] = true;
            if(lostClothes[cur] == true) { continue; }
            
            if(prev >= 0 && lostClothes[prev] && !isReserved[prev]) {
                isReserved[prev] = true;
                continue;
            }
            
            if(next < n && lostClothes[next] && !isReserved[next]) {
                isReserved[next] = true;
            }
        }
        
        int answer = n;
        
        for(int i = 0; i < n; i++) {
            if(lostClothes[i] && !isReserved[i]) { --answer; }
        }
        
        return answer;
    }
}
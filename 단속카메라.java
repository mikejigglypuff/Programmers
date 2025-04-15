import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[0] - b[0]);
        int answer = 0, point = -30001;
        
        for(int[] route : routes) {
            if(route[0] > point) {
                point = route[1];
                ++answer;
            }
            point = Math.min(route[1], point);
        }
        
        return answer;
    }
}
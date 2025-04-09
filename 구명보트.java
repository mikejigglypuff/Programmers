import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int start = 0, end = people.length - 1, answer = 0;
        
        while(start < end) {
            if(people[start] + people[end] <= limit) {
                ++start;
            }
            
            ++answer;
            --end;
        }
        
        if(start == end) { ++answer; }
        
        return answer;
    }
}

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int s : scoville) {
            pq.add(s);
        }
        
        int count = 0;
        
        while(pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            if(first >= K) {
                return count;
            }
            
            pq.add(first + second * 2);
            ++count;
        }
        
        return (pq.peek() < K) ? -1 : count;
    }
}
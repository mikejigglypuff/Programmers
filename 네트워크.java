import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] isVisited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            if(isVisited[i]) { continue; }
            
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            
            while(!queue.isEmpty()) {
                int cur = queue.poll();
                isVisited[cur] = true;
                
                for(int j = 0; j < n; j++) {
                    if(cur != j && !isVisited[j] && computers[cur][j] == 1) {
                        queue.add(j);
                    }
                }
            }
            
            ++answer;
        }
        
        return answer;
    }
}
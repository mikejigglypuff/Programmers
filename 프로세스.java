import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Process> queue = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            queue.add(new Process(priorities[i], i));
        }
        
        while(!queue.isEmpty()) {
            Process cur = queue.poll();
            if(isMax(queue, cur)) {
                ++answer;
                if(cur.getLoc() == location) break;
            } else {
                queue.add(cur);
            }
        }
        
        return answer;
    }
    
    private boolean isMax(Queue<Process> queue, Process process) {
        for(Process p : queue) {
            if(!process.isPrior(p)) { return false; }
        }
        return true;
    }
    
    class Process {
        private int priority;
        private int location;
        
        public Process(int priority, int location) {
            this.priority = priority;
            this.location = location;
        }
        
        public int getPrior() { return this.priority; }
        public int getLoc() { return this.location; }
        public boolean isPrior(Process comp) { return this.priority >= comp.getPrior(); }
    }
}
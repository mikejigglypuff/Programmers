import java.util.*;

class Solution {
    class Process {
        public int start;
        public int duration;

        Process(int start, int dur) {
            this.start = start;
            this.duration = dur;
        }
    }
    
    /*
    //내 풀이
    public int solution(int[][] jobs) {
        int answer = 0, time = 0, index = 0, len = jobs.length;
        Process[] processes = new Process[len];
        for(int i = 0; i < len; i++) {
            processes[i] = new Process(jobs[i][0], jobs[i][1]);
        }
        
        Arrays.sort(processes, new Comparator<Process>() {
            public int compare(Process a, Process b) {
                return a.start - b.start;
            }
        });
        
        PriorityQueue<Process> pq = new PriorityQueue<>(new Comparator<Process>() {
            public int compare(Process a, Process b) {
                return (a.duration - b.duration);
            }
        });
        pq.add(processes[index++]);

        
        while(index < len) {
            Process p = pq.poll();
            time += p.duration;
            answer += time - p.start;
            
            while(true) {
                if(index >= len || time < processes[index].start) { break; }
                pq.add(processes[index++]);
            }
        }
        
        while(!pq.isEmpty()) {
            Process p = pq.poll();
            time += p.duration;
            answer += time - p.start;
        }
        
        return answer / len;
        
    } */
    
    
    //교재 풀이
    public int solution(int[][] jobs) {
        int answer = 0, time = 0, index = 0, len = jobs.length;
        Process[] processes = new Process[len];
        for(int i = 0; i < len; i++) {
            processes[i] = new Process(jobs[i][0], jobs[i][1]);
        }
        
        Arrays.sort(processes, Comparator.comparingInt(process -> process.start));
        
        Queue<Process> q = new LinkedList<>(Arrays.asList(processes));
        PriorityQueue<Process> pq = new PriorityQueue<>(Comparator.comparingInt(process -> process.duration));
        
        while(!q.isEmpty() || !pq.isEmpty()) {
            
            while(!q.isEmpty() && q.peek().start <= time) {
                pq.add(q.poll());
            }
            
            if(pq.isEmpty()) {
                time = q.peek().start;
                continue;
            }
            
            Process p = pq.poll();
            answer += time + p.duration - p.start;
            time += p.duration;
        }
        
        return answer / len;
        
    }
}
import java.util.*;

class Solution {
    private int length = 0;
    
    public int[] solution(String[] operations) {
        Queue<Integer> maxPQ = new PriorityQueue<>((a, b) -> { return b - a; });
        Queue<Integer> minPQ = new PriorityQueue<>((a, b) -> { return a - b; });
        
        for(String oper : operations) {
            String[] command = oper.split(" ");
            
            if(command[0].compareTo("I") == 0) {
                length++;
                maxPQ.add(Integer.parseInt(command[1]));
                minPQ.add(Integer.parseInt(command[1]));
            } else {
                if(command[1].compareTo("1") == 0) {
                    removeQueue(maxPQ, minPQ);
                } else {
                    removeQueue(minPQ, maxPQ);
                }
            }
        }
        
        int max = (length == 0) ? 0 : maxPQ.peek();
        int min = (length == 0) ? 0 : minPQ.peek();
        return new int[]{max, min};
    }
    
    private void removeQueue(Queue<Integer> maxPQ, Queue<Integer> minPQ) {
        if(length == 0) { return; }
        maxPQ.poll();
        
        if(--length == 0) {
            maxPQ.clear();
            minPQ.clear();
        }
    }
}
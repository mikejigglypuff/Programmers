import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        //교재 답안
        int bridgeWeight = 0, time = 0, truckIndex = 0;
        Queue<Integer> bridge = new LinkedList<>();
        
        for(int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }
        
        while(truckIndex < truck_weights.length) {
            bridgeWeight -= bridge.poll();
            
            int truckWeight = truck_weights[truckIndex];
            if(bridgeWeight + truckWeight <= weight) {
                bridge.add(truckWeight);
                bridgeWeight += truckWeight;
                truckIndex++;
            } else {
                bridge.add(0);
            }
            
            time++;
        }
        
        while(bridgeWeight > 0) {
            bridgeWeight -= bridge.poll();
            time++;
        }
        
        return time;
        
        /* 내 풀이
        int time = 1, index = 0, sum = 0;
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> start = new LinkedList<>();
        
        while(index < truck_weights.length) {
            int cur_weight = truck_weights[index];
            
            if(sum + cur_weight <= weight) {
                queue.add(cur_weight);
                start.add(time);
                sum += cur_weight;
                //System.out.println("start " + index + " : " + time);
                index++;
            }
            
            if(start.peek() + bridge_length == time) {
                sum -= queue.remove();
                start.remove();
                //System.out.println("end " + (index - 1) + " : " + time);
                
                if(sum + cur_weight <= weight) {
                    queue.add(cur_weight);
                    start.add(time);
                    sum += cur_weight;
                    //System.out.println("start " + index + " : " + time);
                    index++;
                }
            }
            
            time++;
        }
        
        while(!start.isEmpty()) {
            time = start.remove();
        }
        
        return time + bridge_length;
        */
    }
}
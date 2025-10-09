import java.util.*;
class Solution {
    class Node {
        public List<Integer> outputs = new ArrayList<>();
        public List<Integer> inputs = new ArrayList<>();
    }
    
    public int[] solution(int[][] edges) {
        List<Node> nodes = new ArrayList<>();
        
        int added = -1;
        int bar = 0;
        int doughnut = 0;
        int eight = 0;
        
        for(int[] edge : edges) {
            int start = edge[0] - 1;
            int end = edge[1] - 1;
            int max = Math.max(start, end);
            
            if(nodes.size() <= max) {
                for(int i = nodes.size(); i <= max; i++) {
                    nodes.add(new Node());
                }
            }
            
            nodes.get(start).outputs.add(end);
            nodes.get(end).inputs.add(start);
        }
        
        for(int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            if(n.outputs.size() < 2) { continue; }
            else if(n.inputs.size() > 0) { continue; }
            added = i;
            break;
        }
        
        Stack<Node> stack = new Stack<>();
        stack.push(nodes.get(added));
        
        boolean[] isVisited = new boolean[nodes.size()];
        
        while(!stack.isEmpty()) {
            Node cur = stack.pop();
            
            if(cur.outputs.isEmpty()) { 
                ++bar;
                continue;
            }
            else if(cur.outputs.size() == 2 && cur.inputs.size() >= 2) { 
                ++eight;
                continue;
            }
            else if(cur.outputs.size() == 1 && isVisited[cur.outputs.get(0)]) {
                ++doughnut;
                continue;
            }
            for(Integer i : cur.outputs) {
                isVisited[i] = true;
                stack.push(nodes.get(i));
            }
        }
        
        return new int[]{added + 1, doughnut, bar, eight};
    }
}
import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        boolean[][] graph = new boolean[n + 1][n + 1];
        
        for(int[] wire : wires) {
            int y = wire[0];
            int x = wire[1];
            graph[y][x] = true;
            graph[x][y] = true;
        }
        
        int answer = n;
        
        for(int[] wire : wires) {
            int y = wire[0];
            int x = wire[1];
            graph[y][x] = false;
            graph[x][y] = false;
            
            answer = Math.min(answer, Math.abs(countConnectedNodes(graph, y, n) - countConnectedNodes(graph, x, n)));
            
            graph[y][x] = true;
            graph[x][y] = true;
        }
        
        return answer;
    }
    
    private int countConnectedNodes(boolean[][] graph, int start, int n) {
        boolean[] isVisited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int count = 1;
        
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            isVisited[cur] = true;
            ++count;
            
            for(int i = 1; i <= n; i++) {
                if(!isVisited[i] && graph[cur][i] == true) {
                    queue.add(i);
                }
            }
        }
        
        return count;
    }
}
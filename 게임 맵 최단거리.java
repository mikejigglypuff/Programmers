import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[][] maps) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1});
        int yLen = maps.length, xLen = maps[0].length;
        boolean[][] isVisited = new boolean[yLen][xLen];
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            
            if(cur[0] == yLen - 1 && cur[1] == xLen - 1) { return cur[2]; }
            isVisited[cur[0]][cur[1]] = true;
            
            int depth = cur[2] + 1;
            int[][] next = new int[][]{
                {cur[0], cur[1] + 1},
                {cur[0], cur[1] - 1},
                {cur[0] + 1, cur[1]},
                {cur[0] - 1, cur[1]}
            };
            
            for(int[] yx : next) {
                if(isValid(maps, isVisited, yx)) {
                    queue.add(new int[]{yx[0], yx[1], depth});
                    isVisited[yx[0]][yx[1]] = true;
                }
            }
        }
        
        return -1;
    }
    
    private boolean isValid(int[][] maps, boolean[][] isVisited, int[] yx) {
        int y = yx[0], x = yx[1], yLen = maps.length, xLen = maps[0].length;
        return (y >= 0 && y < yLen && x >= 0 && x < xLen && maps[y][x] == 1 && !isVisited[y][x]);
    }
}
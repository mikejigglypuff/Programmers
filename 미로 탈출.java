import java.util.*;

class Solution {
    static int[][] dyx = new int[][]{
            {-1, 0}, {0, -1}, {0, 1}, {1, 0}
        };
    
    public int solution(String[] maps) {
        int height = maps.length;
        int width = maps[0].length();

        int[] start1 = findTile(maps, height, width, 'S');
        int bfs1 = bfs(start1, maps, 'L');
        
        int[] start2 = findTile(maps, height, width, 'L');
        int bfs2 = bfs(start2, maps, 'E');
        
        return(bfs1 < 0 | bfs2 < 0) ? -1 : bfs1 + bfs2;
    }
    
    private int bfs(int[] start, String[] maps, char end) {
        int height = maps.length;
        int width = maps[0].length();
        int result = -1;
        
        boolean[][] isVisited = new boolean[height][width];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start[0], start[1], 0));
        
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            if(isVisited[cur.y][cur.x]) continue;
            
            char tile = maps[cur.y].charAt(cur.x);
            if(tile == end) {
                result = cur.dis;
                break;
            }
            isVisited[cur.y][cur.x] = true; 
            
            for(int[] d : dyx) {
                int newY = cur.y + d[0];
                int newX = cur.x + d[1];
                
                if(validateNode(maps, height, width, newY, newX)) {
                    queue.add(new Node(newY, newX, cur.dis + 1, cur.lever));
                }
            }
        }
        
        return result;
    }
    
    private boolean isInbounds(int height, int width, int y, int x) {
        return (y >= 0 && y < height && x >= 0 && x < width);
    }
    
    private boolean validateNode(String[] map, int height, int width, int y, int x) {
        return isInbounds(height, width, y, x) && (map[y].charAt(x) != 'X');
    }
    
    private int[] findTile(String[] maps, int height, int width, char target) {
        int[] result = new int[]{-1, -1};
        
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(maps[i].charAt(j) == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        
        return result;
    } 
    
    class Node {
        public int y;
        public int x;
        public int dis;
        public boolean lever = false;
        
        public Node(int y, int x, int dis) {
            this.y = y;
            this.x = x;
            this.dis = dis;
        }
        
        public Node(int y, int x, int dis, boolean lever) {
            this.y = y;
            this.x = x;
            this.dis = dis;
            this.lever = lever;
        }
        
        /*
        public String toString() {
            return "y: " + y + " x: " + x + " dis: " + dis;
        }
        */
    }
}
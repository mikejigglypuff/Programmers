import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String[] board) {
        int height = board.length;
        int width = board[0].length();
        return bfs(board, getRGPos(board, height, width), height, width);
    }
    
    private int[] getRGPos(String[] board, int height, int width) {
        int[] result = new int[]{-1, -1, -1, -1};
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                char c = board[y].charAt(x);
                if(c == 'R') {
                    result[0] = y; 
                    result[1] = x;
                } else if(c == 'G') {
                    result[2] = y; 
                    result[3] = x;
                }
            }
        }
        
        return result;
    }
    
    private int bfs(String[] board, int[] startEnd, int height, int width) {
        int minMoves = -1;
        
        int[][] dyx = new int[][]{
            {-1, 0},
            {0, -1},
            {0, 1},
            {1, 0}
        };
        
        boolean[][] isVisited = new boolean[height][width];
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(startEnd[0], startEnd[1], 0));
        
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            if(cur.goalIn(startEnd[2], startEnd[3])) {
                minMoves = cur.getMoves();
                break;
            }
            
            int[] curPos = cur.getYX();
            int curY = curPos[0];
            int curX = curPos[1];
            int curMove = cur.getMoves();
            isVisited[curY][curX] = true;

            for(int[] d : dyx) {
                int[] nextPos = cur.getYX();
                while(!isCollided(nextPos[0] + d[0], nextPos[1] + d[1], height, width, board)) {
                    nextPos[0] += d[0];
                    nextPos[1] += d[1];
                }
                
                if(!isVisited[nextPos[0]][nextPos[1]]) queue.add(new Node(nextPos, curMove + 1));
            }
        }
        
        return minMoves;
    }
    
    private boolean isOutbounds(int y, int x, int height, int width) {
        return !(y >= 0 && y < height && x >= 0 && x < width);
    }
    
    private boolean isCollided(int y, int x, int height, int width, String[] board) {
        return isOutbounds(y, x, height, width) || (board[y].charAt(x) == 'D');
    }
    
    class Node {
        private int y;
        private int x;
        private int moves;
        
        public Node(int y, int x, int moves) {
            this.y = y;
            this.x = x;
            this.moves = moves;
        }
        
        public Node(int[] yx, int moves) {
            if(yx.length < 2) throw new IllegalArgumentException();
            this.y = yx[0];
            this.x = yx[1];
            this.moves = moves;
        }
        
        public int[] getYX() { return new int[]{y, x}; }
        public int getMoves() { return this.moves; }
        
        public boolean goalIn(int goalY, int goalX) {
            return this.y == goalY && this.x == goalX;
        }
        
        public String toString() {
            return "pos: [" + y + ", " + x + "], moves: " + moves;
        }
    }
}
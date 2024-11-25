import java.util.Arrays;

class Solution {
    static int RESULT = 0;
    static int[][] DYX = new int[][]{
        {-1, -1},
        {-1, 0},
        {-1, 1},
        {0, -1},
        {0, 1},
        {1, -1},
        {1, 0},
        {1, 1}
    };
    
    public int solution(int n) {
        dfs(new boolean[n][n], 0);
        return RESULT;
    }
    
    // 체스의 가로 줄을 depth라 설정
    private void dfs(boolean[][] chess, int depth) {        
        int len = chess.length;
        if(depth == len) {
            ++RESULT;
            return;
        }

        for(int j = 0; j < len; j++) {
            if(isValid(depth, j, chess, len)) {
                boolean[][] newChess = copyChess(chess, len);
                newChess[depth][j] = true;
                dfs(newChess, depth + 1);
            }
        }
    }
    
    private boolean[][] copyChess(boolean[][] chess, int len) {
        boolean[][] newChess = new boolean[len][];
        
        for(int i = 0; i < len; i++) {
            newChess[i] = Arrays.copyOf(chess[i], len);
        }
        
        return newChess;
    }
    
    private boolean isValid(int y, int x, boolean[][] chess, int len) {
        
        for(int[] d : DYX) {
            for(int i = y, j = x; i >= 0 && i < len && j >= 0 && j < len; i += d[0], j += d[1]) {
                if(chess[i][j]) return false;
            }
        }
        
        return true;
    }
}
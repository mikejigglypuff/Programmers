class Solution {
    private boolean[][] graph;
    
    public int solution(int n, int[][] results) {
        graph = new boolean[n][n];
        
        for(int[] result : results) {
            int win = result[0] - 1, lose = result[1] - 1;
            graph[win][lose] = true;
        }
        
        int answer = 0;
        for(int i = 0; i < n; i++) {
            if(count_Win(i, new boolean[n]) + count_Lose(i, new boolean[n]) - 1 == n) { answer++; }
        }
        return answer;
    }
    
    private int count_Win(int n, boolean[] isVisited) {
        int count = 1;
        
        for(int i = 0; i < graph[n].length; i++) {
            if(!graph[n][i] || isVisited[i]) continue;
            isVisited[i] = true;
            count += count_Win(i, isVisited);
        }
        
        return count;
    }
    
    private int count_Lose(int n, boolean[] isVisited) {
        int count = 1;
        
        for(int i = 0; i < graph.length; i++) {
            if(!graph[i][n] || isVisited[i]) continue;
            isVisited[i] = true;
            count += count_Lose(i, isVisited);
        }
        
        return count;
    }
}
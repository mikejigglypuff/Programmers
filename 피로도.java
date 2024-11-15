import java.util.*;

class Solution {
    static int[][] GRAPH;
    static int LEN, MAX = 0;

    public int solution(int k, int[][] dungeons) {
        GRAPH = dungeons;
        LEN = GRAPH.length;

        dfs(k, 0, new boolean[LEN]);

        return MAX;
    }

    public void dfs(int k, int depth, boolean[] isVisited) {
        int count = 0;

        for(int i = 0; i < LEN; i++) {
            int[] dungeon = GRAPH[i];
            if(!isVisited[i] && k >= dungeon[0]) {
                isVisited[i] = true;
                dfs(k - dungeon[1], depth + 1, Arrays.copyOf(isVisited, LEN));
                isVisited[i] = false;
                ++count;
            }
        }

        if(count == 0) MAX = Math.max(MAX, depth);
    }

}
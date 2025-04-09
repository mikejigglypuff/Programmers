import java.util.Arrays;

class Solution {
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        int[] parents = new int[n];
        init(parents);
        
        int answer = 0, edgeCount = 0, maxEdgeCount = n - 1;
        boolean[] isVisited = new boolean[n];
        for(int[] cost : costs) {
            if (union(cost[0], cost[1], parents)) {
                answer += cost[2];
                edgeCount++;
                if (edgeCount == maxEdgeCount) break;
            }
        }
        
        return answer;
    }
    
    private void init(int[] parents) {
        for(int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }
    
    private boolean union(int a, int b, int[] parents) {
        int rootA = find(a, parents);
        int rootB = find(b, parents);
        if (rootA == rootB) { return false; }
        
        parents[rootB] = rootA;
        return true;
    }

	private int find(int x, int[] parents) {
		if (parents[x] == x) { return x; }
		return find(parents[x], parents);
	}
}
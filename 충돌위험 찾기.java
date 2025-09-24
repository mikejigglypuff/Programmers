import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int[][] dr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<List<Integer[]>> moves = new ArrayList<>();

        for(int i = 0; i < routes.length; i++) {
            List<Integer[]> curMoves = new ArrayList<>();
            int[] route = routes[i];
            Integer[] pointsInt = Arrays.stream(points[route[0] - 1]).boxed().toArray(Integer[]::new);
            curMoves.add(pointsInt);
            for(int j = 0; j < route.length - 1; j++) {
                int[] fromRc = points[route[j] - 1];
                int[] toRc = points[route[j + 1] - 1];
                Integer[] curRc = Arrays.stream(fromRc).boxed().toArray(Integer[]::new);
                
                while(curRc[0] != toRc[0] || curRc[1] != toRc[1]) {
                    int drIdx = -1;
                    if(curRc[0] != toRc[0]) { drIdx = (curRc[0] < toRc[0]) ? 1 : 0; }
                    else if(curRc[1] != toRc[1]){ drIdx = (curRc[1] < toRc[1]) ? 3 : 2; }
                    
                    curRc[0] += dr[drIdx][0];
                    curRc[1] += dr[drIdx][1];
                    curMoves.add(new Integer[]{curRc[0], curRc[1]});
                }
            }
            moves.add(curMoves);
        }
        
        int answer = 0;
        int maxTime = 0;
        for(int i = 0; i < moves.size(); i++) {
            maxTime = Math.max(maxTime, moves.get(i).size());
        }
        
        Map<Integer, Integer> map;
        
        for(int i= 0; i < maxTime; i++) {
            map = new HashMap<>();
            for(int j = 0; j < moves.size(); j++) {
                if(moves.get(j).size() <= i) { continue; }
                Integer[] curRc = moves.get(j).get(i);
                int key = curRc[0] * 101 + curRc[1];
                if(!map.containsKey(key)) {
                    map.put(key, 1);
                } else {
                    int count = map.get(key);
                    if(count == 1) { ++answer; }
                    map.put(key, ++count);
                }
            }
        }
        
        return answer;
    }
}
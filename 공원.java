import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        Integer[] matsInt = Arrays.stream(mats).boxed().toArray(Integer[]::new);
        Arrays.sort(matsInt, new Comparator<Integer>(){
            @Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
        });
        int height = park.length;
        int width = park[0].length;
        
        for(int mat : matsInt) {
            for(int i = 0; i <= height - mat; i++) {
                for(int j = 0; j <= width - mat; j++) {
                    if(!park[i][j].equals("-1")) { continue; }
                    if(checkMatAvailable(i, j, mat, park)) { return mat; }
                }
            }
        }
        
        return -1;
    }
    
    public boolean checkMatAvailable(int startY, int startX, int size, String[][] park) {
        for(int i = startY; i < startY + size; i++) {
            for(int j = startX; j < startX + size; j++) {
                if(!park[i][j].equals("-1")) return false;
            }
        }
        
        
        return true;
    }
}
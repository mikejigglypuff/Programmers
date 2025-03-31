class Solution {
    public int solution(int[][] sizes) {
        int longerMax = 0;
        int shorterMax = 0;
        
        for(int[] size : sizes) {
            longerMax = Math.max(Math.max(size[0], size[1]), longerMax);
            shorterMax = Math.max(Math.min(size[0], size[1]), shorterMax);
        }
        
        return longerMax * shorterMax;
    }
}
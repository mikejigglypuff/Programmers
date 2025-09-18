class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int len = times.length;
        int[] retryTimes = new int[len];
        retryTimes[0] = times[0];
        for(int i = 1; i < len; i++) {
            retryTimes[i] = times[i] + times[i - 1];
        }
        
        int maxDiff = 0;
        for(int diff : diffs) {
            maxDiff = Math.max(maxDiff, diff);
        }
        
        int answer = maxDiff / 2;
        int searchRange = ((maxDiff - answer) / 2);
        while(searchRange > 0) {
            if(isInLimit(diffs, times, retryTimes, limit, answer)) {
                if(!isInLimit(diffs, times, retryTimes, limit, answer - 1)) { break; }
                answer = Math.max(answer - searchRange, 1);
            } else {
                answer += searchRange;
            }
            searchRange /= 2;
        }
        
        while(answer > 1 && answer <= maxDiff) {
            if(isInLimit(diffs, times, retryTimes, limit, answer)) {
                if(!isInLimit(diffs, times, retryTimes, limit, answer - 1)) { break; }
                --answer;
            } else {
                ++answer;
            }
        }
        
        return answer;
    }
    
    private boolean isInLimit(int[] diffs, int[] times, int[] retryTimes, long limit, int level) {
        long sum = 0;
        for(int i = 0; i < diffs.length; i++) {
            sum += times[i] + (long)Math.max(diffs[i] - level, 0) * retryTimes[i];
        }
        
        return sum <= limit;
    }
}
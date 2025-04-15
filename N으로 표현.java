import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if(N == number) { return 1; }
        
        Set<Integer>[] dp = new Set[9];
        int curN = 0;
        
        for(int i = 1; i < 9; i++) {
            dp[i] = new HashSet<>();
            curN = curN * 10 + N;
            dp[i].add(curN);
        }
        
        for(int i = 2; i < 9; i++) {
            for(int j = 1; j < i; j++) {
                for(int k : dp[j]) {
                    for(int l : dp[i - j]) {
                        dp[i].add(k + l);
                        dp[i].add(k - l);
                        dp[i].add(k * l);
                        if(l != 0) {
                            dp[i].add(k / l);
                        }
                    }
                    if(dp[i].contains(number)) { return i; }
                }
            }
        }
        
        return -1;
    }
}
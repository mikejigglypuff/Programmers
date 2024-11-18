// Top-Down 방식
class Solution {
    static int COUNT = 0, TARGET;
    static int[] NUMBERS;
    public int solution(int[] numbers, int target) {
        TARGET = target;
        NUMBERS = numbers;
        dfs(0, 0);
        return COUNT;
    }
    
    public void dfs(int sum, int index) {
        if(index == NUMBERS.length) {
            if(sum == TARGET) ++COUNT;
            return;
        }
        
        dfs(sum + NUMBERS[index], index + 1);
        dfs(sum - NUMBERS[index], index + 1);
    }
}

/*
Bottum-Up 방식

class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(0, 0, numbers, target);
    }
    
    public int dfs(int sum, int index, int[] numbers, int target) {
        if(index == numbers.length) {
            if(sum == target) return 1;
            return 0;
        }
        
        return dfs(sum + numbers[index], index + 1, numbers, target) + dfs(sum - numbers[index], index + 1, numbers, target);
    }
}
*/
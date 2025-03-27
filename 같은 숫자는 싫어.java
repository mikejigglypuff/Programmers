import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        
        for(int i : arr) { 
            if(stack.isEmpty() || i != stack.peek()) {
                stack.push(i);
            }
        }
        
        int len = stack.size();
        int[] answer = new int[len];
        
        for(int i = len - 1; i >= 0; i--) {
            answer[i] = stack.pop();
        }
        
        return answer;
    }
}
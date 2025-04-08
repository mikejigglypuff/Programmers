import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        char[] arr = number.toCharArray();
        Stack<Character> stack = new Stack<>();
        String result = "";
        
        for(char c : arr) {
            while(k > 0 && !stack.isEmpty() && stack.peek() < c) {
                stack.pop();
                --k;
            }
            stack.push(c);
        }
        
        while(k > 0) {
            stack.pop();
            --k;
        }
        
        while(!stack.isEmpty()) {
            result = stack.pop() + result;
        }
        
        return result;
    }
}

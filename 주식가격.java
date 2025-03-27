import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int pointer = 0;
        int size = prices.length;
        int[] answer = new int[size];
        Stack<Purchase> stack = new Stack<>();
        
        while(size > pointer) {
            int price = prices[pointer];
            
            while(!stack.isEmpty() && price < stack.peek().getPrice()) {
                Purchase curPurchase = stack.pop();
                answer[curPurchase.getTime()] = pointer - curPurchase.getTime();
            }
            
            stack.add(new Purchase(pointer, price));
            
            ++pointer;
        }
        
        while(!stack.isEmpty()) {
            Purchase curPurchase = stack.pop();
            answer[curPurchase.getTime()] = size - 1 - curPurchase.getTime();
        }
        
        return answer;
    }
    
    class Purchase {
        private int time;
        private int price;
        
        public Purchase(int time, int price) {
            this.time = time;
            this.price = price;
        }
        
        public int getTime() { return this.time; }
        public int getPrice() { return this.price; }
    }
}
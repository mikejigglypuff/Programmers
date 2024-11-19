import java.util.*;

/*
카드 그룹 클래스를 도입한 버전
class Solution {
    public int solution(int[] cards) {
        int len = cards.length;
        List<CardGroup> groups = new ArrayList<>();
        boolean[] visited = new boolean[len];
        
        for(int i = 0; i < len; i++) {
            if(visited[i]) continue;
            
            int cur = i;
            CardGroup cg = new CardGroup();
            while(!visited[cur]) {
                cg.addCard(cur);
                visited[cur] = true;
                cur = cards[cur] - 1;
            }
            groups.add(cg);
        }
        
        Collections.sort(groups);
        
        return (groups.size() > 1) ? groups.get(0).getSize() * groups.get(1).getSize() : 0;
    }
    
    public class CardGroup implements Comparable<CardGroup> {
        private List<Integer> cards = new ArrayList<>();
        private int size = 0;
        
        public int getSize() { return size; }
        public void addCard(int card) { 
            cards.add(card); 
            ++size;
        }
        
        @Override
        public int compareTo(CardGroup cardGroup) {
            return cardGroup.getSize() - size;
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("cards: ");
            
            for(int card : cards) {
                sb.append(card).append(", ");
            }
            
            sb.append("size: ").append(size);
            return sb.toString();
        }
    }
}
*/

// 우선순위 큐를 사용한 버전
class Solution {
    public int solution(int[] cards) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int len = cards.length;
        int result = 0;
        boolean[] visited = new boolean[len];
        
        for(int i = 0; i < len; i++) {
            if(visited[i]) continue;
            
            int count = 0;
            int cur = i;
            while(!visited[cur]) {
                ++count;
                visited[cur] = true;
                cur = cards[cur] - 1;
            }
            
            pq.add(count);
        }
        
        if(pq.size() > 1) result = pq.poll() * pq.poll();
        return result;
    }
    
}
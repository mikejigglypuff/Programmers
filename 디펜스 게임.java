import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        
        for(int i : enemy) {
            pq.add(i);
            
            if(pq.size() > k) {
                if(n < pq.peek()) break;
                
                n -= pq.poll();
            }
            
            ++answer;
        }
        
        return answer;
    }
}

/*
    무적권은 가능한 적의 수가 많은 라운드에 사용하면 좋음, 반대로 병사들은 적의 수가 적을 때 사용하면 좋음
    -> 우선순위 큐를 활용해 이를 구현할 것

    우선순위 큐 사용하기
        1-1. enemy를 모두 우선순위 큐에 넣기
            1-1-1. n을 사용할 최적의 라운드들을 구할 때 라운드 순서가 보장되지 않음
        
        1-2. k개만큼 넣어가며 탐색하기
            1-2-1. 넣은 수 만큼 k값을 줄이고 n이 큐 맨 앞 수보다 클 때 까지 k값을 1씩 더함
            1-2-2. 1-2-1을 k가 0이 될 때 까지 반복
            1-2-3. 위와 같이 enemy를 분할하는 방식으로는 n을 사용할 최적의 라운드를 얻지 못했음
            
        1-3. n에 맞춰 넣기
            1-3-1. n 값을 enemy[i]의 최소값으로 나눈 수만큼 넣는 것은 1-1과 크게 다를 게 없음
            1-3-2. 최대값으로 나눈 수만큼 넣는 것 또한 1-2와 크게 다를 게 없음
            1-3-3. 평균값 등을 사용하는 경우에도 1-3-1, 1-3-2의 문제를 벗어나지 못함
            
        1-4. 무적권 k개는 무조건 사용한다고 가정하기(https://20240228.tistory.com/117 참고함)
            1-4-1. 큐 길이가 무조건 k가 된다고 가정하고 k를 넘으면 맨 앞의 값을 n이랑 비교
*/
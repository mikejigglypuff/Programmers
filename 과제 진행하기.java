import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        int len = plans.length;
        String[] answer = new String[len];
        
        Arrays.sort(plans, new Comparator<String[]>(){
            @Override
            public int compare(String[] a, String[] b) {
                return convTime(a[1]) - convTime(b[1]);
            }
        });
        
        Stack<String[]> stack = new Stack<>();
        int answerIndex = 0, planIndex = 0;
        
        while(planIndex < len - 1) {
            String[] cur = plans[planIndex];
            String[] next = plans[planIndex + 1];
            int limit = convTime(next[1]) - convTime(cur[1]);
            int playtime = Integer.parseInt(cur[2]);
            
            if(limit == playtime) {
                answer[answerIndex] = cur[0];
                ++answerIndex;
            } else if(limit > playtime) {
                answer[answerIndex] = cur[0];
                ++answerIndex;
                int remainTime = limit - playtime;
                
                while(remainTime > 0 && !stack.isEmpty()) {
                    String[] homework = stack.pop();
                    int homeworkTime = Integer.parseInt(homework[2]);
                    if(remainTime >= homeworkTime) {
                        answer[answerIndex++] = homework[0];
                    } else {
                        homework[2] = String.format("%d", homeworkTime - remainTime);
                        stack.push(homework);
                    }
                    remainTime -= homeworkTime;
                }
                
            } else {
                cur[2] = String.format("%d", playtime - limit);
                stack.push(cur);
            }
            
            ++planIndex;
        }
        
        answer[answerIndex++] = plans[planIndex][0];
        while(!stack.isEmpty()) {
            String[] cur = stack.pop();
            answer[answerIndex++] = cur[0];
        }
        
        return answer;
    }
    
    public int convTime(String time) {
        String[] splits = time.split(":");
        return Integer.parseInt(splits[0]) * 60 + Integer.parseInt(splits[1]);
    }

}

/*

    1. plans 정렬
        1-1. comparator를 이용해 start 값을 기준으로 오름차순 정렬할 것

    2. 스택을 이용해 과제 진행 순서 결정하기
        2-1. 다음 과제 start - 현재 과제 start을 구해 현재 과제의 playtime와 비교한 후 과제를 스택에 담을 것
            2-1-1. 다음 과제 start - 현재 과제 start >= 현재 과제 playtime인 경우 결과 배열에 현재 과제 담을 것
                2-1-1-1. 이 때 스택에 과제가 있는지 확인할 것
                2-1-1-2. 남은 과제가 있다면 남은 시간 내로 스택의 과제를 수행할 수 있는지 확인할 것
                2-1-1-3. 수행 완료할 수 있다면 스택에서 제거하고 배열에 넣을 것
            2-1-2. 다음 과제 start - 현재 과제 start < 현재 과제 playtime인 경우 스택에 현재 과제 담을 것
                2-1-2. 과제를 담을 때 playtime을 playtime - (다음 과제 start - 현재 과제 start)로 저장할 것
            
        2-3. 시간 연산 구현
            2-3-1. 문자열로 구성된 시/분 을 :로 나눠 각각 int로 변환하는 식으로 연산 구현할 것 
*/
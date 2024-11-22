class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        for(int y = r2 - 1; y >= 0; y--) {
            double p2 = pythagoras(r2, y);
            
            if(y >= r1) {
                answer += Math.floor(p2);
            } else {
                double p1 = pythagoras(r1, y);
                /*
                    틀린 코드
                    if(p1 % 1 == 0) answer++;
                    answer += Math.floor(p2 - p1);
                */
                answer += Math.floor(p2 - Math.ceil(p1)) + 1;
            }
        }
        
        return answer * 4;
    }
    
    public double pythagoras(int r, int y) {
        return Math.sqrt(Math.pow(r, 2) - Math.pow(y, 2));
    }
}

/*
    1. r2 - 1 부터 시작해 각 y좌표별로 나올 수 있는 점들 구하기
        1-1. y가 r2 - 1 ~ 0까지 반복하도록 하기
        1-2. y가 r1보다 크거나 같을 경우 결과에 (r2 ^ 2 - y ^ 2)를 long으로 변환한 값 추가하기
            1-2. 이 때는 (r2 ^ 2 - y ^ 2) 값이 정수일 경우 고려하지 않아도 됨
        1-3. y가 r1보다 작을 경우 결과에 (r2 ^ 2 - y ^ 2) - (r1 ^ 2 - y ^ 2)를 long으로 변환한 값 추가하기
            1-3-1. 이 때 (r1 ^ 2 - y ^ 2) 값이 정수라면 +1 추가

    2. 최종 결과에 *4 수행할 것
*/
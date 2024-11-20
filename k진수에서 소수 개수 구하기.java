import java.util.*;

class Solution {
    public int solution(int n, int k) {
        return countPrime(toDecimal(n, k));
    }
    
    private List<Long> toDecimal(int n, int k) {
        List<Long> result = new ArrayList<>();
        long decimal = 0, mul = 1;
        
        while(n != 0) {
            int mod = n % k;
            
            if(mod == 0) {
                if(decimal != 0) result.add(decimal);
                decimal = 0;
                mul = 1;
            } else {
                decimal += mul * mod;
                mul *= 10;
            }
            n /= k;
        }
        
        if(decimal != 0) result.add(decimal);
        return result;
    }
    
    private int countPrime(List<Long> numbers) {
        int count = 0;
        
        for(long n : numbers) {
            int sqrt = (int)Math.sqrt(n);
            boolean isPrime = true;
            
            for(int i = 2; i <= sqrt; i++) {
                if(n % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            
            if(n > 1 && isPrime) ++count;
        }
        
        return count;
    }
}

/*
    1. n을 k진수로 변환하기
        1-1. n을 k로 나눠지지 않을 때 까지 나눌 것
        1-2. n을 k로 나눈 나머지들을 계속 합칠 것
            1-2-1. 나머지가 0이 나오는 경우 나눠 보관하기
        
    
    2. 소수 구하기
    
*/
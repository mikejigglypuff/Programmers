class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        while(!fitWallet(wallet, bill)) {
            int index = (bill[0] >= bill[1]) ? 0 : 1;
            bill[index] /= 2;
            ++answer;
        }
        
        return answer;
    }
    
    private boolean fitWallet(int[] wallet, int[] bill) {
        return (wallet[0] >= bill[0] && wallet[1] >= bill[1]) || (wallet[0] >= bill[1] && wallet[1] >= bill[0]);
    }
}
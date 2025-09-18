class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoSec = convertToSeconds(video_len);
        int curSec = convertToSeconds(pos);
        int opStartSec = convertToSeconds(op_start);
        int opEndSec = convertToSeconds(op_end);
        
        for(String command : commands) {
            if(curSec >= opStartSec && curSec <= opEndSec) {
                curSec = opEndSec;
            }
            if(command.equals("prev")) {
                curSec = Math.max(curSec - 10, 0);
            } else if(command.equals("next")) {
                curSec = Math.min(curSec + 10, videoSec);
            }
        }
        
        if(curSec >= opStartSec && curSec <= opEndSec) {
            curSec = opEndSec;
        }
        
        return convertToTime(curSec);
    }
    
    private int convertToSeconds(String time) {
        String[] minSec = time.split(":");
        int min = Integer.parseInt(minSec[0]);
        int sec = Integer.parseInt(minSec[1]);
        
        return min * 60 + sec;
    }
    
    private String convertToTime(int seconds) {
        int min = seconds / 60;
        int sec = seconds % 60;
        
        String minStr = Integer.toString(min);
        String secStr = Integer.toString(sec);
        
        if(minStr.length() == 1) {
            minStr = "0" + minStr;
        }
        if(secStr.length() == 1) {
            secStr = "0" + secStr;
        }
        
        return minStr + ":" + secStr;
    }
}
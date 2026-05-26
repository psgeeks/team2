class Solution {
    
    int length;
    public int solution(int[] diffs, int[] times, long limit) {
        length = diffs.length;
        int end = 0;
        for(int i = 1; i < length; i++){
            if(end < diffs[i]) end = diffs[i];
        }
        int start = 1;
        while(start <= end){
            
            int mid = (start + end) / 2;
            if(pos(diffs, times, limit, mid)){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        return start;
    }
    
    private boolean pos(int[] diffs, int[] times, long limit, int level){
        long total_time = times[0];
        
        for(int i = 1; i < length; i++){
            long cur_time = solveTime(diffs[i], times[i], times[i - 1], level);
            total_time += cur_time;
            
            if(total_time > limit) return false;
        }
        System.out.println(total_time + " " + level);
        return true;
    }
    
    private long solveTime(int diff, int time_cur, int time_prev, int level){
        if(diff <= level) return time_cur;
        
        long total_time = 0;
        long wrong = diff - level;
        total_time = wrong * (time_prev + time_cur) + time_cur;
        return total_time;
    }
}
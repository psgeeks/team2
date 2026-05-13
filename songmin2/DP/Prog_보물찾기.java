import java.util.*;
import java.util.function.Function;

class Solution {
            
    int MAXV = 1000_000_007;
    public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
        
        int w = depth.length;
        int[][] dp = new int[w][w];
        int[][] bestPick = new int[w][w];
        
        for(int i = 0; i < w; i++){
            Arrays.fill(dp[i], MAXV);
            dp[i][i] = depth[i];
            bestPick[i][i] = i;
        }
        
        for(int gap = 1; gap < w; gap++){
            for(int start = 0; start + gap < w; start++){
                int end = start + gap;
                for(int i = start; i <= end; i++){
                    if(i == start) {
                        if(dp[start][end] > depth[i] + dp[start + 1][end]){
                            dp[start][end] = depth[i] + dp[start + 1][end];
                            bestPick[start][end] = start;
                        }
                    }
                    else if(i == end){
                        if(dp[start][end] > depth[i] + dp[start][end - 1]){
                            dp[start][end] = depth[i] + dp[start][end - 1];
                            bestPick[start][end] = end;
                        }
                    }
                    else {
                        if(dp[start][end] > depth[i] + Math.max(dp[start][i - 1], dp[i + 1][end])){
                            dp[start][end] = depth[i] + Math.max(dp[start][i - 1], dp[i + 1][end]);
                            bestPick[start][end] = i;
                        }
                    }
                }
            }
        }
        
        int result = 0;
        int start = 0;
        int end = w - 1;
        for(int i = 1; i <= depth.length; i++){

            int pick = bestPick[start][end];
            int cur = excavate.apply(pick + 1);
            
            if (cur == 0) return pick + 1;
            else if(cur == -1){
                end = pick - 1;
            }
            else{
                start = pick + 1;
            }
        }
        return 0;
    }
}
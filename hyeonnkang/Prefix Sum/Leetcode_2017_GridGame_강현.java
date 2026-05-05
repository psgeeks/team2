class Leetcode_2017_GridGame_강현 {
    int n;
    long[][] pSum;

    public long gridGame(int[][] grid) {
        n = grid[0].length;
        
        // 누적합 구하기
        pSum = new long[2][n];
        pSum[0][0] = grid[0][0];
        pSum[1][0] = grid[1][0];
        for(int i = 1; i < n; i++){
            pSum[0][i] = pSum[0][i-1] + grid[0][i];
            pSum[1][i] = pSum[1][i-1] + grid[1][i];
        }

        long ans = Long.MAX_VALUE;
        for(int i = 0; i < n; i++){
            long max;
            if(i == 0){
                max = pSum[0][n-1] - pSum[0][0];
            }else if(i == n-1){
                max = pSum[1][n-2];
            }else{
                max = Math.max(pSum[0][n-1] - pSum[0][i], pSum[1][i-1]);
            }
            ans = Math.min(ans, max);
        }

        return ans;
    }
}
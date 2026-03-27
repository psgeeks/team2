
import java.util.*;
import java.io.*;

public class BOJ_29152_디지털트윈 {
    static final long INF = Long.MAX_VALUE / 4;
    static int N;
    static String[] grid;
    static int[] L, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        grid = new String[N + 1];
        for (int i = 1; i <= N; i++) {
            grid[i] = " " + br.readLine(); // 1-index 맞추기
        }
        
        L = new int[N + 1]; // 행 i에서 가장 왼쪽의 '1'
        R = new int[N + 1]; // 행 i에서 가장 오른쪽의 '1'
        Arrays.fill(L, N + 1);
        Arrays.fill(R, 0);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (grid[i].charAt(j) == '1') {
                    L[i] = Math.min(L[i], j);
                    R[i] = Math.max(R[i], j);
                }
            }
        }

        // dp[j] = i번째 행을 처리한 후, 열 j에서 끝났을 때의 최소 비용
        long[] dp = new long[N + 1];
        long[] ndp = new long[N + 1];
        Arrays.fill(dp, INF);

        // 시작점은 (1,1)
        dp[1] = 0;

        for (int i = 1; i <= N; i++) {
            Arrays.fill(ndp, INF);

            // 기계가 없는 행
            if (R[i] == 0) {
                long[] left = new long[N + 1]; // j보다 왼쪽에서 올 때 최솟값
                long[] right = new long[N + 2]; // j보다 오른쪽에서 올 때 최솟값
                Arrays.fill(left, INF);
                Arrays.fill(right, INF);

                for (int j = 1; j <= N; j++) {
                	left[j] = Math.min(left[j - 1], dp[j] - j);
                }

                for (int j = N; j >= 1; j--) {
                	right[j] = Math.min(right[j + 1], dp[j] + j);
                }

                for (int j = 1; j <= N; j++) {
                    // k <= j 인 경우: dp[k] + (j-k) + 1
                    ndp[j] = Math.min(ndp[j], left[j] + j + 1);

                    // k >= j 인 경우: dp[k] + (k-j) + 1
                    ndp[j] = Math.min(ndp[j], right[j] - j + 1);
                }
            }
            // 기계가 있는 행
            else {
                int l = L[i];
                int r = R[i];

                // 왼쪽에서 들어와 오른쪽으로 지나가는 경우 (k <= l, j >= r)
                long leftBest = INF;
                for (int k = 1; k <= l; k++) {
                    leftBest = Math.min(leftBest, dp[k] - k);
                }
                for (int j = r; j <= N; j++) {
                    if (leftBest < INF) {
                        ndp[j] = Math.min(ndp[j], leftBest + j + 1);
                    }
                }

                // 오른쪽에서 들어와 왼쪽으로 지나가는 경우 (k >= r, j <= l)
                long rightBest = INF;
                for (int k = r; k <= N; k++) {
                    rightBest = Math.min(rightBest, dp[k] + k);
                }
                for (int j = 1; j <= l; j++) {
                    if (rightBest < INF) {
                        ndp[j] = Math.min(ndp[j], rightBest - j + 1);
                    }
                }
            }

            System.arraycopy(ndp, 0, dp, 0, N + 1);
        }

        if (dp[N] >= INF / 2) {
            System.out.println(-1);
        } else {
            System.out.println(dp[N]);
        }
    }
}
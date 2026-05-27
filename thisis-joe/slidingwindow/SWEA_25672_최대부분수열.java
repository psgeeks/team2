import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int windowCount = N - K + 1;
            int[] window = new int[windowCount];

            int sum = 0;
            for (int i = 0; i < K; i++) {
                sum += arr[i];
            }
            window[0] = sum;

            for (int i = 1; i < windowCount; i++) {
                sum = sum - arr[i - 1] + arr[i + K - 1];
                window[i] = sum;
            }

            int[] leftMax = new int[windowCount];
            leftMax[0] = window[0];

            for (int i = 1; i < windowCount; i++) {
                leftMax[i] = Math.max(leftMax[i - 1], window[i]);
            }

            int max = Integer.MIN_VALUE;

            for (int j = K; j < windowCount; j++) {
                max = Math.max(max, leftMax[j - K] + window[j]);
            }

            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }

        System.out.print(sb);
    }
}
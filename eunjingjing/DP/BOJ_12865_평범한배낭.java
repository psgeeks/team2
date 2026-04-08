package algorithm;
import java.io.*;
import java.util.*;

/*
 * 메모리 53772 KB
 * 시간 224 ms
 */

public class BOJ_12865_평범한배낭 {
	static int N, K, dp[][], W[], V[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[N+1][K+1];
		W = new int[N+1];
		V = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int w = 1; w <= K; w++) {
			for(int i = 1; i <= N; i++) {
				if(W[i] > w) dp[i][w] = dp[i-1][w];
				else {
					int iBefore = 0;
					if(i-1>0 && w-W[i]>0) iBefore = dp[i-1][w-W[i]];
					dp[i][w] = Integer.max(iBefore + V[i], dp[i-1][w]);
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}

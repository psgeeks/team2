package day0408;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 메모리: 19764kb 시간: 172ms
 */

public class BOJ_2098_외판원순회 {
	static int N,W[][],dp[][];
	static final int INF = 1_000_000_000;
	static int fullVisited;

	
	static int dfs(int cur, int visited) {
		if(visited == fullVisited) {
			if(W[cur][0]==0) return INF;
			return W[cur][0];
		}
		
		if(dp[cur][visited] != -1) return dp[cur][visited];
		
		dp[cur][visited] = INF;
		
		for(int next=0;next<N;next++) {
			if(W[cur][next]==0) continue;
			if((visited&(1<<next))!=0) continue;
			
			int visitedNext = visited + (1<<next);
			
			dp[cur][visited] = Math.min(dp[cur][visited], dfs(next,visitedNext)+W[cur][next]);
		}
		
		return dp[cur][visited];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		W = new int[N][N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		fullVisited = 1<<N-1;
		
		dp = new int[N][1<<N]; //[현재도시][방문한 도시]
		
		for(int i=0;i<N;i++) {
			Arrays.fill(dp[i], -1);
		}
		
		int ans = dfs(0,1); //시작 노드 0, 0노드 방문:2^0 == 1
		
		System.out.println(ans);
	}

}

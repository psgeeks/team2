package day0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142_연구소3 {
	static int N,M,blank,ans,stamp;
	static int map[][],pickedV[],visited[][];
	static ArrayList<int[]> virus;
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	static class Node{
		int r,c,t;
		Node(int r,int c,int t){
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
	
	static void dfs(int start,int picked) {
		if(picked==M) {
			stamp ++;
			int time = diff();
			if(time!=-1) ans = Math.min(ans, time);
			return;
		}
		
		for(int i=start;i<virus.size();i++) {
			pickedV[picked] = i;
			dfs(i+1,picked+1);

		}
	}
	
	static int diff() {
		Queue<Node> q = new ArrayDeque<>();	
		int b = blank;
		if (b == 0) return 0;
		
		for(int i=0;i<M;i++) {
			int sr = virus.get(pickedV[i])[0];
			int sc = virus.get(pickedV[i])[1];
			q.add(new Node(sr,sc,0));
			visited[sr][sc] = stamp;
		}
		
		while(!q.isEmpty()) {
			Node cur = q.remove();
			
			for(int di=0;di<4;di++) {
				int nr = cur.r+dr[di];
				int nc = cur.c+dc[di];
				int nt = cur.t+1;
				
				if(nr<0||nc<0||nr>=N||nc>=N) continue;
				if(visited[nr][nc]==stamp) continue;
				if(map[nr][nc] == 1) continue;
				
				if(map[nr][nc] == 0) b --;
				if(b==0) return nt;
				visited[nr][nc] = stamp;
				q.add(new Node(nr,nc,nt));
			}
			
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		blank = 0;
		virus = new ArrayList<>();
		pickedV = new int[M];
		visited = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) virus.add(new int[]{i,j});
				else if(map[i][j]==0) blank ++;
			}
		}
		
		ans = Integer.MAX_VALUE;
		dfs(0,0);
		if(ans == Integer.MAX_VALUE) ans = -1;
		System.out.println(ans);
	}

}

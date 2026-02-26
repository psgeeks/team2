package day0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {
	static int N,M,map[][],safe,ans;
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	static ArrayList<Virus> v;
	static int visited[][];
	static int stamp = 0;
	static ArrayList<int[]> empty;
	
	static class Virus{
		int r;
		int c;
		Virus(int r,int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static void dfs(int depth,int start) {
		if(depth==3) {
			stamp ++;
			ans = Math.max(ans, diffusion());
			return;
		}
		
		for(int idx=start;idx<empty.size();idx++) {
			int i = empty.get(idx)[0];
			int j = empty.get(idx)[1];
			
			map[i][j] = 1;
			dfs(depth+1,idx+1);
			map[i][j] = 0;
		}
	}
	
	static int diffusion() {
		int s = safe - 3; //벽 3개 빼주기
		Queue<Integer> q = new ArrayDeque<>();
		
		for(Virus vi:v) {
			q.add(vi.r*M+vi.c);
		}
		while(!q.isEmpty()) {
			int cur = q.remove();
			int r = cur/M;
			int c = cur%M;
			for(int di=0;di<4;di++) {
				int nr = r+dr[di];
				int nc = c+dc[di];
				
				if(nr<0||nc<0||nr>=N||nc>=M) continue;
				if(map[nr][nc]!=0) continue;
				if(visited[nr][nc]==stamp) continue;
				
				visited[nr][nc] = stamp;
				q.add(nc+nr*M);
				s --;
				if(s<=ans) return s; //가지치기
			}
		}
		
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		v = new ArrayList<>();
		safe = 0;
		empty = new ArrayList<>();
		visited = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					v.add(new Virus(i,j));
				}else if(map[i][j]==0) {
					empty.add(new int[]{i,j});
					safe += 1;
				}
			}
		}
		
		ans = 0;
		
		dfs(0,0);
		
		System.out.println(ans);
		
	}
}

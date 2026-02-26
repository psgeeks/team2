 package day0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_17141_연구소2 {
	static int N,M,safe,stamp,ans;
	static int map[][],visited[][],sPoint[];
	static boolean visitedS[];
	static ArrayList<int[]> startable;
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	static void dfs(int depth,int start,int picked) {
		if(picked==M) {
			stamp ++;
			int time = diffusion();
			if(ans==-1) {
				ans = time;
			}else {
				if(time != -1) ans = Math.min(ans, time);
			}
			
			return;
		}
		
		for(int idx=start;idx<startable.size();idx++) {
			if(visitedS[idx]) continue;
			visitedS[idx] = true;
			sPoint[picked]=idx;
			dfs(depth+1,idx+1,picked+1);
			visitedS[idx] = false;
		}
	}
	
	static class Node{
		int r;
		int c;
		int time;
		Node(int r,int c,int time){
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	
	static int diffusion() {
	    int s = safe;
	    if(s==0) return 0;
	    Queue<Node> q = new ArrayDeque<>();
	    

	    // 멀티소스: 시작점 먼저 전부 넣기
	    for (int i = 0; i < M; i++) {
	        int sp = sPoint[i];
	        int sr = startable.get(sp)[0];
	        int sc = startable.get(sp)[1];
	        visited[sr][sc] = stamp;
	        q.add(new Node(sr, sc, 0));
	    }

	    while (!q.isEmpty()) {
	        Node cur = q.remove();
	        int r = cur.r, c = cur.c;

	        for (int di = 0; di < 4; di++) {
	            int nr = r + dr[di];
	            int nc = c + dc[di];
	            int nt = cur.time + 1;

	            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
	            if (map[nr][nc] != 0) continue;          // 벽이면 못감 
	            if (visited[nr][nc] == stamp) continue;
	          

	            visited[nr][nc] = stamp;
	            
	            q.add(new Node(nr, nc, nt));

	            // 원래 빈칸만 safe에 들어있으므로, 퍼질 때마다 감소시키면 됨
	            // (바이러스 칸들은 safe에 포함 안 했음)
	            if (/*원래 빈칸인지*/ true) { // map을 0/1로만 쓰고 있으니 그냥 감소해도 됨
	                s--;
	                if (s == 0) return nt;
	            }
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
		startable = new ArrayList<>();
		visited = new int[N][N];
		sPoint = new int[M];
		safe = 0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					startable.add(new int[] {i,j});
					map[i][j] = 0;
				}else if(map[i][j]==0) {
					safe += 1;
				}
			}
		}
		safe += startable.size() - M;
		visitedS = new boolean[startable.size()];
		ans = Integer.MAX_VALUE;
		
		dfs(0,0,0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

	}

}

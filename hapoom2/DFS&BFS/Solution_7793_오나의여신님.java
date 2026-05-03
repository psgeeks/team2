package may2026.day0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리:26,880kb 시간:95ms
 */

public class Solution_7793_오나의여신님 {
	static int N, M;
	static char map[][];
	static int E[];
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	static int bfs() {
		Queue<int[]> sq = new ArrayDeque<>();
		Queue<int[]> dq = new ArrayDeque<>();
		boolean visited[][] = new boolean[N][M];
		boolean infected[][] = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				switch(map[i][j]) {
				case 'S':
					sq.add(new int[] {i,j});
					visited[i][j]= true;
					break;
				case 'D':
					E[0] = i;
					E[1] = j;
					break;
				case '*':
					dq.add(new int[] {i,j});
					infected[i][j] = true; 
					break;
				}
			}
		}
		int time = 0;
		while(!sq.isEmpty()) {
			//악마
			int size = dq.size();
			for(int i=0;i<size;i++) {
				int devil[] = dq.poll();
				for(int di=0;di<4;di++) {
					int nr = devil[0] + dr[di];
					int nc = devil[1] + dc[di];
					
					if(nr<0||nc<0||nr>=N||nc>=M) continue;
					if(infected[nr][nc]) continue;
					if(map[nr][nc]=='X') continue;
					if(nr==E[0]&&nc==E[1]) continue;
					
					infected[nr][nc] = true;
					dq.add(new int[] {nr,nc});
				}
			}
			
			
			//수연
			size = sq.size();
			for(int i=0;i<size;i++) {
				int su[] = sq.poll();
				for(int di=0;di<4;di++) {
					int nr = su[0] + dr[di];
					int nc = su[1] + dc[di];
					
					if(nr<0||nc<0||nr>=N||nc>=M) continue;
					if(infected[nr][nc]) continue;
					if(visited[nr][nc]) continue;
					if(map[nr][nc]=='X') continue;
					if(nr==E[0]&&nc==E[1]) return time + 1;
					
					visited[nr][nc] = true;
					sq.add(new int[] {nr,nc});
				}
			}
			time ++;
		}
		return -1;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M= Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			E = new int[2];
			
			for(int i=0;i<N;i++) {
				String str = br.readLine();
				for(int j=0;j<M;j++) {
					map[i][j] = str.charAt(j);
				}
			}
			
			int ans = bfs();
			
			sb.append("#").append(tc).append(" ").append(ans==-1?"GAME OVER":ans).append("\n");
		}
		System.out.println(sb);
	}

}

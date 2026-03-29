package day0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리:15356kb 시간:128ms
 */

public class BOJ_1194_달이차오른다가자 {
	static int N,M;
	static char map[][];
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	static class Node{
		int r,c,key,t;
		
		Node(int r,int c,int key,int t){
			this.r = r;
			this.c = c;
			this.key = key;
			this.t = t;
		}
	}
	
	static int bfs(int sr,int sc) {
		Queue<Node> q = new ArrayDeque<>();
		boolean visited[][][] = new boolean[64][N][M];
		visited[0][sr][sc] = true;
		q.add(new Node(sr,sc,0,0));
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int di=0;di<4;di++) {
				int nr = cur.r + dr[di];
				int nc = cur.c + dc[di];
				int key = cur.key;
				
				if(nr<0||nc<0||nr>=N||nc>=M) continue;
				if(visited[key][nr][nc]) continue;
				if(map[nr][nc]=='#') continue;
				if(map[nr][nc]=='1') return cur.t+1;
				//열쇠일 경우
				if(map[nr][nc]-'a'>=0&&map[nr][nc]-'a'<=5) {
					int hasKey = map[nr][nc] - 'a';
					if((key&(1<<hasKey))==0) {
						key += 1<<(map[nr][nc] - 'a');
					}
				}
				
				//문일 경우
				if(map[nr][nc]-'A'>=0&&map[nr][nc]-'A'<=5) {
					int hasKey = map[nr][nc] - 'A';
					//키가 없으면 못감
					if((key&(1<<hasKey)) == 0) continue;
				}
				
				visited[key][nr][nc] = true;
				q.add(new Node(nr,nc,key,cur.t+1));
			}
		}
		return -1;
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		int sr=0,sc=0;
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j]=='0') {
					sr = i;
					sc = j;
				}
			}
		}
		
		System.out.println(bfs(sr,sc));
		
		
		
	}

}

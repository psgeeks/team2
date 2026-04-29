package day0420;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/*
 * 메모리:47.4mb 시간:336ms
 */

public class Leetcode_417_PacificAtlanticWaterFlow {
	
	static int dr[] = {-1,1,0,0};
	static int dc[] = {0,0,-1,1};
	
	public List<List<Integer>> pacificAtlantic(int[][] heights) {
		List<List<Integer>> result = new ArrayList<>();
		
		int M = heights.length;
		int N = heights[0].length;
		
		
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				Queue<int[]> q = new ArrayDeque<>();
				boolean visited[][] = new boolean[M][N];
				boolean P = false;
				boolean A = false;
				q.add(new int[] {i,j});
				visited[i][j] = true;
				
				while(!q.isEmpty()) {
					int[] cur = q.poll();
					int r = cur[0];
					int c = cur[1];
					if(P&&A) break;
					for(int di=0;di<4;di++) {
						int nr = r + dr[di];
						int nc = c + dc[di];
						
						
						
						if(nr<0||nc<0) {
							P = true;
							continue;
						}
						if(nr>=M||nc>=N) {
							A = true;
							continue;
						}
						if(visited[nr][nc]) continue;
						if(heights[nr][nc]>heights[r][c]) continue;
						
						visited[nr][nc] =true;
						q.add(new int[] {nr,nc});
					}
				}
				if(P&&A) {
					result.add(Arrays.asList(i,j));
				}
			}
		}
		
		
		return result;
	}

}

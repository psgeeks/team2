package day0306;

import java.io.*;
import java.util.*;
/*
 * 메모리:68,808kb 시간:976ms
 */

public class BOJ_16118_달빛여우 {
	static int N,M,Fdist[],Wdist[][];
	static ArrayList<Node> graph[];
	
	static class Node{
		int to, w;

		public Node(int to, int w) {
			super();
			this.to = to;
			this.w = w;
		}
	}
	
	static class FNode{
		int ver,dist;

		public FNode(int ver, int dist) {
			super();
			this.ver = ver;
			this.dist = dist;
		}
		
	}
	
	static class WNode{
		int ver,dist,mode;

		public WNode(int ver, int dist, int mode) {
			super();
			this.ver = ver;
			this.dist = dist;
			this.mode = mode;
		}
		
	}
	
	static void fox() {
		PriorityQueue<FNode> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.dist));
		Fdist = new int[N+1];
		Arrays.fill(Fdist, Integer.MAX_VALUE);
		pq.add(new FNode(1,0));
		Fdist[1] = 0;
		while(!pq.isEmpty()) {
			FNode cur = pq.poll();
			int v = cur.ver;
			int d = cur.dist;
			if(d>Fdist[v]) continue;
			for(Node node:graph[v]) {
				if(Fdist[node.to]>d+node.w*2) {
					Fdist[node.to] = d+node.w*2;
					pq.add(new FNode(node.to,Fdist[node.to]));
				}
			}
		}
	}
	
	static void wolf() {
		PriorityQueue<WNode> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.dist));
		Wdist = new int[N+1][2];
		for(int i=0;i<Wdist.length;i++) {
			Wdist[i][0] = Integer.MAX_VALUE;
			Wdist[i][1] = Integer.MAX_VALUE;
		}
		pq.add(new WNode(1,0,0));
		Wdist[1][0] = 0;
		while(!pq.isEmpty()) {
			WNode cur = pq.poll();
			int v = cur.ver;
			int d = cur.dist;
			int m = cur.mode;
			int nm = (m+1)%2;
			
			if(d>Wdist[v][m]) continue;
			
			for(Node node:graph[v]) {
				int nd;
				if(m==0) nd = d + (node.w); //빠른 이동
				else nd = d + (node.w)*4; //느린 이동
				if(Wdist[node.to][nm]>nd) {
					Wdist[node.to][nm] = nd;
					pq.add(new WNode(node.to,Wdist[node.to][nm],nm));
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for(int i=0;i<=N;i++) graph[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to,w));
			graph[to].add(new Node(from,w));
		}
		
		fox();
		wolf();
		
		int ans = 0;
		for(int i=1;i<=N;i++) {
			if(Fdist[i]<Math.min(Wdist[i][0], Wdist[i][1])) ans ++;
		}
		
		System.out.println(ans);
	}

}

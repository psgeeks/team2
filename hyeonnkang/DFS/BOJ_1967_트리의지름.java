package day0406;

import java.io.*;
import java.util.*;

/*
 * 메모리: 22,932kb, 실행시간: 192ms
 */
public class BOJ_1967_트리의지름 {
	static int n;
	static List<Node>[] edges;
	static boolean[] visited;
	static int maxNode = 0;
	static long maxWeight = 0;
	
	static class Node{
		int to;
		int weight;
		
		Node(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		edges = new List[n+1];
		for(int i = 1; i <= n; i++) edges[i] = new ArrayList<>();
		for(int i = 0; i < n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges[p].add(new Node(c, w));
			edges[c].add(new Node(p, w));
		}
		
		// 루트로부터 가장 먼 노드 찾기 
		visited = new boolean[n+1];
		maxNode = 0;
		maxWeight = 0;
		visited[1] = true;
		dfs(1, 0);
		visited[1] = false;
		
		// maxNode로부터 가장 먼 노드까지의 가중치 찾기
		maxWeight = 0;
		visited[maxNode] = true;
		dfs(maxNode, 0);
		
		System.out.print(maxWeight);
	}
	
	static void dfs(int cur, long weight) {
		if(weight >= maxWeight) {
			maxWeight = weight;
			maxNode = cur;
		}
		
		for(Node next : edges[cur]) {
			if(visited[next.to]) continue;
			visited[next.to] = true;
			dfs(next.to, weight + next.weight);
			visited[next.to] = false;
		}
	}

}

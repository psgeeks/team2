package 알고연습장;
import java.io.*;
import java.util.*;

public class BOJ_1854_K번째최단경로찾기_dijkstra {
	static class Edge implements Comparable<Edge>{
		int to;
		long weight;
		
		public Edge(int to, long weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	static List<Long> wayList[];
	static List<Edge> edges[];
	static boolean complete[];
	static int N, K;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		wayList = new ArrayList[N+1];
		edges = new ArrayList[N+1];
		complete = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			wayList[i] = new ArrayList<>();
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[from].add(new Edge(to, weight));
		}
		
		dijkstra();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			if(wayList[i].size() < K) {
				sb.append(-1);
			} else {
				sb.append(wayList[i].get(K-1));
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0));
		
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			if(complete[curr.to]) continue;	//이미 k개만큼의 길을 보유한 노드는 pass
			// 지금까지의 거리합 리스트에 추가 + 사이즈가 K와 같아지면 complete true로 바꿔서 더이상의 연산 막기
			wayList[curr.to].add(curr.weight);
			if(wayList[curr.to].size() == K) complete[curr.to] = true;
			
			for(Edge next : edges[curr.to]) {
				long nextW = curr.weight + next.weight;
				pq.add(new Edge(next.to, nextW));
			}
		}
	}
}
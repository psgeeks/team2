package 알고연습장;
import java.io.*;
import java.util.*;

public class BOJ_1854_K번째최단경로찾기_maxHeap {
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
	static PriorityQueue<Long> dist[];
	static List<Edge> edges[];
	static int N, K;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dist = new PriorityQueue[N+1];
		edges = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			dist[i] = new PriorityQueue<>(Collections.reverseOrder());
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
			if(dist[i].size() < K) {
				sb.append(-1).append("\n");
			} else {
				sb.append(dist[i].peek()).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0));
		dist[1].add(0L);
		
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			for(Edge next : edges[curr.to]) {
				long nextW = curr.weight + next.weight;
				
				// 다음 노드로 가는 경우의 수를 아직 K개 찾지 못했다면 무조건 넣기
				if(dist[next.to].size() < K) {
					dist[next.to].add(nextW);
					pq.add(new Edge(next.to, nextW));
				} 
				// 이미 K개를 찾았지만 다음 이번 경로가 K번째 경로보다 짧은 경우
				else if(dist[next.to].peek() > nextW) {
					dist[next.to].poll(); //현재 K번째 값 버리기
					dist[next.to].add(nextW); 
					pq.add(new Edge(next.to, nextW));
				}
			}
		}
	}
}
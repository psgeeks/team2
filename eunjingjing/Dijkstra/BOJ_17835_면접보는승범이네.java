package 알고연습장;
import java.io.*;
import java.util.*;

public class BOJ_17835_면접보는승범이네 {
	static class Node implements Comparable<Node>{
		int to;
		long weight;

		public Node(int to, long weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	final static long INF = Long.MAX_VALUE;
	static int N, M, K, maxCity;
	static long minDist[], max;
	static List<Node>[] city;
	static PriorityQueue<Node> pq;
	
	static void init() {
		minDist = new long[N+1];
		Arrays.fill(minDist, INF);
		city = new List[N+1];
		for(int i = 1; i <= N; i++) {
			city[i] = new ArrayList<>();
		}
		pq = new PriorityQueue<>();
		maxCity = N;
		max = -1;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		init();
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			/* 
			 * 방향 그래프인데 u -> v 형태로 저장하면
			 * 타도시 -> 면접장이 아닌 면접장 -> 타도시로 연산되기 때문에
			 * 반대로 v->u 저장해야함
			 */ 
			city[v].add(new Node(u, c));
		}
		
		st = new StringTokenizer(br.readLine());
		for(int k = 0; k < K; k++) {
			int start = Integer.parseInt(st.nextToken());
			pq.add(new Node(start, 0));
			minDist[start] = 0;
		}
		
		dijkstra();
		findFurthest();
		
		System.out.println(maxCity);
		System.out.println(max);
	}
	
	static void dijkstra() {
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			// 이미 처리된 최단거리보다 현재 거리가 더 크면 continue
			if(minDist[curr.to] < curr.weight) continue;
			
			for(Node adjCity : city[curr.to]) {
				long nextWeight = curr.weight + adjCity.weight;
				
				if(minDist[adjCity.to] > nextWeight) {
					minDist[adjCity.to] = nextWeight;
					pq.add(new Node(adjCity.to, nextWeight));
				}
			}
		}
	}
	
	static void findFurthest() {
		for(int i = N; i > 0; i--) {
			if(max > minDist[i]) continue;
			maxCity = i;
			max = minDist[i];
		}
	}
}
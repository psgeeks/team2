import java.io.*;
import java.util.*;

public class BOJ_1707_이분그래프 {
	static int K, V, E, color[];
	static List<Integer>[] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < K; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			color = new int[V+1];
			
			graph = new List[V+1];
			//그래프 초기화
			for(int i = 0; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}
			
			// 입력
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				graph[v1].add(v2);
				graph[v2].add(v1);
			}

			bipartiteGraph();
		}
	}
	
	static void bipartiteGraph() {
		Queue<Integer> q = new ArrayDeque<>();
		
		int c = 1;	// 색상 토글 변수 (-1, 1)
		
		for(int i = 1; i <= V; i++) {
			// 간선이 없는 노드나 이미 색칠된 노드는 넘어감
			if(color[i] != 0) continue;
			
			q.add(i);
			color[i] = c;
			while(!q.isEmpty()) {
				int curr = q.poll();
				
				for(int next : graph[curr]) {
					//현재 노드와 색이 같으면 이분 그래프 불가능
					if(color[next] == color[curr]) {
						System.out.println("NO");
						return;
					}
					//현재 노드와 다른색이 이미 칠해져 있으면 넘어감
					if(color[next] != 0) continue;
					
					color[next] = -color[curr];
					q.add(next);
				}
			}
		}
		System.out.println("YES");
	}
}
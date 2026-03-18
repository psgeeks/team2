package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_2211_네트워크복구 {
    static int n, m;
    static List<Node>[] adj;
    static int[] dist;
    static int[] parent;

    static class Node implements Comparable<Node> {
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        dist = new int[n + 1];
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, w));
            adj[v].add(new Node(u, w));
        }

        dijkstra(1);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        int edgeCount = 0;
        for (int i = 2; i <= n; i++) {
            if (parent[i] != 0) {
                edgeCount++;
                sb.append(i).append(" ").append(parent[i]).append("\n");
            }
        }

        System.out.println(edgeCount);
        System.out.print(sb);
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (dist[curr.to] < curr.weight) continue;

            for (Node next : adj[curr.to]) {
                if (dist[next.to] > dist[curr.to] + next.weight) {
                    dist[next.to] = dist[curr.to] + next.weight;
                    // 최단 경로 갱신 -> 부모 노드 기록
                    parent[next.to] = curr.to;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }
    }
}
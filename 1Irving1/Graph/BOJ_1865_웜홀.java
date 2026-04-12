import java.util.*;
import java.io.*;

class Edge {
    int start, end, time;

    public Edge(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}

public class Main {
    static int N, M, W;
    static ArrayList<Edge> edges;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();
            dist = new int[N + 1];

            // 도로 정보: 양방향
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, t));
                edges.add(new Edge(e, s, t));
            }

            // 웜홀 정보: 단방향, 시간 감소
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, -t));
            }

            if (bellmanFord()) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static boolean bellmanFord() {
        Arrays.fill(dist, 0);

        for (int i = 1; i <= N; i++) {
            for (Edge edge : edges) {
                // 더 짧은 경로 발견 시 갱신
                if (dist[edge.end] > dist[edge.start] + edge.time) {
                    dist[edge.end] = dist[edge.start] + edge.time;
                    
                    // N번째에도 갱신되면 음수 사이클 존재
                    if (i == N) return true;
                }
            }
        }
        return false;
    }
}
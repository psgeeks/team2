package day0310;
import java.util.*;
import java.io.*;
 
/*
 * 메모리: 179,280kb, 실행시간: 1,359ms
 */
public class SWEA_1855_영준이의진짜BFS {
    static int N;
    static int[][] parent;
    static List<Integer>[] edges; // 자식 배열 저장 
    static int[] depth;
    static int LOG;
    static long cnt; // bfs할때 지나가는 총 간선의 개수 
    static List<Integer> orders; // 노드 방문순서 
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
             
            N = Integer.parseInt(br.readLine());
            LOG = (int)(Math.log(N)/Math.log(2))+1;
            parent = new int[N+1][LOG];
            edges = new List[N+1];
            depth = new int[N+1];
            for(int i = 1; i <= N; i++) edges[i] = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            parent[1][0] = 1;
            for(int i = 2; i <= N; i++) {
                int par = Integer.parseInt(st.nextToken());
                parent[i][0] = par; // 바로 위 부모 세팅 
                edges[par].add(i); // 자식 추가 
            }
            // 부모 배열 전체 세팅 
            makeParent();
             
            orders = new ArrayList<>();
            makeOrders();
             
            cnt = 0;
            for(int i = 1; i < orders.size(); i++) {
                long dist = calcDist(orders.get(i-1), orders.get(i));
                cnt += dist;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
         
    }
     
    // pre노드에서 cur노드로 지나갈 때 간선 개수 구하기 
    static long calcDist(int pre, int cur) {
        int lca = getLCA(pre, cur);
                 
        // lca까지 거리 계산하기
        long dist = (depth[pre] - depth[lca]) + (depth[cur]-depth[lca]);
        return dist;
    }
     
    static int getLCA(int a, int b) {
        // a가 b보다 depth가 더 크게 만들기
        if(depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }
         
        // a랑 b의 depth 동일하게 만들기
        for(int k = LOG-1; k >= 0; k--) {
            if(depth[a] - (1<<k) >= depth[b]) {
                a = parent[a][k];
            }
        }
                 
        // a와 b가 같으면 그게 바로 LCA
        if(a == b) return a;
         
        // a와 b가 다르면 부모가 같을때까지 트리를 올라가기
        for(int k = LOG-1; k >= 0; k--) {
            if(parent[a][k] != parent[b][k]) {
                a = parent[a][k];
                b = parent[b][k];
            }
        }
        return parent[a][0];
    }
     
     
    static void makeOrders() {
        // 루트노드인 1부터 시작
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
         
        while(!q.isEmpty()) {
            int cur = q.poll();
            orders.add(cur);
            for(int child : edges[cur]) {
                depth[child] = depth[cur]+1;
                q.add(child);
            }
        }
    }
     
    static void makeParent() {
        for(int k = 1; k < LOG; k++) {
            for(int i = 1; i <= N; i++) {
                parent[i][k] = parent[parent[i][k-1]][k-1];
            }
        }
    }
}
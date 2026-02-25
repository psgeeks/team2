package baekjoon;

import java.io.*;
import java.util.*;

public class BOJ_1600_말이되고픈원숭이 {

    static int K, W, H;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static int hdr[] = {-2, -2, 2, 2, -1, -1, 1, 1};
    static int hdc[] = {-1, 1, -1, 1, -2, 2, -2, 2};
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        //map 배열 입력
        map = new int[H][W];
        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //visited 배열 - 행, 열, 말 사용 횟수
        visited = new boolean[H][W][K+1];

        bfs(0, 0);

    }

    static void bfs(int r, int c){
        Queue<int[]> queue = new LinkedList<>();
        //r, c, 말 사용, 이동거리
        queue.add(new int[]{r, c, 0, 0});
        visited[r][c][0] = true;
        while(!queue.isEmpty()){
            int curr[] = queue.poll();
            int currR = curr[0];
            int currC = curr[1];
            int move = curr[2];
            int dist = curr[3];

            //목적지 도착 시 반환
            if(currR==H-1&&currC==W-1) {
                System.out.println(dist);
                return;
            }

            //원숭이 움직임
            for(int d=0; d<4; d++){
                int nr = currR + dr[d];
                int nc = currC + dc[d];

                if(nr>=0&&nr<H&&nc>=0&&nc<W&&map[nr][nc]==0){
                    if(!visited[nr][nc][move]){
                        visited[nr][nc][move] = true;
                        queue.add(new int[]{nr, nc, move, dist+1});
                    }
                }
            }

            //말 움직임
            if(move<K) {
                for (int d = 0; d < 8; d++) {
                    int nr = currR + hdr[d];
                    int nc = currC + hdc[d];

                    if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == 0) {
                        if (!visited[nr][nc][move+1]) {
                            visited[nr][nc][move+1] = true;
                            queue.add(new int[]{nr, nc, move + 1, dist + 1});
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
}

package day0420;

import java.util.*;

/*
 * 메모리:47.6mb 시간:10ms
 */

public class Leetcode_417_PacificAtlanticWaterFlow_최적화 {
	static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        Queue<int[]> pq = new ArrayDeque<>();
        Queue<int[]> aq = new ArrayDeque<>();

        // Pacific: 위쪽 행 + 왼쪽 열
        for (int i = 0; i < m; i++) {
            pq.add(new int[]{i, 0});
            pacific[i][0] = true;
        }
        for (int j = 0; j < n; j++) {
            pq.add(new int[]{0, j});
            pacific[0][j] = true;
        }

        // Atlantic: 아래쪽 행 + 오른쪽 열
        for (int i = 0; i < m; i++) {
            aq.add(new int[]{i, n - 1});
            atlantic[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            aq.add(new int[]{m - 1, j});
            atlantic[m - 1][j] = true;
        }

        bfs(heights, pq, pacific);
        bfs(heights, aq, atlantic);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    static void bfs(int[][] heights, Queue<int[]> q, boolean[][] visited) {
        int m = heights.length;
        int n = heights[0].length;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (visited[nr][nc]) continue;

                // 역방향: 현재 칸보다 높거나 같은 칸으로 이동 가능
                if (heights[nr][nc] < heights[r][c]) continue;

                visited[nr][nc] = true;
                q.add(new int[]{nr, nc});
            }
        }
    }
}

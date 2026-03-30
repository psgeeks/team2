import java.io.*;
import java.util.*;

public class BOJ_14658_하늘에서별똥별이빗발친다 {
    static int N, M, L, K;
    static List<Point> stars;

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stars = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new Point(x, y));
        }

        int maxStars = 0;

        // 모든 별똥별의 x y좌표를 조합하여 트램펄린의 좌상단 시작점 결정
        for (Point s1 : stars) {
            for (Point s2 : stars) {
                maxStars = Math.max(maxStars, countStars(s1.x, s2.y));
            }
        }

        // 전체 별똥별 수 - 최대 튕겨낸 수 = 지면에 떨어진 수
        System.out.println(K - maxStars);
    }

    private static int countStars(int startX, int startY) {
        int count = 0;
        for (Point star : stars) {
        	// 트램펄린 범위 안에 별이 포함되면 count++;
            if (star.x >= startX && star.x <= startX + L &&
                star.y >= startY && star.y <= startY + L) {
                count++;
            }
        }
        return count;
    }
}
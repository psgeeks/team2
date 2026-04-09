package day0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 메모리: 17988kb 시간: 164ms
 */

public class BOJ_13549_숨바꼭질3 {
	static int d[] = {-1,+1,2};
	static int time[];
	static int N,K;

	static void bfs() {
		Deque<Integer> q = new ArrayDeque<>();
		q.add(N);

		Arrays.fill(time, Integer.MAX_VALUE);
		time[N] = 0;

		while(!q.isEmpty()) {
			int cur = q.poll();

			if(cur==K) return; //가지치기

			for(int i=0;i<3;i++) {
				int n,nt;
				if(i==2) { //순간이동
					n = cur*2;
					nt = time[cur];
				}else { //걷기
					n = cur + d[i];
					nt = time[cur] + 1;
				}
				if(n>=0 && n<200001 && time[n]>nt) {
					time[n] = nt;
					if(i==2) q.addFirst(n); //순간이동이면 앞에 넣기
					else q.addLast(n);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		//목적지가 시작점보다 왼쪽이면 순간이동은 도움 안 됨
		if (K <= N) {
			System.out.println(N - K);
			return;
		}

		time = new int[200001];


		bfs();

		System.out.println(time[K]);

	}

}

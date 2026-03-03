package day0303;

import java.util.*;
import java.io.*;

/*
 * 메모리: 48,648kb, 실행시간: 416ms
 */
public class BOJ_2342 {	
	static int[][] dp = new int[5][5];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < 5; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
		dp[0][0] = 0;
		
		while(true) {
			int x = Integer.parseInt(st.nextToken());
			if(x == 0) break;
			
			// 다음 단계의 dp 값 배열 생성 
			int[][] ndp = new int[5][5];
			for(int i = 0; i < 5; i++) Arrays.fill(ndp[i], Integer.MAX_VALUE);
			
			for(int left = 0; left < 5; left++) {
				for(int right = 0; right < 5; right++) {
					if(dp[left][right]==Integer.MAX_VALUE) continue;
					// 왼발로 x를 밟는 경우 
					ndp[x][right] = Math.min(ndp[x][right], dp[left][right]+cost(left, x));
					// 오른발로 x를 밟는 경우 
					ndp[left][x] = Math.min(ndp[left][x], dp[left][right]+cost(right, x));
				}
			}
			// dp 배열 다음단계로 업데이트 
			dp = ndp;
		}
		
		int answer = Integer.MAX_VALUE;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				answer = Math.min(answer, dp[i][j]);
			}
		}
		System.out.println(answer);
	}
	
	// 발판 a에서 b로 옮길 때 필요한 힘을 반환한다. 
	static int cost(int a, int b) {
		if(a == b) return 1; // 같은거 한번 더 누르기는 힘 1 사용 
		if(a == 0 || b == 0) return 2; // 중앙에서 누르는거는 힘 2 사용 
		if(Math.abs(a-b) == 2) return 4; // 반대편 발판끼리 누르는 거는 힘4 사용  
		return 3; // 인접한 발판끼리 누르는 거는 힘3 사용
	}
	
}

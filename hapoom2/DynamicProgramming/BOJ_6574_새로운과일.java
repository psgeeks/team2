package day0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 메모리:14,092kb 실행시간:104ms
 */

public class BOJ_6574_새로운과일 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.trim().isEmpty()) continue;

			StringTokenizer st = new StringTokenizer(line);
			StringBuilder sb = new StringBuilder();

			char a[] = st.nextToken().toCharArray();
			char b[] = st.nextToken().toCharArray();

			int lenA = a.length;
			int lenB = b.length;

			//LCS구하기
			int dp[][] = new int[lenA+1][lenB+1];
			for(int i=1;i<=lenA;i++) {
				for(int j=1;j<=lenB;j++) {
					if(a[i-1]==b[j-1]) {
						dp[i][j] = dp[i-1][j-1] + 1;
					}else {
						dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
					}
				}
			}

			//SCS복원
			int i = lenA, j = lenB;
			while(i>0&&j>0) {
				if(a[i-1]==b[j-1]) {
					sb.append(a[i-1]);
					i--;
					j--;
				}else if(dp[i-1][j]>=dp[i][j-1]) {
					sb.append(a[i-1]);
					i--;
				}else {
					sb.append(b[j-1]);
					j--;
				}
			}

			while(i>0) sb.append(a[--i]);
			while(j>0) sb.append(b[--j]);

			System.out.println(sb.reverse().toString());

		}
	}

}

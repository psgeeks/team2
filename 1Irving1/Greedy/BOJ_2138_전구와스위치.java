package Practice;

import java.io.*;
import java.util.*;

//메모리 - 18992 kb
//실행시간 - 184 ms

public class BOJ_2138_전구와스위치 {

	static int N;
	static int[] curr1, curr2, target;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());

		//배열 입력
		curr1 = new int[N];
		curr2 = new int[N];
		target = new int[N];
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		for(int i=0; i<N; i++) {
			curr1[i] = str1.charAt(i) - '0'; 
			curr2[i] = str1.charAt(i) - '0';
			target[i] = str2.charAt(i) - '0';
		}
		
		//1번째 전구를 누를 때
		push(curr1, 0);
		
		//2가지 배열 모두 solve 함수 진행
		int res1 = solve(curr1, 1);
		int res2 = solve(curr2, 0);
		
		int result = Math.min(res1,res2);
		
		//result와 다르면 -1 출력
		if(result==Integer.MAX_VALUE) {
			sb.append(-1);
		}else {
			sb.append(result);
		}

		
		System.out.println(sb);
	}
	
	//현재 idx 값보다 하나 앞의 값을 확인하는 것이 핵심
	public static int solve(int[] arr, int count) {
		for(int i=1; i<N; i++) {
			if(arr[i-1]!=target[i-1]) {
				push(arr, i);
				count++;
			}
		}
		
		if(arr[N-1]!=target[N-1]) return Integer.MAX_VALUE;
		
		return count;
	}

	public static void push(int[] arr, int idx) {
		for(int i=idx-1; i<=idx+1; i++) {
			if(i>=0&&i<N) arr[i] = 1 - arr[i];
		}
	}

}
import java.io.*;
import java.util.*;

/*
 * 메모리: 185,836kb, 실행시간: 780ms
 */
public class BOJ_14003_가장긴증가하는부분수열5 {
	static int N;
	static int[] A;
	
	// 가장 긴 증가하는 수열 길이;
	static int[] INC;
	
	// 가장 긴 증가하는 수열 원소 저장
	static int[] LIS;
	static int lenLIS;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        for(int i = 0; i < N; i++) {
        	A[i] = Integer.parseInt(st.nextToken());
        }
        
        INC = new int[N];
        LIS = new int[N];
        Arrays.fill(LIS, Integer.MIN_VALUE);
        for(int i = 0; i < N; i++) {
        	int idx = Arrays.binarySearch(LIS, 0, lenLIS, A[i]);
        	if(idx < 0) idx = -idx - 1;
        	
        	LIS[idx] = A[i];
        	INC[i] = idx + 1;
        	if(idx == lenLIS) {
        		lenLIS++;
        	}
        }
        
        Deque<Integer> ans = new ArrayDeque<>();
        int target = lenLIS;
        for(int i = N-1; i >= 0; i--) {
        	if(INC[i] == target) {
        		ans.addLast(A[i]);
        		target--;
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(lenLIS).append("\n");
        while(!ans.isEmpty()) {
        	sb.append(ans.pollLast()).append(" ");
        }
        sb.append("\n");
        System.out.print(sb);
    }
}
import java.io.*;
import java.util.*;

/*
 * 메모리: 14,404kb, 실행시간: 112ms
 */
public class BOJ_11054_가장긴바이토닉부분수열_binarysearch사용 {
	static int N;
	static int[] A;
	
	// 가장 긴 증가하는 수열 길이, 가장 긴 감소하는 수열 길이 저장
	static int[] INC, DEC;
	
	// 가장 긴 증가하는 수열, 가장 긴 감소하는 수열 원소 저장
	static int[] LIS, LDS;
	static int lenLIS, lenLDS;

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
        for(int i = 0; i < N; i++) {
        	int idx = Arrays.binarySearch(LIS, 0, lenLIS, A[i]);
        	if(idx < 0) idx = -idx - 1;
        	
        	LIS[idx] = A[i];
        	INC[i] = idx + 1;
        	if(idx == lenLIS) lenLIS++;
        }
        
        DEC = new int[N];
        LDS = new int[N];
        
        for(int i = N-1; i >= 0; i--) {
        	int idx = Arrays.binarySearch(LDS, 0, lenLDS, A[i]);
        	if(idx < 0) idx = -idx - 1;
        	
        	LDS[idx] = A[i];
        	DEC[i] = idx + 1;
        	if(idx == lenLDS) lenLDS++;
        }
        
        int ans = 0;
        for(int i = 0; i < N; i++) {
        	// 본인을 2번 포함하니까 1을 빼줘야 함. 
        	ans = Math.max(ans, INC[i] + DEC[i] - 1);
        }
        System.out.print(ans);
    }
}
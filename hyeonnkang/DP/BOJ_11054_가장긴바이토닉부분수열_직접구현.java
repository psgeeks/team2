import java.io.*;
import java.util.*;

/*
 * 메모리: 14,360kb, 실행시간: 112ms
 */
public class BOJ_11054_가장긴바이토닉부분수열_직접구현 {
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
        	// i번째 원소로 끝나는 가장 긴 증가하는 수열의 길이를 저장 
        	INC[i] = saveLIS(i);
        }
        
        DEC = new int[N];
        LDS = new int[N];
        
        for(int i = N-1; i >= 0; i--) {
        	DEC[i] = saveLDS(i);
        }
        
        int ans = 0;
        for(int i = 0; i < N; i++) {
        	// 본인을 2번 포함하니까 1을 빼줘야 함. 
        	ans = Math.max(ans, INC[i] + DEC[i] - 1);
        }
        System.out.print(ans);
    }
    
    // idx 원소를 포함했을 때 가장 긴 감소하는  수열의 길이 반환 
    static int saveLDS(int idx) {
    	int value = A[idx];
    	
    	int left = 0, right = lenLDS-1;
    	while(left <= right) {
    		int mid = (left + right) / 2;
    		
    		if(LDS[mid] < value) {
    			left = mid + 1;
    		}else {
    			right = mid-1;
    		}
    	}
    	LDS[left] = value;
    	
    	if(left == lenLDS) lenLDS++;
    	
    	return left + 1;
    }
    
    // idx 원소를 포함했을 때 가장 긴 증가하는 수열의 길이 반환 
    static int saveLIS(int idx) {
    	int value = A[idx];
    	
    	int left = 0, right = lenLIS-1;
    	while(left <= right) {
    		int mid = (left + right) / 2;
    		
    		if(LIS[mid] < value) {
    			left = mid + 1;
    		}else {
    			right = mid-1;
    		}
    	}
    	LIS[left] = value;
    	
    	if(left == lenLIS) lenLIS++;
    	
    	return left + 1;
    }
}
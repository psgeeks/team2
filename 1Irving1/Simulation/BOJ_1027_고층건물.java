import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] building;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		building = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			building[i] = Integer.parseInt(st.nextToken());
		}
		
		//0~N-1까지 가면서
		//왼쪽으로 자신보다 작은 빌딩 수를 count
		//오른쪽으로 자신보다 작은 빌딩 수를 count
		//이전까지의 max값이랑 비교해서 더 크면 maxIdx와 max 값 갱신하기
		int maxIdx = 0;
		int maxCnt = 0;
		
		
		for(int i=0; i<N; i++) {
			int count = 0; // 현재 볼 수 있는 건물 count
			//i의 왼쪽
			double minInclination = Double.MAX_VALUE;
			for(int j=i-1; j>=0; j--) {
				if(j==i-1||calc(i, j)<minInclination) {
					count++;
					minInclination = calc(i, j);
				}
			}
			//i의 오른쪽
			double maxInclination = Double.MIN_VALUE;
			for(int j=i+1; j<N; j++) {
				if(j==i+1||calc(i, j)>maxInclination) {
					count++;
					maxInclination = calc(i, j);
				}
			}
			
			if(count>maxCnt) {
				maxCnt = count;
				maxIdx = i;
			}
			
		}
		System.out.println(maxCnt);
	}
	
	public static double calc(int a, int b) {
		double length = a-b;
		double height = building[a]-building[b];
		
		double inclination = height / length;
		
		return inclination;
	}
}
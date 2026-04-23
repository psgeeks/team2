import java.io.*;
import java.util.*;

/*
 * 메모리: 386,960kb, 실행시간: 2,148ms
 */
public class BOJ_17411_가장긴증가하는부분수열6_강현_배열2개사용 {
	static final int MOD = (1_000_000_000 + 7);
	
	static int N;
	static int[] A;
	static int size;
	static int[] treeLen;
	static long[] treeCnt;

	public static void main(String[] args) throws Exception{
		
		getInput();
		
		// order[i] = A[i]가 배열 A 내에서 몇번째로 작은 숫자인지
		int[] order = calcOrder();
		
		// 세그먼트 트리 초기화
		treeLen = new int[4*size];
		treeCnt = new long[4*size];
		Arrays.fill(treeLen, 0);
		Arrays.fill(treeCnt, 0);
		
		for(int i = 0; i < N; i++) {
			// 현재 A[i]가 몇번째 수인지 
			int curOrder = order[i];
			
			// curOrder보다 작은 값들끼리 계산했을 때 가장 긴 LIS와 그 개수(경우의 수)를 구한다.
			// pre[0] : LIS길이 pre[1] : 경우의 수
			long[] pre = query(1, 1, size, 1, curOrder - 1);
			
			// 본인보다 작은 값까지의 가장 긴 LIS에 본인만 덧붙이면 된다.
			int curLen = (int)pre[0] + 1;
			long curCnt = pre[1];
			
			// 개수가 없으면 현재가 시작점이니까 개수를 1개로 설정한다. 
			if(curCnt == 0) curCnt = 1;
			
			// 현재 계산한 값을 tree에 저장한다
			update(1, 1, size, curOrder, curLen, curCnt);
		}
		
		// 전체 구간에서 가장 긴 LIS의 정보 
		System.out.print(treeLen[1] + " " + treeCnt[1]);
	}
	
	static void update(int idx, int start, int end, int order, int len, long cnt) {
		// 리프노드에 도착했으면 tree[idx]에 가장 긴 노드 길이를 저장한다.
		if(start == end) {
			// 현재 길이가 더 길면 덮어쓴다.
			if(len > treeLen[idx]) {
				treeLen[idx] = len;
				treeCnt[idx] = cnt;
			}else if(len == treeLen[idx]) {
				// 같으면 합친다.
				treeCnt[idx] = (treeCnt[idx] + cnt) % MOD;
			}
			return;
		}
		
		// 왼쪽, 오른쪽 중에 order가 포함된 방향으로 내려가서 업데이트 수행 
		int mid = (start + end) / 2;
		if (order <= mid) {
	        update(2 * idx, start, mid, order, len, cnt);
	    } else {
	        update(2 * idx + 1, mid + 1, end, order, len, cnt);
	    }
		
		// 자식 노드에서 업데이트 했으면, 본인 노드도 업데이트한다.
	    merge(idx);
	}
	
	static long[] defRes = {0, 0};
	static long[] query(int idx, int start, int end, int left, int right) {
		// 범위를 벗어났을 경우 그냥 초기값을 반환한다. 
		if(right < start || left > end) {
			return defRes;
		}
		
		// 완전히 구간 내에 있을 경우
		if(left <= start && right >= end) {
			return new long[] {treeLen[idx], treeCnt[idx]};
		}
		
		// 왼쪽, 오른쪽 나누어서 다시 가장 긴 LIS를 탐색한다.
		int mid = (start + end ) / 2;
		long[] l = query(2 * idx,  start, mid, left, right);
        long[] r = query(2 * idx + 1, mid + 1, end, left, right);
        if(l[0] > r[0]) return l;
        else if(l[0] < r[0]) return r;
        else return new long[] {l[0], (l[1]+r[1]) % MOD};
	}
	
	// left 노드, right 노드 중에 더 긴 LIS의 길이와 개수를 반환한다. 
	static void merge(int idx) {
		int left = 2 * idx;
		int right = 2 * idx + 1;
		if(treeLen[left] > treeLen[right]) {
			treeLen[idx] = treeLen[left];
			treeCnt[idx] = treeCnt[left];
		}
		else if(treeLen[left] < treeLen[right]) {
			treeLen[idx] = treeLen[right];
			treeCnt[idx] = treeCnt[right];
		}
		else {
			treeLen[idx] = treeLen[left];
			treeCnt[idx] = (treeCnt[left] + treeCnt[right]) % MOD;
		}
	}
	
	static int[] calcOrder() {
		int[] temp = new int[N];
		System.arraycopy(A, 0, temp, 0, N);
		Arrays.sort(temp);
		
		HashMap<Integer, Integer> map = new HashMap<>();
		int idx = 1;
		map.put(temp[0], idx++);
		for(int i = 1; i < N; i++) {
			if(temp[i] != temp[i-1]) map.put(temp[i], idx++);
		}
		size = idx;
		int[] res = new int[N];
		for(int i = 0; i < N; i++) {
			res[i] = map.get(A[i]);
		}
		return res;
	}
	
	static void getInput() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
	}
	
}

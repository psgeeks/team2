import java.io.*;
import java.util.*;

/*
 * 메모리: 482,984kb, 실행시간: 2,528ms
 */
public class BOJ_17411_가장긴증가하는부분수열6_강현_노드객체사용 {
	static final int MOD = (1_000_000_000 + 7);
	
	static int N;
	static int[] A;
	static int size;
	static Node[] tree;
	
	static class Node{
		int len;
		long cnt;
		Node(int len, long cnt){
			this.len = len;
			this.cnt = cnt;
		}
	}
		
	public static void main(String[] args) throws Exception{
		
		getInput();
		
		// order[i] = A[i]가 배열 A 내에서 몇번째로 작은 숫자인지
		int[] order = calcOrder();
		
		// 세그먼트 트리 초기화
		tree = new Node[4*size];
		for(int i = 0; i < 4*size; i++) {
			tree[i] = new Node(0, 0);
		}
		
		for(int i = 0; i < N; i++) {
			// 현재 A[i]가 몇번째 수인지 
			int curOrder = order[i];
			
			// curOrder보다 작은 값들끼리 계산했을 때 가장 긴 LIS와 그 개수(경우의 수)를 구한다.
			Node pre = query(1, 1, size, 1, curOrder - 1);
			
			// 본인보다 작은 값까지의 가장 긴 LIS에 본인만 덧붙이면 된다.
			int curLen = pre.len + 1;
			long curCnt = pre.cnt;
			
			// 개수가 없으면 현재가 시작점이니까 개수를 1개로 설정한다. 
			if(curCnt == 0) curCnt = 1;
			
			// 현재 계산한 값을 tree에 저장한다
			update(1, 1, size, curOrder, curLen, curCnt);
		}
		
		// 전체 구간에서 가장 긴 LIS의 정보 
		Node lis = tree[1];
		System.out.print(lis.len + " " + lis.cnt);
	}
	
	static void update(int idx, int start, int end, int order, int len, long cnt) {
		// 리프노드에 도착했으면 tree[idx]에 가장 긴 노드 길이를 저장한다.
		if(start == end) {
			// 현재 길이가 더 길면 덮어쓴다.
			if(len > tree[idx].len) {
				tree[idx].len = len;
				tree[idx].cnt = cnt;
			}else if(len == tree[idx].len) {
				// 같으면 합친다.
				tree[idx].cnt = (tree[idx].cnt + cnt) % MOD;
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
	    tree[idx] = merge(tree[2 * idx], tree[2 * idx + 1]);
	}
	
	static Node query(int idx, int start, int end, int left, int right) {
		// 범위를 벗어났을 경우 그냥 초기값을 반환한다. 
		if(right < start || left > end) {
			return new Node(0, 0);
		}
		
		// 완전히 구간 내에 있을 경우
		if(left <= start && right >= end) {
			return tree[idx];
		}
		
		// 왼쪽, 오른쪽 나누어서 다시 가장 긴 LIS를 탐색한다.
		int mid = (start + end ) / 2;
		return merge(query(idx*2, start, mid, left, right), query(idx*2+1, mid+1, end, left, right));
	}
	
	// left 노드, right 노드 중에 더 긴 LIS의 길이와 개수를 반환한다. 
	static Node merge(Node left, Node right) {
		if(left.len > right.len) return left;
		else if(left.len < right.len) return right;
		else return new Node(left.len, (left.cnt + right.cnt) % MOD);
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

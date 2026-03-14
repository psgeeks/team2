package day0314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
/**
 * 메모리:48672kb 시간:552ms
 */

public class BOJ_4195_친구네트워크 {
	static int F,parent[],arrF[],rank[],size[],idx;
	static HashMap<String, Integer> mapF;
	
	static int getFriend(String name) {
		//HashMap에 이미 이름이 있으면 인덱스 반환
		if(mapF.get(name)!=null) return mapF.get(name); 
		
		//없으면 넣고 인덱스 반환
		mapF.put(name, idx++);
		return mapF.get(name);
	}
	
	static void makeSet() {
		for(int i=0;i<parent.length;i++) {
			parent[i] = i;
			rank[i] = 0;
			size[i] = 1;
		}
	}
	
	static int findSet(int a) {
		if(parent[a] == a) return a;
		return parent[a] = findSet(parent[a]); //경로압축
	}
	
	static int Union(int a,int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		
		if(rootA==rootB) return size[rootA]; //같은 집합인 경우
		if(rank[rootA]>rank[rootB]) { //A의 랭크가 더 클 경우 A에 B를 붙임
			parent[rootB] = rootA;
			size[rootA] += size[rootB];
			return size[rootA];
		}else if(rank[rootB]>rank[rootA]) { //B의 랭크가 더 클 경우 B에 A를 붙임
			parent[rootA] = rootB;
			size[rootB] += size[rootA];
			return size[rootB];
		}else { //랭크가 같을 경우 A에 B를 붙임
			parent[rootB] = rootA;
			size[rootA] += size[rootB];
			rank[rootA] += 1;
			return size[rootA];
		}
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			F = Integer.parseInt(br.readLine());
			mapF = new HashMap<String, Integer>();
			idx = 0;
			//최악의 경우 매 친구관계마다 새로운 이름 2개가 나올 수 있으므로 배열 크기는 F*2
			parent = new int[F*2]; 
			rank = new int[F*2];
			size = new int[F*2];
			
			makeSet();
			
			for(int i=0;i<F;i++) {
				st = new StringTokenizer(br.readLine());
				
				int F1 = getFriend(st.nextToken());
				int F2 = getFriend(st.nextToken());
				
				sb.append(Union(F1,F2)).append("\n");
			}
			
		}
		System.out.println(sb);
	}

}

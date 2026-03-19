package day0319;

import java.io.*;
import java.util.*;

/*
 * 메모리: 255,440kb, 실행시간: 1,004ms
 */
public class BOJ_5052_전화번호목록 {
	static int n;
	
	static class Node{
		Map<Character, Node> child = new HashMap<>();
		boolean isEndHere = false;
	}
	
	static class Trie{
		Node root = new Node();
		
		void insert(String s) {
			Node cur = root;
			
			for(char c : s.toCharArray()) {
				cur.child.putIfAbsent(c, new Node());
				cur = cur.child.get(c);
			}
			cur.isEndHere = true;
		}
		
		boolean isTherePrefix(String s) {
			Node cur = root;
			for(int i = 0; i < s.length(); i++) {
				if(cur.isEndHere) return true;
				
				char c = s.charAt(i);
				if(!cur.child.containsKey(c)) return false;
				
				cur = cur.child.get(c);
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {        	
        	n = Integer.parseInt(br.readLine());
        	
        	Trie trie = new Trie();
        	boolean res = true;
        	
        	String[] phones = new String[n];
        	for(int i = 0; i < n; i++) {
        		String phone = br.readLine();
        		
        		phones[i] = phone;
        		
        	}
        	
        	// 길이가 작은것 부터 먼저 오도록 정렬 
        	Arrays.sort(phones, (a, b) -> {
        		if(a.length() == b.length()) return a.compareTo(b);
        		return a.length() - b.length();
        	});
        	
        	for(String phone : phones) {
        		if(trie.isTherePrefix(phone)) res = false;
        		else trie.insert(phone);
        	}
        	
        	sb.append(res ? "YES" : "NO").append("\n");
        }
        System.out.print(sb);
	}
}

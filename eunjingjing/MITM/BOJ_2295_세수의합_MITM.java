package collection;
import java.io.*;
import java.util.*;

public class BOJ_2295_세수의합_MITM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
        	list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list); //정렬 해주기
        
        // key = k - z , value = k
        Map<Integer, Integer> map = new HashMap<>();
        for(int k = N-1; k >= 1; k--) {
        	for(int z = k-1; z >= 0; z--) {
        		int key = list.get(k) - list.get(z);
        		map.putIfAbsent(key, list.get(k));
        	}
        }
        
        int answer = 0;
        for(int x = 0; x < N; x++) {
        	for(int y = 0; y < N; y++) {
        		int xy = list.get(x) + list.get(y);
        		if(map.containsKey(xy)) {
        			answer = Math.max(answer, map.get(xy));
        		}
        	}
        }
        
        System.out.println(answer);
    }
}
import java.util.*;
import java.io.*;

//메모리 - 123288 kb
//실행시간 - 1636 ms

public class BOJ_1202_보석도둑 {

    static class Jewerly implements Comparable<Jewerly>{
        int m, v;

        Jewerly(int m, int v){
            this.m = m;
            this.v = v;
        }
        //무게 기준 오름차순 정렬
        @Override
        public int compareTo(Jewerly other){
            return this.m - other.m;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewerly[] jewelries = new Jewerly[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            jewelries[i] = new Jewerly(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[] bags = new int[K];
        for(int i=0; i<K; i++){
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewelries);
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        long total = 0;
        int jIdx = 0;

        for(int i=0; i<K; i++){
            while(jIdx<N&&jewelries[jIdx].m<=bags[i]){
                pq.add(jewelries[jIdx].v);
                jIdx++;
            }

            if(!pq.isEmpty()){
                total += pq.poll();
            }
        }
        System.out.println(total);
    }
}

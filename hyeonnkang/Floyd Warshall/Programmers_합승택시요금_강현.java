package day0427;
import java.util.*;

public class Programmers_합승택시요금_강현 {
    static final int INF = 987_654_321;
    int[][] dist;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        // 거리 데이터 초기화 
        dist = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) Arrays.fill(dist[i], INF);
        for(int i = 1; i <= n; i++) dist[i][i] = 0;
        
        // 간선 정보 저장
        for(int[] fare : fares){
            dist[fare[0]][fare[1]] = Math.min(dist[fare[0]][fare[1]], fare[2]);
            dist[fare[1]][fare[0]] = Math.min(dist[fare[1]][fare[0]], fare[2]);
        }
        
        // 시작점 s로부터 모든 지점에 대한 최소 거리 구하기
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                if(i == k) continue;
                for(int j = 1; j <= n; j++){
                    if(j == i) continue;
                    if(dist[i][k] != INF && dist[k][j] != INF){
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        int answer = INF;
        
        // 서로 합승하지 않고 a, b로 가는 경우
        if(dist[s][a] != INF && dist[s][b] != INF) answer = Math.min(answer, dist[s][a] + dist[s][b]);
        
        // 서로 합승하고 k 지점까지 간 후, a, b로 가는 경우
        for(int i = 1; i <= n; i++){
            if(i == s) continue;
             if(dist[s][i] != INF && dist[i][a] != INF && dist[i][b] != INF)
                 answer = Math.min(answer, dist[s][i] + dist[i][a] + dist[i][b]);
        }
        return answer;
    }
}
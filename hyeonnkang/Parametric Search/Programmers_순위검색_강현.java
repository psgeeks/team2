package day0427;
import java.util.*;

public class Programmers_순위검색_강현 {
    
    int n; // 지원서 수
    int m; // 문의 수 
    Map<String, List<Integer>> infos = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        n = info.length;
        
        // 지원서가 만족하는 모든 조건 String 생성하여 infos에 저장
        for(String x : info){
            String[] data = x.split(" ");
            makeString(data, "", 0);
        }
        
        // 지원서들을 점수 순으로 정렬
        for (String key : infos.keySet()) {
            Collections.sort(infos.get(key));
        }
        
        // 문의를 순차적으로 탐색
        m = query.length;
        int[] answer = new int[m];
        for (int i = 0; i < m; i++) {
            String q = query[i].replace(" and ", "");
            String[] qData = q.split(" "); // qData = {조건 문장, 점수};
            answer[i] = countByBinarySearch(qData[0], Integer.parseInt(qData[1]));
        }
        return answer;
    }
    
    // data(지원서 내용)으로 가능한 모든 조합 찾기
    private void makeString(String[] data, String str, int cnt) {
        if (cnt == 4) {
            // key: 생성된 String, value: 점수 리스트
            infos.computeIfAbsent(str, k -> new ArrayList<>()).add(Integer.parseInt(data[4]));
            return;
        }
        // 공백을 제거한채로 각 특성 명시 또는 -(상관없음)으로 String 생성
        makeString(data, str + data[cnt], cnt + 1);
        makeString(data, str + "-", cnt + 1);
    }
    
    // key 조건에 해당하는 지원자 중 점수가 score 이상인 지원자 수를 반환한다.
    private int countByBinarySearch(String key, int score) {
        if (!infos.containsKey(key)) return 0;
        List<Integer> list = infos.get(key);
        int start = 0, end = list.size();

        while (start < end) {
            int mid = (start + end) / 2;
            if (list.get(mid) >= score){
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }
        return list.size() - end;
    }
    
}
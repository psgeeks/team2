package algorithm;

import java.util.*;
import java.io.*;

public class BOJ_2179_비슷한단어 {
    static class Node implements Comparable<Node> {
        String word;
        int index;

        Node(String word, int index) {
            this.word = word;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return this.word.compareTo(o.word);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Node> sortedList = new ArrayList<>();
        String[] originalWords = new String[n];

        // 입력 데이터 및 원본 순서 저장
        for (int i = 0; i < n; i++) {
            originalWords[i] = br.readLine();
            sortedList.add(new Node(originalWords[i], i));
        }

        // 사전순 정렬
        Collections.sort(sortedList);

        // 최대 공통 접두사 길이 계산
        int maxLen = -1;
        for (int i = 0; i < n - 1; i++) {
            int len = getCommonPrefixLength(sortedList.get(i).word, sortedList.get(i + 1).word);
            if (len > maxLen) maxLen = len;
        }

        // 입력 순서 기준 최상단 결과 추출
        for (int i = 0; i < n; i++) {
            String s = originalWords[i];
            for (int j = i + 1; j < n; j++) {
                String t = originalWords[j];

                if (getCommonPrefixLength(s, t) == maxLen) {
                    System.out.println(s);
                    System.out.println(t);
                    return;
                }
            }
        }
    }

    // 공통 접두사 길이 반환
    public static int getCommonPrefixLength(String s1, String s2) {
        int minLen = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLen; i++) {
            if (s1.charAt(i) != s2.charAt(i)) return i;
        }
        return minLen;
    }
}
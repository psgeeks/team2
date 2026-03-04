package collection;
import java.io.*;
import java.util.*;

public class BOJ_2295_세수의합_BinarySearch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        
        // x + y 의 모든 조합을 담을 배열 생성
        int[] sumArr = new int[N * N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sumArr[idx++] = arr[i] + arr[j];
            }
        }
        
        // 실제 저장된 크기만큼만 잘라서 이분 탐색을 위해 정렬
        int[] realSumArr = Arrays.copyOf(sumArr, idx);
        Arrays.sort(realSumArr);
        
        // k - z 가 sumArr에 있는지 이분 탐색으로 확인
        for (int k = N - 1; k >= 0; k--) {
            for (int z = 0; z < N; z++) {
                int target = arr[k] - arr[z];
                
                if (binarySearch(realSumArr, target)) {
                    System.out.println(arr[k]);
                    return; // 가장 큰 값을 찾은 거니까 바로 프로그램 종료
                }
            }
        }
    }

    private static boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            // 오버플로우를 방지용 mid 찾는 식
            int mid = left + (right - left) / 2; 
            
            if (arr[mid] == target) {
                return true; // 타겟을 찾음
            } else if (arr[mid] < target) {
                left = mid + 1; // 타겟이 중간값보다 크면 오른쪽 구간 탐색
            } else {
                right = mid - 1; // 타겟이 중간값보다 작으면 왼쪽 구간 탐색
            }
        }
        
        return false; // 탐색을 다 마쳤는데도 없으면 false 반환
    }
}
package day0522;
import java.util.*;

/*
 * 메모리: 76.5mb 실행시간: 6ms
 */
public class Leetcode_1674_MinimumMovestoMakeArrayComplementary_강현 {
    public int minMoves(int[] nums, int limit) {
        // 2개의 정수를 더해서 가능한 합의 범위 : 2 ~ 2*limit
        int[] diff = new int[2*limit+2];

        // 2개씩 짝짓기
        for(int i = 0; i < nums.length/2; i++){
            int a = nums[i];
            int b = nums[nums.length-1-i];

            int low = Math.min(a, b) + 1;
            int high = Math.max(a, b) + limit;
            int sum = a + b;

            // 기본 비용 2
            diff[2] += 2;

            // [low, high] 구간은 비용 1
            diff[low] -= 1;
            diff[high + 1] += 1;

            // sum은 안바꾸고 바로 구할 수 있으니까 비용 0
            diff[sum] -= 1;
            diff[sum + 1] += 1;
        }

        // 최소 변경 횟수 구하기
        int ans = Integer.MAX_VALUE;
        int cur = 0;
        for(int i = 2; i <= 2*limit; i++){
            cur += diff[i];
            ans = Math.min(ans, cur);
        }
        return ans;
    }
}
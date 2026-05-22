package day0507;

/*
 * 실행시간: 5ms, 메모리: 198.8mb
*/
public class Leetcode_3660_JumpGameIX_강현 {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] preMax = new int[n];

        // preMax[i] : 0번 인덱스부터 i번까지 중 가장 큰 값
        preMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }

        // sufMin : 현재 인덱스 i의 오른쪽에 위치한 값들 중 최솟값
        int sufMin = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                ans[i] = preMax[i];
            }
            // 오른쪽으로 점프 가능.
            else if (preMax[i] > sufMin) {
                ans[i] = ans[i + 1];
            }
            // 오른쪽 점프 불가능. 왼쪽 중 최댓값 선택.
            else {
                ans[i] = preMax[i];
            }
            sufMin = Math.min(sufMin, nums[i]);
        }

        return ans;
    }
}
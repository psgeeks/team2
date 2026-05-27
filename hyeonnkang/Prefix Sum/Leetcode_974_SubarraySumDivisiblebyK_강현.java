package day0522;
import java.util.*;

/*
 * 메모리: 54.78mb, 실행시간: 148ms
 */
public class Leetcode_974_SubarraySumDivisiblebyK_강현 {
    public int subarraysDivByK(int[] nums, int k) {
    	
    	// key: 누적합, value: 누적합이 나온 개수
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        int cnt = 0;

        for (int num : nums) {
            sum += num;

            int mod = sum % k;

            // 나머지가 음수면 안되니까 양수로 바꾸기
            if (mod < 0) mod += k;

            cnt += map.getOrDefault(mod, 0);

            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }

        return cnt;
    }
}
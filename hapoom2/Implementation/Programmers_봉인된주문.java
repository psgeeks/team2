package may2026.day0513;

import java.util.*;

public class Programmers_봉인된주문 {

	// 문자열 -> 순서 번호
	public long toNumber(String s) {
		long num = 0;

		for (int i = 0; i < s.length(); i++) {
			num *= 26;
			num += s.charAt(i) - 'a' + 1;
		}

		return num;
	}

	// 순서 번호 -> 문자열
	public String toString(long n) {
		StringBuilder sb = new StringBuilder();

		while (n > 0) {
			n--; 
			char c = (char) ('a' + (n % 26));
			sb.append(c);
			n /= 26;
		}

		return sb.reverse().toString();
	}

	public String solution(long n, String[] bans) {
		long[] banNums = new long[bans.length];

		for (int i = 0; i < bans.length; i++) {
			banNums[i] = toNumber(bans[i]);
		}

		Arrays.sort(banNums);

		for (long ban : banNums) {
			if (ban <= n) {
				n++;
			} else {
				break;
			}
		}

		return toString(n);

	}

}

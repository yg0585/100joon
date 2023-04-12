package org.example.programmers;

import java.util.Arrays;

public class IpGuk {

	long answer = 0;

	public void recur(long lo, long hi, int[] times, int n) {
		if (lo > hi)
			return ;

		long mid = (lo + hi) / 2;
		long num = 0;
		for (int time : times) {
			num += mid / 2;
		}

		if (num >= n) {
			answer = mid;
			recur(lo, mid - 1, times, n);
		}
		else {
			recur(mid + 1, hi, times, n);
		}
	}
	public long solution(int n, int[] times) {

		Arrays.sort(times);
		recur(0, times[times.length - 1] * n, times, n);
		return answer;
	}
}

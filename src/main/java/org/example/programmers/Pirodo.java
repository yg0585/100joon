package org.example.programmers;

import java.util.Collections;
import java.util.PriorityQueue;

public class Pirodo {
	public long solution(int n, int[] works) {
		long answer = 0;
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

		for(int num : works) {
			queue.add(num);
		}

		while(n > 0) {
			queue.add(queue.poll() - 1);
			n--;
		}

		while (!queue.isEmpty()) {
			int num = queue.poll();
			answer += num * num;
		}
		return answer;
	}

	public static void main(String[] args) {
		Pirodo pirodo = new Pirodo();
		pirodo.solution(4, new int[]{4, 3, 3});
	}
}

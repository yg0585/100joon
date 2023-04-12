package org.example.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Siso {
	Set<Float> floats = new HashSet<>(Set.of((float)2 / 3, 1.0f, 0.5f, 0.75f));
	public long solution(int[] weights) {
		long answer = 0;

		for (int i = 0; i < weights.length - 1; i++) {
			for (int j = i + 1; j < weights.length; j++) {
				if (weights[i] > weights[j])
					answer += check(weights[i], weights[j]);
				else
					answer += check(weights[j], weights[i]);
			}
		}
		return answer;
	}

	private long check(int weight, int weight1) {
		if (floats.contains((float) weight1 / weight))
			return 0;
		return 1;
	}

	public static void main(String[] args) {
		Set<Float> floats = new HashSet<>(Set.of((float)2 / 3));
		System.out.println(floats.add((float)3 / 3));
	}
}

package org.example.samsung;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Puzzle {

	static int n;
	static int[][] map;

	//→, ←, ↓, ↑
	static int[][] directs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	static int max = 0;

	private static void draw(int[][] map) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int[][] move(int[][] map, int direct) {
		Stack<Integer> stack = new Stack<Integer>();
		if (direct == 0) {
			for (int i = 0; i < n; i++) {
				for (int j = n - 1; j >= 0; j--) {
					stacking(map, stack, i, j);
				}

				int tmp = stack.size();

				for (int j = 0; j < n - tmp; j++) {
					map[i][j] = 0;
				}

				for (int j = n - tmp; j < n; j++) {
					map[i][j] = stack.pop();
					if (map[i][j] > max)
						max = map[i][j];
				}
			}
		}
		else if (direct == 1) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					stacking(map, stack, i, j);
				}

				int tmp = stack.size();

				for (int j = 0; j < n - tmp; j++) {
					map[i][n - j - 1] = 0;
				}

				for (int j = n - tmp; j < n; j++) {
					map[i][n - 1 - j] = stack.pop();
				}
			}
		}
		else if (direct == 2) {
			for (int i = 0; i < n; i++) {
				for (int j = n - 1; j >= 0; j--) {
					stacking(map, stack, j, i);
				}

				int tmp = stack.size();

				for (int j = 0; j < n - tmp; j++) {
					map[j][i] = 0;
				}

				for (int j = n - tmp; j < n; j++) {
					map[j][i] = stack.pop();
					if (map[j][i] > max)
						max = map[j][i];
				}
			}
		}
		else if (direct == 3) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					stacking(map, stack, j, i);
				}

				int tmp = stack.size();

				for (int j = 0; j < n - tmp; j++) {
					map[n - j - 1][i] = 0;
				}

				for (int j = n - tmp; j < n; j++) {
					map[n - 1 - j][i] = stack.pop();
				}
			}
		}
		return map;
	}

	private static boolean flag = false;

	private static void stacking(int[][] map, Stack<Integer> stack, int i, int j) {
		if (map[i][j] != 0) {
			if (!stack.isEmpty()) {
				if (map[i][j] == stack.peek() && flag == false) {
					stack.pop();
					stack.push(map[i][j] * 2);
					flag = true;
				}
				else {
					stack.push(map[i][j]);
					flag = false;
				}
			}
			else {
				stack.push(map[i][j]);
				flag = false;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		Queue<int[][]> queue = new ArrayDeque<>();
		queue.add(map);

		for (int i = 0; i < 5; i++) {
			int qsize = queue.size();
			// System.out.println("---------level-----------");
			for (int j = 0; j < qsize; j++) {
				int[][] poll = queue.poll();
				//
				// System.out.println("before");
				// draw(poll);
				for(int k = 0; k < 4; k++) {
					int[][] copyMap = copyArr(poll);
					int[][] tMap = move(copyMap, k);
					queue.add(tMap);
					// System.out.println("After");
					// draw(tMap);
					// System.out.println();
				}
			}
		}
		System.out.println(max);
	}

	private static int[][] copyArr(int[][] poll) {
		int[][] ret = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ret[i][j] = poll[i][j];
			}
		}
		return ret;
	}
}

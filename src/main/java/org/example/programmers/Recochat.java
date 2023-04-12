package org.example.programmers;

import java.util.ArrayDeque;

public class Recochat {
	public int solution(String[] board) {
		int answer = 0;
		boolean[][] visited = new boolean[board.length][board[0].length()];
		int[] initCoord = findStart(board);
		int[][] directs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.add(initCoord);
		visited[initCoord[0]][initCoord[1]] = true;

		while(!queue.isEmpty()) {
			answer++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] poll = queue.poll();
				for (int j = 0; j < 4; j++) {
					int[] tmp = poll.clone();
					while(true) {
						int dx = tmp[0] + directs[j][0];
						int dy = tmp[1] + directs[j][1];
						if (!isInRange(dx, dy, board) || isObstacle(dx, dy, board)) {
							break;
						}
						tmp[0] = dx;
						tmp[1] = dy;
					}
					if (board[tmp[0]].charAt(tmp[1]) == 'G')
						return answer;
					if (!visited[tmp[0]][tmp[1]]) {
						visited[tmp[0]][tmp[1]] = true;
						queue.add(tmp);
					}
				}
			}
		}

		return -1;
	}

	private boolean isObstacle(int dx, int dy, String[] board) {
		if (board[dx].charAt(dy) == 'D')
			return true;
		return false;
	}

	private boolean isInRange(int dx, int dy, String[] board) {
		if (dx <0 || dx >= board.length || dy < 0 || dy >= board[0].length()) {
			return false;
		}
		return true;
	}

	private int[] findStart(String[] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length(); j++) {
				if (board[i].charAt(j) == 'R')
					return new int[]{i, j};
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Integer i = 0;
		check(i);
		System.out.println("i = " + i);
	}

	private static void check(Integer i) {
		i++;
	}
}

package org.example.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/13460
public class Prob13460 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int redX = 0, redY = 0, blueX = 0, blueY = 0;

		char[][] arr = new char[row][col];
		boolean[][][][] visited = new boolean[row][col][row][col];

		for (int i = 0; i < row; i++) {
			String str = br.readLine();
			for(int j = 0; j < col; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == 'R') {
					redX = i;
					redY = j;
				}
				else if(arr[i][j] == 'B') {
					blueX = i;
					blueY = j;
				}
			}
		}

		int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {redX, redY, blueX, blueY});
		visited[redX][redY][blueX][blueY] = true;
		for(int i = 0; i < 10; i++) {
			int size = queue.size();
			for (int j = 0; j < size; j++) {
				int[] poll = queue.poll();
				for (int k = 0; k < 4; k++) {
					boolean blueFlag = false;
					boolean redFlag = false;
					int[] temp = poll.clone();
					while (arr[temp[2] + direction[k][0]][temp[3] + direction[k][1]] != '#'
						|| arr[temp[0] + direction[k][0]][temp[1] + direction[k][1]] != '#') {
						if (arr[temp[2] + direction[k][0]][temp[3] + direction[k][1]] != '#') {
							temp[2] += direction[k][0];
							temp[3] += direction[k][1];
						}
						if (arr[temp[2]][temp[3]] == 'O'){
							blueFlag = true;
							break;
						}
						if (arr[temp[0] + direction[k][0]][temp[1] + direction[k][1]] != '#') {
							temp[0] += direction[k][0];
							temp[1] += direction[k][1];
						}
						if (arr[temp[0]][temp[1]] == 'O'){
							redFlag = true;
						}
					}

					if (temp[0] == temp[2] && temp[1] == temp[3]) {
						int redDistance = Math.abs(poll[0] - temp[0]) + Math.abs(poll[1] - temp[1]);
						int blueDistance = Math.abs(poll[2] - temp[2]) + Math.abs(poll[3] - temp[3]);
						if (blueDistance > redDistance) {
							temp[2] -= direction[k][0];
							temp[3] -= direction[k][1];
						}
						else {
							temp[0] -= direction[k][0];
							temp[1] -= direction[k][1];
						}
					}

					if (blueFlag)
						continue;
					else if(redFlag) {
						System.out.println(i + 1);
						return;
					}
					else if(visited[temp[0]][temp[1]][temp[2]][temp[3]])
						continue;
					else {
						visited[temp[0]][temp[1]][temp[2]][temp[3]] = true;
						queue.add(temp);
					}
				}
			}
		}
		System.out.println(-1);
	}
}

package org.example.baekjoon;

import java.util.Scanner;

//https://www.acmicpc.net/problem/23971
public class Prob23971 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int height = scanner.nextInt();
		int width = scanner.nextInt();
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int xc = 0;
		int yc = 0;

		int x = 1, y = 1;
		while (x <= width) {
			xc++;
			x += m + 1;
		}

		while (y <= height) {
			yc++;
			y += n + 1;
		}
		System.out.println(xc * yc);
	}
}

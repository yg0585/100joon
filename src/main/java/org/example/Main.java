package org.example;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int room = sc.nextInt();

		int UPPER_SIZE = 6;
		int currnet_size = 6;
		int num = 1;
		int cnt = 0;
		while (num < room) {
			if (room == 1)
				break;
			cnt++;
			num += currnet_size;
			currnet_size += UPPER_SIZE;
		}
		System.out.println(cnt + 1);
	}
}
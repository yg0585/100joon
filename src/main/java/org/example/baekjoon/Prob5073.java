package org.example.baekjoon;

import java.util.Scanner;

//https://www.acmicpc.net/problem/5073
public class Prob5073 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int[] triangle = new int[3];
			triangle[0] = sc.nextInt();
			triangle[1] = sc.nextInt();
			triangle[2] = sc.nextInt();

			if (triangle[0] == 0 && triangle[1] == 0 && triangle[2] == 0)
				break;

			if (triangle[0] == triangle[1] && triangle[1] == triangle[2])
				System.out.println("Equilateral");
			else if (triangle[0] >= triangle[1] + triangle[2] ||
				triangle[1] >= triangle[2] + triangle[0] ||
				triangle[2] >= triangle[1] + triangle[0])
				System.out.println("Invalid");
			else if (triangle[0] == triangle[1] || triangle[1] == triangle[2]
				|| triangle[0] == triangle[2])
				System.out.println("Isosceles");
			else
				System.out.println("Scalene");
		}
	}
}

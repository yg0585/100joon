package org.example;
import java.util.Scanner;




class Tornado {
	int x = 0;
	int y = 0;

	int exX;
	int exY;

	int step = 0;

	int step_size = 1;
	int curretn_step_size = 0;

	int direct = 0;

	static int[][] directs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

	int exdirect = 0;

	boolean move() {
		exX = x;
		exY = y;
		x += directs[direct][0];
		y += directs[direct][1];
		exdirect = direct;
		curretn_step_size++;
		if (curretn_step_size == step_size) {

			direct = (direct + 1) % 4;
			curretn_step_size = 0;
			step++;
			if (step % 2 == 0)
				step_size++;
		}

		if (x == 0 && y == 0)
			return true;
		return false;
	}
}

public class Main {

	static int n;

	static int[][] map;

	static int sum = 0;
	static Tornado tornado;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		tornado = new Tornado();
		tornado.x = n/2;
		tornado.y = n/2;
		while (!tornado.move()) {
			oneStep();
		}
		oneStep();
		System.out.println(sum);
	}

	private static void oneStep() {
		int sandX = tornado.x;
		int sandY = tornado.y;
		int tmp = map[sandX][sandY];
		map[sandX][sandY] = 0;

		int minus = 0;
		//9%
		minus += moveNinePercent(tmp, sandX, sandY);
		minus += moveXPercent(tmp, tornado.exX, tornado.exY, 1);
		minus += moveXPercent(tmp, tornado.x + Tornado.directs[tornado.exdirect][0],
			tornado.y + Tornado.directs[tornado.exdirect][1], 10);

		int five = tmp * 5 / 100;
		int tmpX = tornado.x + Tornado.directs[tornado.exdirect][0] * 2;
		int tmpY = tornado.y + Tornado.directs[tornado.exdirect][1] * 2;
		moveSand(tmpX, tmpY, five);
		minus += five;

		tmp = tmp - minus;
		tmpX = tornado.x + Tornado.directs[tornado.exdirect][0];
		tmpY = tornado.y + Tornado.directs[tornado.exdirect][1];
		moveSand(tmpX, tmpY, tmp);

	}

	private static int moveXPercent(int tmp, int dx, int dy, int p) {
		int percent = tmp * p / 100;
		int direct = (tornado.exdirect + 1) % 4;

		int tmpX = dx + Tornado.directs[direct][0];
		int tmpY = dy + Tornado.directs[direct][1];

		moveSand(tmpX, tmpY, percent);

		direct = (direct + 2) % 4;
		tmpX = dx + Tornado.directs[direct][0];
		tmpY = dy + Tornado.directs[direct][1];

		moveSand(tmpX, tmpY, percent);

		return percent * 2;
	}

	private static int moveNinePercent(int tmp, int dx, int dy) {
		int seven = tmp * 7 / 100;
		int two = tmp * 2 / 100;
		int direct = (tornado.exdirect + 1) % 4;

		int tmpX = dx + Tornado.directs[direct][0];
		int tmpY = dy + Tornado.directs[direct][1];

		moveSand(tmpX, tmpY, seven);

		tmpX = tmpX + Tornado.directs[direct][0];
		tmpY = tmpY + Tornado.directs[direct][1];

		moveSand(tmpX, tmpY, two);

		direct = (direct + 2) % 4;
		tmpX = dx + Tornado.directs[direct][0];
		tmpY = dy + Tornado.directs[direct][1];

		moveSand(tmpX, tmpY, seven);

		tmpX = tmpX + Tornado.directs[direct][0];
		tmpY = tmpY + Tornado.directs[direct][1];

		moveSand(tmpX, tmpY, two);
		return seven * 2 + two * 2;
	}

	static boolean isInRange(int dx, int dy) {
		if (dx < 0 || dx >= n || dy < 0 || dy >= n)
			return false;
		return true;
	}

	static void moveSand(int dx, int dy, int add) {
		if (isInRange(dx, dy)) {
			map[dx][dy] += add;
		}
		else {
			sum += add;
		}
	}
}
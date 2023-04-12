// package org.example.samsung;
//
// import java.util.ArrayList;
// import java.util.Scanner;
//
//
// https://www.codetree.ai/training-field/frequent-problems/hide-and-seek/
// class Runner {
// 	int x;
// 	int y;
// 	int d;
//
// 	public boolean canMove(org.example.Catcher catcher) {
// 		int distance = Math.abs(catcher.x - x) + Math.abs(catcher.y - y);
// 		if (distance <= 3)
// 			return true;
// 		return false;
// 	}
// }
//
// class Tree {
// 	int x;
// 	int y;
// }
//
// class Catcher {
// 	int x;
// 	int y;
//
// 	int startX;
// 	int startY;
//
// 	int d = 0;
//
// 	int size = 1;
// 	int csize = 0;
//
// 	int current_step = 0;
//
// 	int operator = 1;
//
// 	public void move(int[][] directs) {
// 		if (operator == 1) {
// 			//방향 틀기
// 			csize++;
// 			this.x += directs[d][0];
// 			this.y += directs[d][1];
// 			if (size == csize) {
// 				d = (d + 1) % 4;
// 				csize = 0;
// 				current_step++;
// 			}
// 			if (current_step == 2) {
// 				size++;
// 				current_step = 0;
// 			}
// 			if (x == 0 && y == 0) {
// 				operator = -1;
// 				csize = 1;
// 				current_step = 1;
// 				d = 2;
// 				// System.out.println("------------change------------");
// 			}
// 		}
// 		else {
// 			csize++;
// 			this.x += directs[d][0];
// 			this.y += directs[d][1];
// 			if (size == csize) {
// 				current_step++;
// 				d--;
// 				if (d == -1)
// 					d = 3;
// 				csize = 0;
// 			}
// 			if (current_step == 2) {
// 				size--;
// 				current_step = 0;
// 			}
// 			if (x == startX && y == startY) {
// 				operator = 1;
// 				csize = 0;
// 				current_step = 0;
// 				size = 1;
// 				d = 0;
// 				// System.out.println("------------change------------");
// 			}
// 		}
// 		// System.out.println("x : " + x + " y : " + y + " size : " + size + " d : " + d + " csize : " + csize);
// 	}
// }
//
// public class Main {
//
// 	private static int[][] directs = {{-1, 0},{0, 1},{1, 0},{0, -1}};
// 	private static ArrayList<org.example.Runner> runners = new ArrayList<>();
//
// 	private static ArrayList<org.example.Tree> trees = new ArrayList<>();
//
// 	private static org.example.Catcher catcher;
//
// 	private static int score = 0;
//
// 	private static int n; //길이
// 	private static int m; // 도망자 몇 명
// 	private static int h; // 나무 몇 개
// 	private static int k; // 턴
//
// 	private static boolean isInRange(int dx, int dy) {
// 		if (dx < 0 || dx >= n || dy < 0 || dy >= n) {
// 			return false;
// 		}
// 		return true;
// 	}
//
// 	private static void see(int turn) {
// 		int dx = catcher.x;
// 		int dy = catcher.y;
// 		int caught = 0;
// 		int count = 0;
// 		while (isInRange(dx, dy) && count < 3) {
// 			count++;
// 			if (isTree(dx, dy)){
// 				dx += directs[catcher.d][0];
// 				dy += directs[catcher.d][1];
// 			}
// 			else {
// 				for (int i = runners.size() - 1; i >= 0; i--) {
// 					if (dx == runners.get(i).x && dy == runners.get(i).y) {
// 						runners.remove(i);
// 						caught++;
// 						// System.out.println("caught!!!!!!!!");
// 					}
// 				}
// 				dx += directs[catcher.d][0];
// 				dy += directs[catcher.d][1];
// 			}
// 		}
// 		score += (turn + 1) * caught;
// 	}
//
// 	private static boolean isTree(int dx, int dy) {
// 		for (org.example.Tree tree : trees) {
// 			if (tree.x == dx && tree.y == dy) {
// 				return true;
// 			}
// 		}
// 		return false;
// 	}
//
// 	private static void catcherTurn(int turn) {
// 		catcher.move(directs);
// 		see(turn);
// 	}
//
// 	private static void runnersTurn() {
// 		for (org.example.Runner runner : runners) {
// 			// System.out.println("before runner x : " +  runner.x + " runner y : " + runner.y);
// 			if (runner.canMove(catcher)) {
// 				int dx = runner.x + directs[runner.d][0];
// 				int dy = runner.y + directs[runner.d][1];
// 				if (isInRange(dx, dy)) {
// 					if (!(catcher.x == dx && catcher.y == dy)) {
// 						runner.x = dx;
// 						runner.y = dy;
// 					}
// 				}
// 				else {
// 					runner.d = (runner.d + 2) % 4;
// 					dx = runner.x + directs[runner.d][0];
// 					dy = runner.y + directs[runner.d][1];
// 					if (!(catcher.x == dx && catcher.y == dy)) {
// 						runner.x = dx;
// 						runner.y = dy;
// 					}
// 				}
// 			}
// 			// System.out.println("after runner x : " +  runner.x + " runner y : " + runner.y);
// 		}
// 	}
//
// 	public static void main(String[] args) {
// 		Scanner sc = new Scanner(System.in);
// 		n = sc.nextInt();
// 		m = sc.nextInt();
// 		h = sc.nextInt();
// 		k = sc.nextInt();
//
// 		for (int i = 0; i < m; i++) {
// 			org.example.Runner runner = new org.example.Runner();
// 			runner.x = sc.nextInt() - 1;
// 			runner.y = sc.nextInt() - 1;
// 			runner.d = sc.nextInt();
// 			runners.add(runner);
// 		}
//
// 		for (int i = 0; i < h; i++) {
// 			org.example.Tree tree = new org.example.Tree();
// 			tree.x = sc.nextInt() - 1;
// 			tree.y = sc.nextInt() - 1;
// 			trees.add(tree);
// 		}
//
// 		catcher = new org.example.Catcher();
// 		catcher.x = n/2;
// 		catcher.y = n/2;
// 		catcher.startX = n/2;
// 		catcher.startY = n/2;
//
// 		for (int i = 0; i < k; i++) {
// 			runnersTurn();
// 			catcherTurn(i);
// 		}
//
// 		System.out.println(score);
// 	}
//
// 	// public static void main(String[] args) {
// 	// 	Catcher catcher = new Catcher();
// 	// 	catcher.x = 2;
// 	// 	catcher.y = 2;
// 	// 	catcher.startX = 2;
// 	// 	catcher.startY = 2;
// 	//
// 	// 	for (int i = 0; i < 80; i++) {
// 	// 		catcher.move(directs);
// 	// 	}
// 	// }
// }
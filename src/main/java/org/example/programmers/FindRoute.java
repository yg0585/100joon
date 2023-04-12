package org.example.programmers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

//https://school.programmers.co.kr/learn/courses/30/lessons/42892
public class FindRoute {
	public class Node {
		int[] coord;
		int idx;
		Node parent = null;
		Node left = null;
		Node right = null;

		public Node(int[] coord, int idx) {
			this.coord = coord;
			this.idx = idx;
		}
	}

	public class Tree {
		Node root = null;

		public void put(int[] coord, int idx) {
			Node node = new Node(coord, idx);
			if (root == null)
				root = node;
			else {
				Node temp = root;
				while (true) {
					if (coord[0] < temp.coord[0]) {
						if (temp.left != null)
							temp = temp.left;
						else {
							node.parent = temp;
							temp.left = node;
							return;
						}
					} else {
						if (temp.right != null)
							temp = temp.right;
						else {
							node.parent = temp;
							temp.right = node;
							return;
						}
					}
				}
			}
		}
	}

	List<Integer> prefix = new ArrayList<>();
	List<Integer> postfix = new ArrayList<>();

	public void preFix(Node node) {
		if(node == null)
			return ;
		prefix.add(node.idx);
		preFix(node.left);
		preFix(node.right);
	}

	public void postFix(Node node) {
		if(node == null)
			return ;

		postFix(node.left);
		postFix(node.right);
		postfix.add(node.idx);
	}

	public int[][] solution(int[][] nodeinfo) {
		int[][] answer = new int[2][nodeinfo[0].length];
		Integer[] indexes = IntStream.range(0, nodeinfo.length).boxed().toArray(Integer[]::new);
		Tree tree = new Tree();

		Arrays.sort(indexes, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (nodeinfo[o1][1] == nodeinfo[o2][1]) {
					return nodeinfo[o1][0] - nodeinfo[o2][0];
				} else {
					return nodeinfo[o2][1] - nodeinfo[o1][1];
				}
			}
		});

		for (int i = 0; i < nodeinfo.length; i++) {
			tree.put(nodeinfo[indexes[i]], indexes[i] + 1);
		}

		preFix(tree.root);
		postFix(tree.root);

		answer[0] = prefix.stream().mapToInt(i -> i).toArray();
		answer[1] = postfix.stream().mapToInt(i -> i).toArray();

		return answer;
	}
}
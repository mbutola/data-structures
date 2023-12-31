package com.msb.lrg.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeMaxWidth {

	public static void main(String[] args) {
		Node root = BiTreeMaxWidth.createTree();
		Utility.printBinaryTree(root);

		System.out.println("O/P : " + BiTreeMaxWidth.maxWidth(root));
	}

	public static int maxWidth(Node root) {
		if (root == null)
			return 0;

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		
		int res = Integer.MIN_VALUE;
		while (!queue.isEmpty()) {
			int size = queue.size();
			res = Math.max(res, size);
			Node curr = null;
			for (int i = 0; i < size; i++) {
				curr = queue.poll();
				if (curr.left != null)
					queue.add(curr.left);
				if (curr.right != null)
					queue.add(curr.right);
			}
		}
		return res;
	}

	public static Node createTree() {
		Node root = new Node(10);
		Node temp = null;

		temp = new Node(20);
		root.left = temp;
		temp = new Node(30);
		root.right = temp;

		temp = new Node(40);
		root.left.left = temp;
		temp = new Node(50);
		root.left.right = temp;
		temp = new Node(60);
		root.right.left = temp;

		return root;
	}
}

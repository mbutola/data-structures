package com.msb.lrg.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeLftViewIterative {

	public static void main(String[] args) {
		Node root = BiTreeLftViewIterative.createTree();
		Utility.printBinaryTree(root);

		System.out.print("O/P : ");
		BiTreeLftViewIterative.printLeft(root);
	}

	public static void printLeft(Node root) {
		if (root == null)
			return;

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			Node curr = null;
			for (int i = 0; i < size; i++) {
				curr = queue.poll();
				if(i == 0)
					System.out.printf("%4s", curr.data);
				if (curr.left != null)
					queue.add(curr.left);
				if (curr.right != null)
					queue.add(curr.right);
			}
		}
	}

	public static Node createTree() {
		Node root = new Node(10);
		Node temp = null;
		
		temp = new Node(20);
		root.left = temp;
		temp = new Node(30);
		root.right = temp;

		temp = new Node(40);
		root.right.left = temp;
		temp = new Node(50);
		root.right.right = temp;

		return root;
	}
}

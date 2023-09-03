package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreePrnKDist {

	public static void main(String[] args) {
		Node root =  BiTreePrnKDist.createTree();
		Utility.printBinaryTree(root);
		int k = 2;
		
        System.out.print("O/P : ");
        BiTreePrnKDist.printKDist(root, k);
	}

	public static void printKDist(Node root, int k) {
		if(root == null)
			return;
		
		if(k == 0) {
			System.out.printf("%4s", root.data);
		}else {
			printKDist(root.left, k-1);
			printKDist(root.right, k-1);
		}
	}
	
	public static Node createTree() {
		Node root = new Node(10);
		Node temp = null;
		
		temp = new Node(10);
		root.left = temp;
		temp = new Node(30);
		root.right = temp;

		temp = new Node(40);
		root.left.left = temp;
		temp = new Node(50);
		root.left.right = temp;
		temp = new Node(70);
		root.right.right = temp;

		temp = new Node(80);
		root.right.right.right = temp;

		return root;
	}
}

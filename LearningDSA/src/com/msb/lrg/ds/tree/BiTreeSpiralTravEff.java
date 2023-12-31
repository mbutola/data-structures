package com.msb.lrg.ds.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeSpiralTravEff {

	public static void main(String[] args) {
		Node root =  BiTreeSpiralTravEff.createTree();
		Utility.printBinaryTree(root);
		
        System.out.println("O/P : ");
        BiTreeSpiralTravEff.printSpiralTrav(root);
	}
	
	public static void printSpiralTrav(Node root) {
		if(root == null)
			return;
		
		Stack<Node> s1 = new Stack<Node>();
		Stack<Node> s2 = new Stack<Node>();
		s1.push(root);

		while(!s1.isEmpty() || !s2.isEmpty()) {
			if(!s1.isEmpty()) {
				while (!s1.isEmpty()) {
					Node curr = s1.pop();
					System.out.printf("%4s", curr.data);
					if (curr.left != null)
						s2.push(curr.left);
					if (curr.right != null)
						s2.push(curr.right);
				}
			}else {
				while(!s2.isEmpty()) {
					Node curr = s2.pop();
					System.out.printf("%4s", curr.data);
					if (curr.right != null)
						s1.push(curr.right);
					if (curr.left != null)
						s1.push(curr.left);
				}
			}
			System.out.println("");
		}
	}

	public static Node createTree() {
		Node root = new Node(1);
		Node temp = null;
		
		temp = new Node(2);
		root.left = temp;
		temp = new Node(3);
		root.right = temp;

		temp = new Node(4);
		root.left.left = temp;
		temp = new Node(5);
		root.left.right = temp;
		temp = new Node(6);
		root.right.left = temp;
		temp = new Node(7);
		root.right.right = temp;

		temp = new Node(8);
		root.left.left.left = temp;
		temp = new Node(9);
		root.left.left.right = temp;
		temp = new Node(10);
		root.right.left.left = temp;
		temp = new Node(11);
		root.right.left.right = temp;

		return root;
	}
	
}

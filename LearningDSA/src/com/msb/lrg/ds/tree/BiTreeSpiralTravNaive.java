package com.msb.lrg.ds.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeSpiralTravNaive {

	public static void main(String[] args) {
		Node root =  BiTreeSpiralTravNaive.createTree();
		Utility.printBinaryTree(root);
		
        System.out.println("O/P : ");
        BiTreeSpiralTravNaive.printSpiralTrav(root);
	}
	
	public static void printSpiralTrav(Node root) {
		if(root == null)
			return;
		
		boolean reverse = false;
		Stack<Integer> stack = new Stack<Integer>();
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			Node curr = null;
			for (int i = 0; i < size; i++) {
				curr = queue.poll();
				if(!reverse)
					System.out.printf("%4s", curr.data);
				else
					stack.push(curr.data);
				
				if (curr.left != null)
					queue.add(curr.left);
				if (curr.right != null)
					queue.add(curr.right);
				
			}

			if(reverse) {
				while(!stack.isEmpty())
					System.out.printf("%4s",stack.pop());
			}
			
			reverse = !reverse;
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
		root.left.right.right = temp;
		temp = new Node(10);
		root.right.left.left = temp;
		temp = new Node(11);
		root.right.right.right = temp;

		return root;
	}
	
}

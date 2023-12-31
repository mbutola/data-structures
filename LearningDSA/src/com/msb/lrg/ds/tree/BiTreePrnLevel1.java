package com.msb.lrg.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreePrnLevel1
{

	public static void main(String[] args) {
		Node root =  BiTreePrnLevel1.createTree();
		Utility.printBinaryTree(root);
		
        System.out.println("O/P : ");
        BiTreePrnLevel1.printLevel(root);
	}

	public static void printLevel(Node root) {
		if(root == null)
			return;
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		queue.add(null);
		
		while(queue.size() > 1) {
			Node curr = queue.poll();
			if(curr == null) {
				queue.add(null);
				System.out.println("");
				continue;
			}
			System.out.printf("%4s", curr.data);
			if(curr.left != null)
				queue.add(curr.left);
			if(curr.right != null)
				queue.add(curr.right);
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
		root.left.left = temp;

		return root;
	}
}

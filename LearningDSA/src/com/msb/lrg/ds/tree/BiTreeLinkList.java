package com.msb.lrg.ds.tree;

import com.msb.lrg.ds.Node;
import com.msb.lrg.ds.Utility;

public class BiTreeLinkList {

	public static Node prev = null;

	public static void main(String[] args) {
		Node root = BiTreeLinkList.createTree();
		Utility.printBinaryTree(root);

		System.out.println("O/P : ");
		Node head = BiTreeLinkList.linkList(root);
		while(head != null) {
			System.out.printf("%4s", head.data);
			head = head.right;
		}
	}
	
	public static Node linkList(Node root) {
		
		if(root == null)
			return root;
		
		Node head = linkList(root.left);
		
		if(prev == null) {
			head = root;
		}else{
			root.left = prev;
			prev.right = root;
		}
		
		prev = root;
		linkList(root.right);
		
		return head;
		
	}

	public static Node createTree() {
		Node root = new Node(10);
		Node temp = null;

		temp = new Node(5);
		root.left = temp;
		temp = new Node(20);
		root.right = temp;

		temp = new Node(30);
		root.right.left = temp;
		temp = new Node(35);
		root.right.right = temp;

		return root;
	}
}

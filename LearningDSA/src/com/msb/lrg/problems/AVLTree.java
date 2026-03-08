package com.msb.lrg.problems;

import java.util.ArrayDeque;
import java.util.Queue;

public class AVLTree {
	
	TreeNode root;

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		
		tree.root = tree.insert(tree.root, 10);
		tree.root = tree.insert(tree.root, 20);
		tree.root = tree.insert(tree.root, 30);
		tree.root = tree.insert(tree.root, 40);
		tree.root = tree.insert(tree.root, 50);
		tree.root = tree.insert(tree.root, 25);
		
		tree.printTree(tree.root);
		
		System.out.println("");
		System.out.print("Pre Order :: ");
		tree.preOrder(tree.root);

		System.out.println("");
		System.out.print("In Order :: ");
		tree.inOrder(tree.root);

		System.out.println("");
		System.out.print("Post Order :: ");
		tree.postOrder(tree.root);
		
	}
	
	int height(TreeNode node) {
		if(node == null)
			return 0;
		
		return node.height;
	}
	
	int getBalance(TreeNode node) {
		if(node == null) {
			return 0;
		}
		
		return height(node.left) - height(node.right);
	}
	
	TreeNode leftRotation(TreeNode node) {
		TreeNode rightChild = node.right;
		TreeNode temp = rightChild.left;
		
		rightChild.left = node;
		node.right = temp;
		
		node.height = Math.max(height(node.left),  height(node.right)) + 1;
		rightChild.height = Math.max(height(rightChild.left), height(rightChild.right)) + 1;
		
		return rightChild;
	}
	
	TreeNode insert(TreeNode root, int key){
		if(root == null) {
			return new TreeNode(key);
		}
		
		if(root.key < key) {
			root.right = insert(root.right, key);
		}else if(root.key > key) {
			root.left = insert(root.left, key);
		}else{
			return root;
		}
		
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		int balance = getBalance(root);
		
		
		// left left
		if(balance > 1 && key < root.left.key) {
			return rightRotation(root);			
		}

		// left right
		if(balance > 1 && key > root.left.key) {
			root.left = leftRotation(root.left);
			return rightRotation(root);
		}
		
		// right right
		if(balance < -1 && key > root.right.key) {
			return leftRotation(root);
		}
		
		// right left
		if(balance < -1 && key < root.right.key) {
			root.right = rightRotation(root.right);
			return leftRotation(root);
		}
		
		return root;
	}

	TreeNode rightRotation(TreeNode node) {
		TreeNode leftChild = node.left;
		TreeNode temp = leftChild.right;
		
		leftChild.right = node;
		node.left = temp;
		
		node.height = Math.max(height(node.left),  height(node.right)) + 1;
		leftChild.height = Math.max(height(leftChild.left), height(leftChild.right)) + 1;
		
		return leftChild;
	}
	
	void preOrder(TreeNode node) {
		if(node != null) {
			System.out.print(node.key + "  ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	void postOrder(TreeNode node) {
		if(node != null) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.key + "  ");
		}
	}

	void inOrder(TreeNode node) {
		if(node != null) {
			inOrder(node.left);
			System.out.print(node.key + "  ");
			inOrder(node.right);
		}
	}

	void printTree(TreeNode root) {
		
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size > 0) {
				TreeNode node = queue.poll();
				System.out.print(node.key + "\t");
				if(node.left != null) {
					queue.offer(node.left);
					System.out.print("left\t");
				}
				if(node.right != null) {
					queue.offer(node.right);
					System.out.print("right\t");
				}
				size--;
			}
			System.out.println("");	
		}
	}

}

class TreeNode {
	int key, height;
	TreeNode left, right;
	
	TreeNode(int key) {
		this.key = key;
		this.height = 1;
	}
}


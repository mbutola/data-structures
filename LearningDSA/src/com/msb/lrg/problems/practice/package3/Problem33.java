package com.msb.lrg.problems.practice.package3;

import java.util.LinkedList;
import java.util.Queue;

/*
Diameter of Binary Tree :: LeetCode (543 Easy)
	Given the root of a binary tree, return the length of the diameter of the tree.
	The diameter of a binary tree is the length of the longest path between any two 
	nodes in a tree. This path may or may not pass through the root.
	The length of a path between two nodes is represented by the number of edges between them.
	Example 1:
		Input: root = [1,2,3,4,5]
		Output: 3
		Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
	Example 2:
		Input: root = [1,2]
		Output: 1
	Constraints:
		The number of nodes in the tree is in the range [1, 104].
		-100 <= Node.val <= 100

 */
public class Problem33 {
	
	static int maxDiameter = Integer.MIN_VALUE;

	public static void main(String[] args) {
//		int[] data = {1,2,3,4,5};
		int[] data = {1,2};
		TreeNode root = null;
		for(int i = 0; i < data.length; i++) {
			root = insertData(root, data[i]);
		}
		
		int res = diameterOfBinaryTree(root);
		System.out.println("Result iterative :: " + res);

		root = null;
		for(int i = 0; i < data.length; i++) {
			root = insertDataRecursive(root, data[i]);
		}

		res = diameterOfBinaryTree(root);
		System.out.println("Result recursive:: " + res);
		
}
	
	static int diameterOfBinaryTree(TreeNode root) {
		dfs(root);
		return maxDiameter;
	}
	
	static int dfs(TreeNode node) {
		
		if(node == null) return 0;
		
		int lh = dfs(node.left);
		int rh = dfs(node.right);
		
		maxDiameter = Math.max(maxDiameter, lh + rh);
		
		return 1 + Math.max(lh, rh);
		
	}
	
	static TreeNode insertDataRecursive(TreeNode root, int val) {
		
		if(root == null) return new TreeNode(val);
		
		if(root.val > val) {
			root.left = insertDataRecursive(root.left, val);
		} else {
			root.right = insertDataRecursive(root.right, val);
		}
		return root;
	}
	
	static TreeNode insertData(TreeNode root, int val) {
		
		TreeNode newNode = new TreeNode(val);
		
		if(root == null) return newNode;
		
		TreeNode curr = root;
		while(true) {
			if(curr.val > val) {
				if(curr.left == null) {
					curr.left = newNode;
					break;
				} else {
					curr = curr.left;
				}
			} else {
				if(curr.right == null) {
					curr.right = newNode;
					break;
				} else {
					curr = curr.right;
				}
			}
		}
		return root;
	}
	
	static class TreeNode {
		int val;
		TreeNode left, right;
		
		TreeNode(){}
		TreeNode(int val){ this.val = val;}
		TreeNode(int val, TreeNode left, TreeNode right){
			this.val = val;
			this.right = right;
			this.left = left;
		}
	}

}

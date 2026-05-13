package com.msb.lrg.problems.frequentlyasked;

import com.msb.lrg.problems.frequentlyasked.F028ValidateBST.TreeNode;

/*

Technique: Recursive Insert
	At every node:
		if value smaller → go left
		if value larger → go right
		if null reached → insert here

 */
public class F029InsertBST {

	public static void main(String[] args) {
        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(2);
        root.right = new TreeNode(7);

        insertIntoBST(root, 5);

        inorder(root); // 2 4 5 7
	}
	
	static TreeNode insertIntoBST(TreeNode root, int val) {
		if(root == null)
			return new TreeNode(val);
		
		if(root.val > val)
			root.left = insertIntoBST(root.left, val);
		else
			root.right = insertIntoBST(root.right, val);
		
		return  root;
	}
	
	static void inorder(TreeNode node) {
		if(node == null)
			return;
				
		inorder(node.left);
		System.out.print(node.val + " ");
		inorder(node.right);
	}

	static class TreeNode {
		int val;
		TreeNode left, right;
		
		TreeNode(int val){
			this.val = val;
		}
	}
	
}

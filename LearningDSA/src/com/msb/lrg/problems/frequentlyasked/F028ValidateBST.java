package com.msb.lrg.problems.frequentlyasked;

import com.msb.lrg.problems.frequentlyasked.F027LowestCommonAncestor.TreeNode;

public class F028ValidateBST {

	static TreeNode prev = null;
	
	public static void main(String[] args) {
        TreeNode root = new TreeNode(5);

        root.left = new TreeNode(3);
        root.right = new TreeNode(8);

        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.right.right = new TreeNode(9);

//        root.right.left = new TreeNode(4);
        
        System.out.println(isValidBSTBounded(root)); // true
        System.out.println(isValidBSTInOrder(root)); // true
	}

	/*
Main Intuition of Bounds
Bounds mean:
	What values is this node ALLOWED to have?
Every node carries restrictions from its ancestors.
Start Simple
	Tree:
	        5
	       / \
	      3   8
	Root Node
		For root 5:
			(-∞ , +∞)
		Meaning:
			5 can be anything
			since root has no parent restriction.
	Go Left to 3
		Because 3 is in LEFT subtree of 5:
			3 must be < 5
		So bounds become:
			(-∞ , 5)
	Go Right to 8
		Because 8 is in RIGHT subtree of 5:
			8 must be > 5
		Bounds:
			(5 , +∞)
Important Part: Bounds Keep Propagating
	Now see this tree:
          5
         / \
        3   8
           /
          4
	This is INVALID BST.
	Why?
		Because 4 is inside RIGHT subtree of 5.
		So it must be:
			> 5
		even though:
			4 < 8
	How Bounds Reach Node 4
		Step 1: Root 5
			validate(5, -∞, +∞)
		Step 2: Go Right to 8
			Right subtree rule:
				everything must be > 5
			So:
				validate(8, 5, +∞)
		Step 3: Go Left from 8 to 4
			Left child rule:
				must be < 8
			BUT old restriction still remains:
				must also be > 5
			So bounds become:
				(5, 8)
		Meaning:
			5 < node < 8
		Now Check Node 4
			4 > 5 ? NO
			Violation.
			Hence invalid BST.
	 */
	static boolean isValidBSTBounded(TreeNode root) {
		return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	static boolean isValidBST(TreeNode root, int min, int max) {
		if(root == null)
			return true;
		
		if(root.val <= min || root.val >= max)
			return false;
		
		boolean left = isValidBST(root.left, min, root.val);
		boolean right = isValidBST(root.right, root.val, max);
		
		return left && right;
	}

	/*
		Key Property of BST
		The MOST important BST property:
			Inorder traversal of a BST is always sorted
		Specifically:
			strictly increasing
			if duplicates are not allowed.
		What is Inorder Traversal?
			Order:
				Left → Root → Right
		Example Valid BST
		        5
		       / \
		      3   8
		     / \   \
		    2   4   9
		Inorder Traversal
			Visit:
				2 → 3 → 4 → 5 → 8 → 9
			Notice:
				sorted increasing
				So tree is BST.
		Invalid BST Example
		        5
		       / \
		      3   8
		         /
		        4
		Inorder Traversal
			Traversal becomes:
				3 → 5 → 4 → 8
			Notice:
				5 > 4
			Not sorted.
			Hence NOT BST.
		Core Idea
			While doing inorder traversal:
				Keep track of:
					previous visited node
				Current node must always be:
					greater than previous node
		Why This Works
			In BST:
				all left values < root < all right values
	 */
	static boolean isValidBSTInOrder(TreeNode root) {
		
		if(root == null)
			return true;
		
		if(!isValidBSTInOrder(root.left))
			return false;
		
		if(prev != null && root.val <= prev.val) {
			return false;
		}
		
		prev = root;
		
		return isValidBSTInOrder(root.right);
		
	}

	static class TreeNode {
		int val;
		TreeNode left, right;
		
		TreeNode(int val){
			this.val = val;
		}
	}
}

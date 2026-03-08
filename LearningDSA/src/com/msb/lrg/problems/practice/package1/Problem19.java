package com.msb.lrg.problems.practice.package1;

/*
Symmetric Tree :: LeetCode (101, Easy)
	Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
	Example 1:
		Input: root = [1,2,2,3,4,4,3]
		Output: true
	Example 2:
		Input: root = [1,2,2,null,3,null,3]
		Output: false
	Constraints:
		The number of nodes in the tree is in the range [1, 1000].
		-100 <= Node.val <= 100

 */
public class Problem19 {

	public static void main(String[] args) {
//		Integer[] data = {1,2,2,null,3,null,3};
		Integer[] data = {1,2,2,3,4,4,3};
		TreeNode root = buildTree(data);
		boolean res = isSymmetric(root);
		System.out.println("Result :: " + res);
	}

	public static boolean isSymmetric(TreeNode root) {
		return isSymmetric(root.left, root.right);
	}
	
	public static boolean isSymmetric(TreeNode left, TreeNode right) {
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;
		
		return (left.val == right.val)
				&& isSymmetric(left.left, right.right)
				&& isSymmetric(left.right, right.left);
		
	}
	   
	static TreeNode buildTree(Integer[] data) {
		return build(data, 0);
	}
	
	static TreeNode build(Integer[] data, int index) {
		if(index >= data.length || data[index] == null) {
			return null;
		}
		
		TreeNode curr = new TreeNode(data[index]);
		curr.left = build(data, 2*index + 1);
		curr.right = build(data, 2*index + 2);
		
		return curr;
	}
	
	static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int val){
			this.val = val;
		}
		
		TreeNode(int val, TreeNode left, TreeNode right){
			this.val = val;
			this.right = right;
			this.left = left;
		}
	}
	
}

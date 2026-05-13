package com.msb.lrg.problems.frequentlyasked;

import com.msb.lrg.problems.frequentlyasked.F026LevelOrder.TreeNode;

/*

Lowest Common Ancestor (LCA)
	Find the lowest/common deepest node that has both nodes as descendants.
	Example
		Tree:
		          3
		         / \
		        5   1
		       / \ / \
		      6  2 0  8
		        / \
		       7   4
		Find LCA of:
			5 and 1
		Answer:
			3
	Another Example
		Find LCA of:
			7 and 4
		Answer:
			2
		Because:
			2
			is the deepest node containing both.
	Technique: DFS Recursion
		Go deep into tree.
		At every node:
			Search left subtree
			Search right subtree
		Then decide:
			both found → current node is LCA
			only one found → return that one
			none found → return null
	Core Idea
		Each recursive call returns:
			Did I find p or q in my subtree?

 */
public class F027LowestCommonAncestor {

	public static void main(String[] args) {
        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode p = root.left.right.left;    // 7
        TreeNode q = root.left;          // 5

        TreeNode ans = lowestCommonAncestor(root, p, q);

        System.out.println(ans.val); // 5
	}

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	
    	if(root == null || root == p || root == q)
    		return root;
    	
    	TreeNode left = lowestCommonAncestor(root.left, p, q);
    	TreeNode right = lowestCommonAncestor(root.right, p, q);
    	
    	if(left != null && right != null)
    		return root;
    	
    	return left != null ? left : right;
    }


	static class TreeNode {
		int val;
		TreeNode left, right;
		
		TreeNode(int val){
			this.val = val;
		}
	}
}

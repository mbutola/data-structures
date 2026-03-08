package com.msb.lrg.problems.practice.package3;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/*
Lowest Common Ancestor of a Binary Search Tree :: LeetCode (235 Medium)
	Given a binary search tree (BST), find the lowest common ancestor (LCA) node 
	of two given nodes in the BST.
	According to the definition of LCA on Wikipedia: “The lowest common ancestor 
	is defined between two nodes p and q as the lowest node in T that has both 
	p and q as descendants (where we allow a node to be a descendant of itself).”
	Example 1:
		Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
		Output: 6
		Explanation: The LCA of nodes 2 and 8 is 6.
	Example 2:
		Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
		Output: 2
		Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
	Example 3:
		Input: root = [2,1], p = 2, q = 1
		Output: 2
	Constraints:
		The number of nodes in the tree is in the range [2, 105].
		-109 <= Node.val <= 109
		All Node.val are unique.
		p != q
		p and q will exist in the BST.
 
 */
public class Problem38 {

	public static void main(String[] args) {
		Integer[] data = {6,2,8,0,4,7,9,null,null,3,5}; 
		int p = 0;
		int q = 5;
		TreeNode root = buildTreeRecursive(data, 0);
		System.out.println("Result :: ");
		TreeNode res = lowestCommonAncestor(root, new TreeNode(p), new TreeNode(q));
		System.out.println("Result :: " + res.val);
	}

    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
    	if(p.val < root.val && q.val < root.val) {
    		root = lowestCommonAncestor(root.left, p, q);
    	}

    	if(p.val > root.val && q.val > root.val) {
    		root = lowestCommonAncestor(root.right, p, q);
    	}
    	
    	return root;
    }
	
	
	static TreeNode buildTreeRecursive(Integer[] data, int i) {
		if(i >= data.length || data[i] == null)
			return null;
		
		TreeNode root = new TreeNode(data[i]);
		root.left = buildTreeRecursive(data, 2 * i + 1);
		root.right = buildTreeRecursive(data, 2 * i + 2);

		return root;
	}
	
	static TreeNode buildTreeIterative(Integer[] data) {
		TreeNode root = new TreeNode(data[0]);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int i = 1;		
				
		while(i < data.length) {
			
			TreeNode curr = queue.poll();

			if(data[i] != null) {
				curr.left = new TreeNode(data[i]);
				queue.offer(curr.left);
			}
			i++;
			
			if(data[i] != null) {
				curr.right = new TreeNode(data[i]);
				queue.offer(curr.right);
			}
			i++;
			
		}
		return root;
	}
	
	static void printTree(TreeNode root) {
		
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size > 0) {
				TreeNode node = queue.poll();
				System.out.print(node.val + "\t");
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

	static class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode(int val){
			this.val = val;
		}
	}
}

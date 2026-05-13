package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.msb.lrg.problems.frequentlyasked.F024SameTree.TreeNode;

public class F026LevelOrder {

	public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.right = new TreeNode(6);

        System.out.println(levelOrder(root));
	}

    public static List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> result = new ArrayList<>();
    	
    	if(root == null)
    		return result;
    	
    	Queue<TreeNode> queue = new ArrayDeque<>();
    	queue.offer(root);
    	
    	while(!queue.isEmpty()) {
    		
    		List<Integer> level = new ArrayList<>();
    		int size = queue.size();
    		
    		for(int i = 0; i < size; i++) {
    			
    			TreeNode node = queue.poll();
    			level.add(node.val);
    			
    			if(node.left != null)
    				queue.offer(node.left);

    			if(node.right != null)
    				queue.offer(node.right);
    		}
    		
    		result.add(level);
    	}
    	
    	return result;
    }

	static class TreeNode {
		int val;
		TreeNode left, right;
		
		TreeNode(int val){
			this.val = val;
		}
	}
}

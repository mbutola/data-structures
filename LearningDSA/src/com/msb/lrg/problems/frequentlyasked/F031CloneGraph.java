package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*

Clone Graph
	Create a:
		deep copy
		of an undirected graph.
	Each node contains:
		value
		neighbors list
	Deep Copy Means
		New graph should have:
			new nodesnew neighbor lists
			NOT references to original graph.
	Graph Node Structure
		class Node {
		    public int val;
		    public List<Node> neighbors;
		
		    public Node() {
		        neighbors = new ArrayList<>();
		    }
		
		    public Node(int val) {
		        this.val = val;
		        neighbors = new ArrayList<>();
		    }
		}
	Example
		Original:
			1 --- 2
			|     |
			4 --- 3
		Cloned graph should look identical but use:
			completely new node objects
	Main Challenge
		Graphs can have:
			cycles
		So normal DFS may loop forever.
		Example:
			1 → 2 → 1 → 2 ...
	Technique: DFS/BFS + Map
		Use:
			Map<OriginalNode, ClonedNode>
		Purpose:
			Prevent revisiting
			Reuse already cloned nodes
	Core Idea
		Whenever visiting a node:
			if already cloned → return existing clone
		otherwise:
			create clone
			save in map
			clone neighbors recursively

 */
public class F031CloneGraph {
	
	static Map<Integer, Node> cloneMap = new HashMap<>();

	public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        
        printGraph(node1);
        
//        Node cloned = cloneGraph(node1);
        Node cloned = cloneGraphBFS(node1);
        
        System.out.println("\nCloned Graph:");
        printGraph(cloned);
        
        // Verify deep copy
        System.out.println("\nDeep Copy Check:");
        System.out.println(node1 == cloned); // false
        System.out.println(node1.neighbors.get(0) == cloned.neighbors.get(0)); // false
        
	}
	
	public static Node cloneGraphBFS(Node node) {
		Map<Integer, Node> map = new HashMap<>();
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(node);
		map.put(node.val, new Node(node.val));
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			
			for(Node neighbour : curr.neighbors) {
				if(!map.containsKey(neighbour.val)) {
					map.put(neighbour.val, new Node(neighbour.val));
					queue.offer(neighbour);
				}
				map.get(curr.val).neighbors.add(map.get(neighbour.val));
			}		
		}
		
		return map.get(node.val);
	}
	
	public static Node cloneGraph(Node node) {
		
        if (node == null) {
            return null;
        }

		System.out.println(node.val);

		// Already cloned
        if (cloneMap.containsKey(node.val)) {
            return cloneMap.get(node.val);
        }

        // Create clone node
        Node clone = new Node(node.val);

        // Save mapping
        cloneMap.put(node.val, clone);

        // Clone neighbors
        for (Node neighbor : node.neighbors) {

            clone.neighbors.add(
                    cloneGraph(neighbor));
        }

        return clone;
 	}
	 
	static void printGraph(Node node) {
		boolean[] visited = new boolean[5];
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(node);
		visited[node.val] = true;
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			System.out.println(curr.val + ": " +  curr.neighbors);
			for(Node n : curr.neighbors) {
				if(!visited[n.val]) {
					visited[n.val] = true;
					queue.add(n);
				}
			}
		}
	}

	static class Node {
	    public int val;
	    public List<Node> neighbors;
	
	    public Node() {
	        neighbors = new ArrayList<>();
	    }
	
	    public Node(int val) {
	        this.val = val;
	        neighbors = new ArrayList<>();
	    }
	    
	    public String toString() {
	    	return String.valueOf(val);
	    }
	}


}

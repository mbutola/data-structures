package com.msb.lrg.problems;

import java.util.Arrays;
import java.util.stream.IntStream;

public class UnionFind {
	
	public static void main(String[] args) {
		int[] parent;
		int[] rank;
	}
	
	static void unionByRank(int i, int j, int[] parent, int[] rank) {
		
		int i_parent = find(i, parent);
		int j_parent = find(j, parent);
		
		if(i_parent == j_parent)
			return;
		
		if(rank[i_parent] == rank[i_parent]) {
			rank[i_parent] += 1;
			parent[j_parent] = i_parent;
		}

		if(rank[i_parent] > rank[i_parent]) {
			parent[j_parent] = i_parent;
		}else {
			parent[i_parent] = j_parent;
		}
		
	}
	
	static int find(int i, int[] parent) {
		if(parent[i] == i)
			return i;
		
		return find(parent[i], parent);
	}

	static int findByPathCompression(int i, int[] parent) {
		if(parent[i] == i)
			return i;
		
		return parent[i] = findByPathCompression(parent[i], parent);
	}

	static void print() {
	}
}

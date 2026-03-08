package com.msb.lrg.problems.practice.package5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

Word Search II :: LeetCode (212, hard)
	Given an m x n board of characters and a list of strings words, return all 
	words on the board.
	Each word must be constructed from letters of sequentially adjacent cells, 
	where adjacent cells are horizontally or vertically neighboring. The same letter 
	cell may not be used more than once in a word.
	Example 1:
		Input: board = [["o","a","a","n"],["e","t","a","e"],
		["i","h","k","r"],["i","f","l","v"]], words = 
		["oath","pea","eat","rain"]
		Output: ["eat","oath"]
	Example 2:
		Input: board = [["a","b"],["c","d"]], words = ["abcb"]
		Output: []
	Constraints:
		m == board.length
		n == board[i].length
		1 <= m, n <= 12
		board[i][j] is a lowercase English letter.
		1 <= words.length <= 3 * 104
		1 <= words[i].length <= 10
		words[i] consists of lowercase English letters.
		All the strings of words are unique.

 */
public class Problem512 {

	public static void main(String[] args) {
		char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
		String[] words = {"oath","pea","eat","rain"};
		List<String> results = findWords(board, words);
		results.stream()
			.forEach(System.out::println);
	}

    public static List<String> findWords(char[][] board, String[] words) {
    	Set<String> res = new HashSet<>();
    	TrieNode root = buildTrie(words);

    	int m = board.length;
    	int n = board[0].length;

    	for(int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			dfs(board, res, root, i, j);
    		}
    	}	
    	
    	return new ArrayList<>(res);
    }
    
    static void dfs(char[][] board, Set<String> res, TrieNode curr, int i, int j) {
    	
    	if(i < 0 || i >= board.length || j < 0 || j >= board[0].length)
    		return;
    	
    	char c = board[i][j];
    	if(c == '#' || curr.children[c - 'a'] == null)
    		return;
    	
    	curr = curr.children[c - 'a'];

    	if(curr.word != null)
    		res.add(curr.word);
	    	
    	board[i][j] = '#';
	    	
		dfs(board, res, curr, i + 1, j);
		dfs(board, res, curr, i - 1, j);
    	dfs(board, res, curr, i, j + 1);
    	dfs(board, res, curr, i, j - 1);
    	
    	board[i][j] = c;
    	
    }

    static TrieNode buildTrie(String[] words) {
    	
    	TrieNode root = new TrieNode();
    	
    	for(String word : words) {
    		TrieNode curr = root;
    		for(char c : word.toCharArray()) {
    			int i = c - 'a';
    			if(curr.children[i] == null)
    				curr.children[i] = new TrieNode();
    			curr = curr.children[i]; 
    		}
    		curr.word = word;
    	}
    	return root;
    }
    
    static class TrieNode {
    	TrieNode[] children = new TrieNode[26];
    	String word;
    }
}

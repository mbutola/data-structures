package com.msb.lrg.problems.practice.package5;

/*

Word Search :: LeetCode (79, medium)
	Given an m x n grid of characters board and a string word, return true if 
	word exists in the grid.
	The word can be constructed from letters of sequentially adjacent cells, where 
	adjacent cells are horizontally or vertically neighboring. The same letter cell 
	may not be used more than once.
	Example 1:
		Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
		Output: true
	Example 2:
		Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
		Output: true
	Example 3:
		Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
		Output: false
	Constraints:
		m == board.length
		n = board[i].length
		1 <= m, n <= 6
		1 <= word.length <= 15
		board and word consists of only lowercase and uppercase English letters.
 
 */
public class Problem51 {

	public static void main(String[] args) {
		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		String word = "ABCCGD";
		boolean res = exist(board, word);
		System.out.println("Result :: " + res);
	}

    public static boolean exist(char[][] board, String word) {
        
    	int m = board.length;
    	int n = board[0].length;
    	
    	for(int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			if(dfs(board, word, i, j, 0))
    				return true;
    		}
    	}	
    	return false;
    }
    
    static boolean dfs(char[][] board, String word, int i, int j, int k) {
    	
    	if(k == word.length())
    		return true;
    	
    	if(i < 0 || i == board.length || j < 0 || j == board[0].length)
    		return false;
    	
    	if(board[i][j] != word.charAt(k))
    		return false;
    	
    	char temp = board[i][j];
    	board[i][j] = '#';
    	
    	boolean res = 			
    			dfs(board, word, i + 1, j, k + 1) ||
    			dfs(board, word, i - 1, j, k + 1) ||
    			dfs(board, word, i, j + 1, k + 1) ||
    			dfs(board, word, i, j - 1, k + 1);
    	
    	board[i][j] = temp;
    	
    	return res;
    }

}

package com.msb.lrg.problems.frequentlyasked;

/*

🗜️ String Compression (in-place, two pointers)
	🧠 One-line idea
		👉 Scan groups of same chars, write the char once and its count (if >1)
	🧩 Problem
		Input:  ['a','a','b','b','c','c','c']
		Output: ['a','2','b','2','c','3']  → return length = 6
		👉 Must modify in-place and return new length
	⚙️ Technique: Two pointers
		i → start of a group (read)
		j → scans forward to count
		write → where to write result
	🔄 Core logic
		While i < n:
		    Move j until chars[j] != chars[i]
		    count = j - i
		
		    write chars[i]
		    if count > 1:
		        write digits of count

		    i = j

 */
public class F021StringCompression {

    public static void main(String[] args) {
        char[] chars = {'a','a','b','b','c','c','c'};
        int len = compress(chars);

        System.out.print("Compressed: ");
        for (int i = 0; i < len; i++) {
            System.out.print(chars[i] + " ");
        }
        System.out.println("\nLength: " + len);
    }

    public static int compress(char[] chars) {
    	int n = chars.length;
    	int i = 0;
    	int write = 0;
    	
    	while(i < n) {
    		int j = i;
    		
    		while(j < n && chars[i] == chars[j])
    			j++;
    		
    		int count = j - i;
    		chars[write++] = chars[i];
    		
    		if(count > 1) {		
	    		for(char c : String.valueOf(count).toCharArray())
	        		chars[write++] = c;
    		}
    		
    		i = j;
    	}
    	
    	return write;
    }

}

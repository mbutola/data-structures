package com.msb.lrg.problems.hackerrank;

/*

	You have two strings of lowercase English letters. You can perform two types of operations on the first string:
		Append a lowercase English letter to the end of the string.
		Delete the last character of the string. Performing this operation on an empty string results in an empty string.
		Given an integer, , and two strings,  and , determine whether or not you can convert  to  by performing 
		exactly  of the above operations on . If it's possible, print Yes. Otherwise, print No.
	Example. 
		To convert  to , we first delete all of the characters in  moves. Next we add each of the characters of  
		in order. On the  move, you will have the matching string. Return Yes.
		If there were more moves available, they could have been eliminated by performing multiple deletions on 
		an empty string. If there were fewer than  moves, we would not have succeeded in creating the new string.
	Function Description
		Complete the appendAndDelete function in the editor below. It should return a string, either Yes or No.
		appendAndDelete has the following parameter(s):
			string s: the initial string
			string t: the desired string
			int k: the exact number of operations that must be performed
		Returns
			string: either Yes or No
	Input Format
		The first line contains a string , the initial string.
		The second line contains a string , the desired final string.
		The third line contains an integer , the number of operations.
	Constraints
		 and  consist of lowercase English letters, .
	Sample Input 0
		hackerhappy
		hackerrank
		9
	Sample Output 0
		Yes

 */
public class H029AppendAndDelete {

	public static void main(String[] args) {
		String s = "aaaaaaaaaa";
		String t = "aaaaa";
		int k = 7;
		String res = appendAndDelete(s, t, k);
		System.out.println(res);
	}

    public static String appendAndDelete(String s, String t, int k) {
    	int i = 0;
    	while(i < s.length() && i < t.length()) {
    		if(s.charAt(i) == t.charAt(i))
    			i++;
    		else
    			break;
    	}
    	
    	int totalOps = (s.length() - i) + (t.length() - i);
    	
    	if(totalOps == k)
    		return "Yes";
    	
    	if(k > totalOps && (k - totalOps) % 2 == 0)
    		return "Yes";
    	
    	if(k >= s.length() + t.length())
    		return "Yes";
    	
    	return "No";
    }

}

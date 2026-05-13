package com.msb.lrg.problems.frequentlyasked;

/*

🔤 Longest Common Prefix (LCP)
	🧠 One-line idea
		👉 Compare characters across all strings until they differ
	🧩 Problem
		["flower", "flow", "flight"]
	👉 Output:
		"fl"
	⚙️ Approach 1: Vertical scanning (most intuitive)
		👉 Compare column by column
	🔄 Steps
		Take first string  
		Compare its characters with all other strings  
		Stop when mismatch

 */
public class F016LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs)); // "fl"
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int i = 0; i < strs[0].length(); i++) {
            char ch = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != ch) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0];
    }

    /*
		⚙️ Approach 2: Shrinking prefix (clean 🔥)
			👉 Start with full prefix → shrink until it matches all
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }
}

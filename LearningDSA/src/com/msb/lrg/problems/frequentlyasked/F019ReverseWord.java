package com.msb.lrg.problems.frequentlyasked;

/*

🔁 Reverse Words in a String
	🧠 One-line idea
		👉 Split sentence into words → reverse order → join back
	🧩 Problem
		Input:  "the sky is blue"
		Output: "blue is sky the"
	⚙️ Approach 1: Split + Reverse (simple)
		🔄 Steps
			1. Trim spaces  
			2. Split by spaces  
			3. Reverse words  
			4. Join with single space

 */
public class F019ReverseWord {

    public static void main(String[] args) {
        String s = "  the sky   is blue  ";
        System.out.println(reverseWords(s)); // "blue is sky the"
    }

    public static String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");

        StringBuilder result = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) result.append(" ");
        }

        return result.toString();
    }

    /*
		⚙️ Approach 2: In-place (advanced)
		👉 Reverse entire string → then reverse each word
     */	
    public static String reverseWords2(String s) {
        char[] arr = s.trim().replaceAll("\\s+", " ").toCharArray();

        reverse(arr, 0, arr.length - 1);

        int start = 0;
        for (int end = 0; end <= arr.length; end++) {
            if (end == arr.length || arr[end] == ' ') {
                reverse(arr, start, end - 1);
                start = end + 1;
            }
        }

        return new String(arr);
    }

    private static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    
}

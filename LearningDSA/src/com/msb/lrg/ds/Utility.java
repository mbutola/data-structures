package com.msb.lrg.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Utility {

	public static void printDp(String s1, String s2, int[][] memo) {
		char[] col = s1.toCharArray();
		char[] row = s2.toCharArray();
		for (int i = 0; i < memo[0].length; i++) {
			if(i == 0)
				System.out.printf("%8s", "");
			else
				System.out.printf("%2s  ", row[i-1]);
		}
		System.out.println("");
		for (int i = 0; i < memo.length; i++) {
			if(i == 0)
				System.out.printf("%4s", "");
			else
				System.out.printf("%2s  ", col[i-1]);
			for (int j = 0; j < memo[0].length; j++) {
				System.out.printf("%2d  ", memo[i][j]);
			}
			System.out.println("");
		}
	}

	public static List<String> getList(int n){
		List<String> lis = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			lis.add(i+"");
		}
		return lis;
	}
	
	public static List<String> getList(int[] n){
		List<String> lis = new ArrayList<>();
		for (int i = 0; i < n.length; i++) {
			lis.add(n[i]+"");
		}
		return lis;
	}
	
	public static String[] getArray(List<String> lis){
		String[] a = new String[lis.size()];
		for (int i = 0; i < lis.size(); i++) {
			a[i] = lis.get(i);
		}
		return a;
	}
	

	public static void printDp(List<String> s1, List<String> s2, int[][] memo) {
		String[] col = getArray(s1);
		String[] row = getArray(s2);
		for (int i = 0; i < memo[0].length; i++) {
			if(i == 0) 
				System.out.printf("%6s", 0);
			else 
				System.out.printf("%2s", row[i-1]);
			System.out.printf("%2s", "");
		}
		System.out.println("");
		for (int i = 0; i < memo.length; i++) {
			if(i == 0)
				System.out.printf("%2s",0);
			else
				System.out.printf("%2s", Integer.parseInt(col[i-1]));
			System.out.printf("%2s", "");
			for (int j = 0; j < memo[0].length; j++) {
				System.out.printf("%2d  ", memo[i][j]);
			}
			System.out.println("");
		}
	}

	public static boolean isPalindrome(String str, int i, int j) {
        String sub_str = str.substring(i,j+1);
		String rev = "";
        boolean ans = false;
 
        for (int k = j; k >= i; k--)
            rev = rev + str.charAt(k);
        
        if (sub_str.equals(rev))
            ans = true;

        return ans;
     }

	public static int sumPages(int[] pages, int s, int f) {
		int sum = 0;
		for (int i = s; i <=f ; i++) {
			sum+=pages[i];
		}
		return sum;
	}

    public static void printBinaryTree(Node root)
    {
        LinkedList<Node> treeLevel = new LinkedList<Node>();
        treeLevel.add(root);
        LinkedList<Node> temp = new LinkedList<Node>();
        int counter = 0;
        int height = heightOfTree(root) - 1;
        // System.out.println(height);
        double numberOfElements
            = (Math.pow(2, (height + 1)) - 1);
        // System.out.println(numberOfElements);
        while (counter <= height) {
            Node removed = treeLevel.removeFirst();
            if (temp.isEmpty()) {
                printSpace(numberOfElements
                               / Math.pow(2, counter + 1),
                           removed);
            }
            else {
                printSpace(numberOfElements
                               / Math.pow(2, counter),
                           removed);
            }
            if (removed == null) {
                temp.add(null);
                temp.add(null);
            }
            else {
                temp.add(removed.left);
                temp.add(removed.right);
            }
 
            if (treeLevel.isEmpty()) {
                System.out.println("");
                System.out.println("");
                treeLevel = temp;
                temp = new LinkedList<>();
                counter++;
            }
        }
    }
 
    public static void printSpace(double n, Node removed)
    {
        for (; n > 0; n--) {
            System.out.print("\t");
        }
        if (removed == null) {
            System.out.print(" ");
        }
        else {
            System.out.printf("%2s", removed.data);
        }
    }
 
    public static int heightOfTree(Node root)
    {
        if (root == null) {
            return 0;
        }
        return 1
            + Math.max(heightOfTree(root.left),
                       heightOfTree(root.right));
    }
}

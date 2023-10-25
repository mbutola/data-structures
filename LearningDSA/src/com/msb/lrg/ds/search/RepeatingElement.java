package com.msb.lrg.ds.search;

import java.util.Arrays;

public class RepeatingElement {

	public static void main(String[] args) {
		int[] a = new int[]{0,4,1,3,5,2,6,4};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + RepeatingElement.findRepeat(a));
	}

	public static int findRepeat(int arr[])
	{
		int slow = arr[0]+1, fast = arr[0]+1;
		do{
			slow = arr[slow]+1;
			fast = arr[arr[fast]+1] + 1;
		
		}while(slow != fast);
		
		slow = arr[0]+1;
		while(slow != fast)
		{
			slow = arr[slow]+1;
			fast = arr[fast]+1;
		}
		return slow-1;
	}}

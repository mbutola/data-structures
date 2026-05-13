package com.msb.lrg.problems.interview;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindow {

	public static void main(String[] args) {
        SlidingWindow s = new SlidingWindow();
        System.out.println(Arrays.toString(
            s.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3)
        ));
	}
	
    public int[] maxSlidingWindow(int[] nums,int k){
        Deque<Integer> dq=new ArrayDeque<>();
        int[] res=new int[nums.length-k+1];

        for(int i=0;i<nums.length;i++){
            while(!dq.isEmpty()&&dq.peek()<i-k+1) dq.poll();
            while(!dq.isEmpty()&&nums[dq.peekLast()]<nums[i]) dq.pollLast();
            dq.offer(i);
            if(i>=k-1) res[i-k+1]=nums[dq.peek()];
        }
        return res;
    }

	
//	public int[] maxSlidingWindow(int[] nums, int k) {
//		
//		int sum = nums[0] + nums[1] + nums[2];
//		int[] res = new int[2];
//		int i = 0, j = 2;
//		res[0] = i;
//		res[1] = j;
//		while(j < nums.length - 1) {
//			i++; j++;
//			int newSum = sum + nums[j] - nums[i];
//			if(newSum > sum) {
//				res[0] = i;
//				res[1] = j;
//				sum = newSum;
//			}
//			
//		}
//		return res;
//	}
}

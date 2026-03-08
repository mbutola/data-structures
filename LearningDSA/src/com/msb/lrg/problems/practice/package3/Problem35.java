package com.msb.lrg.problems.practice.package3;

/*

Trapping Rain Water :: LeetCode (42 Hard )
	Given n non-negative integers representing an elevation map where the width of each bar is 1, 
	compute how much water it can trap after raining.
	Example 1:
		Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
		Output: 6
		Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
	Example 2:
		Input: height = [4,2,0,3,2,5]
		Output: 9
	Constraints:
		n == height.length
		1 <= n <= 2 * 104
		0 <= height[i] <= 105

 */
public class Problem35 {

	public static void main(String[] args) {
		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
//		int[] height = {4,2,0,3,2,5};
//		int[] height = {0,1,0,0,0,1};
		int res = trapSP(height);
		System.out.println("Result Prefix Suffix :: " + res);
		res = trapSW(height);
		System.out.println("Result Sliding Window :: " + res);
	}

    static int trapSW(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }
    
    static int trapSP(int[] height) {
    	
    	int N = height.length;
    	if(N == 0)
    		return 0;

    	int[] pre = new int[N];
    	pre[0] = height[0];
    	for(int i = 1; i < N; i++) {
    		pre[i] = Math.max(pre[i - 1], height[i]);
    	}
        
    	int[] suf = new int[N];
    	suf[N - 1] = height[N - 1];
    	for(int i = N - 2; i >= 0; i--) {
    		suf[i] = Math.max(suf[i + 1], height[i]);
    	}
    	
    	int water = 0;
    	for(int i = 0; i < N; i++) {
   			water += (Math.min(pre[i], suf[i]) - height[i]);
    	}
        
    	return water;
    }

}

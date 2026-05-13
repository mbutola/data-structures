package com.msb.lrg.problems.frequentlyasked;

/*

	🧱 Container With Most Water
	🧠 One-line idea
		👉 Start from both ends and move the shorter line inward
	🧩 Problem
		Given heights:
			[1,8,6,2,5,4,8,3,7]
		👉 Choose two lines that form the maximum area container
		📐 Area formula
			Area = min(height[left], height[right]) × (right - left)
	⚙️ Technique: Two pointers
		left = 0
		right = n - 1
	🔄 Core logic
		1. Calculate area
		2. Move the pointer with smaller height
	🧠 Why move smaller height?
		👉 Because:
			Area depends on smaller height
		Moving the taller one:
			❌ won’t increase height
			❌ reduces width → worse
	🔍 Example (short)
		left=1, right=7 → area = 1×8 = 8  
		move left
		left=8, right=7 → area = 7×7 = 49 ✔ best

 */
public class F005ContainerWithMostWater {

	public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height)); // Output: 49
	}

    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            int area = h * w;

            maxArea = Math.max(maxArea, area);

            // move smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

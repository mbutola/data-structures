package com.msb.lrg.problems.frequentlyasked;

import java.util.PriorityQueue;

public class F011TopKFrequent {

	public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        System.out.println(findKthLargest(nums, 2)); // 5
        System.out.println(quickSelect(nums, 0, nums.length - 1, nums.length - 2)); // 5
	}

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.add(num);

            if (minHeap.size() > k) {
                minHeap.poll(); // remove smallest
            }
        }

        return minHeap.peek();
    }

    /*
		⚙️ Approach 2: Quickselect (faster)
			👉 Like quicksort partition
		🔄 Idea
			Partition array  
			Check pivot position  
			Go left or right
	*/
    private static int quickSelect(int[] nums, int left, int right, int k) {
    	
    	int pivot = nums[right];
    	int p = left;
    	for(int i = left; i < right; i++) {
    		if(nums[i] <= pivot)
    			swap(nums, i, p++);
    	}
    	
    	swap(nums, p, right);
    	
    	if(p == k) return nums[p];
    	else if(p < k)
    		return quickSelect(nums, p + 1, right, k);
    	else 
    		return quickSelect(nums, left, p - 1, k);
    	
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

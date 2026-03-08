package com.msb.lrg.problems.practice.package5;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Problem55 {

    public static void main(String[] args) {
        int[] arr = {4, 2, 5, 1, 3};
        int k = 3;

        System.out.println(Arrays.toString(minSlidingWindow(arr, k)));
        // Output: [2, 1, 1]
    }

    public static int[] minSlidingWindow(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0)
            return new int[0];

        int n = arr.length;
        int[] ans = new int[n - k + 1];

        Deque<Integer> dq = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; i++) {

            // 1️⃣ Remove indices outside window
        	int z = 0;
        	int y = 0;
        	if(!dq.isEmpty()) {
        		z = dq.peekFirst();
        	}
            
        	if (!dq.isEmpty() && dq.peekFirst() <= i - k)
                dq.removeFirst();

        	if(!dq.isEmpty()) {
        		y  = dq.peekLast();
        	}
            
        	// 2️⃣ Maintain increasing order (monotonic)
            while (!dq.isEmpty() && arr[dq.peekLast()] > arr[i])
                dq.removeLast();

            // 3️⃣ Add current index
            dq.addLast(i);

            // 4️⃣ Window ready → record min
            if (i >= k - 1)
                ans[i - k + 1] = arr[dq.peekFirst()];
        }

        return ans;
    }

}

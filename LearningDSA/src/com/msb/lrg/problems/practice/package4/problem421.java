package com.msb.lrg.problems.practice.package4;

import java.util.Arrays;

/*

| Algorithm          |   Time (Avg) | Time (Worst) |    Space | Stable |In-Place| When to Use           |
| ------------------ | -----------: | -----------: | -------: |:-----: |:------:| --------------------- |
| **Bubble Sort**    |        O(n²) |        O(n²) |     O(1) |   ✅   |    ✅   | Teaching only         |
| **Selection Sort** |        O(n²) |        O(n²) |     O(1) |   ❌   |    ✅   | Few swaps needed      |
| **Insertion Sort** |        O(n²) |        O(n²) |     O(1) |   ✅   |    ✅   | Small / nearly sorted |
| **Merge Sort**     |   O(n log n) |   O(n log n) |     O(n) |   ✅   |    ❌   | Stable, large data    |
| **Quick Sort**     |   O(n log n) |        O(n²) | O(log n) |   ❌   |    ✅   | Fast in practice      |
| **Heap Sort**      |   O(n log n) |   O(n log n) |     O(1) |   ❌   |    ✅   | Guaranteed bounds     |
| **Counting Sort**  |     O(n + k) |     O(n + k) |     O(k) |   ✅   |    ❌   | Small integer range   |
| **Radix Sort**     | O(d·(n + k)) | O(d·(n + k)) | O(n + k) |   ✅   |    ❌   | Fixed-length keys     |
| **Bucket Sort**    |     O(n + k) |        O(n²) | O(n + k) |Depends |    ❌   | Uniform distribution  |

0-Based Indexing (MOST COMMON)
	| Node             | Formula       |
	| ---------------- | ------------- |
	| Parent of i      | `(i - 1) / 2` |
	| Left child of i  | `2*i + 1`     |
	| Right child of i | `2*i + 2`     |
	
	if array size is n
	then last index = i = n - 1
	so last parent node  =  (i - 1)/2 = (n -1 -1)/2 = n/2 -1


1-Based Indexing
	| Node             | Formula   |
	| ---------------- | --------- |
	| Parent of i      | `i / 2`   |
	| Left child of i  | `2*i`     |
	| Right child of i | `2*i + 1` |


 */
public class problem421 {

	public static void main(String[] args) {
		int[] nums = {3,7,2,1,9,5,6,8,4};
//		System.out.println("Original :: " + Arrays.toString(nums));
//		bubbleSort(Arrays.copyOf(nums, nums.length));
//		selectionSort(Arrays.copyOf(nums, nums.length));
//		insertionSort(Arrays.copyOf(nums, nums.length));
//		int[] temp = Arrays.copyOf(nums, nums.length);
//		System.out.println("Result :: merge sort :: input  :: " + Arrays.toString(temp));
//		mergeSort(temp, 0, temp.length - 1);
//		System.out.println("Result :: merge sort :: output  :: " + Arrays.toString(temp));
//		int[] temp1 = Arrays.copyOf(nums, nums.length);
//		System.out.println("Result :: quick sort :: input  :: " + Arrays.toString(temp1));
//		quickSort(temp1, 0, temp1.length - 1);
//		System.out.println("Result :: quick sort :: output  :: " + Arrays.toString(temp1));
//		int[] temp2 = Arrays.copyOf(nums, nums.length);
//		System.out.println("Result :: heap sort :: input  :: " + Arrays.toString(temp2));
//		heapSort(temp2);
//		System.out.println("Result :: heap sort :: output  :: " + Arrays.toString(temp2));
//      int[] temp3 = Arrays.copyOf(nums, nums.length);
//      System.out.println("Result :: counting sort :: input   :: " + Arrays.toString(temp3));
//      countingSort(temp3);
//      System.out.println("Result :: counting sort :: output  :: " + Arrays.toString(temp3));
//      int[] temp4 = Arrays.copyOf(nums, nums.length);
      int[] temp4 = {170, 45, 75, 90, 802, 24, 2, 66};
      System.out.println("Result :: radix sort :: input   :: " + Arrays.toString(temp4));
      radixSort(temp4);
      System.out.println("Result :: radix sort :: output  :: " + Arrays.toString(temp4));
	}
	
    static void radixSort(int[] arr) {
    	int max = arr[0];
    	
    	for(int i = 1; i < arr.length; i++) {
    		if(arr[1] > max)
    			max = arr[i];
    	}
    	
    	for(int exp = 1; max/exp > 0; exp*=10) {
    		countingSort(arr, exp);
    	}

    }

    static void countingSort(int[] arr, int exp) {
    	int n = arr.length;
    	int[] output = new int[n];
    	int[] count = new int[10];
    	
    	for(int i = 0; i < n; i++) {
    		int digit = (arr[i] / exp) % 10;
    		count[digit]++;    		
    	}
    	
    	for(int i = 1; i < count.length; i++) {
    		count[i] += count[i - 1];
    	}
    	
    	for(int i = n-1; i >= 0; i--) {
    		int digit = (arr[i] / exp) % 10;
    		output[count[digit] - 1] = arr[i];
    		count[digit]--;
    	}
    	
    	for(int i = 0; i < n; i++) {
    		arr[i] = output[i];
    	}
    }

    static void countingSort(int[] nums) {
		int max = -1;
		for(int num : nums) {
			if(num > max)
				max = num;
		}
		
		int[] count = new int[max + 1];

		for(int num : nums) {
			count[num]++;
		}
		
		int k = 0;
		for(int i = 0; i < count.length; i++) {
			while(count[i] > 0) {
				nums[k++] = i;
				count[i]--;
			}
		}

	}

	static void heapSort(int[] nums) {
		int n = nums.length; 
		for(int i = n/2 - 1; i >= 0; i--) {
			heapify(nums, n, i);
		}
		
		for(int i = n - 1; i >= 0; i--) {
			swap(nums, i, 0);
			heapify(nums, i, 0);
		}
		
	}
	
	static void heapify(int[] nums, int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		
		if(l < n && nums[l] > nums[largest]) largest = l;
		if(r < n && nums[r] > nums[largest]) largest = r;
		
		if(largest != i) {
			swap(nums, i, largest);
			heapify(nums, n, largest);
		}
	}
	
	static void quickSort(int[] nums, int low, int high) {
		if(low >= high) return;
		
		int p = partition(nums, low, high);
		
		quickSort(nums, low, p - 1);
		quickSort(nums, p + 1, high);
	}
	
	static int partition(int[] nums, int low, int high) {
		int num = nums[high];
		int i = low;
		
		for(int j = i; j < high; j++) {
			if(nums[j] <= num) {
				swap(nums, i, j);
				i++;
			}
		}
		swap(nums, i, high);
		return i;
	}
	
	static void mergeSort(int[] nums, int l, int r) {
		if(l >= r) return;

		int m = (l + r)/2;

		mergeSort(nums, l, m);
		mergeSort(nums, m + 1, r);

		merge(nums, l, m, r);
	}
	
	static void merge(int[] nums, int l, int m, int r) {
		int n = (r - l) + 1;
		int[] temp = new int[n];
		int i = l;
		int j = m + 1;
		int k = 0;
		while(i <= m && j <= r) {
			if(nums[i] < nums[j]) 
				temp[k++] = nums[i++];
			else 
				temp[k++] = nums[j++];
		}
		
		while(i <= m) 
			temp[k++] = nums[i++];

		while(j <= r) 
			temp[k++] = nums[j++];

	    for (i = 0; i < temp.length; i++)
	        nums[l + i] = temp[i];		
		
	}
	
	static void insertionSort(int[] nums) {
		System.out.println("Result :: insertion sort :: input  :: " + Arrays.toString(nums));
		
		for(int i = 1; i < nums.length; i++) {
			int j = i;
			int num = nums[j];
			while(j > 0 && nums[j-1] > num){
				nums[j] = nums[j-1];
				j--;
			}
			nums[j] = num; 
		}		
		System.out.println("Result :: insertion sort :: input  :: " + Arrays.toString(nums));
	}
	
	static void selectionSort(int[] nums) {
		System.out.println("Result :: selection sort :: input  :: " + Arrays.toString(nums));
		
		for(int i = 0; i < nums.length - 1; i++) {
			int minIndex = i;
			for(int j = i + 1; j < nums.length; j++) {
				if(nums[j] < nums[minIndex]) {
					minIndex = j;
				}
			}
			swap(nums, i, minIndex);
		}
		System.out.println("Result :: selection sort :: output :: " + Arrays.toString(nums));
	}

	static void bubbleSort(int[] nums) {
		System.out.println("Result :: bubble sort :: input  :: " + Arrays.toString(nums));
		for(int i = 0; i < nums.length - 1; i++) {
			for(int j = 0; j < (nums.length - 1) - i; j++) {
				if(nums[j + 1] < nums[j])
					swap(nums, j, j + 1);
			}
		}
		System.out.println("Result :: bubble sort :: output :: " + Arrays.toString(nums));
	}
	
	static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}

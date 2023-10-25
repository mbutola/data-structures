package com.msb.lrg.ds.search;

import java.util.Arrays;

public class AllocateMinPages {

	public static void main(String[] args) {
		int  k = 2;
		int[] a = new int[]{10,20,10,30};
		System.out.println("I/P : " + Arrays.toString(a));
		System.out.println("O/P : " + AllocateMinPages.minPages(a, k));
	}

    public static boolean isFeasible(int arr[], int k, int ans){
        int req = 1,sum = 0;
        for(int i=0; i<arr.length; i++){
            if(sum + arr[i] > ans){
                req++;
                sum = arr[i];
            }
            else{
                sum+=arr[i];
            }
        }
        return (req <= k);
    }
    
    public static int minPages(int arr[], int k){
        int sum = 0,mx = 0;
        for(int i=0; i< arr.length; i++){
            sum+=arr[i];
            mx = Math.max(mx,arr[i]);
        }
        int low = mx, high = sum,res = 0;
        
        while(low <= high){
            int mid = (low+high)/2;
            if(isFeasible(arr, k, mid)){
                res = mid;
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return res;
    } 

}

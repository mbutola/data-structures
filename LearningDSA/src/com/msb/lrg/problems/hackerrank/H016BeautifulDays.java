package com.msb.lrg.problems.hackerrank;

public class H016BeautifulDays {

	public static void main(String[] args) {
		int i = 20;
		int j = 23;
		int k = 6;
		int res = beautifulDays(i, j, k);
		System.out.println(res);
	}

    public static int beautifulDays(int i, int j, int k) {
        int bDays = 0;
        for(int d = i; d <= j; d++){
            int revD = Math.abs(reverse(d) - d);
            if(revD % k == 0)
                bDays++;            
        }
        return bDays;
     }
     
     private static int reverse(int num){
    	 int rev = 0;
    	 
    	 while(num > 0) {
    		 rev = rev * 10 + num % 10;
    		 num /= 10;
    	 }
    	 
    	 return rev;
    	 
//        char[] chars = str.toCharArray();
//        int i = 0;
//        int j = str.length() - 1;
//        while(j > i){
//            char temp = chars[i];
//            chars[i] = chars[j];
//            chars[j] = temp;
//            i++;
//            j--;
//        }
//        return String.valueOf(chars);
        
//        return new StringBuilder(String.valueOf(str)).reverse().toString();
     }

}

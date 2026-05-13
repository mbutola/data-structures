package com.msb.lrg.problems.hackerrank;

import java.math.BigInteger;

/*

	The factorial of the integer , written , is defined as:
		Calculate and print the factorial of a given integer.
		For example, if , we calculate  and get .
	Function Description
		Complete the extraLongFactorials function in the editor below. It should print the result and return.
		extraLongFactorials has the following parameter(s):
			n: an integer
		Note: Factorials of  can't be stored even in a  long long variable. Big integers must be used for such 
		calculations. Languages like Java, Python, Ruby etc. can handle big integers, but we need to write 
		additional code in C/C++ to handle huge values.
		We recommend solving this challenge using BigIntegers.
	Input Format
		Input consists of a single integer 
	Output Format
		Print the factorial of .
	Sample Input
		25	
	Sample Output
		15511210043330985984000000



 */
public class H019ExtraLongFactorials {

	public static void main(String[] args) {
		extraLongFactorials(25);
	}

    public static void extraLongFactorials(int n) {
        BigInteger result = BigInteger.ONE;
        
        for(int i = 2; i <=n; i++){
            result = result.multiply(BigInteger.valueOf(i)); 
        }
        
        System.out.println(result);
    }

}

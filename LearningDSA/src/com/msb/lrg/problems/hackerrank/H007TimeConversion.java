package com.msb.lrg.problems.hackerrank;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/*

	Given a time in -hour AM/PM format, convert it to military (24-hour) time.
		Note: - 12:00:00AM on a 12-hour clock is 00:00:00 on a 24-hour clock.
		- 12:00:00PM on a 12-hour clock is 12:00:00 on a 24-hour clock.
	Example
		Return '12:01:00'.
		Return '00:01:00'.
	Function Description
		Complete the  function with the following parameter(s):
		: a time in  hour format
	Returns
		: the time in  hour format
	Input Format
		A single string  that represents a time in -hour clock format (i.e.:  or ).
	Constraints
		All input times are valid
	Sample Input 0
		07:05:45PM
	Sample Output 0
		19:05:45

 */
public class H007TimeConversion {

	public static void main(String[] args) {
		String s = "07:05:45PM";
		String res = timeConversion(s);
		System.out.println(res);
	}

//    public static String timeConversion(String s) {
//		int hr = Integer.parseInt(s.substring(0, 2));
//		String format = s.substring(8);
//
//		if (format.equalsIgnoreCase("AM")) {
//			if (hr == 12)
//				hr = 0;
//		} else {
//			if (hr != 12)
//				hr += 12;
//		}
//		return String.format("%02d%s", hr, s.substring(2, 8));
//    }

//    public static String timeConversion(String s) {
//        String hr = s.substring(0, 2);
//        String format = s.substring(8);
//
//        if(format.equalsIgnoreCase("AM")) {
//        	if(hr.equalsIgnoreCase("12")) 
//        		hr = "00";
//        }else{
//        	if(!hr.equalsIgnoreCase("12")) 
//        		hr = String.valueOf(Integer.parseInt(hr) + 12);
//        }
//        return hr + s.substring(2, 8);
//    }

    public static String timeConversion(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ssa", Locale.ENGLISH);
        LocalTime time = LocalTime.parse(s, formatter);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return time.format(newFormatter);
    }
}

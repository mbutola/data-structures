package com.msb.lrg.problems.design;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicLong;

public class AATimePractice {

	public static void main(String[] args) {
		
		
//		System.out.println("System.currentTimeMillis() : " + System.currentTimeMillis());
//		System.out.println("System.nanoTime():  " + System.nanoTime());
//
//		AtomicLong atomicLong = new AtomicLong();
//		long time = atomicLong.incrementAndGet();
//		System.out.println("AtomicLong : " + time);
//		
//		long hybrid = System.currentTimeMillis() * 1000 + atomicLong.incrementAndGet();
//		System.out.println("hybrid : " + hybrid);
//		System.out.println(" " + );
		
//        long epochMillis = LocalDateTime.of(2020, 1, 1, 0, 0)
//                .atZone(ZoneId.of("UTC"))
//                .toInstant()
//                .toEpochMilli();
//
//        System.out.println("1 Jan 2020 : " + epochMillis);
//        
        
        long CUSTOM_EPOCH = 1577836800000L; // 2020

        long now = System.currentTimeMillis();
        long time41 = (now - CUSTOM_EPOCH) & ((1L << 41) - 1);

        System.out.println(time41);
        
	}

}

package com.msb.lrg.problems.design.practice;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class SnowflakeIdGenerator {
	
    private final long nodeId;
    private final long epoch = epochMilli(); // custom epoch (2023)

    private final long nodeIdBits = 10;
    private final long sequenceBits = 12;

    private final long maxNodeId = (1 << nodeIdBits) - 1;
    private final long maxSequence = (1 << sequenceBits) - 1;

    private final long nodeShift = sequenceBits;
    private final long timestampShift = sequenceBits + nodeIdBits;

    private long lastTimestamp = -1L;
    private long sequence = 0L;
    
    SnowflakeIdGenerator(long nodeId){
    	/*
    	 * check noe id in range
    	 */
    	this.nodeId = nodeId;
    }
	
	public synchronized long nextId() {
		
		/*
		 * Get System currentTime
		 * if less than last access throw error
		 * if equal to last access
		 * 	increment sequence
		 * 	check if over flow
		 * 	seq & maxSeq
		 * 	wait for next mill second
		 * 	set currenttime
		 * else
		 * 	seq to 0;
		 * generate id with shift
		 * 
		 */
		
		return 0;
	}
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public long epochMilli() {
		return LocalDateTime.of(2020,1,1,0,0)
		.atZone(ZoneId.of("UTC"))		
		.toInstant()
		.toEpochMilli();
		
	}
}

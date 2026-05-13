package com.msb.lrg.problems.design;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

public class K1SnowflakeIdGeneratorDesign {

	static class SnowflakeIdGenerator {
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
	
	    public SnowflakeIdGenerator(long nodeId) {
	        if (nodeId < 0 || nodeId > maxNodeId) {
	            throw new IllegalArgumentException("NodeId out of range");
	        }
	        this.nodeId = nodeId;
	    }
	
	    public synchronized long nextId() {
	    	
	        long currentTimestamp = currentTime();
	
	        // 🔴 Clock moved backward
	        if (currentTimestamp < lastTimestamp) {
	            throw new RuntimeException("Clock moved backwards!");
	        }
	
	        // Same millisecond
	        if (currentTimestamp == lastTimestamp) {
	            sequence = (sequence + 1) & maxSequence;
	
	            // Sequence overflow
	            if (sequence == 0) {
	                currentTimestamp = waitNextMillis(currentTimestamp);
	            }
	        } else {
	            sequence = 0;
	        }
	
	        lastTimestamp = currentTimestamp;
	
	        return ((currentTimestamp - epoch) << timestampShift)
	                | (nodeId << nodeShift)
	                | sequence;
	    }

	    private long waitNextMillis(long currentTimestamp) {
	        while (currentTimestamp <= lastTimestamp) {
	            currentTimestamp = currentTime();
	        }
	        return currentTimestamp;
	    }
	
	    private long currentTime() {
	        return System.currentTimeMillis();
	    }
	    
	    private long epochMilli() {
	      return LocalDateTime.of(2020, 1, 1, 0, 0)
	      .atZone(ZoneId.of("UTC"))
	      .toInstant()
	      .toEpochMilli();
	    }
	}	

    
    // 🧪 MAIN METHOD (TEST)
    public static void main(String[] args) {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1);

        Set<Long> uniqueIds = new HashSet<>();

        System.out.println("Generating IDs:\n");

        for (int i = 0; i < 20; i++) {
            long id = generator.nextId();
            System.out.println(id);

            // Check uniqueness
            if (!uniqueIds.add(id)) {
                System.out.println("❌ Duplicate ID found!");
            }
        }

        System.out.println("\nTotal unique IDs generated: " + uniqueIds.size());
    }
}
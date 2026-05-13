package com.msb.lrg.problems.design;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/*

	🔥 Key Design Patterns Used
		Producer-Consumer → queue + worker
		Strategy Pattern → job handlers
		Factory Pattern → handler selection
		Retry Mechanism → fault tolerance
	⚡ How to Scale (FAANG Discussion)
		👉 Replace in-memory queue with:
			Apache Kafka
			Amazon SQS
			RabbitMQ
		👉 Add:
			Distributed workers
			Job persistence (DB)
			Dead Letter Queue (DLQ)
			Backoff retry strategy
			Idempotency
	🧠 Interview Summary
		👉 Core idea:
			Queue decouples producer & consumer
			Workers scale horizontally
			Retry + DLQ ensures reliability

 */
public class A001JobSystem {
	public static void main(String[] args) {
        JobQueue queue = new InMemoryJobQueue();
        JobHandlerFactory factory = new JobHandlerFactory();

        // 1️⃣ Create thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 2️⃣ Submit workers
        for (int i = 0; i < 3; i++) {
            executor.submit(new Worker(factory, queue));
        }

        // 3️⃣ Submit jobs
        Job job1 = new Job();
        job1.id = "1";
        job1.type = "EMAIL";
        job1.payload = "user@example.com";

        Job job2 = new Job();
        job2.id = "2";
        job2.type = "PAYMENT";
        job2.payload = "order123";

        queue.push(job1);
        queue.push(job2);

        // 4️⃣ Shutdown hook (graceful shutdown)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down...");
            shutdownExecutor(executor);
        }));
	}

	private static void shutdownExecutor(ExecutorService executor) {
		executor.shutdown(); // stop accepting new tasks

		try {
			if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
				executor.shutdownNow(); // force stop
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
	
	static class Worker implements Runnable  {
		private JobHandlerFactory factory;
		private JobQueue queue;
		
		Worker(JobHandlerFactory factory, JobQueue queue){
			this.factory = factory;
			this.queue = queue;
		}

		@Override
		public void run() {
	        while (true) {
	            Job job = queue.pull();
	            if (job == null) continue;

	            try {
	                job.status = JobStatus.RUNNING;

	                JobHandler handler = factory.getHandler(job.type);
	                handler.handle(job);

	                job.status = JobStatus.SUCCESS;
	            } catch (Exception e) {
	                job.retryCount++;
	                job.status = JobStatus.FAILED;

	                if (job.retryCount < 3) {
	                    job.status = JobStatus.RETRY;
	                    queue.push(job);
	                } else {
	                    System.out.println("Moved to DLQ: " + job.id);
	                }
	            }
	        }
		}
	}
	
	static interface JobHandler {
		public void handle(Job job);
	}
	
	static class EmailJobHandler implements JobHandler {
		@Override
		public void handle(Job job) {
			System.out.println("Sending email: " + job.payload);		
		}		
	}
	
	static class PaymentJobHandler implements JobHandler {
		@Override
		public void handle(Job job) {
			System.out.println("Processing payment: " + job.payload);		
		}		
	}

	static class JobHandlerFactory {
		Map<String, JobHandler> handlers = new HashMap<>();

		JobHandlerFactory(){
	        handlers.put("EMAIL", new EmailJobHandler());
	        handlers.put("PAYMENT", new PaymentJobHandler());
		}
		
		public JobHandler getHandler(String type) {
			return handlers.get(type);
		}
		
	}

	static class InMemoryJobQueue implements JobQueue {
		
		BlockingQueue<Job> queue = new LinkedBlockingDeque<>();

	    public void push(Job job) {
	        queue.offer(job);
	    }

	    public Job pull() {
	        try {
	            return queue.take(); // blocking
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            return null;
	        }
	    }
	}
	
	static interface JobQueue {
		void push(Job job);
		Job pull();
	}
	
	static class Job {
		String id;
		String type;
		String payload;
		JobStatus status;
		int retryCount;
		long scheduledAt;
		
	}
	
	static enum JobStatus {
		PENDING, RUNNING, SUCCESS, FAILED, RETRY; 
	}
	
}

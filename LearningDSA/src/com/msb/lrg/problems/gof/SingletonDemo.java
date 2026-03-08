package com.msb.lrg.problems.gof;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;

/*
 	Singleton
 	Purpose: Ensure only one instance of a class exists globally and provide a global access point.
 	Issue Solved: Multiple objects sharing same resource/config (like logging or DB connections) cause inconsistency or high cost.
 	How: Private constructor + static instance + global accessor.
 	Example: Logger.getInstance().log("error")
 */
public class SingletonDemo {

	public static void main(String[] args) throws Exception{
		Logger l1 = Logger.getInstance();		
		Logger l2 = Logger.getInstance();
		l1.logMessage("Helllo");
		System.out.println(l1 == l2);
		
		TestSingleton test = new TestSingleton();
		test.test();
	}
	

}

/*
 * Basic Singleton (Eager Initialization)
 * Explanation:
 * 		Instance created at class load time.
 * 		Simple and thread-safe (class loading is synchronized).
 * 		Downside: instance is created even if never used (memory cost).
 */
class BasicSingleton {
    private static final BasicSingleton instance = new BasicSingleton();

    private BasicSingleton() {
        // private constructor prevents instantiation
    }

    public static BasicSingleton getInstance() {
        return instance;
    }
}

/*
 * Thread-Safe Singleton (Synchronized Method)
 * Pros:
 * 		Thread-safe.
 * Cons:
 * 		Synchronization on every call → performance overhead.
 */

class Logger {
	
	private static Logger instance = null;
	
	private Logger() {}
	
	public static synchronized Logger getInstance() {
		if(instance == null)
			instance = new Logger();
		
		return instance;
	}
	
	public void logMessage(String msg) {
		System.out.println("Log :: " + msg);
	}

}

/*
 * Thread-safe.
 * Lazy loading.
 * Good performance.
 * 
 * Why we need volatile

	The issue lies in this line 👇
		instance = new DoubleCheckedSingleton();

	This statement is not atomic — under the hood, it executes in 3 steps:
	Step	Action
	(1)		Allocate memory for DoubleCheckedSingleton
	(2)		Initialize the object (call constructor)
	(3)		Assign reference to variable instance

	⚡ Problem — Instruction Reordering
		The Java compiler and CPU are allowed to reorder instructions for optimization — as long as the 
		single-threaded result doesn’t change.
		But in multithreaded code, this can cause a race condition!

	Reordering might make steps occur like this:
		Step 1 → Step 3 → Step 2

	So the assignment happens before the constructor finishes!

	❗ Bad Scenario (without volatile)
		Thread 1:
			instance = new DoubleCheckedSingleton(); // reordering occurs
		Thread 2 (at same time):
			if (instance != null) // returns true
    			use(instance);     // but instance is half-initialized!
		
		Thread 1 has assigned reference,
		but constructor hasn’t completed → other threads see partially constructed object.
		This can cause weird null fields, partial initialization, or crashes.

	How volatile fixes this
		When you declare:
			private static volatile DoubleCheckedSingleton instance;
		Java guarantees two things:
			Visibility:
				Every read of a volatile variable sees the latest write (no caching).
				Thread 2 always sees the correct instance.
			Reordering prevention:
				The JVM inserts memory barriers that prevent Step 3 from moving before Step 2.
				So initialization (constructor) always happens before assignment.
				
	Two null checks
	Because we want to achieve both:
		Efficiency – avoid locking every time (synchronized is slow).
		Correctness – ensure only one instance is created, even under concurrency.
	Each null check serves a distinct purpose.

	Let’s understand both checks separately
			🔹 First if (instance == null) — Fast path check
		This check is outside the synchronized block.
		Most of the time, the singleton instance will already be created.
		So, if it’s not null, we can immediately return it — no locking overhead.
		This keeps performance high because:
		Once initialized, multiple threads can read the singleton without blocking.

	Second if (instance == null) — Safety check inside lock
		This one is inside the synchronized block.
		Even if multiple threads passed the first check at the same time,
		only one of them should actually create the object.
		Otherwise, multiple instances might get created.

 	*/

class DoubleCheckedSingleton {
    private static volatile DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton() {}

    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) { // Second check (inside lock)
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
    
 }    

/*
 * Why it’s elegant:
 * 		Lazy loaded (inner class loads only when needed).
 * 		Thread-safe (class loading is synchronized).
 * 		No synchronization overhead.
 */

class BillPughSingleton {
    	
	private BillPughSingleton() {}
	
	private static class Helper{
		private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }
	
	public static BillPughSingleton getInstance() {
		return Helper.INSTANCE;
	}
    	
}

/*
 
	The Problem — Reflection Before getInstance()
	If someone uses reflection before getInstance() is ever called:
		Constructor<HolderSingleton> c = HolderSingleton.class.getDeclaredConstructor();
		c.setAccessible(true);
		HolderSingleton s1 = c.newInstance(); // Reflection creates new object
	
		HolderSingleton s2 = HolderSingleton.getInstance(); // Holder loads now
	
		System.out.println(s1 == s2); // ❌ false
  
 */
class HelperSingleton {
	
	private HelperSingleton() {}
	
	private static class Helper{
		private static final HelperSingleton INSTANCE = new HelperSingleton();
    }
	
	public static HelperSingleton getInstance() {
		return Helper.INSTANCE;
	}
	
}

/*
 
 Add Reflection Safety
 	To block this, add a guard flag in the constructor
 Add Serialization Safety
 	If you serialize and deserialize a Singleton, it creates a new instance.
	To prevent that, override readResolve().
 Add Clone Safety
 	If the class implements Cloneable, a malicious caller could clone the singleton.
 	Override clone() to prevent that.
 */

class HelperSingletonImproved implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
    
	// 🔹 Independent flag in outer class
    private static boolean instanceCreated = false;

    private HelperSingletonImproved() {
        synchronized (HelperSingletonImproved.class) {
            if (instanceCreated) {
                throw new RuntimeException("Use getInstance() - reflection not allowed");
            }
            instanceCreated = true;
        }
    }

    private static class Holder {
        private static final HelperSingletonImproved INSTANCE =
                new HelperSingletonImproved();
    }

    public static HelperSingletonImproved getInstance() {
        return Holder.INSTANCE;
    }

    // 🔒 Serialization-safe
    private Object readResolve() {
        return getInstance();
    }

    // 🔒 Cloning-safe
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }
}

/*
 
 Super-Advanced: Enum Singleton (Best and Simplest)
 Advantages:
	Thread-safe.
	Serialization-safe.
	Reflection-safe.
	Easiest implementation.
	Enum class guarantees only one instance per JVM.
 
 */
enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("Singleton via Enum");
    }
}

class TestSingleton {
    public void test() throws Exception {
    	HelperSingletonImproved s1 = HelperSingletonImproved.getInstance();
    	HelperSingletonImproved s2 = HelperSingletonImproved.getInstance();

        System.out.println("Same instance? " + (s1 == s2)); // ✅ true

        // Test Reflection
        try {
            Constructor<HelperSingletonImproved> c = HelperSingletonImproved.class.getDeclaredConstructor();
            c.setAccessible(true);
            HelperSingletonImproved s3 = c.newInstance(); // ❌ should throw
        } catch (Exception e) {
            System.out.println("Reflection attack blocked: " + e.getMessage());
        }

        // Test Cloning
        try {
        	HelperSingletonImproved s4 = (HelperSingletonImproved) s1.clone(); // ❌ should throw
        } catch (Exception e) {
            System.out.println("Clone attack blocked: " + e.getMessage());
        }

        // Test Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.ser"))) {
            oos.writeObject(s1);
            HelperSingletonImproved s5 = (HelperSingletonImproved) ois.readObject();
            System.out.println("Same after serialization? " + (s1 == s5)); // ✅ true
        }
    }
}

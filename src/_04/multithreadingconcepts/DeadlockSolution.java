package _04.multithreadingconcepts;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockSolution {

	private Lock lock1 = new ReentrantLock(true);
	private Lock lock2 = new ReentrantLock(true);
	
	public static void main(String[] args) {
		
		DeadlockSolution deadLock = new DeadlockSolution();
		
		// with java8 we can create threads like that
		new Thread(deadLock::worker1, "worker1").start();
		new Thread(deadLock::worker2, "worker2").start();
		
	}
	
	public void worker1() {
		lock1.lock();
		
		System.out.println("Worker1 acquires the lock1.");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		lock2.lock();
		System.out.println("Worker1 acquired the lock2.");
		
		lock1.unlock();
		lock2.unlock();
	}
	
	public void worker2() {
		lock1.lock();
		
		System.out.println("Worker2 acquires the lock1.");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		lock2.lock();
		System.out.println("Worker2 acquired the lock2.");
		
		lock1.unlock();
		lock2.unlock();
	}
}

// Each thread acquires the locks in the same order to prevent cyclic dependency

//Worker1 acquires the lock1.
//Worker1 acquired the lock2.
//Worker2 acquires the lock1.
//Worker2 acquired the lock2.
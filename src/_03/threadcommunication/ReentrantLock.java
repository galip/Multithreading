package _03.threadcommunication;

import java.util.concurrent.locks.Lock;

public class ReentrantLock {

	private static int counter = 0;
	private static Lock lock = new java.util.concurrent.locks.ReentrantLock();
	// If fairness parameter is set to be TRUE then longest thread will get the lock

	private static void increment() {

		lock.lock();
		
		try {
			for (int i = 0; i < 10000; i++)
				counter++;
		} finally {
			lock.unlock(); // we can move it to a seperate method independently.
							// this is an advantage of reentrant lock.
		}
	}

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				increment();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				increment();
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(counter);
	}
}

// 20000
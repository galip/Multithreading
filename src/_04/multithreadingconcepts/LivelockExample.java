package _04.multithreadingconcepts;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockExample {

	private Lock lock1 = new ReentrantLock(true);
	private Lock lock2 = new ReentrantLock(true);

	public static void main(String[] args) {

		LivelockExample livelock = new LivelockExample();
		new Thread(livelock::worker1, "worker1").start();
		new Thread(livelock::worker2, "worker2").start();
	}

	private void worker1() {

		while (true) {

			try {
				lock1.tryLock(50, TimeUnit.MILLISECONDS);
				System.out.println("worker1 acquires the lock1.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("worker1 tries to get lock2.");

			if (lock2.tryLock()) {
				System.out.println("worker1 acquires the lock2.");
				lock2.unlock();
			} else {
				System.out.println("worker1 can not acquire lock2.");
				continue;
			}
			break;
		}

		lock1.unlock();
		lock2.unlock();
	}

	private void worker2() {
		while (true) {

			try {
				lock2.tryLock(50, TimeUnit.MILLISECONDS);
				System.out.println("worker2 acquires the lock2.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("worker2 tries to get lock1");

			if (lock1.tryLock()) {
				System.out.println("worker2 acquires the lock1.");
				lock1.unlock();
			} else {
				System.out.println("worker2 can not acquire lock1.");
				continue;
			}
			break;
		}

		lock1.unlock();
		lock2.unlock();
	}
}

// When livelock exists, thread are not blocked. They are too busy to respond each other.

//worker1 tries to get lock2.
//worker1 can not acquire lock2.
//worker1 acquires the lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker1 tries to get lock2.
//worker1 can not acquire lock2.
//worker1 acquires the lock1.
//worker1 tries to get lock2.
//worker1 can not acquire lock2.
//worker1 acquires the lock1.
//worker1 tries to get lock2.
//worker1 can not acquire lock2.
//worker1 acquires the lock1.
//worker1 tries to get lock2.
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker1 can not acquire lock2.
//worker1 acquires the lock1.
//worker1 tries to get lock2.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker1 can not acquire lock2.
//worker1 acquires the lock1.
//worker1 tries to get lock2.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker2 can not acquire lock1.
//worker2 acquires the lock2.
//worker2 tries to get lock1
//worker1 can not acquire lock2.
//worker1 acquires the lock1.
//worker1 tries to get lock2.
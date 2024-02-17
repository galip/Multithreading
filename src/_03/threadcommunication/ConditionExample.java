package _03.threadcommunication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

class Worker {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void produce() throws InterruptedException {
		lock.lock();
		System.out.println("Producer method works.");
		condition.await(); // wait
		System.out.println("Again producer method works.");
	}

	public void consume() throws InterruptedException {
		Thread.sleep(2000); // to make sure to start with producer, we make this thread sleep for a while.
		lock.lock();
		System.out.println("Consumer method works.");
		Thread.sleep(3000);
		condition.signal(); // notify
		lock.unlock();
	}
}

public class ConditionExample {

	public static void main(String[] args) {

		Worker worker = new Worker();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					worker.produce();
				} catch (InterruptedException e) {
					System.out.println("Exception in producer thread: " + e);
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					worker.consume();
				} catch (InterruptedException e) {
					System.out.println("Exception in consumer thread: " + e);
				}
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
	}
}

//Producer method works.
//Consumer method works.
//Again producer method works.
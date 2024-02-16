package _03.threadcommunication;

import java.util.ArrayList;
import java.util.List;

public class WaitAndNotifyTwo {

	public static void main(String[] args) {
		Processor processor = new Processor();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.producer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
	}

}

class Processor {
	private List<Integer> list = new ArrayList<>();
	private static final int UPPER_LIMIT = 5;
	private static final int LOWER_LIMIT = 0;

	private final Object lock = new Object();
	private int value = 0;

	public void producer() throws InterruptedException {

		synchronized (lock) {

			while (true) {
				if (list.size() == UPPER_LIMIT) {
					System.out.println("Waiting for removing items.");
					lock.wait();
				} else {
					System.out.println("Add the value " + value);
					list.add(value);
					value++;
					lock.notify();
				}
				Thread.sleep(500);
			}

		}

	}

	public void consumer() throws InterruptedException {
		synchronized (lock) {

			while (true) {
				if (list.size() == LOWER_LIMIT) {
					System.out.println("Waiting for adding items.");
					value = 0;
					lock.wait();
				} else {
					System.out.println("Removing the value " + list.remove(list.size() - 1));
					lock.notify(); 
				}
				Thread.sleep(500);
			}

		}
	}
}

//Add the value 0
//Add the value 1
//Add the value 2
//Add the value 3
//Add the value 4
//Waiting for removing items.
//Removing the value 4
//Removing the value 3
//Removing the value 2
//Removing the value 1
//Removing the value 0
//Waiting for adding items.
//Add the value 0
//Add the value 1
//Add the value 2
//Add the value 3
//Add the value 4
//Waiting for removing items.
//Removing the value 4
//Removing the value 3
//Removing the value 2
//Removing the value 1
//Removing the value 0

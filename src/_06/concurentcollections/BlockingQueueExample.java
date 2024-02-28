package _06.concurentcollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// we do not use any synchronized block here, BlockingQueue handles it under the hood.
class FirstWorker implements Runnable {
	
	private BlockingQueue<Integer> queue;
	
	public FirstWorker(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		int counter = 0;
		while(true) {
			try {
				queue.put(counter);
				System.out.println("Putting item to the counter " + counter);
				counter++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SecondWorker implements Runnable {
	
	private BlockingQueue<Integer> queue;
	
	public SecondWorker(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while(true) {
			try {
				int counter = queue.take();
				System.out.println("Taking item from queue: " + counter);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class BlockingQueueExample {
 	
	public static void main(String[] args) {
		
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
		
		FirstWorker firstWorker = new FirstWorker(queue);
		SecondWorker secondWorker = new SecondWorker(queue);
		
		new Thread(firstWorker).start();
		new Thread(secondWorker).start();
	}
}

//Putting item to the counter 0
//Taking item from queue: 0
//Putting item to the counter 1
//Taking item from queue: 1
//Taking item from queue: 2
//Putting item to the counter 2
//Putting item to the counter 3
//Taking item from queue: 3
//Putting item to the counter 4
//Taking item from queue: 4
//Putting item to the counter 5
//Taking item from queue: 5
//Putting item to the counter 6
//Taking item from queue: 6
//Putting item to the counter 7
//Taking item from queue: 7

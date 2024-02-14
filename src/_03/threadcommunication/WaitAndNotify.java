package _03.threadcommunication;

public class WaitAndNotify {
	
	
	// 2 synchronized block have the same intrinsic lock
	public void produce() throws InterruptedException {
		
		synchronized (this) {
			System.out.println("Running in the produce method.");
			wait(); // releases the lock
			System.out.println("Again in the produce method.");
		}
	}
	
	public void consume() throws InterruptedException {
		
		Thread.sleep(1000);
		
		synchronized (this) {
			System.out.println("Consume method is executed.");
			notify(); // notifies the waiting state thread
			System.out.println("After notify, this block works until it finishes the syncronized block."); // After this block it notifies the waiting state thread.
		}
	}
	
	public static void main(String args[]) {
		
		WaitAndNotify waitAndNotify = new WaitAndNotify();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					waitAndNotify.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					waitAndNotify.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
	}
}
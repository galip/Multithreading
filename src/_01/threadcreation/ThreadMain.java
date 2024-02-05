package _01.threadcreation;

public class ThreadMain {
	
	public static void main(String args[]) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Thread name: " + Thread.currentThread().getName());
			}
		});
		
		thread.setName("Custom named thread");
		thread.setPriority(Thread.MAX_PRIORITY);
		
		System.out.println("Before starting a new thread. Thread name: " + Thread.currentThread().getName());
		thread.start();
		System.out.println("After starting a new thread. Thread name: " + Thread.currentThread().getName());
	}
}

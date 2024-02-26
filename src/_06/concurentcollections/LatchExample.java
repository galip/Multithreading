package _06.concurentcollections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable {

	private int id;
	private CountDownLatch latch;
	
	public Worker(int id, CountDownLatch latch) {
		this.id = id;
		this.latch = latch;
	}
	
	@Override
	public void run() {
		doWork();
		latch.countDown();
	}
	
	private void doWork() {
		try {
			System.out.println("Thread id: " + this.id + " working.");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

public class LatchExample {
	
	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(5);
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		for(int i = 0; i < 5; i++) {
			service.execute(new Worker(i, latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed.");
		service.shutdown();
	}

}

package _05.executorframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task1 implements Runnable {
	
	private int id;
	
	public Task1(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		
		System.out.println("Task1 id: " + id + " thread id : " + Thread.currentThread().getName());
		long duration = (long) Math.random()*5;
		
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}

public class StopExecutor {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);

		for (int i = 0; i < 100; i++) {
			executor.execute(new Task1(i + 1));
		}
		
		// prevents the executor to execute for further tasks.
		executor.shutdown();
		
		// terminates the actual - running tasks
		try {
			if(!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}
	}
}

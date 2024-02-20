package _05.executorframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class FixedThreadTask implements Runnable {
	
	private int id;
	
	public FixedThreadTask(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		
		System.out.println("Task id: " + id + " thread id : " + Thread.currentThread().getName());
		long duration = (long) Math.random()*5;
		
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class FixedThreadExecutor {
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(int i = 0; i < 10; i++) {
			executor.execute(new FixedThreadTask(i+1));
		}
	}
}

//Task id: 1 thread id : pool-1-thread-1
//Task id: 2 thread id : pool-1-thread-2
//Task id: 3 thread id : pool-1-thread-3
//Task id: 4 thread id : pool-1-thread-2
//Task id: 5 thread id : pool-1-thread-3
//Task id: 6 thread id : pool-1-thread-1
//Task id: 7 thread id : pool-1-thread-2
//Task id: 8 thread id : pool-1-thread-1
//Task id: 9 thread id : pool-1-thread-3
//Task id: 10 thread id : pool-1-thread-2
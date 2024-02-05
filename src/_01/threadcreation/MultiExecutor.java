package _01.threadcreation;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {
	
	private List<Runnable> tasks;
	
	public MultiExecutor(List<Runnable> tasks) {
		this.tasks = tasks;
	}
	
	public void execuoteAll() {
		List<Thread> threads = new ArrayList<>();
		
		for(Runnable task : tasks) {
			Thread thread = new Thread(task);
			threads.add(thread);
		}
		
		for(Thread thread : threads) {
			thread.start();
		}
	}

}

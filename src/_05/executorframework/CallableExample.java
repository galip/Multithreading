package _05.executorframework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Processor implements Callable<String> {

	private int id;

	public Processor(int id) {
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(2000);
		return "Id: " + id + " Thread id: " + Thread.currentThread().getName();
	}
}

public class CallableExample {
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		List<Future<String>>  list = new ArrayList<>();
		
		for(int i = 0; i < 20; i++) {
			Future<String>  future = executor.submit(new Processor(i+1));
			list.add(future);
		}
		
		for(Future<String> f : list) {
			try {
				System.out.println(f.get());
			}  catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}

//Id: 1 Thread id: pool-1-thread-1
//Id: 2 Thread id: pool-1-thread-2
//Id: 3 Thread id: pool-1-thread-3
//Id: 4 Thread id: pool-1-thread-4
//Id: 5 Thread id: pool-1-thread-4
//Id: 6 Thread id: pool-1-thread-3
//Id: 7 Thread id: pool-1-thread-2
//Id: 8 Thread id: pool-1-thread-1
//Id: 9 Thread id: pool-1-thread-2
//Id: 10 Thread id: pool-1-thread-1
//Id: 11 Thread id: pool-1-thread-4
//Id: 12 Thread id: pool-1-thread-3
//Id: 13 Thread id: pool-1-thread-2
//Id: 14 Thread id: pool-1-thread-1
//Id: 15 Thread id: pool-1-thread-3
//Id: 16 Thread id: pool-1-thread-4
//Id: 17 Thread id: pool-1-thread-2
//Id: 18 Thread id: pool-1-thread-4
//Id: 19 Thread id: pool-1-thread-3
//Id: 20 Thread id: pool-1-thread-1

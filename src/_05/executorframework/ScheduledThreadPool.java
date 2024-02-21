package _05.executorframework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class DataUpdater implements Runnable {

	@Override
	public void run() {
		System.out.println("Downloading data.");
	}
}

public class ScheduledThreadPool {

	public static void main(String[] args) {

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

		// 5000 is initialDelay so it starts to execute after 5000 ms
		// prints Downloading data. in every 2000 ms
		executor.scheduleAtFixedRate(new DataUpdater(), 5000, 2000, TimeUnit.MILLISECONDS);

	}
}
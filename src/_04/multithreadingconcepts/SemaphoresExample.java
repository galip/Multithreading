package _04.multithreadingconcepts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// It controls access tpo a shared resource.
// It uses counter variable

// acquire() --> if a permit is available then takes it
// release() --> adds a permit
public class SemaphoresExample {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();

		for (int i = 0; i < 12; i++) {
			service.execute(new Runnable() {
				@Override
				public void run() {
					Downloader.INSTANCE.download();
				}
			});
		}
	}
}

enum Downloader {
	INSTANCE;

	private Semaphore semaphore = new Semaphore(3, true);

	public void download() {
		try {
			semaphore.acquire();
			downloadData();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	private void downloadData() {
		try {
			System.out.println("Downloading data."); // each time 3 permitted threads execute this block.
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//Downloading data.
//Downloading data.
//Downloading data.
//Downloading data.
//Downloading data.
//Downloading data.
//Downloading data.
//Downloading data.
//Downloading data.
//Downloading data.
//Downloading data.
//Downloading data.

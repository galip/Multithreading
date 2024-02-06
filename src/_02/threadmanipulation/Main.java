package _02.threadmanipulation;

public class Main {
	public static void main(String args[]) {
		Thread thread = new Thread(new BlockingTask());
		
		thread.start();
		thread.interrupt();
		
	}
	
	private static class BlockingTask implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println("Exception in blocked thread: " + e);
			}
		}
	}
}

// Exception in blocked thread: java.lang.InterruptedException: sleep interrupted


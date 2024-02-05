package _01.threadcreation;

public class UncaughtExceptionHandler {
	public static void main(String args[]) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				throw new RuntimeException("Conscious exception");
				
			}
		});
		
		thread.setName("Thread A");
		thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("Error occured in thread " + t.getName() + " error message is " + e.getMessage());
			}
		});
		thread.start();
	}

}

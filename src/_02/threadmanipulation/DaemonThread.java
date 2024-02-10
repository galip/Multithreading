package _02.threadmanipulation;

public class DaemonThread {
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(new DaemonWorker());
		Thread thread2 = new Thread(new NormalWorker());
		
		thread1.setDaemon(true);
		thread1.start();
		
		thread2.start();
		 
	}

}

class DaemonWorker implements Runnable {

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
			System.out.println("Daemon thread is running...");
		}
	}
}

class NormalWorker implements Runnable {

	@Override
	public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			System.out.println("Normal thread finished...");
		}
}

//Daemon thread is running...
//Daemon thread is running...
//Normal thread finished...


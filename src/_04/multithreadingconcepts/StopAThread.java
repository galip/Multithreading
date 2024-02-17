package _04.multithreadingconcepts;

class Worker implements Runnable {
	
	// volatile guarantees to store this variable in the main memory
	//that means writing and reading to this volatile variable will be from main memory not from CPU registers.
	private volatile boolean terminated;

	@Override
	public void run() {
		
		while(!terminated) {
			System.out.println("Working class is running.");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isTerminated() {
		return terminated;
	}
	
	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}
}


public class StopAThread {
	
	public static void main(String[] args) {
		
		Worker worker = new Worker();
		
		Thread t1 = new Thread(worker);
		
		t1.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		worker.setTerminated(true);
		System.out.println("Thread is terminated.");
	}

}

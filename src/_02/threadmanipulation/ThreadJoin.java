package _02.threadmanipulation;

public class ThreadJoin {
	public static void main(String args[]) {
		ThreadJoining t1 = new ThreadJoining();
		ThreadJoining t2 = new ThreadJoining();
		ThreadJoining t3 = new ThreadJoining();
		
		t1.start();
		try {
			t1.join();
			System.out.println("Current thread: " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			System.out.println("Exception : " + e);
		}
		
		t2.start();
		try {
			t2.join();
			System.out.println("Current thread: " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			System.out.println("Exception : " + e);
		}
		
		t3.start();
		try {
			t3.join();
			System.out.println("Current thread: " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			System.out.println("Exception : " + e);
		}
	}
}

class ThreadJoining extends Thread {
	
	@Override
	public void run() {
		
		for(int i = 0; i < 2; i++) {
			try {
				Thread.sleep(2000);
				System.out.println("Current thread: " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				System.out.println("Exception occured " + e);
			}
			System.out.println("i = " + i);
		}
	}
}
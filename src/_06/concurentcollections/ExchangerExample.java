package _06.concurentcollections;

import java.util.concurrent.Exchanger;

class FirstThread implements Runnable {

	private int counter;
	private Exchanger<Integer> exchanger;

	public FirstThread(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		while (true) {
			counter++;
			System.out.println("FirstThread incremented the counter: " + counter);
			try {
				counter = exchanger.exchange(counter);
				System.out.println("FirstThread gets the counter: " + counter);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SecondThread implements Runnable {

	private int counter;
	private Exchanger<Integer> exchanger;

	public SecondThread(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		while (true) {
			counter--;
			System.out.println("SecondThread decremented the counter: " + counter);
			try {
				counter = exchanger.exchange(counter);
				System.out.println("SecondThread gets the counter: " + counter);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ExchangerExample {
	
	public static void main(String[] args) {
		
		Exchanger<Integer> exchanger  = new Exchanger<>();
		
		FirstThread t1 = new FirstThread(exchanger);
		SecondThread t2 = new SecondThread(exchanger);
		
		new Thread(t1).start();
		new Thread(t2).start();
	}
}

//FirstThread incremented the counter: 1
//SecondThread decremented the counter: -1
//SecondThread gets the counter: 1
//FirstThread gets the counter: -1
//FirstThread incremented the counter: 0
//SecondThread decremented the counter: 0
//SecondThread gets the counter: 0
//FirstThread gets the counter: 0
//SecondThread decremented the counter: -1
//FirstThread incremented the counter: 1
//SecondThread gets the counter: 1
//FirstThread gets the counter: -1
//SecondThread decremented the counter: 0
//FirstThread incremented the counter: 0
//FirstThread gets the counter: 0
//SecondThread gets the counter: 0
//SecondThread decremented the counter: -1
//FirstThread incremented the counter: 1
package _06.concurentcollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsSynchronization {
	
	public static void main(String[] args) {
		
		List<Integer> nums = Collections.synchronizedList(new ArrayList<>());
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 1000; i++) {
					nums.add(i);
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 1000; i++) {
					nums.add(i);
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Array size: " + nums.size());
	}
}

// without Collections.synchronizedList, we get the exception in the below.
//Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: Index 115 out of bounds for length 109
//at java.base/java.util.ArrayList.add(ArrayList.java:455)
//at java.base/java.util.ArrayList.add(ArrayList.java:467)
//at _06.concurentcollections.CollectionsSynchronization$1.run(CollectionsSynchronization.java:16)
//at java.base/java.lang.Thread.run(Thread.java:842)
//Array size: 1029


// with Collections.synchronizedList, we get the output as in the below.
//Array size: 2000

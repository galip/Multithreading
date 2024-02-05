package _01.threadcreation;

public class Main {
    public static void main(String [] args) {
        Thread thread = new Thread(new Task2());
        thread.start();
    }
 
    public static class Task2 implements Runnable {
        @Override
        public void run(){
            System.out.println("Hello from new thread");
        }
    }
}
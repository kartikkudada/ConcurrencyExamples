package com.myproject.concurent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LatchesExample {

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(3);

		Waiter      waiter      = new Waiter(latch);
		Decrementer decrementer = new Decrementer(latch);

		new Thread(waiter).start();
		new Thread(decrementer).start();

		TimeUnit.SECONDS.sleep(5);
		
	}
}

class Waiter implements Runnable{

    CountDownLatch latch = null;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiter Released");
    }
}

class Decrementer implements Runnable {

    CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {

        try {
            Thread.sleep(1000);
            latch.countDown();
            
            System.out.println("countdown of latch 1");
            Thread.sleep(1000);
            latch.countDown();
            
            System.out.println("countdown of latch 2");
            Thread.sleep(1000);
            latch.countDown();
            
            System.out.println(latch.getCount());
            
            System.out.println("countdown of latch 3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


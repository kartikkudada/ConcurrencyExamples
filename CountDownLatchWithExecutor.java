package com.myproject.concurent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchWithExecutor  {

	public static void main(String[] args) throws InterruptedException {
		
		CountDownLatch latch = new CountDownLatch(10);
	   ExecutorService executor = Executors.newFixedThreadPool(10);
	   
	   for(int i =0;i<10;i++)
	   {
		   Task t = new Task(latch);
		   executor.execute(t);
	   }
		
	   
	   latch.await();
	   executor.shutdown();
	   System.out.println("wait over : latch finished ");
	}
}

class Task implements Runnable {
	
	CountDownLatch latch ;
	
	public Task(CountDownLatch latch) {
      this.latch = latch;
	}

	@Override
	public void run() {
        		for(int i =0;i<5;i++)
        		{
        			try {
        				System.out.println(Thread.currentThread().getName() + " " + i);
						TimeUnit.MILLISECONDS.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
       latch.countDown(); 		
	}
	
}
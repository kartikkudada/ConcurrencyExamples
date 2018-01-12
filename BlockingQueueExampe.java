package com.myproject.concurent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Use of BlockingQueue for Producer-Consumer 
 * 
 * @author kartik
 *
 */
public class BlockingQueueExampe {
	
	  BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
	
	private  void producer() throws InterruptedException{
      Random random = new Random();
      while(true){
    	  queue.put(random.nextInt(100));
      }
	}

	private  void consumer() throws InterruptedException{
		Random random  = new Random();
		while(true)
		{
			TimeUnit.MILLISECONDS.sleep(10);;
			if(random.nextInt(10) == 0)
			{
				Integer value = queue.take();
				
				System.out.println(" Value taken :" + value + " " + queue.size());
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueueExampe ex = new BlockingQueueExampe();
		Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
							try {
								ex.producer();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
					try {
						ex.consumer();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		 t1.start();
		 t2.start();
		 
	}

}

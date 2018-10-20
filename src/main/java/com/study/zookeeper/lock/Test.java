package com.study.zookeeper.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

	static volatile AtomicInteger count = new AtomicInteger(0);
	
	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					DistributedLock lock = DistributedLock.instanceLock("mylock");
						try {
							lock.lock();
							count.incrementAndGet();
							System.err.println(System.currentTimeMillis()+"|"+Thread.currentThread().getId() + " | lock value: " + count.get());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							lock.unlock();
						}

					
				}
			}).start();
		}
		Thread.sleep(10000);
	}

}

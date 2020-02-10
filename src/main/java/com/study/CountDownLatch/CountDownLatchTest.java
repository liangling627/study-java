package com.study.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Mack
 * @Date 2016年11月08日
 */
public class CountDownLatchTest {
    private static final int MAX_WORK_DURATION = 5000;
    private static final int MIN_WORK_DURATION = 5000;

    public static long getRandom(long min, long max) {
        return (long) (Math.random() * (max - min) + min);
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Worker worker1 = new Worker("1", getRandom(MIN_WORK_DURATION, MAX_WORK_DURATION));
        Worker worker2 = new Worker("2", getRandom(MIN_WORK_DURATION, MAX_WORK_DURATION));
        new Thread(new WorkTestThread(worker1, countDownLatch)).start();
        new Thread(new WorkTestThread(worker2, countDownLatch)).start();

        try {
            countDownLatch.await();

            System.out.println("all job have been finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

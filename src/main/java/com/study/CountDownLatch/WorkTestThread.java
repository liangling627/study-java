package com.study.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Mack
 * @Date 2016年11月08日
 */
public class WorkTestThread implements Runnable {
    private Worker worker;
    private CountDownLatch countDownLatch;

    public WorkTestThread(Worker worker, CountDownLatch countDownLatch) {
        this.worker = worker;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        worker.doWork();
        countDownLatch.countDown();
    }
}

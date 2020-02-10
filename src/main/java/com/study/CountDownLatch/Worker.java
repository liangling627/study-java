package com.study.CountDownLatch;

/**
 * @author Mack
 * @Date 2016年11月08日
 */
public class Worker {
    private String name;
    private Long workDuration;

    public Worker(String name, Long workDuration) {
        this.name = name;
        this.workDuration = workDuration;
    }

    public void doWork() {
        System.out.println(name + " begin to work");
        try {
            Thread.sleep(workDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "has finished the job");
    }
}

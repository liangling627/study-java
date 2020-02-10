package com.study.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Mack
 * @Date 2017年12月21日
 */
public class CyclicBarrierTest {

    private final static int student_count = 12;

    final CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println("3个人到齐了，开门");
        }
    });

    public void goHome() throws BrokenBarrierException, InterruptedException {
        System.out.println(Thread.currentThread().getName() +"一刷卡， 等待回家");
        barrier.await();
    }

    public static void main(String[] args) {
        final CyclicBarrierTest test = new CyclicBarrierTest();
        for (int i = 0; i < student_count; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        test.goHome();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
}

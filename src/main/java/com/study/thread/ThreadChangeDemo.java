package com.study.thread;

import java.util.concurrent.*;

/**
 * @author Hank
 * @Date 2020年06月01日
 */
public class ThreadChangeDemo {
    public static void main(String[] args) throws InterruptedException {
        dynamicModifyExecutor();
    }

    private static ThreadPoolExecutor buildTheadPoolExecutor() {
        Executors.defaultThreadFactory();
        return new ThreadPoolExecutor(2, 5,
                60, TimeUnit.SECONDS,
                new ResizableCapacityLinkedBlockIngQueue<>(10));
    }

    private static void dynamicModifyExecutor() throws InterruptedException {
        ThreadPoolExecutor pool = buildTheadPoolExecutor();
        for (int i = 0; i < 15; i++) {
            pool.submit(() -> {
               threadPoolStatus(pool, "任务创建");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }          });
        }
        threadPoolStatus(pool, "改变之前");
        pool.setCorePoolSize(10);
        pool.setMaximumPoolSize(10);
        ResizableCapacityLinkedBlockIngQueue queue = (ResizableCapacityLinkedBlockIngQueue) pool.getQueue();
        queue.setCapacity(100);

        threadPoolStatus(pool, "改变之后");
        Thread.currentThread().join();
    }

    private static void threadPoolStatus(ThreadPoolExecutor pool, String str) {
        ResizableCapacityLinkedBlockIngQueue<Runnable> queue = (ResizableCapacityLinkedBlockIngQueue<Runnable>) pool.getQueue();

        System.out.println(Thread.currentThread().getName() +"-" + str +"-" +
                "核心线程："+ pool.getCorePoolSize()+
                "活动线程数："+ pool.getActiveCount()+
                "最大线程数："+ pool.getMaximumPoolSize()+
                "线程池活跃度："+ divide(pool.getActiveCount(), pool.getMaximumPoolSize()) +
                "任务完成数："+ pool.getCompletedTaskCount()+
                "队列大小：" + (queue.size() + queue.remainingCapacity())+
                "当前排队线程数："+ queue.size() +
                "队列剩余大小：" + queue.remainingCapacity()+
                "队列使用度："+ divide(queue.size(), queue.size() + queue.remainingCapacity()));

    }
    private static String divide(int num1, int num2) {
        return String.format("%1.2f%%", Double.parseDouble(num1 + "") / Double.parseDouble(num2 + "") * 100);
    }
}

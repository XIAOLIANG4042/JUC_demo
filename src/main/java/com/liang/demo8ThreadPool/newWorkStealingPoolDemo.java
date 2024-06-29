package com.liang.demo8ThreadPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class newWorkStealingPoolDemo {


    public static void main(String[] args) throws InterruptedException {

        //默认按照 CPU 线程数量 创建线程池 都是守护线程
        ExecutorService executorService = Executors.newWorkStealingPool();

        //主线程 不会等待 workStealingPool
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {

            int finalI = i;
            executorService.submit(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" "+ finalI);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            });

        }

        //等待线程池任务跑完
        countDownLatch.await();
        executorService.shutdown();

    }


}

package com.liang.demo8ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class newCachedThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {

        //缓存线程池，先查看线程池中是否有当前执行线程的缓存，
        // 如果有就resue(复用),如果没有,那么需要创建一个线程来完成当前的调用.
        // 并且这类线程池只能完成一些生存期很短的一些任务.并且这类线程池内部规定能resue(复用)的线程，
        // 空闲的时间不能超过60s,一旦超过了60s,就会被移出线程池

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" "+ finalI);

                }
            });

            if (i%10==0){
                TimeUnit.SECONDS.sleep(1);
            }
        }

        //这里没有主动关闭线程池 ，主线程会卡在这里 60秒后 线程池内线程被回收 主线程才结束

    }

}

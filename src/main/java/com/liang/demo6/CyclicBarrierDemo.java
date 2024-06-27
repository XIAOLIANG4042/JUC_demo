package com.liang.demo6;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    /**
     * 使用cyclicBarrier
     */
//设置同步屏障点
    private static  final  int NUMBER = 7;





    public static void main(String[] args) {


        CyclicBarrier barrier = new CyclicBarrier(CyclicBarrierDemo.NUMBER, new Runnable() {
            @Override
            public void run() {

                System.out.println("执行召唤神龙");

            }
        });


        //创建 7个线程  执行收集龙珠的操作

        for (int i = 1; i <= CyclicBarrierDemo.NUMBER; i++) {

            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"星龙珠被收集到了");

                    //等待其他线程 收集完成
                    try {
                        barrier.await();

                        //继续执行后面的操作 开始召唤
                        System.out.println(finalI +"星龙珠 启动");

                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }
            },"thread" + i).start();

        }


    }


}

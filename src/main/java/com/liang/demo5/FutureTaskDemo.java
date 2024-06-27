package com.liang.demo5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * ● futureTask 一个可取消的异步任务 ，Futuretask 是Future的实现 ，可以调用方法 去开始执行 或者取消一个任务，可以查询是否完成并获取计算结果 。只有当任务完成 才能获取结果 。
 * ● FutureTask 还实现了 Runnable接口 因此FutureTask 可以交由Exector执行
 * ● 根据FutureTask 7中状态
 * private volatile int state;
 * private static final int NEW		= 0; //新建
 * private static final int COMPLETING	= 1; //即将完成
 * private static final int NORMAL		= 2; //完成
 * private static final int EXCEPTIONAL= 3; //抛异常
 * private static final int CANCELLED	= 4; //任务取消
 * private static final int INTERRUPTING= 5;//任务打断中
 * private static final int INTERRUTED	= 6;// 任务已打断
 *
 * ● FutureTask.get() 怎么做到阻塞调用线程的 ？
 * 调用get方法 内部会先判断 FutureTask 线程的状态 是否<=2 ，符合条件 说明任务没有结束 或者没有启动
 * 就会 调用awaitDone()方法 ，该方法内部 是一个死循环 不断地判断 线程状态 直到任务装>=2 返回true 表示可以返回结果，
 */
public class FutureTaskDemo  {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //使用Runnable 创建线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, "thread1").start();


        //使用FutureTask 创建线程
        //可以使用FutureTask 将任务拆分
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable() {
            @Override
            public Integer call() throws InterruptedException {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(200);
                return 0;
            }
        });
        new Thread(futureTask, "thread2").start();


        // 使用futureTask.get() 获取任务结果是 会导致可能导致当前线程等待状态 直到 任务执行完成返回结果
        System.out.println("获取futuretask 返回的结果"+futureTask.get());

    }

}

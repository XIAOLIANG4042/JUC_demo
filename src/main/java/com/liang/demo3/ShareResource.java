package com.liang.demo3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//1 使用3个线程 按顺序打印
public class ShareResource {

    private int flag = 1;  //1 线程执行  2 B线程执行  3 C线程执行

    private final ReentrantLock lock = new ReentrantLock();

    //使用多个condition 实现定向唤醒
    private final Condition conditionA = lock.newCondition();
    private final Condition conditionB = lock.newCondition();
    private final Condition conditionC = lock.newCondition();

    //1 第一个方法 打印1次
    public void print1(int loop) {
        lock.lock();
        try {

            while (flag != 1) {
                conditionA.await();
            }

            //打印1次
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + ":loop" + loop);
            }
            flag = 2;
            //通知B线程 执行
            conditionB.signal();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void print2(int loop) {
        lock.lock();
        try {
            while (flag != 2) {
                conditionB.await();
            }

            //打印1次
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + ":loop" + loop);
            }
            flag = 3;
            //通知B线程 执行
            conditionC.signal();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void print3(int loop) {
        lock.lock();

        try {
            while (flag != 3) {
                conditionC.await();
            }

            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + ":loop" + loop);
            }
            flag = 1;
            conditionA.signal();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }

    }


}

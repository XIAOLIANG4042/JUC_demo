package com.liang.demo2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 Lock 完成 demo1 的示例
 */
public class Share {

    private int number = 0;

    //创建一个锁
    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();



    public void incr(){

        lock.lock();
        try {
            //判断
            while (number!=0){
                condition.await();
            }

            // 核心执行代码
            number++;

            System.out.println(Thread.currentThread().getName()+"::"+number);
            //通知
            condition.signalAll();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

            // 解锁
            lock.unlock();
        }

    }



    public void decr(){
        lock.lock();

        try {
            while (number==0){
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"::"+number);
            condition.signalAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }

    }


}

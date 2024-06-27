package com.liang.demo7;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {


    /**
     *读
     * 写锁的使用
     *
     *
     */
    public static void main(String[] args) {

        MyCache myCache = new MyCache();


        //创建 线程存放数据

//        for (int i = 0; i < 10; i++) {
//
//            int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    myCache.put("a", finalI);
//                }
//            }, "WriteThread" + i).start();
//
//        }
//
//
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    myCache.get("a");
//                }
//            }, "ReadThread" + i).start();
//        }
//

        //锁降级 测试


        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();


        //锁降级
        //获取到写锁  （如果是先获取读锁 在获取写锁 锁升级是不可行的）
        writeLock.lock();
        System.out.println("获取到写锁");

        //2 获取到读锁
        readLock.lock();
        System.out.println("获取到读锁");


        //3释放 写锁
        writeLock.unlock();
        System.out.println("释放写锁");

        //4 释放读锁
        readLock.unlock();
        System.out.println("释放读锁");


    }



}

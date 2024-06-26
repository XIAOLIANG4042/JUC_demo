package com.liang.demo1;

public class Share {

    //1 创建资源对象

    private int number = 0;


    //2 干活的方法
    public synchronized void incr() throws InterruptedException {

        //判断 要使用while 判断 否则可能会出现虚假唤醒的情况
        //什么 是虚假唤醒 如果使用if 判断 符合条件进入等待 并释放锁
        // 但是下一个线程释放锁了 使用notify唤醒时 唤醒此线程 但是此时if 代码已经执行过了
        // 不会再 判断条件 会直接执行后面的代码
        while (number != 0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + ":" + number);
        notify();
    }


    // 2 干活的方法
    public synchronized void decr() throws InterruptedException {
        while (number != 1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + ":" + number);
        notify();
    }

}

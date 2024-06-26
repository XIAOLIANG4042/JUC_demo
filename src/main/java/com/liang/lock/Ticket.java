package com.liang.lock;

import java.util.concurrent.locks.ReentrantLock;

//1 创建资源类 定义属性和操作方法
public class Ticket {

    //这个锁不能变 所以要设置为final
   private final ReentrantLock lock = new ReentrantLock();

    //票数
    private int ticketNum = 30;

    //操作
    public  void sale(){
        lock.lock();
        try {
            //判断是否有票
            if (ticketNum>0){
                System.out.println(Thread.currentThread().getName()+":卖出一张票："+ticketNum);
                ticketNum--;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //防止方法执行过程中 出现异常 导后面解锁的代码无法执行
            lock.unlock();
        }

    }

    public synchronized boolean haveTicket(){
        return ticketNum>0;
    }

}

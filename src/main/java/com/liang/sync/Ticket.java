package com.liang.sync;
//1 创建资源类 定义属性和操作方法
public class Ticket {

    //票数
    private int ticketNum = 30;

    //操作
    public synchronized void sale(){
        //判断是否有票
        if (ticketNum>0){
            System.out.println(Thread.currentThread().getName()+":卖出一张票："+ticketNum);
            ticketNum--;
        }
    }

    public synchronized boolean haveTicket(){
        return ticketNum>0;
    }

}

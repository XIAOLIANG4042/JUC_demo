package com.liang.sync;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class SaleTicket {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        //创建三个线程
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (ticket.haveTicket()){
                        ticket.sale();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            },"thread"+i).start();

        }

    }
}

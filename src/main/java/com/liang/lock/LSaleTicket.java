package com.liang.lock;

public class LSaleTicket {

    /**
     * 使用 Lock 实现 卖票
     */
    public static void main(String[] args) {

        Ticket ticket = new Ticket();

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

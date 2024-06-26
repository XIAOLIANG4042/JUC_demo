package com.liang.demo1;

public class TestDemo1 {

    public static void main(String[] args) {


        Share share = new Share();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    try {
                        share.decr();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        },"AA").start();




        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 20; i++) {

                    try {
                        share.incr();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        },"BB").start();



        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    try {
                        share.decr();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        },"CC").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 20; i++) {

                    try {
                        share.incr();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        },"DD").start();

    }

}

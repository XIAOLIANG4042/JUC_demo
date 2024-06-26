package com.liang.demo2;

public class Testdemo2 {

    public static void main(String[] args) {

        Share share = new Share();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    share.decr();
                }
            }
        },"d1").start();


        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    share.incr();
                }
            }
        },"i1").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    share.decr();
                }
            }
        },"d2").start();


        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    share.incr();
                }
            }
        },"i2").start();


    }





}
